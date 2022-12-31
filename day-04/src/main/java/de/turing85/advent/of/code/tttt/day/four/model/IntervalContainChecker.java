package de.turing85.advent.of.code.tttt.day.four.model;

import java.util.Collection;

/** Counts the overlapping interval pairs. */
public class IntervalContainChecker {
  private static final IntervalContainChecker INSTANCE = new IntervalContainChecker();

  private IntervalContainChecker() {}

  /**
   * Instance-getter.
   *
   * @return the singleton-instance of this class.
   */
  public static IntervalContainChecker instance() {
    return INSTANCE;
  }

  /**
   * Counts the containing interval pairs.
   *
   * @param intervalPairs the {@link Collection} of {@link Pair}s of {@link Interval}s to check
   * @return count of intervals contained in each other.
   */
  public int countIntervalContainment(Collection<Pair<Interval, Interval>> intervalPairs) {
    return Long.valueOf(
            intervalPairs.stream()
                .filter(pair -> Interval.oneContainsTheOther(pair.first(), pair.second()))
                .count())
        .intValue();
  }
}
