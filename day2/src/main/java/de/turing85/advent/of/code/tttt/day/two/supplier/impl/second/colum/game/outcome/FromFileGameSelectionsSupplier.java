package de.turing85.advent.of.code.tttt.day.two.supplier.impl.second.colum.game.outcome;

import de.turing85.advent.of.code.tttt.day.two.model.GameSelection;
import de.turing85.advent.of.code.tttt.day.two.supplier.GameSelectionsSupplier;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;

/**
 * Reads a {@link String}-representation of {@link GameSelection}s from the file represented by an
 * {@link URI} and constructs the corresponding {@link Collection} of {@link GameSelection}s from
 * it.
 *
 * <p>
 * Each line in the {@link String} represents a {@link GameSelection}, with the first character
 * {@code != \s+} is interpreted as the opponents choice {@code 'A'} = Rock, {@code 'B'} = Paper,
 * {@code 'C'} = Scissors) and the second character {@code != \s+} is interpreted as the outcome of
 * the game ({@code 'X'} = lose, {@code 'Y'} = draw, {@code 'Z'} = win).
 */
public class FromFileGameSelectionsSupplier extends GameSelectionsSupplier {
  private FromStringGameSelectionsSupplier fromStringGameSelectionsSupplier;

  /**
   * Given a {@link URI} to a file holding a {@link String}-representation of
   * {@link GameSelection}s, constructs the corresponding {@link Collection} of
   * {@link GameSelection}s from it.
   *
   * @param inputFile a {@link URI} to a file holding a{@link String}-representation of the
   *        {@link GameSelection}s.
   *
   * @throws IOException if some I/O exception occurs when the file is read.
   */
  public FromFileGameSelectionsSupplier(URI inputFile) throws IOException {
    super(Files.readString(Path.of(inputFile)));
  }

  @Override
  protected void initializeFromString(String gameSelectionsAsString) {
    fromStringGameSelectionsSupplier = new FromStringGameSelectionsSupplier(gameSelectionsAsString);
  }

  @Override
  public Collection<GameSelection> gameSelections() {
    return fromStringGameSelectionsSupplier.gameSelections();
  }
}
