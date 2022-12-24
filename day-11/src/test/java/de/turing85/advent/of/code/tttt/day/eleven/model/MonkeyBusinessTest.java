package de.turing85.advent.of.code.tttt.day.eleven.model;

import static com.google.common.truth.Truth.assertThat;

import de.turing85.advent.of.code.tttt.day.eleven.parser.MonkeysParser;
import java.io.IOException;
import java.nio.file.Path;
import java.util.function.LongUnaryOperator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("MonkeyBusiness tests")
class MonkeyBusinessTest {
  private static final Path COMMON_INPUT = Path.of("src/test/resources/commonInput.txt");
  private static final Path PERSONAL_INPUT = Path.of("src/test/resources/personalInput.txt");

  @Nested
  @DisplayName("playNRoundsAndCalculateTopMMonkeyBusiness test with stress modifier")
  class PlayNRoundsAndCalculateTopMMonkeyBusinessTestWithStressModifier {
    public static final int ROUNDS = 20;
    public static final LongUnaryOperator STRESS_MODIFIER = value -> value / 3;

    @DisplayName("correct result on common input")
    @Test
    void correctResultOnCommonInput() throws IOException {
      // GIVEN

      // WHEN
      long actual = new MonkeyBusiness(MonkeysParser.parse(COMMON_INPUT))
          .playNRoundsAndCalculateTopMMonkeyBusiness(ROUNDS, 2, STRESS_MODIFIER);

      // THEN
      assertThat(actual).isEqualTo(10_605L);
    }

    @DisplayName("correct result on personal input")
    @Test
    void correctResultOnPersonalInput() throws IOException {
      // GIVEN

      // WHEN
      long actual = new MonkeyBusiness(MonkeysParser.parse(PERSONAL_INPUT))
          .playNRoundsAndCalculateTopMMonkeyBusiness(ROUNDS, 2, STRESS_MODIFIER);

      // THEN
      assertThat(actual).isEqualTo(118_674L);
    }
  }

  @Nested
  @DisplayName("playNRoundsAndCalculateTopMMonkeyBusiness test without stress modifier")
  class PlayNRoundsAndCalculateTopMMonkeyBusinessTestWithoutStressModifier {
    public static final int ROUNDS = 10_000;
    public static final LongUnaryOperator NO_STRESS_MODIFIER = value -> value;

    @DisplayName("correct result on common input")
    @Test
    void correctResultOnCommonInput() throws IOException {
      // GIVEN

      // WHEN
      long actual = new MonkeyBusiness(MonkeysParser.parse(COMMON_INPUT))
          .playNRoundsAndCalculateTopMMonkeyBusiness(ROUNDS, 2, NO_STRESS_MODIFIER);

      // THEN
      assertThat(actual).isEqualTo(2_713_310_158L);
    }

    @DisplayName("correct result on personal input")
    @Test
    void correctResultOnPersonalInput() throws IOException {
      // GIVEN

      // WHEN
      long actual = new MonkeyBusiness(MonkeysParser.parse(PERSONAL_INPUT))
          .playNRoundsAndCalculateTopMMonkeyBusiness(ROUNDS, 2, NO_STRESS_MODIFIER);

      // THEN
      assertThat(actual).isEqualTo(32_333_418_600L);
    }
  }
}
