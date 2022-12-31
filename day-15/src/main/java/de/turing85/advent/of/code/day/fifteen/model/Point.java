package de.turing85.advent.of.code.day.fifteen.model;

/**
 * A 2D-point.
 *
 * @param x x-coordinate (left-right direction)
 * @param y y-coordinate (down-up direction)
 */
public record Point(long x, long y) {
  /**
   * Calculate the manhattan distance between {@code this} and {@code that}.
   *
   * @param that the point to calculate the distance to
   * @return the manhattan distance between {@code this} and {@code that}
   */
  public long manhattanDistance(Point that) {
    return Math.abs(x() - that.x()) + Math.abs(y() - that.y());
  }

  /**
   * Calculates the (minimum) manhattan distance to the given {@code y}-coordinate.
   *
   * @param yCoordinate to calculate distance to
   * @return manhattan distance to {@code yCoordinate}
   */
  public long manhattanDistanceToYCoordinate(long yCoordinate) {
    return Math.max(y(), yCoordinate) - Math.min(y(), yCoordinate);
  }

  /**
   * Generate all points that are at most {@code distance} (in manhattan distance) from {@code this}
   * {@link Point} away and are located on {@code y = yCoordinate}.
   *
   * @param distance maximum manhattan distance from {@code this}
   * @param yCoordinate to only generate points on this specific {@code y}-coordinate
   * @return all points satisfying the specification, as {@link Interval}
   */
  public Interval allPointsInManhattanDistanceAndYCoordinate(long distance, long yCoordinate) {
    long deltaX = distance - Math.abs(yCoordinate - y());
    return new Interval(x() - deltaX, x() + deltaX);
  }
}
