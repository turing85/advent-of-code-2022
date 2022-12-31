package de.turing85.advent.of.code.tttt.day.five.supplier.impl;

import de.turing85.advent.of.code.tttt.day.five.supplier.NumberedStacksSupplier;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import lombok.experimental.Delegate;

/**
 * Reads a {@link String}-representation of numbered stacks from a file, represented as a {@link
 * Path}.
 */
public class FromFileNumberedStackSupplier implements NumberedStacksSupplier {
  @Delegate private final FromStringNumberedStacksSupplier fromStringNumberedStacksSupplier;

  /**
   * Reads a {@link String}-representation of numbered stacks from a file, represented as a {@link
   * Path}.
   *
   * @param inputFile the file holding the {@link String}-representation of numbered stacks
   * @throws IOException if some I/O exception occurs when the file is read.
   */
  public FromFileNumberedStackSupplier(Path inputFile) throws IOException {
    fromStringNumberedStacksSupplier =
        new FromStringNumberedStacksSupplier(Files.readString(inputFile));
  }
}
