package de.turing85.adventure.of.code.tttt.day.seventeen.model;

import static com.google.common.truth.Truth.assertThat;

import de.turing85.adventure.of.code.tttt.day.seventeen.supplier.WindDirectionsSupplier;
import de.turing85.adventure.of.code.tttt.day.seventeen.supplier.impl.FromFileWindDirectionsSupplier;
import java.io.IOException;
import java.nio.file.Path;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("TetrisInTheWind tests")
class TetrisInTheWindTest {
  private static final Path COMMON_INPUT = Path.of("src/test/resources/commonInput.txt");
  private static final Path PERSONAL_INPUT = Path.of("src/test/resources/personalInput.txt");

  @Nested
  @DisplayName("Let 2022 rocks fall")
  class Let2022RocksFallTest {
    @DisplayName("correct result on common input")
    @Test
    void correctResultOnCommonInput() throws IOException {
      // GIVEN
      WindDirectionsSupplier windDirectionsSupplier =
          new FromFileWindDirectionsSupplier(COMMON_INPUT);
      TetrisInTheWind underTest =
          new TetrisInTheWind(windDirectionsSupplier.get(), 7, 2022, new Point(2, 4));

      // WHEN
      long actual = underTest.letItFall();

      // THEN
      assertThat(actual).isEqualTo(3_068);
    }

    @DisplayName("correct result on personal input")
    @Test
    void correctResultOnPersonalInput() throws IOException {
      // GIVEN
      WindDirectionsSupplier windDirectionsSupplier =
          new FromFileWindDirectionsSupplier(PERSONAL_INPUT);
      TetrisInTheWind underTest =
          new TetrisInTheWind(windDirectionsSupplier.get(), 7, 2022, new Point(2, 4));

      // WHEN
      long actual = underTest.letItFall();

      // THEN
      assertThat(actual).isEqualTo(3_179);
    }
  }

  @Nested
  @DisplayName("Let 1 000 000 000 000 rocks fall")
  class Let1000000000000RocksFallTest {
    @DisplayName("correct result on common input")
    @Test
    void correctResultOnCommonInput() throws IOException {
      // GIVEN
      WindDirectionsSupplier windDirectionsSupplier =
          new FromFileWindDirectionsSupplier(COMMON_INPUT);
      TetrisInTheWind underTest =
          new TetrisInTheWind(windDirectionsSupplier.get(), 7, 1_000_000_000_000L, new Point(2, 4));

      // WHEN
      long actual = underTest.letItFall();

      // THEN
      assertThat(actual).isEqualTo(1514285714288L);
    }

    @DisplayName("correct result on personal input")
    @Test
    void correctResultOnPersonalInput() throws IOException {
      // GIVEN
      WindDirectionsSupplier windDirectionsSupplier =
          new FromFileWindDirectionsSupplier(PERSONAL_INPUT);
      TetrisInTheWind underTest =
          new TetrisInTheWind(windDirectionsSupplier.get(), 7, 1_000_000_000_000L, new Point(2, 4));

      // WHEN
      long actual = underTest.letItFall();

      // THEN
      assertThat(actual).isEqualTo(1_567_723_342_929L);
    }
  }
}
