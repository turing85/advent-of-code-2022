package de.turing85.advent.of.code.tttt.day.two.supplier.impl.second.colum.game.outcome;

import de.turing85.advent.of.code.tttt.day.two.model.Selection;
import de.turing85.advent.of.code.tttt.day.two.supplier.impl.second.colum.SecondColumnConverter;

/**
 * Interprets the second colum as own selection.
 *
 * <p>Character {@code 'X'} denotes Lose, {@code 'Y'} denotes Draw, {@code 'Z'} denotes Win.
 */
public class SecondColumGameOutcomeConverter implements SecondColumnConverter {
  private static final SecondColumGameOutcomeConverter INSTANCE =
      new SecondColumGameOutcomeConverter();

  private SecondColumGameOutcomeConverter() {}

  /**
   * Instance-getter.
   *
   * @return the singleton-instance of this class.
   */
  public static SecondColumGameOutcomeConverter instance() {
    return INSTANCE;
  }

  @Override
  public Selection apply(Selection opponentSelection, Character ownChar) {
    return convert(opponentSelection, ownChar);
  }

  public Selection convert(Selection opponentSelection, Character ownChar) {
    return switch (ownChar) {
      case 'X' -> opponentSelection.beats();
      case 'Y' -> opponentSelection;
      case 'Z' -> opponentSelection.beatenBy();
      default -> throw new IllegalStateException("Can never occur");
    };
  }
}
