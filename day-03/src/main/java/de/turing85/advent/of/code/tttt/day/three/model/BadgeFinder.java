package de.turing85.advent.of.code.tttt.day.three.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given a {@link java.util.List} of {@link Rucksack}s, calculates the sum of all badges per {@code
 * n}-Group.
 */
public class BadgeFinder {
  private static final BadgeFinder INSTANCE = new BadgeFinder();

  private BadgeFinder() {}

  /**
   * Instance-getter.
   *
   * @return the singleton-instance of this class.
   */
  public static BadgeFinder instance() {
    return INSTANCE;
  }

  /**
   * Finds the badge-sum for {@code n}-groups.
   *
   * @param rucksacks the {@link Rucksack}s to analyze
   * @param n the group size
   * @return the sum of badge-priorities
   */
  public int findBade(List<Rucksack> rucksacks, int n) {
    validateInput(rucksacks, n);
    int badgeScore = 0;
    for (int index = 0; index < rucksacks.size(); index += n) {
      List<Rucksack> groupList = rucksacks.subList(index, index + n);
      badgeScore += findBadgeScoreOfGroup(groupList);
    }
    return badgeScore;
  }

  private void validateInput(List<Rucksack> rucksacks, int n) {
    if (rucksacks.size() % n != 0) {
      throw new IllegalStateException(
          "number of rucksacks must be evenly divisible by %d".formatted(n));
    }
  }

  private int findBadgeScoreOfGroup(List<Rucksack> groupList) {
    Set<Integer> result =
        groupList.stream()
            .map(Rucksack::getAllItemPriorities)
            .reduce(groupList.getFirst().getAllItemPriorities(), BadgeFinder::intersection);
    if (result.size() != 1) {
      throw new IllegalStateException("Exactly one item must be in common in a group!");
    }
    return result.iterator().next();
  }

  private static Set<Integer> intersection(Set<Integer> lhs, Set<Integer> rhs) {
    Set<Integer> result = new HashSet<>(lhs);
    result.retainAll(rhs);
    return result;
  }
}
