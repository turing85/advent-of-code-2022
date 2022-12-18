package de.turing85.advent.of.code.tttt.day.three.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Represents a Rucksack with two equally-sized compartments.
 *
 * <p>
 * Each compartment holds the same number of items. Each item is identified by an {@code int}-value
 * - its prority - in the closed interval {@code [1, 52]}.
 */
public class Rucksack {
  private final Set<Integer> firstCompartment;
  private final Set<Integer> secondCompartment;

  /**
   * Constructs a {@link Rucksack} from a list of priorities ({@code int}-values).
   *
   * <p>
   * The {@link List} must be of equal length, and only contain values in the closed interval
   * {@code [1, 52]}. The first half of the list is packed into the first compartment; the second
   * half of the list is packed into the second compartment.
   *
   * @param itemPriorities the items, represented by their priority, in this backpack.
   */
  public Rucksack(List<Integer> itemPriorities) {
    int halfSize = verifyInput(itemPriorities).size() / 2;
    firstCompartment = itemPriorities.stream().limit(halfSize).collect(Collectors.toSet());
    secondCompartment = itemPriorities.stream().skip(halfSize).collect(Collectors.toSet());
  }

  private List<Integer> verifyInput(List<Integer> itemPriorities) {
    if (itemPriorities.size() % 2 != 0) {
      throw new IllegalArgumentException("number of items must be even");
    }
    if (itemPriorities.stream().anyMatch(priority -> priority < 1 || priority > 52)) {
      throw new IllegalArgumentException("All priorities must be in the closed interval [1, 52]");
    }
    return itemPriorities;
  }

  /**
   * Returns a set of item priorities that are contained in both compartments.
   *
   * @return the item priorities in both compartments.
   */
  public Set<Integer> getItemPrioritiesInBothCompartments() {
    Set<Integer> result = new HashSet<>(firstCompartment);
    result.retainAll(secondCompartment);
    return result;
  }

  /**
   * Returns all priorities of all items in both containers.
   *
   * @return all priorities of both containers.
   */
  public Set<Integer> getAllItemPriorities() {
    Set<Integer> result = new HashSet<>(firstCompartment);
    result.addAll(secondCompartment);
    return result;
  }
}
