package de.turing85.advent.of.code.tttt.day.nine.supplier.impl;

import de.turing85.advent.of.code.tttt.day.nine.model.MovementCommand;
import de.turing85.advent.of.code.tttt.day.nine.supplier.MovementCommandsSupplier;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import lombok.experimental.Delegate;

/**
 * Reads a {@link String}-representation of {@link MovementCommand}s from a file, represented by a
 * {@link Path}.
 */
public class FromFileMovementCommandsSupplier implements MovementCommandsSupplier {
  @Delegate
  private final FromStringMovementCommandSupplier fromStringMovementCommandSupplier;

  /**
   * Reads a {@link String}-representation of {@link MovementCommand}s from a file, represented by a
   * {@link Path}.
   *
   * @param inputFile the file holding the {@link String}-representation of commands
   *
   * @throws IOException if some I/O exception occurs when the file is read.
   */
  public FromFileMovementCommandsSupplier(Path inputFile) throws IOException {
    this.fromStringMovementCommandSupplier =
        new FromStringMovementCommandSupplier(Files.readString(inputFile));
  }
}
