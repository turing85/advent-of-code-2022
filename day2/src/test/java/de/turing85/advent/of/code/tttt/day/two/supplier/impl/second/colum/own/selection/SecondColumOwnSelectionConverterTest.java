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

  @Test
  @DisplayName("Converts 'X' to Rock (\uD83E\uDEA8)")
  void xToRock() {
    // GIVEN

    // WHEN
    Selection actual = UNDER_TEST.apply(null, 'X');

    // THEN
    assertThat(actual).isEqualTo(Selection.ROCK);
  }

  @Test
  @DisplayName("Converts 'Y' to Paper (\uD83E\uDEA8)")
  void yToPaper() {
    // GIVEN

    // WHEN
    Selection actual = UNDER_TEST.apply(null, 'Y');

    // THEN
    assertThat(actual).isEqualTo(Selection.PAPER);
  }

  @Test
  @DisplayName("Converts 'Z' to Scissors (✂️)")
  void zToScissors() {
    // GIVEN

    // WHEN
    Selection actual = UNDER_TEST.apply(null, 'Z');

    // THEN
    assertThat(actual).isEqualTo(Selection.SCISSORS);
  }

  @Test
  @DisplayName("Illegal input throws exception")
  void illegalInputThrows() {
    // GIVEN

    // WHEN & THEN
    assertThrows(IllegalStateException.class, () -> UNDER_TEST.convert(Selection.ROCK, 'N'));
  }
}
