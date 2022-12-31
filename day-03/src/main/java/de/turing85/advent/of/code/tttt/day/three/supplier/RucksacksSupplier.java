package de.turing85.advent.of.code.tttt.day.three.supplier;

import de.turing85.advent.of.code.tttt.day.three.model.Rucksack;
import java.util.List;
import java.util.function.Supplier;

/** Supplies some {@link List} of {@link Rucksack}s. */
public interface RucksacksSupplier extends Supplier<List<Rucksack>> {
  @Override
  default List<Rucksack> get() {
    return rucksacks();
  }

  /**
   * The {@link Rucksack}s to supply.
   *
   * @return the {@link Rucksack}s to supply.
   */
  List<Rucksack> rucksacks();
}
