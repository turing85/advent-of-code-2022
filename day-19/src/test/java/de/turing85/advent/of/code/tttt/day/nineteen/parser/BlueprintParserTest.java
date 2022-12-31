package de.turing85.advent.of.code.tttt.day.nineteen.parser;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import de.turing85.advent.of.code.tttt.day.nineteen.model.Blueprint;
import de.turing85.advent.of.code.tttt.day.nineteen.model.ResourceType;
import de.turing85.advent.of.code.tttt.day.nineteen.model.RobotRecipe;
import java.nio.file.Path;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("BlueprintParser tests")
class BlueprintParserTest {
  @DisplayName("throws on illegal input")
  @ParameterizedTest(name = "{0} -> IllegalStateException")
  @CsvSource({
    "illegalInputMalformedId.txt",
    "illegalInputMalformedCollectType.txt",
    "illegalInputMissingRecipe.txt",
    "illegalInputMalformedFirstRecipeAmount.txt",
    "illegalInputMalformedFirstRecipeResourceType.txt",
    "illegalInputMalformedAdditionalRecipeAmount.txt",
    "illegalInputMalformedAdditionalRecipeResourceType.txt"
  })
  void throwsOnIllegalInput(String fileName) {
    // GIVEN
    Path path = Path.of("src/test/resources", fileName);

    // WHEN & THEN
    assertThrows(IllegalStateException.class, () -> BlueprintParser.parseBlueprints(path));
  }

  @DisplayName("produces expected blueprints")
  @Test
  void producesExpectedBlueprints() {
    // GIVEN
    String inputAsString =
        "Blueprint 0: Each ore robot costs 1 ore. Each clay robot costs 2 ore. Each obsidian robot costs 3 ore and 4 clay. Each geode robot costs 5 ore and 6 obsidian.";

    RobotRecipe oreRobotRecipe = new RobotRecipe(Map.of(ResourceType.ORE, 1L), ResourceType.ORE);
    RobotRecipe clayRobotRecipe = new RobotRecipe(Map.of(ResourceType.ORE, 2L), ResourceType.CLAY);
    RobotRecipe obsidianRobotRecipe =
        new RobotRecipe(Map.of(ResourceType.ORE, 3L, ResourceType.CLAY, 4L), ResourceType.OBSIDIAN);
    RobotRecipe geodeRobotRecipe =
        new RobotRecipe(
            Map.of(ResourceType.ORE, 5L, ResourceType.OBSIDIAN, 6L), ResourceType.GEODE);
    Set<RobotRecipe> expectedRobotRecipes =
        Set.of(oreRobotRecipe, clayRobotRecipe, obsidianRobotRecipe, geodeRobotRecipe);

    // WHEN
    Set<Blueprint> actual = BlueprintParser.parseBlueprints(inputAsString);

    // THEN
    assertThat(actual).hasSize(1);
    Blueprint actualBlueprint = actual.iterator().next();
    assertThat(actualBlueprint.id()).isEqualTo(0);
    assertThat(actualBlueprint.recipes()).isEqualTo(expectedRobotRecipes);
  }
}
