package de.turing85.advent.of.code.tttt.day.two.supplier.impl.second.colum.game.outcome;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import de.turing85.advent.of.code.tttt.day.two.model.Selection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("SecondColumGameOutcomeConverter tests")
class SecondColumGameOutcomeConverterTest {
  public static final SecondColumGameOutcomeConverter UNDER_TEST =
      SecondColumGameOutcomeConverter.instance();

  @DisplayName("Converts 'X' to lose")
  @Test
  void xToLose() {
    // GIVEN
    Selection opponentSelection = Selection.ROCK;

    // WHEN
    Selection actual = UNDER_TEST.apply(opponentSelection, 'X');

    // THEN
    assertThat(actual).isEqualTo(opponentSelection.beats());
  }

  @DisplayName("Converts 'Y' to draw")
  @Test
  void yToDraw() {
    // GIVEN
    Selection opponentSelection = Selection.ROCK;

    // WHEN
    Selection actual = UNDER_TEST.apply(opponentSelection, 'Y');

    // THEN
    assertThat(actual).isEqualTo(opponentSelection);
  }

  @DisplayName("Converts 'Z' to win")
  @Test
  void zToWin() {
    // GIVEN
    Selection opponentSelection = Selection.ROCK;

    // WHEN
    Selection actual = UNDER_TEST.apply(opponentSelection, 'Z');

    // THEN
    assertThat(actual).isEqualTo(opponentSelection.beatenBy());
  }

  @DisplayName("Illegal input throws exception")
  @Test
  void illegalInputThrows() {
    // GIVEN

    // WHEN & THEN
    assertThrows(IllegalStateException.class, () -> UNDER_TEST.apply(null, 'N'));
  }
}
