package de.turing85.adventure.of.code.tttt.day.seventeen.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * Simulating falling tetris-like shapes, in the wind!
 */
public class TetrisInTheWind {
  private static final List<Shape> SHAPES_IN_ORDER =
      List.of(Shape.H_BAR, Shape.PLUS, Shape.CORNER, Shape.V_BAR, Shape.SQUARE);
  private static final long HEIGHT_OF_ALL_SHAPES_STACKED =
      SHAPES_IN_ORDER.stream().map(Shape::pointsOfShape)
          .map(points -> points.stream().reduce(new Point(0, 0), (lhs, rhs) -> lhs.y() > rhs.y() ? lhs : rhs))
          .mapToLong(Point::y).map(y -> y + 1).sum();

  private final List<Direction> windDirections;
  private final int width;
  private final long rocksToDrop;
  private final Point spawnOffset;

  private final Map<OptimizationIndex, RocksAtRestAndMaxY> optimizationCache;
  private final Set<Point> blocked;
  private long additionalMaxY;
  private long maxY;
  private long rocksAtRest;
  private int shapeIndex;
  private int windDirectionIndex;

  /**
   * Constructor.
   *
   * @param windDirections the wind directions
   * @param width width of the playing field
   * @param rocksToDrop how many rocks should be dropped
   * @param spawnOffset the offset to spawn new rocks from
   */
  public TetrisInTheWind(List<Direction> windDirections, int width, long rocksToDrop,
      Point spawnOffset) {
    this.windDirections = windDirections;
    this.width = width;
    this.rocksToDrop = rocksToDrop;
    this.spawnOffset = spawnOffset;
    optimizationCache = new HashMap<>();
    blocked = new HashSet<>();
    additionalMaxY = 0L;
    shapeIndex = 0;
    windDirectionIndex = -1;
  }


  /**
   * Let rocks fall until {@code rocksToDrop} have come to rest.
   *
   * @return the highest point ({@code y}-coordinate) occupied by a rock at rest.
   */
  public long letItFall() {
    maxY = 0L;
    FallingRock fallingRock = spawnNextRock();
    windDirectionIndex = -1;
    rocksAtRest = 0L;
    while (rocksAtRest < rocksToDrop) {
      incrementWindDirectionIndex();
      fallingRock = fallingRock.move(windDirections.get(windDirectionIndex));
      if (fallingRock.isAtRest()) {
        fallingRock = settleCurrentRockAndSpawnNewRock(fallingRock);
      } else {
        fallingRock = fallingRock.move(Direction.DOWN);
      }
    }
    return maxY + additionalMaxY;
  }

  private void incrementWindDirectionIndex() {
    ++windDirectionIndex;
    windDirectionIndex = windDirectionIndex % windDirections.size();
  }

  private FallingRock settleCurrentRockAndSpawnNewRock(FallingRock fallingRock) {
    ++rocksAtRest;
    incrementShapeIndex();
    blocked.addAll(fallingRock.pointsOfTile());
    long highestYInRock = fallingRock.pointsOfTile().stream().mapToLong(Point::y).max().orElse(0);
    if (maxY < highestYInRock) {
      maxY = highestYInRock;
      optimize(windDirectionIndex, highestYInRock);
    }
    return spawnNextRock();
  }

  private void incrementShapeIndex() {
    ++shapeIndex;
    shapeIndex %= SHAPES_IN_ORDER.size();
  }

  private void optimize(int windDirectionIndex, long highestYInRock) {
    if (additionalMaxY > 0 || maxY < HEIGHT_OF_ALL_SHAPES_STACKED) {
      return;
    }
    Set<Point> relevantPoints = generateRelevantPoints(highestYInRock);
    OptimizationIndex index =
        new OptimizationIndex(relevantPoints, getShape(shapeIndex), windDirectionIndex);
    if (optimizationCache.containsKey(index)) {
      handleCacheHit(index);
    } else {
      optimizationCache.put(index, new RocksAtRestAndMaxY(rocksAtRest, maxY));
    }
  }

  private Set<Point> generateRelevantPoints(long highestYInRock) {
    long lowerYBound = highestYInRock - HEIGHT_OF_ALL_SHAPES_STACKED;
    Set<Point> relevantPoints = new HashSet<>();
    Point offset = new Point(0, -lowerYBound);
    for (long y = highestYInRock; y >= lowerYBound; --y) {
      for (long x = 1; x <= width; ++x) {
        getWithoutOffsetIfBlocked(new Point(x, y), offset).ifPresent(relevantPoints::add);
      }
    }
    return relevantPoints;
  }

  private Optional<Point> getWithoutOffsetIfBlocked(Point point, Point offset) {
    Optional<Point> toAdd;
    if (blocked.contains(point)) {
      toAdd = Optional.of(point.add(offset));
    } else {
      toAdd = Optional.empty();
    }
    return toAdd;
  }

  private static Shape getShape(int shapeIndex) {
    return SHAPES_IN_ORDER.get(shapeIndex);
  }

  private void handleCacheHit(OptimizationIndex index) {
    RocksAtRestAndMaxY cacheHit = optimizationCache.get(index);
    long deltaRocksAtRest = rocksAtRest - cacheHit.rocksAtRest();
    long deltaMaxY = maxY - cacheHit.maxY();
    long factor = (rocksToDrop - cacheHit.rocksAtRest()) / deltaRocksAtRest - 1;
    rocksAtRest += factor * deltaRocksAtRest;
    additionalMaxY += factor * deltaMaxY;
  }

  private FallingRock spawnNextRock() {
    return new FallingRock(spawnOffset.x() + 1, maxY + spawnOffset.y(), getShape(shapeIndex), width,
        blocked);
  }

  private record FallingRock(ShapeTile tile, long width, Set<Point> blocked)
      implements MovableTile<FallingRock> {

  private FallingRock(long x, long y, Shape shape, long width, Set<Point> blocked) {
      this(new ShapeTile(x, y, shape), width, blocked);
    }

  @Override
  public Set<Point> pointsOfTile() {
    return tile.pointsOfTile();
  }

  @Override
  public FallingRock move(Direction direction) {
    FallingRock moved = new FallingRock(tile.move(direction), width, blocked);
    if (moved.collidesWithAnyBlocked()) {
      return this;
    }
    if (moved.isOutOfBounds()) {
      return this;
    }
    return moved;
  }

  private boolean isAtRest() {
    FallingRock oneDown = move(Direction.DOWN);
    if (oneDown == this) {
      return true;
    }
    return oneDown.pointsOfTile().stream().anyMatch(blocked::contains);
  }

  private boolean collidesWithAnyBlocked() {
    for (Point pointOfTile : pointsOfTile()) {
      if (blocked.contains(pointOfTile)) {
        return true;
      }
    }
    return false;
  }

  private boolean isOutOfBounds() {
    return tile.lowerLeftCorner().y() <= 0 || tile.lowerLeftCorner().x() <= 0
        || pointsOfTile().stream().mapToLong(Point::x).max().orElse(width) > width;
  }

  }

  private record OptimizationIndex(Set<Point> points, Shape shape, int shapeIndex) {}

  private record RocksAtRestAndMaxY(long rocksAtRest, long maxY) {}
}
