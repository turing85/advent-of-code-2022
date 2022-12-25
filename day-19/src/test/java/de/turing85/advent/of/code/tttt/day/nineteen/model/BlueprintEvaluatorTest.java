package de.turing85.advent.of.code.tttt.day.nineteen.model;

import static com.google.common.truth.Truth.assertThat;

import de.turing85.advent.of.code.tttt.day.nineteen.parser.BlueprintParser;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@DisplayName("BlueprintEvaluator tests")
class BlueprintEvaluatorTest {
  private static final Path COMMON_INPUT = Path.of("src/test/resources/commonInput.txt");
  private static final Path PERSONAL_INPUT = Path.of("src/test/resources/personalInput.txt");

  @DisplayName("mine for 24 minutes")
  @Nested
  class MineFor24Minutes {
    @DisplayName("correct result on common input")
    @Test
    void correctResultOnCommonInput() throws IOException {
      // GIVEN
      Set<Blueprint> blueprints = BlueprintParser.parseBlueprints(COMMON_INPUT);
      long actual = 0L;

      // WHEN
      for (Blueprint blueprint : blueprints) {
        long maxGeodesFromBlueprint =
            new BlueprintEvaluator(blueprint).findMaximumAmountOfGeodes(24);
        actual += maxGeodesFromBlueprint * blueprint.id();
      }

      // THEN
      assertThat(actual).isEqualTo(33);
    }

    @DisplayName("correct result on personal input")
    @Test
    void correctResultOnPersonalInput() throws IOException {
      // GIVEN
      Set<Blueprint> blueprints = BlueprintParser.parseBlueprints(PERSONAL_INPUT);
      long actual = 0L;

      // WHEN
      for (Blueprint blueprint : blueprints) {
        long maxGeodesFromBlueprint =
            new BlueprintEvaluator(blueprint).findMaximumAmountOfGeodes(24);
        actual += maxGeodesFromBlueprint * blueprint.id();
      }

      // THEN
      assertThat(actual).isEqualTo(1_725);
    }
  }

  @Nested
  @DisplayName("Mine for 32 minutes")
  class MineFor32Minutes {
    @Test
    @Tag("slow")
    @DisplayName("correct result on common input")
    void correctResultOnCommonInput() throws IOException {
      // GIVEN
      Set<Blueprint> blueprints = BlueprintParser.parseBlueprints(COMMON_INPUT);

      // WHEN
      long actual = blueprints.stream().sorted(Comparator.comparing(Blueprint::id)).limit(3)
          .map(BlueprintEvaluator::new).parallel()
          .mapToLong(evaluator -> evaluator.findMaximumAmountOfGeodes(32))
          .reduce(1, (lhs, rhs) -> lhs * rhs);

      // THEN
      assertThat(actual).isEqualTo(56 * 62);
    }

    @DisplayName("correct result on personal input")
    @Test
    void correctResultOnPersonalInput() throws IOException {
      // GIVEN
      Set<Blueprint> blueprints = BlueprintParser.parseBlueprints(PERSONAL_INPUT);

      // WHEN
      long actual = blueprints.stream().sorted(Comparator.comparing(Blueprint::id)).limit(3)
          .map(BlueprintEvaluator::new).parallel()
          .mapToLong(evaluator -> evaluator.findMaximumAmountOfGeodes(32))
          .reduce(1, (lhs, rhs) -> lhs * rhs);

      // THEN
      assertThat(actual).isEqualTo(15_510);
    }
  }
}
