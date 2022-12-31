package de.turing85.advent.of.code.tttt.day.sixteen.model;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import de.turing85.advent.of.code.tttt.day.sixteen.supplier.ValveDescriptionsSupplier;
import de.turing85.advent.of.code.tttt.day.sixteen.supplier.impl.FromFileValveDescriptionsSupplier;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@DisplayName("ValveNetwork tests")
class ValveNetworkTest {
  private static final Path COMMON_INPUT = Path.of("src/test/resources/commonInput.txt");
  private static final Path PERSONAL_INPUT = Path.of("src/test/resources/personalInput.txt");

  @DisplayName("throws on missing valve description")
  @Test
  void throwsOnMissingValveDescription() {
    // GIVEN
    Set<ValveDescription> descriptions = Set.of(new ValveDescription("AA", 1, Set.of("BB")));

    // WHEN & THEN
    assertThrows(IllegalStateException.class, () -> new ValveNetwork(descriptions));
  }

  @DisplayName("throws on negative flow rate")
  @Test
  void throwsOnNegativeFlowRate() {
    // GIVEN
    Set<ValveDescription> descriptions =
        Set.of(
            new ValveDescription("AA", -1, Set.of("BB")),
            new ValveDescription("BB", 1, Set.of("AA")));

    // WHEN & THEN
    assertThrows(IllegalStateException.class, () -> new ValveNetwork(descriptions));
  }

  @Nested
  @DisplayName("releaseMaximumPressure with one actor tests")
  class ReleaseMaximumPressureWithOneActorTest {
    @DisplayName("correct result on common input")
    @Test
    void correctResultOnCommonInput() throws IOException {
      // GIVEN
      ValveDescriptionsSupplier valveDescriptionsSupplier =
          new FromFileValveDescriptionsSupplier(COMMON_INPUT);

      // WHEN
      int actual =
          new ValveNetwork(valveDescriptionsSupplier.get()).releaseMaximumPressure("AA", 30, 1);

      // THEN
      assertThat(actual).isEqualTo(1_651);
    }

    @DisplayName("correct result on personal input")
    @Test
    void correctResultOnPersonalInput() throws IOException {
      // GIVEN
      ValveDescriptionsSupplier valveDescriptionsSupplier =
          new FromFileValveDescriptionsSupplier(PERSONAL_INPUT);

      // WHEN
      int actual =
          new ValveNetwork(valveDescriptionsSupplier.get()).releaseMaximumPressure("AA", 30, 1);

      // THEN
      assertThat(actual).isEqualTo(1_947);
    }
  }

  @Nested
  @DisplayName("releaseMaximumPressure with two actors tests")
  class ReleaseMaximumPressureWithTwoActorsTest {
    @DisplayName("correct result on common input")
    @Test
    void correctResultOnCommonInput() throws IOException {
      // GIVEN
      ValveDescriptionsSupplier valveDescriptionsSupplier =
          new FromFileValveDescriptionsSupplier(COMMON_INPUT);

      // WHEN
      int actual =
          new ValveNetwork(valveDescriptionsSupplier.get()).releaseMaximumPressure("AA", 26, 2);

      // THEN
      assertThat(actual).isEqualTo(1707);
    }

    @Test
    @Tag("slow")
    @DisplayName("correct result on personal input")
    void correctResultOnPersonalInput() throws IOException {
      // GIVEN
      ValveDescriptionsSupplier valveDescriptionsSupplier =
          new FromFileValveDescriptionsSupplier(PERSONAL_INPUT);

      // WHEN
      int actual =
          new ValveNetwork(valveDescriptionsSupplier.get())
              .releaseMaximumPressure("AA", 26, 2, 1_000_000);

      // THEN
      assertThat(actual).isEqualTo(2_556);
    }
  }
}
