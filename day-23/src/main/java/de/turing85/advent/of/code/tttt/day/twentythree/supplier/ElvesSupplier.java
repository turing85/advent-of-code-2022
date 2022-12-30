package de.turing85.advent.of.code.tttt.day.twentythree.supplier;

import de.turing85.advent.of.code.tttt.day.twentythree.model.Elf;
import java.util.Set;
import java.util.function.Supplier;

/**
 * A supplier, supplying a {@link Set} of {@link Elf}s.
 */
public interface ElvesSupplier extends Supplier<Set<Elf>> {
  @Override
  default Set<Elf> get() {
    return elves();
  }

  /**
   * Supplies {@link Elf}s
   *
   * @return the {@link Elf}s to return
   */
  Set<Elf> elves();
}
