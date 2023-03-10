package de.turing85.advent.of.code.tttt.day.two.supplier.impl;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import de.turing85.advent.of.code.tttt.day.two.model.Selection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("OpponentCharacterToSelectionConverter tests")
class OpponentCharacterToSelectionConverterTest {
  public static final OpponentCharacterToSelectionConverter UNDER_TEST =
      OpponentCharacterToSelectionConverter.instance();

  @DisplayName("Converts 'A' to Rock (\uD83E\uDEA8)")
  @Test
  void aToRock() {
    // GIVEN

    // WHEN
    Selection actual = UNDER_TEST.apply('A');

    // THEN
    assertThat(actual).isEqualTo(Selection.ROCK);
  }

  @DisplayName("Converts 'B' to Paper (\uD83E\uDEA8)")
  @Test
  void bToPaper() {
    // GIVEN

    // WHEN
    Selection actual = UNDER_TEST.apply('B');

    // THEN
    assertThat(actual).isEqualTo(Selection.PAPER);
  }

  @DisplayName("Converts 'C' to Scissors (✂️)")
  @Test
  void cToScissors() {
    // GIVEN

    // WHEN
    Selection actual = UNDER_TEST.apply('C');

    // THEN
    assertThat(actual).isEqualTo(Selection.SCISSORS);
  }

  @DisplayName("Illegal input throws exception")
  @Test
  void illegalInputThrows() {
    // GIVEN

    // WHEN & THEN
    assertThrows(IllegalStateException.class, () -> UNDER_TEST.apply('N'));
  }
}
