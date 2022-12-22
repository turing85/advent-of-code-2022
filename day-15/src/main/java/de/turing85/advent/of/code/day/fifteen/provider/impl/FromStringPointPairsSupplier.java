package de.turing85.advent.of.code.day.fifteen.provider.impl;

import de.turing85.advent.of.code.day.fifteen.model.Pair;
import de.turing85.advent.of.code.day.fifteen.model.Point;
import de.turing85.advent.of.code.day.fifteen.provider.PointPairsSupplier;
import java.util.Arrays;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Reads a {@link String}-representation {@link Pair}s of {@link Point}s.
 */
public class FromStringPointPairsSupplier implements PointPairsSupplier {
  private static final Pattern POINT_PAIR_EXTRACTOR = Pattern.compile(
      "^Sensor at x=(?<firstX>-?\\d+), y=(?<firstY>-?\\d+): closest beacon is at x=(?<secondX>-?\\d+), y=(?<secondY>-?\\d+)$");
  private final Set<Pair<Point, Point>> pointPairs;

  /**
   * Reads a {@link String}-representation of {@link Pair}s of {@link Point}s.
   *
   * @param inputAsString the {@link String}-representation of {@link Pair}s of {@link Point}s
   */
  public FromStringPointPairsSupplier(String inputAsString) {
    pointPairs = Arrays.stream(inputAsString.split(System.lineSeparator()))
        .map(FromStringPointPairsSupplier::parse).collect(Collectors.toSet());
  }

  private static Pair<Point, Point> parse(String line) {
    Matcher matcher = POINT_PAIR_EXTRACTOR.matcher(line);
    if (!matcher.matches()) {
      throw new IllegalStateException("line \"%s\": cannot parse".formatted(line));
    }
    long firstX = Long.parseLong(matcher.group("firstX"));
    long firstY = Long.parseLong(matcher.group("firstY"));
    long secondX = Long.parseLong(matcher.group("secondX"));
    long secondY = Long.parseLong(matcher.group("secondY"));
    return new Pair<>(new Point(firstX, firstY), new Point(secondX, secondY));
  }

  @Override
  public Set<Pair<Point, Point>> pointPairs() {
    return pointPairs;
  }
}
