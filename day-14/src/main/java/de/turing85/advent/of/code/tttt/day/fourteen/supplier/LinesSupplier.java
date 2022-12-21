package de.turing85.advent.of.code.tttt.day.fourteen.supplier;

import de.turing85.advent.of.code.tttt.day.fourteen.model.Line;
import java.util.Set;
import java.util.function.Supplier;

/**
 * A supplier, supplying a {@link Set} of {@link Line}s.
 */
public interface LinesSupplier extends Supplier<Set<Line>> {
  default Set<Line> get() {
    return lines();
  }

  /**
   * Supplies {@link Line}s.
   *
   * @return the {@link Line}s to supply.
   */
  Set<Line> lines();
}
