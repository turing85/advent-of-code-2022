package de.turing85.advent.of.code.tttt.day.twentyfour.supplier;

import java.util.function.Supplier;

/** A supplier, supplying a {@link BlizzardRoutingConfiguration}. */
public interface BlizzardRoutingConfigSupplier extends Supplier<BlizzardRoutingConfiguration> {
  default BlizzardRoutingConfiguration get() {
    return blizzardRoutingConfiguration();
  }

  /**
   * Supplies a {@link BlizzardRoutingConfiguration}.
   *
   * @return the {@link BlizzardRoutingConfiguration} to return
   */
  BlizzardRoutingConfiguration blizzardRoutingConfiguration();
}
