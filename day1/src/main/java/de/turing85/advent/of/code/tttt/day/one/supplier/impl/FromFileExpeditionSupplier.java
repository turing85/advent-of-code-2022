package de.turing85.advent.of.code.tttt.day.one.supplier.impl;

import de.turing85.advent.of.code.tttt.day.one.supplier.ExpeditionSupplier;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import lombok.experimental.Delegate;

/**
 * Reads a {@link String}-representation of an expedition from a file represented by a {@link URI}
 * and constructs the corresponding expedition from it.
 *
 * <p>
 * Backpacks in an expedition are separate by two {@link System#lineSeparator()}s, while items
 * within a backpack are separated by only one {@link System#lineSeparator()}.
 */
public class FromFileExpeditionSupplier implements ExpeditionSupplier {
  @Delegate
  private final FromStringExpeditionSupplier fromStringExpeditionSupplier;

  /**
   * Given a {@link URI} to a file that contains a {@link String}-representation of an expedition,
   * constructs the corresponding expedition from it.
   *
   * @param inputFile a {@link URI} to a file, containing a {@link String}-representation of an
   *        expedition.
   *
   * @throws IOException if some I/O exception occurs when the file is read.
   */
  public FromFileExpeditionSupplier(URI inputFile) throws IOException {
    String fileContent = Files.readString(Path.of(inputFile));
    fromStringExpeditionSupplier = new FromStringExpeditionSupplier(fileContent);
  }
}
