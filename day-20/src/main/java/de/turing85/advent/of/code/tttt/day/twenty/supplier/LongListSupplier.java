package de.turing85.advent.of.code.tttt.day.twenty.supplier;

import java.util.List;
import java.util.function.Supplier;

/** A supplier, supplying a {@link List} of {@link Long}s. */
public interface LongListSupplier extends Supplier<List<Long>> {
  default List<Long> get() {
    return longList();
  }

  /**
   * Supplies {@link Long}s
   *
   * @return the {@link Long}s to return
   */
  List<Long> longList();
}
