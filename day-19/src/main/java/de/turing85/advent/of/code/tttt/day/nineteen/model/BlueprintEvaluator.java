package de.turing85.advent.of.code.tttt.day.nineteen.model;

import java.util.Collections;
import java.util.Deque;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;


/**
 * Evaluate a blueprint.
 */
public class BlueprintEvaluator {
  private final Blueprint blueprint;
  private long maxAmountOfGeodes;

  /**
   * Constructor.
   *
   * @param blueprint the blueprint to evaluate
   */
  public BlueprintEvaluator(Blueprint blueprint) {
    this.blueprint = blueprint;
    maxAmountOfGeodes = 0;
  }

  /**
   * Find the maximum number of {@link ResourceType#GEODE}s can be mined in {@code timeInMinutes}.
   *
   * @param timeInMinutes time to mine
   * @return maximum number of {@link ResourceType#GEODE}s
   */
  public long findMaximumAmountOfGeodes(int timeInMinutes) {
    Logger.getGlobal().info("Starting search for blueprint %d".formatted(blueprint.id()));
    maxAmountOfGeodes = 0;
    Deque<State> queue = constructInitialStates(timeInMinutes);
    while (!queue.isEmpty()) {
      State currentState = queue.removeFirst();
      if (currentState.timeLeft() == 0) {
        updateMaxAmountOfGeode(currentState);
      } else {
        currentState.generateFollowupStates(maxAmountOfGeodes).forEach(queue::addFirst);
      }
    }
    Logger.getGlobal().info("found optimum for blueprint %d".formatted(blueprint.id()));
    return maxAmountOfGeodes;
  }

  private void updateMaxAmountOfGeode(State currentState) {
    long maxAmountFromCurrentState = currentState.geodes();
    if (maxAmountOfGeodes < maxAmountFromCurrentState) {
      maxAmountOfGeodes = maxAmountFromCurrentState;
      Logger.getGlobal()
          .info("Blueprint %d: new max: %d".formatted(blueprint.id(), maxAmountOfGeodes));
    }
  }

  private Deque<State> constructInitialStates(int time) {
    Logger.getGlobal().info("Starting evaluation of Blueprint %d".formatted(blueprint.id()));
    Map<ResourceType, Long> initialProductionPerMinute = Map.of(ResourceType.ORE, 1L);
    Map<ResourceType, Long> initialInventory = Map.of();
    Deque<State> queue = new LinkedList<>();
    for (RobotRecipe recipe : blueprint.recipes()) {
      if (recipe.isEnoughProductionToEventuallyStartConstruction(initialProductionPerMinute)) {
        queue.add(new State(blueprint, initialProductionPerMinute, initialInventory, time, recipe));
      }
    }
    return queue;
  }

