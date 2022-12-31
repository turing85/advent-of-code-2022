package de.turing85.advent.of.code.tttt.day.eighteen.model;

import java.util.Set;

/**
 * A point in 3D-space.
 *
 * @param x {@code x}-coordinate of the {@link Point3D}
 * @param y {@code y}-coordinate of the {@link Point3D}
 * @param z {@code z}-coordinate of the {@link Point3D}
 */
public record Point3D(long x, long y, long z) {
  /**
   * Calculates the six direct neighbour in the cardinal directions (up, down, right, left,
   * forwards, backwards)
   *
   * @return the direct neighbours of {@code this} {@link Point3D}
   */
  Set<Point3D> neighbours() {
    return Set.of(
        new Point3D(x(), y(), z() + 1),
        new Point3D(x(), y(), z() - 1),
        new Point3D(x(), y() + 1, z()),
        new Point3D(x(), y() - 1, z()),
        new Point3D(x() + 1, y(), z()),
        new Point3D(x() - 1, y(), z()));
  }

  /**
   * Determines whether the point is in bound, i.e. {@code lower.x() <= this.x() && this.x() <=
   * upper.x() && lowerBound.y() <= this.y() && this.y() <= upperBound.y() && lowerBound.z() <=
   * this.z() && this.z() <= upperBound.z()}
   *
   * @param lowerBound the {@link Point3D} defining the lower bound
   * @param upperBound the {@link Point3D} defining the upper bound
   * @return {@code true} if and only if {@code this} {@link Point3D} is in bounds.
   */
  boolean isInBounds(Point3D lowerBound, Point3D upperBound) {
    return lowerBound.x() <= this.x()
        && this.x() <= upperBound.x()
        && lowerBound.y() <= this.y()
        && this.y() <= upperBound.y()
        && lowerBound.z() <= this.z()
        && this.z() <= upperBound.z();
  }
}
