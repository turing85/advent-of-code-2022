package de.turing85.advent.of.code.tttt.day.two.supplier.impl.second.colum.own.selection;

import de.turing85.advent.of.code.tttt.day.two.model.GameSelection;
import de.turing85.advent.of.code.tttt.day.two.supplier.GameSelectionsSupplier;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import lombok.experimental.Delegate;

/**
 * Reads a {@link String}-representation of {@link GameSelection}s from the file represented by a
 * {@link Path} and constructs the corresponding {@link java.util.Collection} of {@link
 * GameSelection}s from it.
 *
 * <p>Each line in the {@link String} represents a {@link GameSelection}, with the first character
 * {@code != \s+} is interpreted as the opponents choice {@code 'A'} = Rock, {@code 'B'} = Paper,
 * {@code 'C'} = Scissors) and the second character {@code != \s+} is interpreted as the own move (
 * {@code 'X'} = Rock, {@code 'Y'} = Paper, {@code 'Z'} = Scissors).
 */
public class FromFileGameSelectionsSupplier extends GameSelectionsSupplier {
  @Delegate private FromStringGameSelectionsSupplier fromStringGameSelectionsSupplier;

  /**
   * Given a {@link Path} to a file holding a {@link String}-representation of {@link
   * GameSelection}s, constructs the corresponding {@link java.util.Collection} of {@link
   * GameSelection}s from it.
   *
   * @param inputFile a {@link Path} to a file holding a{@link String}-representation of the {@link
   *     GameSelection}s.
   * @throws IOException if some I/O exception occurs when the file is read.
   */
  public FromFileGameSelectionsSupplier(Path inputFile) throws IOException {
    super(Files.readString(inputFile));
  }

  @Override
  protected void initializeFromString(String gameSelectionsAsString) {
    fromStringGameSelectionsSupplier = new FromStringGameSelectionsSupplier(gameSelectionsAsString);
  }
}
