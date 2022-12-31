package de.turing85.advent.of.code.tttt.day.eighteen.model;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.stream.Collectors;

/** Calculates the surface acre of a {@link java.util.Set} of {@link Point3D}. */
public class SurfaceCalculator {
  private final Set<Point3D> points;

  private final Set<Point3D> processed;
  private final Deque<Point3D> queue;
  private final Point3D upperBound;
  private final Point3D lowerBound;
  private long facesExposedToTheOutside;

  /**
   * Constructor.
   *
   * @param points points to analyze.
   */
  public SurfaceCalculator(Set<Point3D> points) {
    this.points = points;
    long maxX = points.stream().mapToLong(Point3D::x).max().orElse(0);
    long minX = points.stream().mapToLong(Point3D::x).min().orElse(0);
    long maxY = points.stream().mapToLong(Point3D::y).max().orElse(0);
    long minY = points.stream().mapToLong(Point3D::y).min().orElse(0);
    long maxZ = points.stream().mapToLong(Point3D::z).max().orElse(0);
    long minZ = points.stream().mapToLong(Point3D::z).min().orElse(0);

    processed = new HashSet<>();
    upperBound = new Point3D(maxX + 1, maxY + 1, maxZ + 1);
    lowerBound = new Point3D(minX - 1, minY - 1, minZ - 1);
    queue = new LinkedList<>();
    queue.addLast(upperBound);
    facesExposedToTheOutside = 0;
  }

  /**
   * Calculates the surface acre of a {@link java.util.Set} of {@code 1x1x1} cubes, represented
   * by{@link Point3D}s.
   *
   * <p>Calculation is done as follows:
   *
   * <ul>
   *   <li>Each cube has {@code 6} sides
   *   <li>If two cubes touch, the total sum of sides as surface are is reduced by {@code 2}
   * </ul>
   *
   * Hence, the result is calculated by the following formula:
   *
   * <pre>
   *   6 * number of points - sides touching
   * </pre>
   *
   * @return the surface area
   */
  public long surfaceAreaIgnoreTrappedAirBubbles() {
    long touching = 0;
    for (Point3D point : points) {
      touching += point.neighbours().stream().filter(points::contains).count();
    }
    return 6L * points.size() - touching;
  }

  /**
   * Calculates the surface acre of a {@link java.util.Set} of {@code 1x1x1} cubes, represented
   * by{@link Point3D}s.
   *
   * <p>Calculation is done as follows:
   *
   * <ul>
   *   <li>calculate each point that is "outside" by starting at a point that is known to be outside
   *   <li>For each point in {@code points}, count the faces that touch an outside point
   * </ul>
   *
   * @return the surface area
   */
  public long surfaceAreaConsiderTrappedAirBubbles() {
    facesExposedToTheOutside = 0;
    queue.clear();
    queue.addLast(upperBound);
    while (!queue.isEmpty()) {
      processNeighbours();
    }
    return facesExposedToTheOutside;
  }

  private void processNeighbours() {
    Set<Point3D> neighbours = getNeighboursInBound();
    for (Point3D neighbour : neighbours) {
      if (points.contains(neighbour)) {
        ++facesExposedToTheOutside;
      } else if (processed.add(neighbour)) {
        queue.addLast(neighbour);
      }
    }
  }

  private Set<Point3D> getNeighboursInBound() {
    return queue.removeFirst().neighbours().stream()
        .filter(point -> point.isInBounds(lowerBound, upperBound))
        .collect(Collectors.toSet());
  }
}
