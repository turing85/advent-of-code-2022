package de.turing85.advent.of.code.tttt.day.nineteen.model;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Represents a blueprint.
 *
 * @param id the id of this {@link Blueprint}
 * @param recipes the {@link RobotRecipe}s contained in this {@link Blueprint}
 * @param maximumConsumptionPerMinutePerResource maximum consumption per minute and resource
 */
public record Blueprint(
    long id,
    Set<RobotRecipe> recipes,
    Map<ResourceType, Long> maximumConsumptionPerMinutePerResource) {
  /**
   * Constructor.
   *
   * @param id the {@link #id} of {@code this} {@link Blueprint}
   * @param recipes the {@link RobotRecipe} contained in {@code this} {@link Blueprint}
   */
  public Blueprint(long id, Set<RobotRecipe> recipes) {
    this(id, recipes, maximumConsumptionPerMinutePerResource(recipes));
  }

  /**
   * For each resource, returns the maximum amount of resources needed by any {@link RobotRecipe}.
   *
   * @return returns the maximum amount of resources needed by any {@link RobotRecipe}
   */
  private static Map<ResourceType, Long> maximumConsumptionPerMinutePerResource(
      Set<RobotRecipe> recipes) {
    return recipes.stream()
        .map(RobotRecipe::resourcesNeeded)
        .map(Map::entrySet)
        .flatMap(Collection::stream)
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, Long::max));
  }
}
