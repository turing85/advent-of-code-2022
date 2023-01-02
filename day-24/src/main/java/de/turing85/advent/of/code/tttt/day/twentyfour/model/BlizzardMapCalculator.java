package de.turing85.advent.of.code.tttt.day.twentyfour.model;

import java.util.ArrayList;
import java.util.List;

/** An utility class to pre-calculate all possible blizzard maps from an initial state. */
public class BlizzardMapCalculator {
  private BlizzardMapCalculator() {}

  /**
   * Given a {@link BlizzardMap} as {@code initialState}, pre-calculates all possible maps from
   * there.
   *
   * <p>Since blizzards move in a linear fashion, the states have to repeat after {@code lcm(width,
   * height)} steps, with {@code lcm} being the least common multiple.
   *
   * @param initialState the initial {@link BlizzardMap}
   * @return all states resulting from the {@code initialState}
   */
  public static List<BlizzardMap> precalculateMapsByTimeSteps(BlizzardMap initialState) {
    int cycleLength = lowestCommonMultiple(initialState.width() - 2, initialState.height() - 2);
    List<BlizzardMap> blizzardMapByTimeSteps = new ArrayList<>(cycleLength);
    BlizzardMap currentState = initialState;
    for (int step = 0; step < cycleLength; ++step) {
      blizzardMapByTimeSteps.add(currentState);
      currentState = currentState.nextMap();
    }
    return blizzardMapByTimeSteps;
  }

  private static int lowestCommonMultiple(int left, int right) {
    return left * right / greatestCommonDivisor(Math.min(left, right), Math.max(left, right));
  }

  private static int greatestCommonDivisor(int left, int right) {
    if (left == 0) {
      return Math.max(right, 1);
    }
    return greatestCommonDivisor(right % left, left);
  }
}
