package de.turing85.advent.of.code.tttt.day.thirteen;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import de.turing85.advent.of.code.tttt.day.thirteen.model.Pair;
import de.turing85.advent.of.code.tttt.day.thirteen.parser.FromFileParser;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("Decoder tests")
class DecoderTest {
  private static final Path COMMON_INPUT = Path.of("src/test/resources/commonInput.txt");
  private static final Path PERSONAL_INPUT = Path.of("src/test/resources/personalInput.txt");

  @Test
  @DisplayName("throws on non-int and non-list input on left list")
  void throwsOnNonIntAndNonListInputOnLeftList() {
    // GIVEN
    List<Pair<Object, Object>> input = List.of(new Pair<>(List.of("Oh", "no"), 1));

    // WHEN & THEN
    assertThrows(IllegalArgumentException.class, () -> Decoder.pairsInRightOrder(input));
  }

  @Test
  @DisplayName("throws on non-int and non-list input on right list")
  void throwsOnNonIntAndNonListInputOnRightList() {
    // GIVEN
    List<Pair<Object, Object>> input = List.of(new Pair<>(List.of(1), "Oh no"));

    // WHEN & THEN
    assertThrows(IllegalArgumentException.class, () -> Decoder.pairsInRightOrder(input));
  }

  @Test
  @DisplayName("in right order")
  void inRightOrder() {
    // GIVEN
    List<Pair<Object, Object>> input =
        List.of(new Pair<>(List.of(1, 1, 3, 1, 1), List.of(1, 1, 5, 1, 1)),
            new Pair<>(List.of(List.of(1), List.of(2, 3, 4)), List.of(List.of(1), 4)),
            new Pair<>(List.of(9), List.of(List.of(8, 7, 6))),
            new Pair<>(List.of(List.of(4, 4), 4, 4), List.of(List.of(4, 4), 4, 4, 4)),
            new Pair<>(List.of(7, 7, 7, 7), List.of(7, 7, 7)), new Pair<>(List.of(), List.of(3)),
            new Pair<>(List.of(List.of(List.of())), List.of(List.of())),
            new Pair<>(List.of(1, List.of(2, List.of(3, List.of(4, List.of(5, 6, 7)))), 8, 9),
                List.of(1, List.of(2, List.of(3, List.of(4, List.of(5, 6, 0)))), 8, 9)),
            new Pair<>(List.of(2, 3, 4), 1));

    // WHEN
    Set<Integer> actual = Decoder.pairsInRightOrder(input);

    // THEN
    assertThat(actual).isEqualTo(Set.of(1, 2, 4, 6));
  }


  @Nested
  @DisplayName("pairsInRightOrder tests")
  class PairsInRightOrderTest {
    @Test
    @DisplayName("correct result on common input")
    void correctResultOnCommonInput() throws IOException {
      // GIVEN
      List<Pair<Object, Object>> input = FromFileParser.parse(COMMON_INPUT);

      // WHEN
      int actual = Decoder.pairsInRightOrder(input).stream().mapToInt(value -> value).sum();

      // THEN
      assertThat(actual).isEqualTo(13);
    }

    @Test
    @DisplayName("correct result on personal input")
    void correctResultOnPersonalInput() throws IOException {
      // GIVEN
      List<Pair<Object, Object>> input = FromFileParser.parse(PERSONAL_INPUT);

      // WHEN
      int actual = Decoder.pairsInRightOrder(input).stream().mapToInt(value -> value).sum();

      // THEN
      assertThat(actual).isEqualTo(6_623);
    }
  }

  @Nested
  @DisplayName("calculateDecoderKey tests")
  class CalculateDecoderKeyTest {
    @Test
    @DisplayName("correct result on common input")
    void correctResultOnCommonInput() throws IOException {
      // GIVEN
      List<Pair<Object, Object>> input = FromFileParser.parse(COMMON_INPUT);

      // WHEN
      int actual = Decoder.calculateDecoderKey(input);

      // THEN
      assertThat(actual).isEqualTo(140);
    }

    @Test
    @DisplayName("correct result on personal input")
    void correctResultOnPersonalInput() throws IOException {
      // GIVEN
      List<Pair<Object, Object>> input = FromFileParser.parse(PERSONAL_INPUT);

      // WHEN
      int actual = Decoder.calculateDecoderKey(input);

      // THEN
      assertThat(actual).isEqualTo(23_049);
    }
  }
}
