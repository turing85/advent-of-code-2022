package de.turing85.advent.of.code.tttt.day.nine.model;

/**
 * A 2D-point.
 *
 * @param x x-coordinate (left-right direction)
 * @param y y-coordinate (down-up direction)
 */
public record Point(int x, int y) {
  /**
   * Default-constructor, constructing a point at (0, 0).
   */
  public Point() {
    this(0, 0);
  }

  /**
   * Checks whether the points are adjacent to each other.
   *
   * @param that the point to check against
   *
   * @return {@code true}, if and only if the points are adjacent or the same.
   */
  public boolean isAdjacentTo(Point that) {
    return Math.abs(this.x() - that.x()) <= 1 && Math.abs(this.y() - that.y()) <= 1;
  }

  /**
   * Move one step towards {@code that} point (diagonal steps allowed).
   *
   * @param that the point to move to
   *
   * @return a new point, at the new location
   */
  public Point moveOneStepTowards(Point that) {
    int deltaX = Integer.signum(that.x() - this.x());
    int deltaY = Integer.signum(that.y() - this.y());
    return new Point(x() + deltaX, y() + deltaY);
  }

  /**
   * Move one step in the given direction.
   *
   * @param direction to move in
   *
   * @return a new point, at the new position.
   */
  public Point move(Direction direction) {
    return switch (direction) {
      case UP -> moveUp();
      case DOWN -> moveDown();
      case RIGHT -> moveRight();
      case LEFT -> moveLeft();
    };
  }

  private Point moveUp() {
    return new Point(x(), y() + 1);
  }

  private Point moveDown() {
    return new Point(x(), y() - 1);
  }

  private Point moveRight() {
    return new Point(x() + 1, y());
  }

  private Point moveLeft() {
    return new Point(x() - 1, y());
  }
}