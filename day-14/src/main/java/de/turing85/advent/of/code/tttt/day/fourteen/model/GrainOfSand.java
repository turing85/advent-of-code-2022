package de.turing85.advent.of.code.tttt.day.fourteen.model;

import java.util.Optional;
import java.util.Set;
import lombok.Getter;

/** Represents a grain of salt, falling in a map of some blocked {@link Point}s. */
@Getter
public final class GrainOfSand {
  private Point point;
  private final Set<Point> blocked;
  private final int floorAtYCoordinate;
  private final int maxX;
  private final int minX;
  private final int maxY;

  /**
   * Constructor.
   *
   * <p>No floor is assumed.
   *
   * @param point the {@link Point} of this grain of sand
   * @param blocked the blocked {@link Point}s
   * @param maxX the max x-coordinate of a blocking {@link Point}
   * @param minX the min x-coordinate of a blocking {@link Point}
   * @param maxY the max y-coordinate of a blocking {@link Point}
   */
  public GrainOfSand(Point point, Set<Point> blocked, int maxX, int minX, int maxY) {
    this(point, blocked, maxX, minX, maxY, Integer.MIN_VALUE);
  }

  /**
   * Constructor.
   *
   * @param point the {@link Point} of this grain of sand
   * @param blocked the blocked {@link Point}s
   * @param maxX the max x-coordinate of a blocking {@link Point}
   * @param minX the min x-coordinate of a blocking {@link Point}
   * @param maxY the max y-coordinate of a blocking {@link Point}
   * @param floorAtYCoordinate the {@code y}-coordinate of the floor. A negative value means "no
   *     floor".
   */
  public GrainOfSand(
      Point point, Set<Point> blocked, int maxX, int minX, int maxY, int floorAtYCoordinate) {
    this.point = point;
    this.blocked = blocked;
    this.floorAtYCoordinate = floorAtYCoordinate;
    this.maxX = maxX;
    this.minX = minX;
    this.maxY = maxY;
  }

  /**
   * Determines whether this grain of sand can continue falling, or is as rest.
   *
   * @return {@code true} if and only if this grain of sand does not fall anymore.
   */
  public boolean isAtRest() {
    return isBlockedByBlocks() || isBlockedByFloor();
  }

  private boolean isBlockedByBlocks() {
    int yOneDeeper = point.y() + 1;
    int x = point.x();
    return blocked.containsAll(
        Set.of(Point.of(x - 1, yOneDeeper), Point.of(x, yOneDeeper), Point.of(x + 1, yOneDeeper)));
  }

  private boolean isBlockedByFloor() {
    return floorAtYCoordinate >= 0 && point.y() + 1 >= floorAtYCoordinate();
  }

  /**
   * Fal. down one field.
   *
   * <p>If {@code this} {@link GrainOfSand} cannot fall anymore, its {@link #point} will not change.
   */
  public void fall() {
    this.point =
        tryFallStraightDown()
            .orElse(tryFallDownLeft().orElse(tryFallDownRight().orElse(this.point)));
  }

  private Optional<Point> tryFallStraightDown() {
    return tryFallTo(Point.of(point.x(), point.y() + 1));
  }

  private Optional<Point> tryFallTo(Point point) {
    if (!blocked.contains(point)) {
      return Optional.of(point);
    }
    return Optional.empty();
  }

  private Optional<Point> tryFallDownLeft() {
    return tryFallTo(Point.of(point.x() - 1, point.y() + 1));
  }

  private Optional<Point> tryFallDownRight() {
    return tryFallTo(Point.of(point.x() + 1, point.y() + 1));
  }

  /**
   * Determine whether {@code this} {@link GrainOfSand} will fall into the abyss, i.e. will not be
   * stopped by any {@link #blocked} {@link Point}s.
   *
   * @return {@code true} if and only if this grain of sand will fall into the abyss.
   */
  public boolean willFallIntoTheAbyss() {
    return floorAtYCoordinate < 0
        && (isOufOfBoundsOnTheLeft() || isOutOfBoundToTheRight() || isOutOfBoundsOnTheBottom());
  }

  private boolean isOufOfBoundsOnTheLeft() {
    return point().x() < minX();
  }

  private boolean isOutOfBoundToTheRight() {
    return point().x() > maxX();
  }

  private boolean isOutOfBoundsOnTheBottom() {
    return point().y() >= maxY();
  }
}
