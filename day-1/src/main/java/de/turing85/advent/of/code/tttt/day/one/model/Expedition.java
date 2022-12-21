package de.turing85.advent.of.code.tttt.day.one.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Represents an elven expedition, where each elf caries a backpack with a number of food times,
 * each having a calorie value {@code >= 0}.
 */
public class Expedition {
  private final List<Backpack> backpacks;

  /**
   * Given a {@link List<Backpack>}, constructs the corresponding expedition from it.
   *
   * @param backpacks the backpacks carried by this expedition.
   */
  public Expedition(List<Backpack> backpacks) {
    this.backpacks = new ArrayList<>(backpacks);
    this.backpacks.sort(Comparator.comparing(Backpack::getSumOfCalories).reversed());
  }

  /**
   * Clone-constructor.
   *
   * @param that the instance to clone.
   */
  public Expedition(Expedition that) {
    this(that.backpacks);
  }

  /**
   * From the expedition, gets the sum of calories of food items from the backpack with the highest
   * calories sum.
   *
   * @return sum of calories of food items from the backpack with the highest calories sum.
   */
  public int getHighestCaloriesFromAllBackpacks() {
    return getHighestCaloriesFromNHighestBackpacks(1);
  }

  /**
   * From the expedition, gets the sum of calories of food items from the {@code n} backpacks with
   * the highest calories sum.
   *
   * @param n the {@code n} highest backpacks to look for.
   *
   * @return sum of calories of food items from the {@code n} backpacks with the highest calories
   *         sum.
   */
  public int getHighestCaloriesFromNHighestBackpacks(int n) {
    return backpacks.stream().limit(n).map(Backpack::getSumOfCalories).reduce(0, Integer::sum);
  }
}
