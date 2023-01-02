package de.turing85.advent.of.code.tttt.day.twentyfive.supplier;

import java.util.List;
import java.util.function.Supplier;

/** A supplier, supplying snaufs as {@link List} of {@link String}s. */
public interface SnafusSupplier extends Supplier<List<String>> {
  default List<String> get() {
    return snafus();
  }

  /**
   * Supplies a {@link List} of {code snafu}-{@link String}s.
   *
   * @return the {code snafu}-{@link String}s to return
   */
  List<String> snafus();
}
