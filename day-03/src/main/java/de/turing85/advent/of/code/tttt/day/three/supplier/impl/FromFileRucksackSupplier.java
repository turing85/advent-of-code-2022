package de.turing85.advent.of.code.tttt.day.three.supplier.impl;

import de.turing85.advent.of.code.tttt.day.three.model.Rucksack;
import de.turing85.advent.of.code.tttt.day.three.supplier.RucksacksSupplier;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import lombok.experimental.Delegate;

/**
 * Reads a {@link String}-representation of {@link Rucksack}s from a file, represented as {@link
 * Path}, and constructs the corresponding {@link java.util.Collection} of {@link Rucksack}s from
 * it.
 */
public class FromFileRucksackSupplier implements RucksacksSupplier {
  @Delegate private final FromStringRucksackSupplier fromStringRucksackSupplier;

  /**
   * Reads a {@link String}-representation of {@link Rucksack}s from a file, represented as {@link
   * Path} and constructs the corresponding {@link java.util.Collection} of {@link Rucksack}s from
   * it.
   *
   * @param inputFile the file holding the {@link String}-representation of {@link Rucksack}s
   * @throws IOException if some I/O exception occurs when the file is read.
   */
  public FromFileRucksackSupplier(Path inputFile) throws IOException {
    this.fromStringRucksackSupplier = new FromStringRucksackSupplier(Files.readString(inputFile));
  }
}
