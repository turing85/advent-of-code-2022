package de.turing85.advent.of.code.tttt.day.one.supplier;

import de.turing85.advent.of.code.tttt.day.one.model.Expedition;
import java.util.function.Supplier;

/** A supplier, supplying some {@link Expedition}. */
public interface ExpeditionSupplier extends Supplier<Expedition> {
  @Override
  default Expedition get() {
    return getExpedition();
  }

  /**
   * Gets the expedition to supply.
   *
   * @return the {@link Expedition} to supply.
   */
  Expedition getExpedition();
}
