package de.turing85.advent.of.code.tttt.day.eight.supplier.impl;

import de.turing85.advent.of.code.tttt.day.eight.supplier.TreeMapSupplier;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import lombok.experimental.Delegate;

/**
 * Reads a {@link String}-representation of a tree map from a file, represented by a {@link Path}.
 */
public class FromFileTreeMapSupplier implements TreeMapSupplier {
  @Delegate
  private final FromStringTreeMapSupplier fromStringTreeMapSupplier;

  /**
   * Reads a {@link String}-representation of a tree map form a file, represented by a {@link Path}.
   *
   * @param inputFile the file holding the {@link String}-representation a tree map
   *
   * @throws IOException if some I/O exception occurs when the file is read.
   */
  public FromFileTreeMapSupplier(Path inputFile) throws IOException {
    fromStringTreeMapSupplier = new FromStringTreeMapSupplier(Files.readString(inputFile));
  }
}
