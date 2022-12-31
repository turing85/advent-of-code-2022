package de.turing85.advent.of.code.day.fifteen;

import de.turing85.advent.of.code.day.fifteen.model.Interval;
import de.turing85.advent.of.code.day.fifteen.model.Pair;
import de.turing85.advent.of.code.day.fifteen.model.Point;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

/** Calculates coverage statistics. */
public class CoverageCalculator {
  private CoverageCalculator() {}

  /**
   * Given a {@link Set} of sensors and beacons, represented as {@link Pair} (where {@link
   * Pair#first()} is the sensor, and {@link Pair#second()} is the beacon), calculate how many tiles
   * are covered at {@code y}-coordinate {@code yCoordinate}.
   *
   * @param sensorsWithBeacons {@link Pair}s of sensors and beacons
   * @param yCoordinate the {@code y}-coordinate to look for
   * @return points covered
   */
  public static long getCoverageOnYCoordinate(
      Set<Pair<Point, Point>> sensorsWithBeacons, long yCoordinate) {
    long sensorsAndBeaconsOnYCoordinate =
        sensorsWithBeacons.stream()
            .map(pair -> Set.of(pair.first(), pair.second()))
            .flatMap(Collection::stream)
            .distinct()
            .filter(point -> point.y() == yCoordinate)
            .count();
    return getCoveredIntervalsForYCoordinate(
                calculateSensorsWithRange(sensorsWithBeacons), yCoordinate)
            .stream()
            .mapToLong(Interval::length)
            .sum()
        - sensorsAndBeaconsOnYCoordinate;
  }

  private static Set<Interval> getCoveredIntervalsForYCoordinate(
      Set<Pair<Point, Long>> sensorsWithRange, long yCoordinate) {
    Set<Interval> intervals =
        sensorsWithRange.stream()
            .filter(
                pair -> pair.first().manhattanDistanceToYCoordinate(yCoordinate) <= pair.second())
            .map(
                pair ->
                    pair.first()
                        .allPointsInManhattanDistanceAndYCoordinate(pair.second(), yCoordinate))
            .collect(Collectors.toSet());
    return Interval.merge(intervals);
  }

  private static Set<Pair<Point, Long>> calculateSensorsWithRange(
      Set<Pair<Point, Point>> sensorsWithBeacons) {
    return sensorsWithBeacons.stream()
        .map(pair -> new Pair<>(pair.first(), pair.first().manhattanDistance(pair.second())))
        .collect(Collectors.toSet());
  }

  /**
   * Given some sensors-beacon-{@link Pair}s, find the one field not covered, and calculate the
   * tuning frequency from it.
   *
   * @param sensorsWithBeacons sensors with beacons
   * @param maxCoordinate the max search range (from {@code 0} to {@code maxCoordinate}, both
   *     inclusive)
   * @return the tuning frequency
   */
  public static long findTuningFrequency(
      Set<Pair<Point, Point>> sensorsWithBeacons, long maxCoordinate) {
    Set<Pair<Point, Long>> sensorsWithRange = calculateSensorsWithRange(sensorsWithBeacons);
    long tuningFrequency = -1;
    long yCoordinate = 0;
    while (tuningFrequency == -1) {
      Set<Interval> intervals = getCoveredIntervalsForYCoordinate(sensorsWithRange, yCoordinate);
      if (intervals.stream()
          .anyMatch(interval -> interval.fullyContains(new Interval(0, maxCoordinate)))) {
        ++yCoordinate;
      } else {
        Interval lower =
            intervals.stream()
                .filter(interval -> interval.fullyContains(new Interval(0, 0)))
                .findAny()
                .orElseThrow();
        tuningFrequency = (lower.upper() + 1L) * 4_000_000 + yCoordinate;
      }
    }
    return tuningFrequency;
  }
}
