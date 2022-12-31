package de.turing85.advent.of.code.tttt.day.twentythree.supplier.impl;

import de.turing85.advent.of.code.tttt.day.twentythree.model.Elf;
import de.turing85.advent.of.code.tttt.day.twentythree.supplier.ElvesSupplier;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;
import lombok.experimental.Delegate;

/**
 * Reads a {@link String}-representation of a {@link Set} of {@link Elf}s from a file, represented
 * by a {@link Path}.
 */
public class FromFileElvesSupplier implements ElvesSupplier {
  @Delegate private final FromStringElvesSupplier fromStringElvesSupplier;

  /**
   * Reads a {@link String}-representation of a {@link Set} of {@link Elf}s from a file, represented
   * by a {@link Path}.
   *
   * @param inputFile the file holding the {@link String}-representation of a {@link Set} of {@link
   *     Elf}s
   * @throws IOException if some I/O exception occurs when the file is read.
   */
  public FromFileElvesSupplier(Path inputFile) throws IOException {
    this.fromStringElvesSupplier = new FromStringElvesSupplier(Files.readString(inputFile));
  }
}
