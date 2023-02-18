package de.turing85.advent.of.code.tttt.day.four.model;

import java.util.Collection;

/** Counts the overlapping interval pairs. */
public class IntervalOverlapChecker {
  private static final IntervalOverlapChecker INSTANCE = new IntervalOverlapChecker();

  private IntervalOverlapChecker() {}

  /**
   * Instance-getter.
   *
   * @return the singleton-instance of this class.
   */
  public static IntervalOverlapChecker instance() {
    return INSTANCE;
  }

  /**
   * Counts the overlapping interval pairs.
   *
   * @param intervalPairs the {@link Collection} of {@link Pair}s of {@link Interval}s to check
   * @return count of intervals overlapping.
   */
  public int countIntervalOverlaps(Collection<Pair<Interval, Interval>> intervalPairs) {
    return (int) intervalPairs.stream()
        .filter(pair -> Interval.overlap(pair.first(), pair.second()))
        .count();
  }
}
