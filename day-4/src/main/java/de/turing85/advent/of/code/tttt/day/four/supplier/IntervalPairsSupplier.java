package de.turing85.advent.of.code.tttt.day.four.supplier;

import de.turing85.advent.of.code.tttt.day.four.model.Interval;
import de.turing85.advent.of.code.tttt.day.four.model.Pair;
import java.util.Collection;
import java.util.function.Supplier;

/**
 * A supplier, supplying some {@link Pair}s of {@link Interval}s.
 */
public interface IntervalPairsSupplier extends Supplier<Collection<Pair<Interval, Interval>>> {
  @Override
  default Collection<Pair<Interval, Interval>> get() {
    return intervalPairs();
  }

  /**
   * The {@link Pair}s of {@link Interval}s to supply.
   *
   * @return the {@link Pair}s of {@link Interval}s to supply
   */
  Collection<Pair<Interval, Interval>> intervalPairs();
}
