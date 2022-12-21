package de.turing85.advent.of.code.tttt.day.fourteen.supplier.impl;

import de.turing85.advent.of.code.tttt.day.fourteen.model.Line;
import de.turing85.advent.of.code.tttt.day.fourteen.model.Point;
import de.turing85.advent.of.code.tttt.day.fourteen.supplier.LinesSupplier;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import lombok.Getter;

/**
 * Reads a {@link String}-representation {@link Line}s.
 */
@Getter
public class FromStringLinesSupplier implements LinesSupplier {
  private static final Pattern FIRST_POINT_MATCHER =
      Pattern.compile("^\\s*(?<x>\\d+)\\s*,\\s*(?<y>\\d+).*$");
  private static final Pattern ADDITIONAL_POINT_MATCHER =
      Pattern.compile("\\s*->\\s*(?<x>\\d+)\\s*,\\s*(?<y>\\d+)");

  private final Set<Line> lines;

  /**
   * Reads a {@link String}-representation of {@link Line}s.
   *
   * @param inputAsString the {@link String}-representation of {@link Line}s.
   */
  public FromStringLinesSupplier(String inputAsString) {
    lines = Arrays.stream(inputAsString.split(System.lineSeparator()))
        .map(FromStringLinesSupplier::parse).flatMap(Collection::stream)
        .collect(Collectors.toSet());
  }

  private static Set<Line> parse(String line) {
    Point first = parseFirstPoint(line);
    return parseAdditionalPoints(line, first);
  }

  private static Point parseFirstPoint(String line) {
    Matcher firstMatcher = FIRST_POINT_MATCHER.matcher(line);
    if (!firstMatcher.matches()) {
      throw new IllegalStateException(
          "line \"%s\": illegal format, cannot read first point".formatted(line));
    }
    return Point.of(Integer.parseInt(firstMatcher.group("x")),
        Integer.parseInt(firstMatcher.group("y")));
  }

  private static Set<Line> parseAdditionalPoints(String line, Point first) {
    Matcher additionalMatcher = ADDITIONAL_POINT_MATCHER.matcher(line);
    if (!additionalMatcher.find()) {
      throw new IllegalStateException(
          "line \"%s\": illegal format, cannot read second point".formatted(line));
    }
    Set<Line> linesParsed = new HashSet<>();
    do {
      Point second = Point.of(Integer.parseInt(additionalMatcher.group("x")),
          Integer.parseInt(additionalMatcher.group("y")));
      linesParsed.add(Line.of(first, second));
      first = second;
    } while (additionalMatcher.find());
    return linesParsed;
  }
}
