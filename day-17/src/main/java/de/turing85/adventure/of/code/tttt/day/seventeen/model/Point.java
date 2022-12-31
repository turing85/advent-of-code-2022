package de.turing85.adventure.of.code.tttt.day.seventeen.model;

/**
 * A 2D-point.
 *
 * @param x x-coordinate (left-right direction)
 * @param y y-coordinate (down-up direction)
 */
public record Point(long x, long y) {
  /**
   * Constructs a new {@link Point} by adding {@code this} {@link Point} to {@code that} {@link
   * Point}.
   *
   * @param that point to add
   * @return a new point with {@code x = this.x() + that.x()} and {@code y = this.y() + that.y()}.
   */
  Point add(Point that) {
    return new Point(x() + that.x(), y() + that.y());
  }
}
