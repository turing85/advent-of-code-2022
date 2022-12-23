package de.turing85.advent.of.code.tttt.day.nine;

import static com.google.common.truth.Truth.assertThat;

import de.turing85.advent.of.code.tttt.day.nine.supplier.impl.FromFileMovementCommandsSupplier;
import java.io.IOException;
import java.nio.file.Path;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("RopeMovementSimulator tests")
class RopeMovementSimulatorTest {
  public static final Path PERSONAL_INPUT = Path.of("src/test/resources/personalInput.txt");

  @Nested
  @DisplayName("simulateMovementAndCountCoordinatesVisitedByTail tests")
  class SimulateMovementAndCountCoordinatesVisitedByTailTest {
    @Test
    @DisplayName("correct count on common input")
    void correctCountOnCommonInput() throws IOException {
      // GIVEN
      Path input = Path.of("src/test/resources/commonInputOne.txt");

      // WHEN
      int actual = RopeMovementSimulator.simulateMovementAndCountCoordinatesVisitedByTail(
          new FromFileMovementCommandsSupplier(input).get());

      // THEN
      assertThat(actual).isEqualTo(13);
    }

    @Test
    @DisplayName("correct count on personal input")
    void correctCountOnPersonalInput() throws IOException {
      // GIVEN

      // WHEN
      int actual = RopeMovementSimulator.simulateMovementAndCountCoordinatesVisitedByTail(
          new FromFileMovementCommandsSupplier(PERSONAL_INPUT).get());

      // THEN
      assertThat(actual).isEqualTo(6_197);
    }
  }

  @Nested
  @DisplayName("simulateMovementOfNKnotsAndCountCoordinatesVisitedByTail tests")
  class SimulateMovementOfNKnotsAndCountCoordinatesVisitedByTailTest {
    @Test
    @DisplayName("correct count on common input")
    void correctCountOnCommonInput() throws IOException {
      // GIVEN
      Path input = Path.of("src/test/resources/commonInputTwo.txt");

      // WHEN
      int actual = RopeMovementSimulator.simulateMovementOfNKnotsAndCountCoordinatesVisitedByTail(
          new FromFileMovementCommandsSupplier(input).get(), 10);

      // THEN
      assertThat(actual).isEqualTo(36);
    }

    @Test
    @DisplayName("correct count on personal input")
    void correctCountOnPersonalInput() throws IOException {
      // GIVEN

      // WHEN
      int actual = RopeMovementSimulator.simulateMovementOfNKnotsAndCountCoordinatesVisitedByTail(
          new FromFileMovementCommandsSupplier(PERSONAL_INPUT).get(), 10);

      // THEN
      assertThat(actual).isEqualTo(2_562);
    }
  }
}
