package de.turing85.advent.of.code.tttt.day.two.model;

import static com.google.common.truth.Truth.assertThat;

import java.util.Random;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Game Selection test")
class GameSelectionTest {
  @DisplayName("own and opponent selection are as expected")
  @Test
  void ownAndOpponentSelectionAreAsExpected() {
    // GIVEN
    Random random = new Random();
    Selection[] selectionValues = Selection.values();
    Selection expectedOwn = selectionValues[random.nextInt(selectionValues.length)];
    Selection expectedOpponent = selectionValues[random.nextInt(selectionValues.length)];

    // WHEN
    GameSelection actual = new GameSelection(expectedOwn, expectedOpponent);

    // THEN
    assertThat(actual.ownSelection()).isEqualTo(expectedOwn);
    assertThat(actual.opponentSelection()).isEqualTo(expectedOpponent);
  }
}
