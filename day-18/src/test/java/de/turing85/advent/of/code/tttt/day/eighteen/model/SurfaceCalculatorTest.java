package de.turing85.advent.of.code.tttt.day.eighteen.model;

import static com.google.common.truth.Truth.assertThat;

import de.turing85.advent.of.code.tttt.day.eighteen.supplier.PointsSupplier;
import de.turing85.advent.of.code.tttt.day.eighteen.supplier.impl.FromFilePointsSupplier;
import java.io.IOException;
import java.nio.file.Path;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("SurfaceCalculator tests")
class SurfaceCalculatorTest {
  private static final Path COMMON_INPUT = Path.of("src/test/resources/commonInput.txt");
  private static final Path PERSONAL_INPUT = Path.of("src/test/resources/personalInput.txt");

  @Nested
  @DisplayName("surfaceAreaIgnoreTrappedAirBubbles tests")
  class SurfaceAreaIgnoreTrappedAirBubblesTest {
    @DisplayName("correct result on common input")
    @Test
    void correctResultOnCommonInput() throws IOException {
      // GIVEN
      PointsSupplier pointsSupplier = new FromFilePointsSupplier(COMMON_INPUT);
      SurfaceCalculator underTest = new SurfaceCalculator(pointsSupplier.get());

      // WHEN
      long actual = underTest.surfaceAreaIgnoreTrappedAirBubbles();

      // THEN
      assertThat(actual).isEqualTo(64);
    }

    @DisplayName("correct result on personal input")
    @Test
    void correctResultOnPersonalInput() throws IOException {
      // GIVEN
      PointsSupplier pointsSupplier = new FromFilePointsSupplier(PERSONAL_INPUT);
      SurfaceCalculator underTest = new SurfaceCalculator(pointsSupplier.get());

      // WHEN
      long actual = underTest.surfaceAreaIgnoreTrappedAirBubbles();

      // THEN
      assertThat(actual).isEqualTo(3_496);
    }
  }

  @Nested
  @DisplayName("surfaceAreaConsiderTrappedAirBubbles tests")
  class SurfaceAreaConsiderTrappedAirBubblesTest {
    @DisplayName("correct result on common input")
    @Test
    void correctResultOnCommonInput() throws IOException {
      // GIVEN
      PointsSupplier pointsSupplier = new FromFilePointsSupplier(COMMON_INPUT);
      SurfaceCalculator underTest = new SurfaceCalculator(pointsSupplier.get());

      // WHEN
      long actual = underTest.surfaceAreaConsiderTrappedAirBubbles();

      // THEN
      assertThat(actual).isEqualTo(58);
    }

    @DisplayName("correct result on personal input")
    @Test
    void correctResultOnPersonalInput() throws IOException {
      // GIVEN
      PointsSupplier pointsSupplier = new FromFilePointsSupplier(PERSONAL_INPUT);
      SurfaceCalculator underTest = new SurfaceCalculator(pointsSupplier.get());

      // WHEN
      long actual = underTest.surfaceAreaConsiderTrappedAirBubbles();

      // THEN
      assertThat(actual).isEqualTo(2_064);
    }
  }
}
