package de.turing85.advent.of.code.tttt.day.two.supplier.impl.second.colum;

import de.turing85.advent.of.code.tttt.day.two.model.Selection;
import java.util.function.BiFunction;

/**
 * Converts the second colum to our own move.
 *
 * <p>
 * The interpretation of the second column may depend on the opponent's {@link Selection}, hence it
 * is passed in as argument as well.
 */
public interface SecondColumnConverter extends BiFunction<Selection, Character, Selection> {
  @Override
  default Selection apply(Selection opponentSelection, Character myChar) {
    return convert(opponentSelection, myChar);
  }

  /**
   * Converts the opponent's {@link Selection} and the {@link Character} read in the 2nd column to
   * our own {@link Selection}.
   *
   * @param opponentSelection the opponent's {@link Selection}
   * @param ownChar own character, i.e. character in the 2nd colum
   * @return own {@link Selection}
   */
  Selection convert(Selection opponentSelection, Character ownChar);
}
