package de.turing85.advent.of.code.tttt.day.eighteen.supplier;

import de.turing85.advent.of.code.tttt.day.eighteen.model.Point3D;
import java.util.Set;
import java.util.function.Supplier;

/** A supplier, supplying a {@link Set} of {@link Point3D}s. */
public interface PointsSupplier extends Supplier<Set<Point3D>> {
  default Set<Point3D> get() {
    return points();
  }

  /**
   * Supplies {@link Point3D}s
   *
   * @return the {@link Point3D}s to return
   */
  Set<Point3D> points();
}
