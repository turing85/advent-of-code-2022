package de.turing85.advent.of.code.tttt.day.twentyfour.supplier.impl;

import de.turing85.advent.of.code.tttt.day.twentyfour.supplier.BlizzardRoutingConfigSupplier;
import de.turing85.advent.of.code.tttt.day.twentyfour.supplier.BlizzardRoutingConfiguration;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import lombok.experimental.Delegate;

/**
 * Reads a {@link String}-representation of a {@link BlizzardRoutingConfiguration} from a file,
 * represented by a {@link Path}.
 */
public class FromFileBlizzardRoutingConfigSupplier implements BlizzardRoutingConfigSupplier {
  @Delegate
  private final FromStringBlizzardRoutingConfigSupplier fromStringBlizzardRoutingConfigSupplier;

  /**
   * Reads a {@link String}-representation of a {@link BlizzardRoutingConfiguration} from a file,
   * represented by a {@link Path}.
   *
   * @param inputFile the file holding the {@link String}-representation of a {@link
   *     BlizzardRoutingConfiguration}
   * @throws IOException if some I/O exception occurs when the file is read.
   */
  public FromFileBlizzardRoutingConfigSupplier(Path inputFile) throws IOException {
    this.fromStringBlizzardRoutingConfigSupplier =
        new FromStringBlizzardRoutingConfigSupplier(Files.readString(inputFile));
  }
}
