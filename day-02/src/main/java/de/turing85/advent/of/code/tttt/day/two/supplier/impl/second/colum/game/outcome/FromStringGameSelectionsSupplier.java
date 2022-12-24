package de.turing85.advent.of.code.tttt.day.two.supplier.impl.second.colum.game.outcome;

import de.turing85.advent.of.code.tttt.day.two.model.GameSelection;
import de.turing85.advent.of.code.tttt.day.two.model.Selection;
import de.turing85.advent.of.code.tttt.day.two.supplier.GameSelectionsSupplier;
import de.turing85.advent.of.code.tttt.day.two.supplier.impl.OpponentCharacterToSelectionConverter;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Reads a {@link String}-representation of {@link GameSelection}s and constructs the corresponding
 * {@link Collection} of {@link GameSelection}s from it.
 *
 * <p>
 * Each line in the {@link String} represents a {@link GameSelection}, with the first character
 * {@code != \s+} is interpreted as the opponents choice {@code 'A'} = Rock, {@code 'B'} = Paper,
 * {@code 'C'} = Scissors) and the second character {@code != \s+} is interpreted as the outcome of
 * the game ({@code 'X'} = lose, {@code 'Y'} = draw, {@code 'Z'} = win).
 */
public class FromStringGameSelectionsSupplier extends GameSelectionsSupplier {
  private static final Pattern SELECTION_EXTRACTOR =
      Pattern.compile("^\\s*(?<opponent>[A-C])\\s*(?<own>[X-Z])\\s*$");

  /**
   * Given a {@link String}-representation of {@link GameSelection}s, constructs the corresponding
   * {@link Collection} of {@link GameSelection}s from it.
   *
   * @param gameSelectionsAsString a {@link String}-representation of the {@link GameSelection}s.
   */
  public FromStringGameSelectionsSupplier(String gameSelectionsAsString) {
    super(gameSelectionsAsString);
  }

  @Override
  protected void initializeFromString(String gameSelectionsAsString) {
    for (String gameSelectionLine : gameSelectionsAsString.split(System.lineSeparator())) {
      Matcher matcher = SELECTION_EXTRACTOR.matcher(gameSelectionLine);
      if (!matcher.matches()) {
        throw new IllegalArgumentException(
            "line [%s]: does not match expected format".formatted(gameSelectionLine));
      }
      Selection opponentSelection = OpponentCharacterToSelectionConverter.instance()
          .apply(matcher.group("opponent").charAt(0));
      Selection ownSelection = SecondColumGameOutcomeConverter.instance().apply(opponentSelection,
          matcher.group("own").charAt(0));
      gameSelections.add(new GameSelection(ownSelection, opponentSelection));
    }
  }

  @Override
  public Collection<GameSelection> gameSelections() {
    return gameSelections;
  }
}
