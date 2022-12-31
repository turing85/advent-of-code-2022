package de.turing85.advent.of.code.tttt.day.ten.supplier.impl;

import de.turing85.advent.of.code.tttt.day.ten.model.InstructionWithParameter;
import de.turing85.advent.of.code.tttt.day.ten.supplier.InstructionsWithParameterSupplier;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import lombok.experimental.Delegate;

/**
 * Reads a {@link String}-representation of {@link InstructionWithParameter}s from a file,
 * represented by a {@link Path}.
 */
public class FromFileInstructionsWithParameterSupplier
    implements InstructionsWithParameterSupplier {
  @Delegate
  private final FromStringInstructionsWithParameterSupplier
      fromStringInstructionsWithParameterSupplier;

  /**
   * Reads a {@link String}-representation of {@link InstructionsWithParameterSupplier}s from a
   * file, represented by a {@link Path}.
   *
   * @param inputFile the file holding the {@link String}-representation of Instructions
   * @throws IOException if some I/O exception occurs when the file is read.
   */
  public FromFileInstructionsWithParameterSupplier(Path inputFile) throws IOException {
    this.fromStringInstructionsWithParameterSupplier =
        new FromStringInstructionsWithParameterSupplier(Files.readString(inputFile));
  }
}
