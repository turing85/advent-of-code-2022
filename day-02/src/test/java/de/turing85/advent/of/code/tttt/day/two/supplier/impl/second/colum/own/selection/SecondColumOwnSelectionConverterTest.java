package de.turing85.advent.of.code.tttt.day.two.supplier.impl.second.colum.own.selection;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import de.turing85.advent.of.code.tttt.day.two.model.Selection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("SecondColumOwnSelectionConverter tests")
class SecondColumOwnSelectionConverterTest {
  private static final SecondColumOwnSelectionConverter UNDER_TEST =
      SecondColumOwnSelectionConverter.instance();

  @DisplayName("Converts 'X' to Rock (\uD83E\uDEA8)")
  @Test
  void xToRock() {
    // GIVEN

    // WHEN
    Selection actual = UNDER_TEST.apply(null, 'X');

    // THEN
    assertThat(actual).isEqualTo(Selection.ROCK);
  }

  @DisplayName("Converts 'Y' to Paper (\uD83E\uDEA8)")
  @Test
  void yToPaper() {
    // GIVEN

    // WHEN
    Selection actual = UNDER_TEST.apply(null, 'Y');

    // THEN
    assertThat(actual).isEqualTo(Selection.PAPER);
  }

  @DisplayName("Converts 'Z' to Scissors (✂️)")
  @Test
  void zToScissors() {
    // GIVEN

    // WHEN
    Selection actual = UNDER_TEST.apply(null, 'Z');

    // THEN
    assertThat(actual).isEqualTo(Selection.SCISSORS);
  }

  @DisplayName("Illegal input throws exception")
  @Test
  void illegalInputThrows() {
    // GIVEN

    // WHEN & THEN
    assertThrows(IllegalStateException.class, () -> UNDER_TEST.convert(Selection.ROCK, 'N'));
  }
}
