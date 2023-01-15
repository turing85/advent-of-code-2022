package de.turing85.advent.of.code.tttt.day.seventeen.model;

import java.util.Set;
import lombok.Getter;

/**
 * A shape is a series of points, relative to its lower-left corner.
 *
 * <p>The lower-left corner is assumed to be at {@code x = y = 0}.
 */
public enum Shape {
  /**
   * The plus shape:
   *
   * <pre>
   *   .*.
   *   ***
   *   .*.
   * </pre>
   */
  PLUS(Set.of(new Point(1, 0), new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(1, 2))),
  /**
   * The horizontal bar shape:
   *
   * <pre>
   *   ****
   * </pre>
   */
  H_BAR(Set.of(new Point(0, 0), new Point(1, 0), new Point(2, 0), new Point(3, 0))),
  /**
   * The vertical bar shape:
   *
   * <pre>
   *   *
   *   *
   *   *
   *   *
   * </pre>
   */
  V_BAR(Set.of(new Point(0, 0), new Point(0, 1), new Point(0, 2), new Point(0, 3))),
  /**
   * The square shape:
   *
   * <pre>
   *   **
   *   **
   * </pre>
   */
  SQUARE(Set.of(new Point(0, 0), new Point(1, 0), new Point(0, 1), new Point(1, 1))),
  /**
   * The Corner shape:
   *
   * <pre>
   *   ..*
   *   ..*
   *   ..*
   * </pre>
   */
  CORNER(
      Set.of(new Point(0, 0), new Point(1, 0), new Point(2, 0), new Point(2, 1), new Point(2, 2)));

  @Getter private final Set<Point> pointsOfShape;

  Shape(Set<Point> pointsOfShape) {
    this.pointsOfShape = pointsOfShape;
  }
}
