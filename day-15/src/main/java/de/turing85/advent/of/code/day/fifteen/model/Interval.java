package de.turing85.advent.of.code.day.fifteen.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * An Interval.
 *
 * @param lower lower bound of the {@link Interval} (inclusive)
 * @param upper upper bound of the {@link Interval} (inclusive)
 */
public record Interval(long lower, long upper) {
  /**
   * Returns whether {@code this} {@link Interval} fully contains {@code that} {@link Interval}.
   *
   * @param that {@link Interval}
   *
   * @return {@code true} if and only if {@code this} fully contains {@code that}
   */
  public boolean fullyContains(Interval that) {
    return lower() <= that.lower() && that.upper() <= upper();
  }

  /**
   * Returns whether {@code this} and {@code that} {@link Interval} can be merged into one
   * interval.
   *
   * @param that {@link Interval} to merge with
   *
   * @return {@code true} if and only if a merge is possible
   */
  boolean canMergeWith(Interval that) {
    Interval smaller = this.lower() < that.lower() ? this : that;
    Interval larger = this.lower() < that.lower() ? that : this;
    return smaller.upper() >= larger.lower() || smaller.upper() + 1 == larger.lower();
  }

  private Interval mergeWith(Interval that) {
    return new Interval(Math.min(lower(), that.lower()), Math.max(upper(), that.upper()));
  }

  /**
   * Returns the length of the interval, i.e. the number of fields covered from
   * {@link Interval#lower} to {@link Interval#upper} (both inclusive).
   *
   * @return the length of the {@link Interval}
   */
  public long length() {
    return upper() - lower() + 1L;
  }

  /**
   * Given a set of {@link Interval}s, merge and reduce them if possible.
   *
   * <p>
   * Two {@link Interval}s can be merged if they overlap (one's {@link Interval#lower} is larger
   * than the other's {@link Interval#upper}) or if they are touching (one's {@link Interval#lower}
   * is one less than the other's {@link Interval#upper}).
   *
   * @param intervals {@link Interval}s to merge
   *
   * @return merged {@link Interval}s.
   */
  public static Set<Interval> merge(Set<Interval> intervals) {
    List<Interval> sortedIntervals = new ArrayList<>(intervals);
    sortedIntervals.sort(
        Comparator.comparing(Interval::lower).reversed().thenComparing(Interval::upper).reversed());
    Set<Interval> mergedIntervals = new HashSet<>();
    Interval currentInterval = sortedIntervals.remove(0);
    while (!sortedIntervals.isEmpty()) {
      Interval interval = sortedIntervals.remove(0);
      if (currentInterval.fullyContains(interval)) {
        continue;
      }
      if (currentInterval.canMergeWith(interval)) {
        currentInterval = currentInterval.mergeWith(interval);
      } else {
        mergedIntervals.add(currentInterval);
        currentInterval = interval;
      }
    }
    mergedIntervals.add(currentInterval);
    return mergedIntervals;
  }
}
