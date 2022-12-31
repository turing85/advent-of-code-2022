package de.turing85.advent.of.code.tttt.day.eighteen.supplier.impl;

import de.turing85.advent.of.code.tttt.day.eighteen.model.Point3D;
import de.turing85.advent.of.code.tttt.day.eighteen.supplier.PointsSupplier;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import lombok.experimental.Delegate;

/**
 * Reads a {@link String}-representation of {@link Point3D}s from a file, represented by a {@link
 * Path}.
 */
public class FromFilePointsSupplier implements PointsSupplier {
  @Delegate private final FromStringPointsSupplier fromStringPointsSupplier;

  /**
   * Reads a {@link String}-representation of {@link Point3D}s from a file, represented by a {@link
   * Path}.
   *
   * @param inputFile the file holding the {@link String}-representation of {@link Point3D}s
   * @throws IOException if some I/O exception occurs when the file is read.
   */
  public FromFilePointsSupplier(Path inputFile) throws IOException {
    this.fromStringPointsSupplier = new FromStringPointsSupplier(Files.readString(inputFile));
  }
}
