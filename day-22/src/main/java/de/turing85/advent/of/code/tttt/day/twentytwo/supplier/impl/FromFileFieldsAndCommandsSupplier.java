package de.turing85.advent.of.code.tttt.day.twentytwo.supplier.impl;

import de.turing85.advent.of.code.tttt.day.twentytwo.supplier.FieldsAndCommands;
import de.turing85.advent.of.code.tttt.day.twentytwo.supplier.FieldsAndCommandsSupplier;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import lombok.experimental.Delegate;

/**
 * Reads a {@link String}-representation of a {@link FieldsAndCommands} from a file, represented by
 * a {@link Path}.
 */
public class FromFileFieldsAndCommandsSupplier implements FieldsAndCommandsSupplier {
  @Delegate
  private final FromStringFieldsAndCommandsSupplier fromStringFieldsAndCommandsSupplier;

  /**
   * Reads a {@link String}-representation of a {@link FieldsAndCommands} from a file, represented
   * by a {@link Path}.
   *
   * @param inputFile the file holding the {@link String}-representation of
   *        {@link FieldsAndCommands}
   *
   * @throws IOException if some I/O exception occurs when the file is read.
   */
  public FromFileFieldsAndCommandsSupplier(Path inputFile) throws IOException {
    fromStringFieldsAndCommandsSupplier =
        new FromStringFieldsAndCommandsSupplier(Files.readString(inputFile));
  }
}
