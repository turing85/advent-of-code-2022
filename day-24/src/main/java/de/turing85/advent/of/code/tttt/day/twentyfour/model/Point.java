package de.turing85.advent.of.code.tttt.day.twentyfour.model;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * A 2D-point.
 *
 * @param x x-coordinate (left-right direction)
 * @param y y-coordinate (down-up direction)
 */
public record Point(int x, int y) {
  /**
   * Get all eight neighbouring {@link Point}s of a {@link Point}.
   *
   * @return all eight neighbours
   */
  public Set<Point> neighbours() {
    return neighbours(Set.of(Direction.values()));
  }

  /**
   * Get all neighbouring {@link Point}s in the specified {@code Direction}s.
   *
   * @param directions the {@link Direction}s to consider
   * @return the neighbouring {@link Point}s
   */
  public Set<Point> neighbours(Set<Direction> directions) {
    return directions.stream().map(this::neighbour).collect(Collectors.toSet());
  }

  /**
   * Get the neighbouring {@link Point} in the given {@code direction}
   *
   * @param direction the direction of the neighbouring {@link Point}
   * @return the neighbouring {@link Point}
   */
  public Point neighbour(Direction direction) {
    return switch (direction) {
      case UP -> new Point(x(), y() - 1);
      case DOWN -> new Point(x(), y() + 1);
      case LEFT -> new Point(x() - 1, y());
      case RIGHT -> new Point(x() + 1, y());
    };
  }
}
