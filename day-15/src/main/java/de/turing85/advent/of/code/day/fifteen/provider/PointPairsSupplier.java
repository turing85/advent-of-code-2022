package de.turing85.advent.of.code.day.fifteen.provider;

import de.turing85.advent.of.code.day.fifteen.model.Pair;
import de.turing85.advent.of.code.day.fifteen.model.Point;
import java.util.Set;
import java.util.function.Supplier;

/**
 * A supplier, supplying a {@link Set} of {@link Pair}s of {@link Point}s.
 */
public interface PointPairsSupplier extends Supplier<Set<Pair<Point, Point>>> {
  default Set<Pair<Point, Point>> get() {
    return pointPairs();
  }

  /**
   * Supplies {@link Pair}s of {@link Point}s.
   *
   * @return the {@link Pair}s of {@link Point}s to supply
   */
  Set<Pair<Point, Point>> pointPairs();
}
