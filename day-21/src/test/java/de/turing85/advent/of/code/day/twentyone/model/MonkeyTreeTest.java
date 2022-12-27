package de.turing85.advent.of.code.day.twentyone.model;

import static com.google.common.truth.Truth.assertThat;

import de.turing85.advent.of.code.day.twentyone.model.parser.MonkeysParser;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("monkey tree tests")
class MonkeyTreeTest {
  private static final Path COMMON_INPUT = Path.of("src/test/resources/commonInput.txt");
  private static final Path PERSONAL_INPUT = Path.of("src/test/resources/personalInput.txt");

  @Nested
  @DisplayName("evaluate tests")
  class EvaluateTest {
    @DisplayName("correct result on common input")
    @Test
    void correctResultOnCommonInput() throws IOException {
      // GIVEN
      Map<String, Monkey> monkeys = new MonkeysParser(COMMON_INPUT).get();

      // WHEN
      long actual = MonkeyTree.evaluate(monkeys, "root");

      // THEN
      assertThat(actual).isEqualTo(152);
    }

    @DisplayName("correct result on personal input")
    @Test
    void correctResultOnPersonalInput() throws IOException {
      // GIVEN
      Map<String, Monkey> monkeys = new MonkeysParser(PERSONAL_INPUT).get();

      // WHEN
      long actual = MonkeyTree.evaluate(monkeys, "root");

      // THEN
      assertThat(actual).isEqualTo(152_479_825_094_094L);
    }
  }

  @Nested
  @DisplayName("solveFor tests")
  class SolveForTest {
    @DisplayName("correct result on common input")
    @Test
    void correctResultOnCommonInput() throws IOException {
      // GIVEN
      Map<String, Monkey> monkeys = new MonkeysParser(COMMON_INPUT).get();

      // WHEN
      long actual = MonkeyTree.solveFor(monkeys, "root", "humn");

      // THEN
      assertThat(actual).isEqualTo(301);
    }

    @DisplayName("correct result on personal input")
    @Test
    void correctResultOnPersonalInput() throws IOException {
      // GIVEN
      Map<String, Monkey> monkeys = new MonkeysParser(PERSONAL_INPUT).get();

      // WHEN
      long actual = MonkeyTree.solveFor(monkeys, "root", "humn");

      // THEN
      assertThat(actual).isEqualTo(3_360_561_285_172L);
    }
  }
}
