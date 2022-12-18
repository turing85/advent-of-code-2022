package de.turing85.advent.of.code.tttt.day.four.supplier.impl;

import de.turing85.advent.of.code.tttt.day.four.model.Interval;
import de.turing85.advent.of.code.tttt.day.four.model.Pair;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import lombok.experimental.Delegate;

/**
 * Reads a {@link String}-representation of {@link Pair}s of {@link Interval}s from a file,
 * represented by a {@link URI}, and constructs the corresponding {@link java.util.Collection} of
 * {@link Pair}s from it.
 */
public class FromFileIntervalPairSupplier {
  @Delegate
  private final FromStringIntervalPairsSupplier fromStringIntervalPairsSupplier;

  /**
   * Reads a {@link String}-representation of {@link Pair}s of {@link Interval}s from a file,
   * represented by a {@link URI}, and constructs the corresponding {@link java.util.Collection} of
   * {@link Pair}s from it.
   *
   * @param inputFile the file holding the {@link String}-representation of the {@link Pair}s of
   *        {@link Interval}s.
   *
   * @throws IOException if some I/O exception occurs when the file is read.
   */
  public FromFileIntervalPairSupplier(URI inputFile) throws IOException {
    fromStringIntervalPairsSupplier =
        new FromStringIntervalPairsSupplier(Files.readString(Path.of(inputFile)));
  }
}
