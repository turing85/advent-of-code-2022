package de.turing85.advent.of.code.tttt.day.five.supplier.impl;

import de.turing85.advent.of.code.tttt.day.five.model.UnloadInstruction;
import de.turing85.advent.of.code.tttt.day.five.supplier.UnloadInstructionsSupplier;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import lombok.experimental.Delegate;

/**
 * Reads a {@link String}-representation of {@link UnloadInstruction}s from a file, represented by a
 * {@link Path}.
 */
public class FromFileUnloadInstructionsSupplier implements UnloadInstructionsSupplier {
  @Delegate
  private final FromStringUnloadInstructionsSupplier fromStringUnloadInstructionsSupplier;

  /**
   * Reads a {@link String}-representation of {@link UnloadInstruction}s.
   *
   * @param inputFile the file holding the {@link String}-representation ot the
   *        {@link UnloadInstruction}s
   *
   * @throws IOException if some I/O exception occurs when the file is read.
   */
  public FromFileUnloadInstructionsSupplier(Path inputFile) throws IOException {
    fromStringUnloadInstructionsSupplier =
        new FromStringUnloadInstructionsSupplier(Files.readString(inputFile));
  }

}
