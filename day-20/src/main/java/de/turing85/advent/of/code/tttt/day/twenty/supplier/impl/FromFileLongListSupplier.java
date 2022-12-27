package de.turing85.advent.of.code.tttt.day.twenty.supplier.impl;

import de.turing85.advent.of.code.tttt.day.twenty.supplier.LongListSupplier;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import lombok.experimental.Delegate;

/**
 * Reads a {@link String}-representation of a {@link List} of {@link Long}s from a file, represented
 * by a {@link Path}.
 */
public class FromFileLongListSupplier implements LongListSupplier {
  @Delegate
  private final FromStringLongListSupplier fromStringLongListSupplier;

  /**
   * Reads a {@link String}-representation of a {@link List} of {@link Long}s from a file,
   * represented by a {@link Path}.
   *
   * @param inputFile the file holding the {@link String}-representation of a {@link List} of
   *     {@link Long}s
   *
   * @throws IOException if some I/O exception occurs when the file is read.
   */
  public FromFileLongListSupplier(Path inputFile) throws IOException {
    fromStringLongListSupplier = new FromStringLongListSupplier(Files.readString(inputFile));
  }
}
