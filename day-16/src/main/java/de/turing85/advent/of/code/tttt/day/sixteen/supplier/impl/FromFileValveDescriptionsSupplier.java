package de.turing85.advent.of.code.tttt.day.sixteen.supplier.impl;

import de.turing85.advent.of.code.tttt.day.sixteen.model.ValveDescription;
import de.turing85.advent.of.code.tttt.day.sixteen.supplier.ValveDescriptionsSupplier;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import lombok.experimental.Delegate;

/**
 * Reads a {@link String}-representation of {@link ValveDescription}s from a file, represented by a
 * {@link Path}.
 */
public class FromFileValveDescriptionsSupplier implements ValveDescriptionsSupplier {
  @Delegate private final FromStringValveDescriptionsSupplier fromStringValveDescriptionsSupplier;

  /**
   * Reads a {@link String}-representation of {@link ValveDescription}s from a file, represented by
   * a {@link Path}.
   *
   * @param inputFile the file holding the {@link String}-representation of {@link
   *     ValveDescription}s
   * @throws IOException if some I/O exception occurs when the file is read.
   */
  public FromFileValveDescriptionsSupplier(Path inputFile) throws IOException {
    this.fromStringValveDescriptionsSupplier =
        new FromStringValveDescriptionsSupplier(Files.readString(inputFile));
  }
}
