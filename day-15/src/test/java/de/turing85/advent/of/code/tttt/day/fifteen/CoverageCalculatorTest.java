package de.turing85.advent.of.code.tttt.day.fifteen;

import static com.google.common.truth.Truth.assertThat;

import de.turing85.advent.of.code.tttt.day.fifteen.supplier.impl.FromFilePointPairsSupplier;
import java.io.IOException;
import java.nio.file.Path;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("CoverageCalculator tests")
class CoverageCalculatorTest {
  private static final Path COMMON_INPUT = Path.of("src/test/resources/commonInput.txt");
  private static final Path PERSONAL_INPUT = Path.of("src/test/resources/personalInput.txt");

  @Nested
  @DisplayName("getCoverageOnYCoordinate tests")
  class GetCoverageOnYCoordinateTest {
    @DisplayName("correct result on common input")
    @Test
    void correctResultOnCommonInput() throws IOException {
      // GIVEN

      // WHEN
      long actual =
          CoverageCalculator.getCoverageOnYCoordinate(
              new FromFilePointPairsSupplier(COMMON_INPUT).get(), 10);

      // THEN
      assertThat(actual).isEqualTo(26L);
    }

    @DisplayName("correct result on personal input")
    @Test
    void correctResultOnPersonalInput() throws IOException {
      // GIVEN

      // WHEN
      long actual =
          CoverageCalculator.getCoverageOnYCoordinate(
              new FromFilePointPairsSupplier(PERSONAL_INPUT).get(), 2_000_000);

      // THEN
      assertThat(actual).isEqualTo(5_508_234L);
    }
  }

  @Nested
  @DisplayName("findTuningFrequency tests")
  class FindTuningFrequency {
    @DisplayName("correct result on common input")
    @Test
    void correctResultOnCommonInput() throws IOException {
      // GIVEN

      // WHEN
      long actual =
          CoverageCalculator.findTuningFrequency(
              new FromFilePointPairsSupplier(COMMON_INPUT).get(), 20);

      // THEN
      assertThat(actual).isEqualTo(56_000_011L);
    }

    @DisplayName("correct result on personal input")
    @Test
    void correctResultOnPersonalInput() throws IOException {
      // GIVEN

      // WHEN
      long actual =
          CoverageCalculator.findTuningFrequency(
              new FromFilePointPairsSupplier(PERSONAL_INPUT).get(), 4_000_000);

      // THEN
      assertThat(actual).isEqualTo(10_457_634_860_779L);
    }
  }
}
