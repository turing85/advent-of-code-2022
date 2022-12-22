package de.turing85.advent.of.code.day.fifteen.provider.impl;

import de.turing85.advent.of.code.day.fifteen.model.Pair;
import de.turing85.advent.of.code.day.fifteen.model.Point;
import de.turing85.advent.of.code.day.fifteen.provider.PointPairsSupplier;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import lombok.experimental.Delegate;

/**
 * Reads a {@link String}-representation of {@link Pair}s of {@link Point}s from a file, represented
 * by a {@link Path}.
 */
public class FromFilePointPairsSupplier implements PointPairsSupplier {
  @Delegate
  private final FromStringPointPairsSupplier fromStringPointPairsSupplier;

  /**
   * Reads a {@link String}-representation of {@link Pair}s of {@link Point}s from a file,
   * represented by a {@link Path}.
   *
   * @param inputFile the file holding the {@link String}-representation of {@link Pair}s of
   *        {@link Point}s
   *
   * @throws IOException if some I/O exception occurs when the file is read.
   */
  public FromFilePointPairsSupplier(Path inputFile) throws IOException {
    fromStringPointPairsSupplier = new FromStringPointPairsSupplier(Files.readString(inputFile));
  }
}
