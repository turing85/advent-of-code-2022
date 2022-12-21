package de.turing85.advent.of.code.tttt.day.two.supplier.impl;

import de.turing85.advent.of.code.tttt.day.two.model.Selection;
import java.util.function.Function;

/**
 * Converts the first column read from the input to the opponent's {@link Selection}.
 *
 * <p>
 * An {@code 'A'} represents Rock. A {@code 'B'} represents Paper. A {@code 'C'} represents
 * Scissors.
 */
public class OpponentCharacterToSelectionConverter implements Function<Character, Selection> {
  private static final OpponentCharacterToSelectionConverter INSTANCE =
      new OpponentCharacterToSelectionConverter();

  private OpponentCharacterToSelectionConverter() {}

  /**
   * Instance-getter.
   *
   * @return the singleton-instance of this class.
   */
  public static OpponentCharacterToSelectionConverter instance() {
    return INSTANCE;
  }

  @Override
  public Selection apply(Character character) {
    return convert(character);
  }

  /**
   * Converts the first column read from the input to the opponent's {@link Selection}.
   *
   * @param opponentChar the {@code char} that represents the opponent's selection.
   *
   * @return the opponent's {@link Selection}.
   */
  public Selection convert(Character opponentChar) {
    return switch (opponentChar) {
      case 'A' -> Selection.ROCK;
      case 'B' -> Selection.PAPER;
      case 'C' -> Selection.SCISSORS;
      default -> throw new IllegalStateException("Can never occur");
    };
  }
}
