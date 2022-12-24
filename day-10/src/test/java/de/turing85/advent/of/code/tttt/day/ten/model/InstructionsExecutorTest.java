package de.turing85.advent.of.code.tttt.day.ten.model;

import static com.google.common.truth.Truth.assertThat;

import de.turing85.advent.of.code.tttt.day.ten.supplier.impl.FromFileInstructionsWithParameterSupplier;
import java.io.IOException;
import java.nio.file.Path;
import java.util.function.BiFunction;
import java.util.function.IntPredicate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("InstructionsExecutor tests")
class InstructionsExecutorTest {
  private static final Path COMMON_INPUT = Path.of("src/test/resources/commonInput.txt");
  private static final Path PERSONAL_INPUT = Path.of("src/test/resources/personalInput.txt");

  @Nested
  @DisplayName("Signal strength tests")
  class SignalStrengthTest {
    public static final IntPredicate CYCLE_FILTER = cycle -> cycle % 40 == 20;

    public static final BiFunction<Integer, Integer, Integer> CYCLES_TIMES_X_REGISTER =
        (cycle, x) -> cycle * x;

    @DisplayName("correct result on common input")
    @Test
    void correctResultOnCommonInput() throws IOException {
      // GIVEN

      // WHEN
      int actual = InstructionsExecutor.executeFilterMapReduce(
          new FromFileInstructionsWithParameterSupplier(COMMON_INPUT).get(), CYCLE_FILTER,
          CYCLES_TIMES_X_REGISTER, 0, Integer::sum);

      // THEN
      assertThat(actual).isEqualTo(13_140);
    }

    @DisplayName("correct result on personal input")
    @Test
    void correctResultOnPersonalInput() throws IOException {
      // GIVEN

      // WHEN
      int actual = InstructionsExecutor.executeFilterMapReduce(
          new FromFileInstructionsWithParameterSupplier(PERSONAL_INPUT).get(), CYCLE_FILTER,
          CYCLES_TIMES_X_REGISTER, 0, Integer::sum);

      // THEN
      assertThat(actual).isEqualTo(14_560);
    }
  }

  @Nested
  @DisplayName("CRT tests")
  class CrtTest {
    public static final IntPredicate CYCLE_FILTER = cycle -> true;
    public static final BiFunction<Integer, Integer, String> IS_PIXEL_LIT =
        (cycle, x) -> (Math.abs(((cycle - 1) % 40) - x) <= 1 ? "#" : ".")
            + (cycle % 40 == 0 ? System.lineSeparator() : "");

    @DisplayName("correct result on common input")
    @Test
    void correctResultOnCommonInput() throws IOException {
      // GIVEN

      // WHEN
      String actual = InstructionsExecutor.executeFilterMapReduce(
          new FromFileInstructionsWithParameterSupplier(COMMON_INPUT).get(), CYCLE_FILTER,
          IS_PIXEL_LIT, "", String::concat);

      // THEN
      assertThat(actual).isEqualTo("""
          ##..##..##..##..##..##..##..##..##..##..
          ###...###...###...###...###...###...###.
          ####....####....####....####....####....
          #####.....#####.....#####.....#####.....
          ######......######......######......####
          #######.......#######.......#######.....
          """);
    }

    @DisplayName("correct result on personal input")
    @Test
    void correctResultOnPersonalInput() throws IOException {
      // GIVEN

      // WHEN
      String actual = InstructionsExecutor.executeFilterMapReduce(
          new FromFileInstructionsWithParameterSupplier(PERSONAL_INPUT).get(), CYCLE_FILTER,
          IS_PIXEL_LIT, "", String::concat);

      // THEN
      assertThat(actual).isEqualTo("""
          ####.#..#.###..#..#.####.###..#..#.####.
          #....#.#..#..#.#..#.#....#..#.#..#....#.
          ###..##...#..#.####.###..#..#.#..#...#..
          #....#.#..###..#..#.#....###..#..#..#...
          #....#.#..#.#..#..#.#....#....#..#.#....
          ####.#..#.#..#.#..#.####.#.....##..####.
          """);
    }
  }
}
