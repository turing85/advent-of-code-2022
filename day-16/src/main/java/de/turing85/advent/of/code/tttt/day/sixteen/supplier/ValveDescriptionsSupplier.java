package de.turing85.advent.of.code.tttt.day.sixteen.supplier;

import de.turing85.advent.of.code.tttt.day.sixteen.model.ValveDescription;
import java.util.Set;
import java.util.function.Supplier;

/**
 * A supplier, supplying a {@link Set} of {@link ValveDescription}ss.
 */
public interface ValveDescriptionsSupplier extends Supplier<Set<ValveDescription>> {
  default Set<ValveDescription> get() {
    return valveDescriptions();
  }

  /**
   * Supplies {@link ValveDescription}s.
   *
   * @return the {@link ValveDescription}s to supply
   */
  Set<ValveDescription> valveDescriptions();
}
