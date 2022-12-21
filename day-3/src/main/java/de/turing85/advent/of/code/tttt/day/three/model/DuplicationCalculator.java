package de.turing85.advent.of.code.tttt.day.three.model;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Calculates the duplication factor.
 *
 * <p>
 * The duplication factor is calculated by:
 * <ul>
 * <li>determine which item is duplicated in both containers of each rucksack (must be exactly
 * one)</li>
 * <li>and then all priorities of all duplicated items over all rucksacks are summed up.</li>
 * </ul>
 */
public class DuplicationCalculator {
  private static final DuplicationCalculator INSTANCE = new DuplicationCalculator();

  private DuplicationCalculator() {}

  /**
   * Instance-getter.
   *
   * @return the singleton-instance of this class.
   */
  public static DuplicationCalculator instance() {
    return INSTANCE;
  }

  /**
   * Calculates the duplication factor.
   *
   * @param rucksacks the {@link Rucksack}s to analyze.
   *
   * @return the duplication factor.
   */
  public int calculateDuplication(List<Rucksack> rucksacks) {
    if (rucksacks.stream().map(Rucksack::getItemPrioritiesInBothCompartments)
        .anyMatch(set -> set.size() != 1)) {
      throw new IllegalStateException("Each rucksack should have exactly one duplication");
    }
    return rucksacks.stream().map(Rucksack::getItemPrioritiesInBothCompartments).map(Set::iterator)
        .map(Iterator::next).mapToInt(Integer::intValue).sum();
  }
}
