package de.turing85.advent.of.code.tttt.day.fourteen;

import de.turing85.advent.of.code.tttt.day.fourteen.model.GrainOfSand;
import de.turing85.advent.of.code.tttt.day.fourteen.model.Line;
import de.turing85.advent.of.code.tttt.day.fourteen.model.Point;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/** A falling sand simulator. */
public class FallingSandSimulator {
  private static final Point ORIGIN_FOR_SAND = Point.of(500, 0);
  private final Set<Point> blocked;
  private final Set<Point> grainsOfSandAtRest;
  private GrainOfSand fallingGrainOfSand;
  private final int maxX;
  private final int minX;
  private final int maxY;

  /**
   * Initializes a falling sand simulator with blocked {@link Point}s, defined through a {@link Set}
   * of {@link Line}s.
   *
   * @param linesDefiningBlockedPoints {@link Line}s defining blocked {@link Point}s
   */
  public FallingSandSimulator(Set<Line> linesDefiningBlockedPoints) {
    blocked =
        (linesDefiningBlockedPoints.stream()
            .map(Line::allPointsOnLine)
            .flatMap(Collection::stream)
            .collect(Collectors.toSet()));
    grainsOfSandAtRest = new HashSet<>();
    maxX = blocked.stream().mapToInt(Point::x).max().orElse(Integer.MIN_VALUE);
    minX = blocked.stream().mapToInt(Point::x).min().orElse(Integer.MAX_VALUE);
    maxY = blocked.stream().mapToInt(Point::y).max().orElse(Integer.MIN_VALUE);
  }

  private GrainOfSand makeNewGrainOfSandWithAbyss() {
    return new GrainOfSand(ORIGIN_FOR_SAND, blocked, maxX, minX, maxY);
  }

  /**
   * Let sand fall until additional sand will fall into the abyss.
   *
   * @return the number of grains of sand that are at rest.
   */
  public int letSandFallUntilEverythingWillFallIntoTheAbyss() {
    fallingGrainOfSand = makeNewGrainOfSandWithAbyss();
    while (true) {
      if (tick()) {
        break;
      }
    }
    return grainsOfSandAtRest.size();
  }

  private boolean tick() {
    if (fallingGrainOfSand.isAtRest()) {
      blocked.add(fallingGrainOfSand.point());
      grainsOfSandAtRest.add(fallingGrainOfSand.point());
      fallingGrainOfSand =
          new GrainOfSand(
              ORIGIN_FOR_SAND, blocked, maxX, minX, maxY, fallingGrainOfSand.floorAtYCoordinate());
      if (fallingGrainOfSand.isAtRest()) {
        grainsOfSandAtRest.add(fallingGrainOfSand.point());
        return true;
      }
    }
    fallingGrainOfSand.fall();
    return fallingGrainOfSand.willFallIntoTheAbyss();
  }

  /**
   * Let sand fall until additional sand will fall into the abyss.
   *
   * @return the number of grains of sand that are at rest.
   */
  public int letSandFallUntilOriginIsBlocked() {
    fallingGrainOfSand = makeNewGrainOfSandWithFloor();
    while (true) {
      if (tick()) {
        break;
      }
    }
    return grainsOfSandAtRest.size();
  }

  private GrainOfSand makeNewGrainOfSandWithFloor() {
    int floorDepth = blocked.stream().mapToInt(Point::y).max().orElse(Integer.MIN_VALUE) + 2;
    return new GrainOfSand(ORIGIN_FOR_SAND, blocked, maxX, minX, maxY, floorDepth);
  }
}
