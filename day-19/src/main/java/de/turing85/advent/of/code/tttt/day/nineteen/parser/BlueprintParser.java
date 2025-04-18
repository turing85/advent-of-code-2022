package de.turing85.advent.of.code.tttt.day.nineteen.parser;

import de.turing85.advent.of.code.tttt.day.nineteen.model.Blueprint;
import de.turing85.advent.of.code.tttt.day.nineteen.model.ResourceType;
import de.turing85.advent.of.code.tttt.day.nineteen.model.RobotRecipe;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/** A parser that parses a {@link String}-representation of {@link Blueprint}s. */
public class BlueprintParser {
  private static final Pattern ID_EXTRACTOR = Pattern.compile("^Blueprint (?<id>\\d+)$");

  private static final Pattern BASE_RECIPE_EXTRACTOR =
      Pattern.compile(
          "^\\s*Each (?<collectType>ore|clay|obsidian|geode) robot costs (?<amount>\\d+) (?<resourceType>ore|clay|obsidian)(?<more>.*)$");

  private static final Pattern MORE_RECIPE_INGREDIENTS_EXTRACTOR =
      Pattern.compile("and (?<amount>\\d+) (?<resourceType>ore|clay|obsidian)");

  private BlueprintParser() {}

  /**
   * Parses a {@link String}-representation of {@link Blueprint}s from a file, represented by a
   * {@link Path}.
   *
   * @param inputFile the file holding the {@link String}-representation of {@link Blueprint}s.
   * @return the parsed {@link Blueprint}s.
   * @throws IOException if some I/O exception occurs when the file is read.
   */
  public static Set<Blueprint> parseBlueprints(Path inputFile) throws IOException {
    return parseBlueprints(Files.readString(inputFile));
  }

  /**
   * Parses a {@link String}-representation of {@link Blueprint}s.
   *
   * @param inputAsString the {@link String}-representation of {@link Blueprint}s.
   * @return the parsed {@link Blueprint}s.
   */
  public static Set<Blueprint> parseBlueprints(String inputAsString) {
    return Arrays.stream(inputAsString.split(System.lineSeparator()))
        .filter(Predicate.not(String::isBlank))
        .map(BlueprintParser::parseLineToBlueprint)
        .collect(Collectors.toSet());
  }

  private static Blueprint parseLineToBlueprint(String line) {
    List<String> parts = Arrays.stream(line.split("\\s*[:.]\\s*")).toList();
    try {
      long id = extractId(parts.getFirst());
      Set<RobotRecipe> robotRecipes =
          parts.stream()
              .skip(1)
              .map(BlueprintParser::parseRobotRecipes)
              .collect(Collectors.toSet());
      if (robotRecipes.isEmpty()) {
        throw new IllegalStateException("no recipe found");
      }
      return new Blueprint(id, robotRecipes);
    } catch (IllegalStateException e) {
      throw new IllegalStateException("line \"%s\" cannot be parsed".formatted(line), e);
    }
  }

  private static long extractId(String header) {
    Matcher matcher = ID_EXTRACTOR.matcher(header);
    if (!matcher.matches()) {
      throw new IllegalStateException("Cannot parse header \"%s\"".formatted(header));
    }
    return Long.parseLong(matcher.group("id"));
  }

  private static RobotRecipe parseRobotRecipes(String recipeAsString) {
    Matcher baseMatcher = BASE_RECIPE_EXTRACTOR.matcher(recipeAsString);
    if (!baseMatcher.matches()) {
      throw new IllegalStateException("Cannot parse recipe \"%s\"".formatted(recipeAsString));
    }
    ResourceType collectType = ResourceType.of(baseMatcher.group("collectType"));
    Map<ResourceType, Long> resourcesNeeded = new EnumMap<>(ResourceType.class);
    resourcesNeeded.put(
        ResourceType.of(baseMatcher.group("resourceType")),
        Long.parseLong(baseMatcher.group("amount")));
    Matcher moreMatcher = MORE_RECIPE_INGREDIENTS_EXTRACTOR.matcher(baseMatcher.group("more"));
    while (moreMatcher.find()) {
      resourcesNeeded.put(
          ResourceType.of(moreMatcher.group("resourceType")),
          Long.parseLong(moreMatcher.group("amount")));
    }
    return new RobotRecipe(resourcesNeeded, collectType);
  }
}
