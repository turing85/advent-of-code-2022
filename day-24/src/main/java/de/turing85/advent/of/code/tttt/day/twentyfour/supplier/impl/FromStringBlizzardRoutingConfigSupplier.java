package de.turing85.advent.of.code.tttt.day.twentyfour.supplier.impl;

import de.turing85.advent.of.code.tttt.day.twentyfour.model.BlizzardMap;
import de.turing85.advent.of.code.tttt.day.twentyfour.model.Direction;
import de.turing85.advent.of.code.tttt.day.twentyfour.model.Point;
import de.turing85.advent.of.code.tttt.day.twentyfour.supplier.BlizzardRoutingConfigSupplier;
import de.turing85.advent.of.code.tttt.day.twentyfour.supplier.BlizzardRoutingConfiguration;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import lombok.Getter;

/** Reads a {@link String}-representation of a {@link BlizzardRoutingConfiguration}. */
public class FromStringBlizzardRoutingConfigSupplier implements BlizzardRoutingConfigSupplier {
  private static final Pattern LINE_VALIDATOR_PATTERN = Pattern.compile("^(?:#*[.]#*|#[.^v<>]+#)$");

  @Getter private final BlizzardRoutingConfiguration blizzardRoutingConfiguration;

  /**
   * Reads a {@link String}-representation of a {@link BlizzardRoutingConfiguration}.
   *
   * @param inputAsString the {@link String}-representation of a {@link
   *     BlizzardRoutingConfiguration}
   */
  public FromStringBlizzardRoutingConfigSupplier(String inputAsString) {
    List<String> lines = List.of(inputAsString.split(System.lineSeparator()));
    validate(lines);
    int height = lines.size();
    int width = lines.getFirst().length();
    Point start = parseStartFromFirstLine(lines.getFirst());
    Point end = parseEndFromLastLine(lines.getLast(), height - 1);
    Set<BlizzardMap.BlizzardPoint> blizzardPoints = new HashSet<>();
    for (int lineIndex = 1; lineIndex < height - 1; ++lineIndex) {
      blizzardPoints.addAll(parseBlizzardPointsFromLine(lines.get(lineIndex), lineIndex));
    }
    Map<Point, Set<BlizzardMap.BlizzardPoint>> blizzardsByPoint =
        blizzardPoints.stream()
            .collect(Collectors.toMap(BlizzardMap.BlizzardPoint::point, Set::of));
    blizzardRoutingConfiguration =
        new BlizzardRoutingConfiguration(
            new BlizzardMap(blizzardsByPoint, width, height), start, end);
  }

  private static Point parseStartFromFirstLine(String firstLine) {
    return new Point(getIndexOfDot(firstLine), 0);
  }

  private static int getIndexOfDot(String firstLine) {
    int xCoordinate = 0;
    while (true) {
      char c = firstLine.toCharArray()[xCoordinate];
      if (c == '.') {
        break;
      }
      ++xCoordinate;
    }
    return xCoordinate;
  }

  private static Point parseEndFromLastLine(String lastLine, int yCoordinate) {
    return new Point(getIndexOfDot(lastLine), yCoordinate);
  }

  private static void validate(List<String> lines) {
    for (String line : lines) {
      if (!LINE_VALIDATOR_PATTERN.matcher(line).matches()) {
        throw new IllegalStateException("line \"%s\" cannot be parsed".formatted(line));
      }
    }
  }

  private static Set<BlizzardMap.BlizzardPoint> parseBlizzardPointsFromLine(
      String line, int yCoordinate) {
    Set<BlizzardMap.BlizzardPoint> blizzardPoints = new HashSet<>();
    for (int xCoordinate = 0; xCoordinate < line.length(); ++xCoordinate) {
      char c = line.charAt(xCoordinate);
      if (c != '#' && c != '.') {
        blizzardPoints.add(
            new BlizzardMap.BlizzardPoint(new Point(xCoordinate, yCoordinate), Direction.of(c)));
      }
    }
    return blizzardPoints;
  }
}
