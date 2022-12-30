package de.turing85.advent.of.code.tttt.day.twentythree.model;

import static com.google.common.truth.Truth.assertThat;

import de.turing85.advent.of.code.tttt.day.twentythree.supplier.impl.FromFileElvesSupplier;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Set;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("ElfMovementSimulator tests")
class ElfMovementSimulatorTest {
  private static final Path COMMON_INPUT = Path.of("src/test/resources/commonInput.txt");
  private static final Path PERSONAL_INPUT = Path.of("src/test/resources/personalInput.txt");

  @Nested
  @DisplayName("simulate 10 steps")
  class SimulateTenStepsTest {
    @DisplayName("correct result on common input")
    @Test
    void correctResultOnCommonInput() throws IOException {
      // GIVEN
      Set<Elf> initialState = new FromFileElvesSupplier(COMMON_INPUT).get();

      // WHEN
      Set<Elf> actual = new ElfMovementSimulator(initialState).simulate(10);

      // THEN
      Set<Point> actualPoints = actual.stream().map(Elf::position).collect(Collectors.toSet());
      int maxX = actualPoints.stream().mapToInt(Point::x).max().orElse(Integer.MIN_VALUE);
      int minX = actualPoints.stream().mapToInt(Point::x).min().orElse(Integer.MAX_VALUE);
      int maxY = actualPoints.stream().mapToInt(Point::y).max().orElse(Integer.MIN_VALUE);
      int minY = actualPoints.stream().mapToInt(Point::y).min().orElse(Integer.MAX_VALUE);
      int freeFields = (maxX - minX + 1) * (maxY - minY + 1) - actual.size();

      assertThat(freeFields).isEqualTo(110);
    }

    @DisplayName("correct result on personal input")
    @Test
    void correctResultOnPersonalInput() throws IOException {
      // GIVEN
      Set<Elf> initialState = new FromFileElvesSupplier(PERSONAL_INPUT).get();

      // WHEN
      Set<Elf> actual = new ElfMovementSimulator(initialState).simulate(10);

      // THEN
      Set<Point> actualPoints = actual.stream().map(Elf::position).collect(Collectors.toSet());
      int maxX = actualPoints.stream().mapToInt(Point::x).max().orElse(Integer.MIN_VALUE);
      int minX = actualPoints.stream().mapToInt(Point::x).min().orElse(Integer.MAX_VALUE);
      int maxY = actualPoints.stream().mapToInt(Point::y).max().orElse(Integer.MIN_VALUE);
      int minY = actualPoints.stream().mapToInt(Point::y).min().orElse(Integer.MAX_VALUE);
      int freeFields = (maxX - minX + 1) * (maxY - minY + 1) - actual.size();

      assertThat(freeFields).isEqualTo(4_123);
    }
  }

  @Nested
  @DisplayName("simulate until stabilization")
  class SimulateUntilStabilizationTest {
    @DisplayName("correct result on common input")
    @Test
    void correctResultOnCommonInput() throws IOException {
      // GIVEN
      Set<Elf> initialState = new FromFileElvesSupplier(COMMON_INPUT).get();

      // WHEN
      int actual = new ElfMovementSimulator(initialState).simulateUntilStableStateIsReached();

      // THEN
      assertThat(actual).isEqualTo(20);
    }

    @DisplayName("correct result on personal input")
    @Test
    void correctResultOnPersonalInput() throws IOException {
      // GIVEN
      Set<Elf> initialState = new FromFileElvesSupplier(PERSONAL_INPUT).get();

      // WHEN
      int actual = new ElfMovementSimulator(initialState).simulateUntilStableStateIsReached();

      // THEN
      assertThat(actual).isEqualTo(1_029);
    }
  }
}