  private record State(Blueprint blueprint, Map<ResourceType, Long> productionPerMinute,
      Map<ResourceType, Long> inventory, int timeLeft, RobotRecipe nextRecipeToExecute) {
    private State(Blueprint blueprint, Map<ResourceType, Long> productionPerMinute,
        Map<ResourceType, Long> inventory, int timeLeft, RobotRecipe nextRecipeToExecute) {
      this.blueprint = blueprint;
      this.productionPerMinute = Collections.unmodifiableMap(productionPerMinute);
      this.inventory = Collections.unmodifiableMap(inventory);
      this.timeLeft = timeLeft;
      this.nextRecipeToExecute = nextRecipeToExecute;
    }

    private long geodes() {
      return inventory().getOrDefault(ResourceType.GEODE, 0L);
    }

    private Set<State> generateFollowupStates(long maxAmountOfGeodes) {
      if (canProduceNextRobot()) {
        Set<State> followupStates = generateFollowupStatesWithNewRecipes(maxAmountOfGeodes);
        if (followupStates.size() == 1) {
          return handleSingleFollowupState(maxAmountOfGeodes, followupStates);
        }
        return followupStates;
      }
      return fastForwardTime(maxAmountOfGeodes);
    }

    private Map<ResourceType, Long> copyInventory() {
      Map<ResourceType, Long> newInventory;
      if (inventory().isEmpty()) {
        newInventory = new EnumMap<>(ResourceType.class);
      } else {
        newInventory = new EnumMap<>(inventory());
      }
      return newInventory;
    }

    private static Set<State> handleSingleFollowupState(long maxAmountOfGeodes,
        Set<State> followupStates) {
      State followupState = followupStates.iterator().next();
      if (followupState.timeLeft() == 0) {
        return Set.of(followupState);
      }
      return followupState.generateFollowupStates(maxAmountOfGeodes);
    }

    private Set<State> generateFollowupStatesWithNewRecipes(long maxAmountOfGeodes) {
      int newTimeLeft = timeLeft() - 1;
      Map<ResourceType, Long> newProduction = getNextProductionPerMinute();
      Map<ResourceType, Long> newInventory = subtractCostsAndAddProduction();
      Set<State> followupStates = new HashSet<>();
      for (RobotRecipe recipe : blueprint().recipes()) {
        if (canSkipThisRecipe(recipe, newProduction, newInventory, maxAmountOfGeodes)) {
          continue;
        }
        followupStates.add(new State(blueprint, newProduction, newInventory, newTimeLeft, recipe));
      }
      return Collections.unmodifiableSet(followupStates);
    }

    private Set<State> fastForwardTime(long maxAmountOfGeodes) {
      int newTimeLeft = timeLeft;
      Map<ResourceType, Long> newInventory = copyInventory();
      while (newTimeLeft > 0 && !nextRecipeToExecute().isEnoughOfResourceToBuild(newInventory)) {
        newInventory = addProductionPerMinute(newInventory);
        --newTimeLeft;
      }
      State state = new State(blueprint, productionPerMinute(), newInventory, newTimeLeft,
          nextRecipeToExecute());
      if (newTimeLeft == 0) {
        return Set.of(state);
      }
      return state.generateFollowupStates(maxAmountOfGeodes);
    }

    private boolean canSkipThisRecipe(RobotRecipe recipe, Map<ResourceType, Long> newProduction,
        Map<ResourceType, Long> newInventory, long maxAmountOfGeodes) {
      ResourceType resourceTypeOfProducedRobot = recipe.collectTypeOfProducedRobot();
      long maximumConsumptionOfThisResourceByBlueprint = blueprint()
          .maximumConsumptionPerMinutePerResource()
          .getOrDefault(resourceTypeOfProducedRobot, 0L);
      if (recipe.isNotEnoughProductionToEventuallyStartConstruction(newProduction)) {
        return true;
      }
      boolean producedRobotIsNotGeodeRobot = resourceTypeOfProducedRobot != ResourceType.GEODE;
      boolean enoughProductionOfThisType =
          maximumConsumptionOfThisResourceByBlueprint <=
              productionPerMinute().getOrDefault(resourceTypeOfProducedRobot, 0L);
      if (producedRobotIsNotGeodeRobot && enoughProductionOfThisType) {
        return true;
      }
      long geodesPerTimeUnit = newProduction.getOrDefault(ResourceType.GEODE, 0L);
      if (geodesPerTimeUnit <= 0L) {
        return false;
      }
      return forecastCanBeatCurrentBest(recipe, newInventory, maxAmountOfGeodes, geodesPerTimeUnit);
    }

    private boolean forecastCanBeatCurrentBest(RobotRecipe recipe,
        Map<ResourceType, Long> newInventory, long maxAmountOfGeodes, long geodesPerTimeUnit) {
      int timeToNextRobot = 1;
      Map<ResourceType, Long> inventoryForecast = new EnumMap<>(newInventory);
      while (timeToNextRobot < timeLeft() - 1 &&
          !recipe.isEnoughOfResourceToBuild(inventoryForecast)) {
        inventoryForecast = addProductionPerMinute(inventoryForecast);
        ++timeToNextRobot;
      }
      if (recipe.collectTypeOfProducedRobot() != ResourceType.GEODE) {
        ++timeToNextRobot;
      }
      long geodesNow = newInventory.getOrDefault(ResourceType.GEODE, 0L);
      long geodesDeltaToNextRobot = geodesPerTimeUnit * timeToNextRobot;
      long timeLeftWhenRobotCompletes = ((long) timeLeft) - timeToNextRobot;
      long optimalGeodesDelta =
          littleGauss(geodesPerTimeUnit + timeLeftWhenRobotCompletes) -
              littleGauss(geodesPerTimeUnit);
      return geodesNow + optimalGeodesDelta + geodesDeltaToNextRobot <= maxAmountOfGeodes;
    }

    private static long littleGauss(long n) {
      return (n * (n + 1)) / 2;
    }

    private boolean canProduceNextRobot() {
      return nextRecipeToExecute.isEnoughOfResourceToBuild(inventory());
    }

    private Map<ResourceType, Long> subtractCostsAndAddProduction() {
      Map<ResourceType, Long> afterSubtraction = subtractCosts();
      return addProductionPerMinute(afterSubtraction);
    }

    private Map<ResourceType, Long> addProductionPerMinute(Map<ResourceType, Long> newInventory) {
      Map<ResourceType, Long> afterProductionAdded = new EnumMap<>(ResourceType.class);
      for (Map.Entry<ResourceType, Long> entry : productionPerMinute.entrySet()) {
        ResourceType resourceType = entry.getKey();
        long afterProductionPerMinuteAdded =
            newInventory.getOrDefault(resourceType, 0L) + entry.getValue();
        afterProductionAdded.put(resourceType, afterProductionPerMinuteAdded);
      }
      return Collections.unmodifiableMap(afterProductionAdded);
    }

    private Map<ResourceType, Long> subtractCosts() {
      Map<ResourceType, Long> inventoryAfterSubtraction = new EnumMap<>(inventory());
      for (Map.Entry<ResourceType, Long> entry : this.nextRecipeToExecute().resourcesNeeded().entrySet()) {
        ResourceType resourceType = entry.getKey();
        long afterSubtraction = inventoryAfterSubtraction.get(resourceType) - entry.getValue();
        inventoryAfterSubtraction.put(resourceType, afterSubtraction);
      }
      return inventoryAfterSubtraction;
    }

    private Map<ResourceType, Long> getNextProductionPerMinute() {
      ResourceType resourceType = nextRecipeToExecute().collectTypeOfProducedRobot();
      Map<ResourceType, Long> newProductionPerMinute = new EnumMap<>(productionPerMinute());
      long newProduction = newProductionPerMinute.getOrDefault(resourceType, 0L) + 1;
      newProductionPerMinute.put(resourceType, newProduction);
      return Collections.unmodifiableMap(newProductionPerMinute);
    }
  }
}
