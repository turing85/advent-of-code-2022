package de.turing85.advent.of.code.tttt.day.two.supplier.impl.second.colum.own.selection;

import de.turing85.advent.of.code.tttt.day.two.model.Selection;
import de.turing85.advent.of.code.tttt.day.two.supplier.impl.second.colum.SecondColumnConverter;

/**
 * Interprets the second colum as own selection.
 *
 * <p>Character {@code 'X'} denotes Rock, {@code 'Y'} denotes Paper, {@code 'Z'} denotes Scissors.
 */
public class SecondColumOwnSelectionConverter implements SecondColumnConverter {
  private static final SecondColumOwnSelectionConverter INSTANCE =
      new SecondColumOwnSelectionConverter();

  private SecondColumOwnSelectionConverter() {}

  /**
   * Instance-getter.
   *
   * @return the singleton-instance of this class.
   */
  public static SecondColumOwnSelectionConverter instance() {
    return INSTANCE;
  }

  @Override
  public Selection convert(Selection opponentSelection, Character ownChar) {
    return switch (ownChar) {
      case 'X' -> Selection.ROCK;
      case 'Y' -> Selection.PAPER;
      case 'Z' -> Selection.SCISSORS;
      default -> throw new IllegalStateException("Can never occur");
    };
  }
}
