package de.turing85.advent.of.code.tttt.day.nineteen.model;

import java.util.Collections;
import java.util.Map;

/**
 * A robot recipe.
 *
 * @param resourcesNeeded the resources needed to build the given robot
 * @param collectTypeOfProducedRobot what the built robot is going to collect
 */
public record RobotRecipe(
    Map<ResourceType, Long> resourcesNeeded, ResourceType collectTypeOfProducedRobot) {
  /**
   * Constructor.
   *
   * @param resourcesNeeded the resources needed to build the given robot
   * @param collectTypeOfProducedRobot what the built robot is going to collect
   */
  public RobotRecipe(
      Map<ResourceType, Long> resourcesNeeded, ResourceType collectTypeOfProducedRobot) {
    this.resourcesNeeded = Collections.unmodifiableMap(resourcesNeeded);
    this.collectTypeOfProducedRobot = collectTypeOfProducedRobot;
  }

  /**
   * Checks whether the {@code production} (a {@link Map} of {@link ResourceType} and {@link Long},
   * representing the production per time unit) is sufficient to execute {@code this} {@link
   * RobotRecipe} eventually.
   *
   * @param production the production
   * @return {@code false} if and only if the {@code production} is sufficient to execute {@code
   *     this} {@link RobotRecipe} eventually.
   */
  public boolean isNotEnoughProductionToEventuallyStartConstruction(
      Map<ResourceType, Long> production) {
    return !isEnoughProductionToEventuallyStartConstruction(production);
  }

  /**
   * Checks whether the {@code production} (a {@link Map} of {@link ResourceType} and {@link Long},
   * representing the production per time unit) is sufficient to execute {@code this} {@link
   * RobotRecipe} eventually.
   *
   * @param production the production
   * @return {@code true} if and only if the {@code production} is sufficient to execute {@code
   *     this} {@link RobotRecipe} eventually.
   */
  public boolean isEnoughProductionToEventuallyStartConstruction(
      Map<ResourceType, Long> production) {
    for (Map.Entry<ResourceType, Long> entry : resourcesNeeded().entrySet()) {
      if (production.getOrDefault(entry.getKey(), 0L) <= 0) {
        return false;
      }
    }
    return true;
  }

  /**
   * Determines if {@code amount} of {@code resourceType} is enough to start production.
   *
   * @param inventory the inventory to check against
   * @return {@code true} if and only if {@code inventory} holds enough to start {@code this}
   *     recipe.
   */
  public boolean isEnoughOfResourceToBuild(Map<ResourceType, Long> inventory) {
    for (Map.Entry<ResourceType, Long> entry : resourcesNeeded().entrySet()) {
      if (entry.getValue() > inventory.getOrDefault(entry.getKey(), 0L)) {
        return false;
      }
    }
    return true;
  }
}
