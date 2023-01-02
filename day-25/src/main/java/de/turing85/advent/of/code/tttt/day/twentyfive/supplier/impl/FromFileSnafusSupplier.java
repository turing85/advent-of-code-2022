package de.turing85.advent.of.code.tttt.day.twentyfive.supplier.impl;

import de.turing85.advent.of.code.tttt.day.twentyfive.supplier.SnafusSupplier;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import lombok.experimental.Delegate;

/** Reads {@code snafu} {@link String}s from a file, represented by a {@link Path}. */
public class FromFileSnafusSupplier implements SnafusSupplier {
  @Delegate private final FromStringSnafusSupplier fromStringSnafusSupplier;

  /**
   * Reads {@code snafu}-{@link String}s from a file, represented by a {@link Path}.
   *
   * @param inputFile the file holding the {@code snafu}-{@link String}s
   * @throws IOException if some I/O exception occurs when the file is read.
   */
  public FromFileSnafusSupplier(Path inputFile) throws IOException {
    this.fromStringSnafusSupplier = new FromStringSnafusSupplier(Files.readString(inputFile));
  }
}
