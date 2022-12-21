package de.turing85.advent.of.code.tttt.day.twelve.model;

import static com.google.common.truth.Truth.assertThat;

import de.turing85.advent.of.code.tttt.day.twelve.model.impl.FromFileBoard;
import java.io.IOException;
import java.nio.file.Path;
import java.util.function.Predicate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("BoardSolver tests")
class BoardSolverTest {
  private static final Path COMMON_INPUT = Path.of("src/test/resources/commonInput.txt");
  private static final Path PERSONAL_INPUT = Path.of("src/test/resources/personalInput.txt");

  @Nested
  @DisplayName("from start to finish tests")
  class FromStartToFinish {
    public static final Predicate<Field> START_FIELD_CONDITION = field -> field.symbol() == 'S';

    @Test
    @DisplayName("correct result on common input")
    void correctResultOnCommonInput() throws IOException {
      // GIVEN
      Board board = new FromFileBoard(COMMON_INPUT);

      // WHEN
      int actual = BoardSolver.findShortestPathLength(board, START_FIELD_CONDITION, board.end());

      // THEN
      assertThat(actual).isEqualTo(31);
    }

    @Test
    @DisplayName("correct result on personal input")
    void correctResultOnPersonalInput() throws IOException {
      // GIVEN
      Board board = new FromFileBoard(PERSONAL_INPUT);

      // WHEN
      int actual = BoardSolver.findShortestPathLength(board, START_FIELD_CONDITION, board.end());

      // THEN
      assertThat(actual).isEqualTo(484);
    }
  }

  @Nested
  @DisplayName("from any 0-height field to finish tests")
  class FromAnyZeroHeightFieldToFinish {
    public static final Predicate<Field> START_FIELD_CONDITION = field -> field.height() == 0;

    @Test
    @DisplayName("correct result on common input")
    void correctResultOnCommonInput() throws IOException {
      // GIVEN
      Board board = new FromFileBoard(COMMON_INPUT);

      // WHEN
      int actual = BoardSolver.findShortestPathLength(board, START_FIELD_CONDITION, board.end());

      // THEN
      assertThat(actual).isEqualTo(29);
    }

    @Test
    @DisplayName("correct result on personal input")
    void correctResultOnPersonalInput() throws IOException {
      // GIVEN
      Board board = new FromFileBoard(PERSONAL_INPUT);

      // WHEN
      int actual = BoardSolver.findShortestPathLength(board, START_FIELD_CONDITION, board.end());

      // THEN
      assertThat(actual).isEqualTo(478);
    }
  }
}
