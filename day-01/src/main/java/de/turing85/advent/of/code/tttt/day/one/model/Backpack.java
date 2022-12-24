package de.turing85.advent.of.code.tttt.day.one.model;

import java.util.ArrayList;
import java.util.List;

/**
 * A backpack, holding any number of food items, where each food items has a certain whole number of
 * calories, each value being {@code >= 0}.
 */
public class Backpack {
  private final List<Integer> itemsCalories;

  /**
   * Given a list of items (represented by their calories), constructs a backpack, holding all those
   * items.
   *
   * @param itemsCalories the calories of each item, represented by a single {@link Integer} value.
   */
  public Backpack(List<Integer> itemsCalories) {
    this.itemsCalories = new ArrayList<>(validate(itemsCalories));
  }

  private List<Integer> validate(List<Integer> itemsCalories) {
    if (itemsCalories.stream().anyMatch(itemCalories -> itemCalories < 0)) {
      throw new IllegalArgumentException("Calories cannot be negative");
    }
    return itemsCalories;
  }

  /**
   * Sums all calories in this backpack.
   *
   * @return the sum of all items.
   */
  public int getSumOfCalories() {
    return itemsCalories.stream().reduce(0, Integer::sum);
  }
}
