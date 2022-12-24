package de.turing85.adventure.of.code.tttt.day.seventeen.model;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * A movable tile, that is backed by a {@link Shape}, which position is defined through a single
 * {@link Point}, defining the lower-left corner of the {@link ShapeTile}.
 *
 * @param lowerLeftCorner {@link Point} representing the lower left corner
 * @param shape the shape that {@code this} {@link ShapeTile} represents
 */
public record ShapeTile(Point lowerLeftCorner, Shape shape) implements MovableTile<ShapeTile> {
  /**
   * Constructor
   * @param x {@code x}-coordinate of lower left corner
   * @param y {@code y}-coordinate of lower left corner
   * @param shape the {@link Shape}
   */
  public ShapeTile(long x, long y, Shape shape) {
    this(new Point(x, y), shape);
  }

  @Override
  public Set<Point> pointsOfTile() {
    return shape().pointsOfShape().stream().map(lowerLeftCorner::add).collect(Collectors.toSet());
  }

  @Override
  public ShapeTile move(Direction direction) {
    return switch (direction) {
      case UP -> new ShapeTile(lowerLeftCorner().x(), lowerLeftCorner().y() + 1, shape());
      case DOWN -> new ShapeTile(lowerLeftCorner().x(), lowerLeftCorner().y() - 1, shape());
      case RIGHT -> new ShapeTile(lowerLeftCorner().x() + 1, lowerLeftCorner().y(), shape());
      case LEFT -> new ShapeTile(lowerLeftCorner().x() - 1, lowerLeftCorner().y(), shape());
    };
  }
}
