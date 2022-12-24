package de.turing85.advent.of.code.tttt.day.two;

import static com.google.common.truth.Truth.assertThat;

import de.turing85.advent.of.code.tttt.day.two.model.GameSelection;
import de.turing85.advent.of.code.tttt.day.two.model.Selection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("Selection tests")
class ScoreFunctionTest {
  @Nested
  @DisplayName("scoring test")
  class ScoringTest {
    @Nested
    @DisplayName("Rock (\uD83E\uDEA8, own) against...")
    class RockAgainst {
      private static final Selection OWN_SELECTION = Selection.ROCK;

      @DisplayName("... Rock (\uD83E\uDEA8, opponent)")
      @Test
      void againstRock() {
        // GIVEN
        int expected = 4;

        // WHEN
        int actual =
            ScoreFunction.instance().applyAsInt(new GameSelection(OWN_SELECTION, Selection.ROCK));

        // THEN
        assertThat(actual).isEqualTo(expected);
      }

      @DisplayName("... Paper (\uD83D\uDCC3, opponent)")
      @Test
      void againstPaper() {
        // GIVEN
        int expected = 1;

        // WHEN
        int actual =
            ScoreFunction.instance().applyAsInt(new GameSelection(OWN_SELECTION, Selection.PAPER));

        // THEN
        assertThat(actual).isEqualTo(expected);
      }

      @DisplayName("... Scissors (✂️, opponent)")
      @Test
      void againstScissors() {
        // GIVEN
        int expected = 7;

        // WHEN
        int actual = ScoreFunction.instance()
            .applyAsInt(new GameSelection(OWN_SELECTION, Selection.SCISSORS));

        // THEN
        assertThat(actual).isEqualTo(expected);
      }
    }

    @Nested
    @DisplayName("Paper (\uD83D\uDCC3, own) against...")
    class PaperAgainst {
      private static final Selection OWN_SELECTION = Selection.PAPER;

      @DisplayName("... Rock (\uD83E\uDEA8, opponent)")
      @Test
      void againstRock() {
        // GIVEN
        int expected = 8;

        // WHEN
        int actual =
            ScoreFunction.instance().applyAsInt(new GameSelection(OWN_SELECTION, Selection.ROCK));

        // THEN
        assertThat(actual).isEqualTo(expected);
      }

      @DisplayName("... Paper (\uD83D\uDCC3, opponent)")
      @Test
      void againstPaper() {
        // GIVEN
        int expected = 5;

        // WHEN
        int actual =
            ScoreFunction.instance().applyAsInt(new GameSelection(OWN_SELECTION, Selection.PAPER));

        // THEN
        assertThat(actual).isEqualTo(expected);
      }

      @DisplayName("... Scissors (✂️, opponent)")
      @Test
      void againstScissors() {
        // GIVEN
        int expected = 2;

        // WHEN
        int actual = ScoreFunction.instance()
            .applyAsInt(new GameSelection(OWN_SELECTION, Selection.SCISSORS));

        // THEN
        assertThat(actual).isEqualTo(expected);
      }
    }

    @Nested
    @DisplayName("Scissors (✂️, own) against...")
    class ScissorsAgainst {
      private static final Selection OWN_SELECTION = Selection.SCISSORS;

      @DisplayName("... Rock (\uD83E\uDEA8, opponent)")
      @Test
      void againstRock() {
        // GIVEN
        int expected = 3;

        // WHEN
        int actual =
            ScoreFunction.instance().applyAsInt(new GameSelection(OWN_SELECTION, Selection.ROCK));

        // THEN
        assertThat(actual).isEqualTo(expected);
      }

      @DisplayName("... Paper (\uD83D\uDCC3, opponent)")
      @Test
      void againstPaper() {
        // GIVEN
        int expected = 9;

        // WHEN
        int actual =
            ScoreFunction.instance().applyAsInt(new GameSelection(OWN_SELECTION, Selection.PAPER));

        // THEN
        assertThat(actual).isEqualTo(expected);
      }

      @DisplayName("... Scissors (✂️, opponent)")
      @Test
      void againstScissors() {
        // GIVEN
        int expected = 6;

        // WHEN
        int actual = ScoreFunction.instance()
            .applyAsInt(new GameSelection(OWN_SELECTION, Selection.SCISSORS));

        // THEN
        assertThat(actual).isEqualTo(expected);
      }
    }
  }
}
