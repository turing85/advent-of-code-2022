package de.turing85.advent.of.code.tttt.day.twentyfour.model;

import static com.google.common.truth.Truth.assertThat;

import de.turing85.advent.of.code.tttt.day.twentyfour.supplier.BlizzardRoutingConfiguration;
import de.turing85.advent.of.code.tttt.day.twentyfour.supplier.impl.FromFileBlizzardRoutingConfigSupplier;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("BlizzardPathFinder tests")
class BlizzardPathFinderTest {
  private static final Path COMMON_INPUT = Path.of("src/test/resources/commonInput.txt");
  private static final Path PERSONAL_INPUT = Path.of("src/test/resources/personalInput.txt");

  @Nested
  @DisplayName("Direct path tests")
  class DirectPathTest {
    @DisplayName("correct result on common input")
    @Test
    void correctResultOnCommonInput() throws IOException {
      // GIVEN
      BlizzardRoutingConfiguration configuration =
          new FromFileBlizzardRoutingConfigSupplier(COMMON_INPUT).get();

      // WHEN
      int actual =
          new BlizzardPathFinder()
              .shortestPathLength(
                  configuration.initialBlizzardMap(), configuration.start(), configuration.end());

      // THEN
      assertThat(actual).isEqualTo(18);
    }

    @DisplayName("correct result on personal input")
    @Test
    void correctResultOnPersonalInput() throws IOException {
      // GIVEN
      BlizzardRoutingConfiguration configuration =
          new FromFileBlizzardRoutingConfigSupplier(PERSONAL_INPUT).get();

      // WHEN
      int actual =
          new BlizzardPathFinder()
              .shortestPathLength(
                  configuration.initialBlizzardMap(), configuration.start(), configuration.end());

      // THEN
      assertThat(actual).isEqualTo(255);
    }
  }

  @Nested
  @DisplayName("Multi goal path tests")
  class MultiGoalPathTest {
    @DisplayName("correct result on common input")
    @Test
    void correctResultOnCommonInput() throws IOException {
      // GIVEN
      BlizzardRoutingConfiguration configuration =
          new FromFileBlizzardRoutingConfigSupplier(COMMON_INPUT).get();

      // WHEN
      int actual =
          new BlizzardPathFinder()
              .shortestPathLength(
                  configuration.initialBlizzardMap(),
                  configuration.start(),
                  List.of(configuration.end(), configuration.start(), configuration.end()));

      // THEN
      assertThat(actual).isEqualTo(54);
    }

    @DisplayName("correct result on personal input")
    @Test
    void correctResultOnPersonalInput() throws IOException {
      // GIVEN
      BlizzardRoutingConfiguration configuration =
          new FromFileBlizzardRoutingConfigSupplier(PERSONAL_INPUT).get();

      // WHEN
      int actual =
          new BlizzardPathFinder()
              .shortestPathLength(
                  configuration.initialBlizzardMap(),
                  configuration.start(),
                  List.of(configuration.end(), configuration.start(), configuration.end()));

      // THEN
      assertThat(actual).isEqualTo(809);
    }
  }
}
