package de.turing85.advent.of.code.tttt.day.fourteen.supplier.impl;

import de.turing85.advent.of.code.tttt.day.fourteen.model.Line;
import de.turing85.advent.of.code.tttt.day.fourteen.supplier.LinesSupplier;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import lombok.experimental.Delegate;

/**
 * Reads a {@link String}-representation of {@link Line}s from a file, represented by a {@link
 * Path}.
 */
public class FromFileLinesSupplier implements LinesSupplier {
  @Delegate private final FromStringLinesSupplier fromStringLinesSupplier;

  /**
   * Reads a {@link String}-representation of {@link Line}s from a file, represented by a {@link
   * Path}.
   *
   * @param inputFile the file holding the {@link String}-representation of {@link Line}s
   * @throws IOException if some I/O exception occurs when the file is read.
   */
  public FromFileLinesSupplier(Path inputFile) throws IOException {
    this.fromStringLinesSupplier = new FromStringLinesSupplier(Files.readString(inputFile));
  }
}
