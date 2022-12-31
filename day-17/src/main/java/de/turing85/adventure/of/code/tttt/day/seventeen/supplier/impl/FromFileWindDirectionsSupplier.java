package de.turing85.adventure.of.code.tttt.day.seventeen.supplier.impl;

import de.turing85.adventure.of.code.tttt.day.seventeen.model.Direction;
import de.turing85.adventure.of.code.tttt.day.seventeen.supplier.WindDirectionsSupplier;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import lombok.experimental.Delegate;

/**
 * Reads a {@link String}-representation of {@link Direction#RIGHT}s (represented by {@code >}) and
 * {@link Direction#LEFT}s (represented by {@code <}) from a file, represented by a {@link Path}.
 */
public class FromFileWindDirectionsSupplier implements WindDirectionsSupplier {
  @Delegate private final FromStringWindDirectionsSupplier fromStringWindDirectionsSupplier;

  /**
   * Reads a {@link String}-representation of {@link Direction#RIGHT}s (represented by {@code >})
   * and {@link Direction#LEFT}s (represented by {@code <}) from a file, represented by a {@link
   * Path}.
   *
   * @param inputFile the file holding the {@link String}-representation of {@link Direction}s.
   * @throws IOException if some I/O exception occurs when the file is read.
   */
  public FromFileWindDirectionsSupplier(Path inputFile) throws IOException {
    fromStringWindDirectionsSupplier =
        new FromStringWindDirectionsSupplier(Files.readString(inputFile));
  }
}
