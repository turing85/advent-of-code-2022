package de.turing85.advent.of.code.tttt.day.seven.supplier.impl;

import de.turing85.advent.of.code.tttt.day.seven.supplier.CommandsAndResponsesSupplier;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import lombok.experimental.Delegate;

/**
 * Reads a {@link String}-representation of commands and responses form a file, represented by a
 * {@link Path}.
 */
public class FromFileCommandsAndResponsesSupplier implements CommandsAndResponsesSupplier {
  @Delegate
  private final FromStringCommandsAndResponsesSupplier fromStringCommandsAndResponsesSupplier;

  /**
   * Reads a {@link String}-representation of commands and responses from a file, represented by a
   * {@link Path}.
   *
   * @param inputFile the file holding the {@link String}-representation of commands and responses
   * @throws IOException if some I/O exception occurs when the file is read.
   */
  public FromFileCommandsAndResponsesSupplier(Path inputFile) throws IOException {
    fromStringCommandsAndResponsesSupplier =
        new FromStringCommandsAndResponsesSupplier(Files.readString(inputFile));
  }
}
