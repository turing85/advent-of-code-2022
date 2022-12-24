package de.turing85.advent.of.code.tttt.day.eighteen.supplier.impl;

import de.turing85.advent.of.code.tttt.day.eighteen.model.Point3D;
import de.turing85.advent.of.code.tttt.day.eighteen.supplier.PointsSupplier;
import java.util.Arrays;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import lombok.Getter;

/**
 * Reads a {@link String}-representation of {@link Point3D}s.
 */
public class FromStringPointsSupplier implements PointsSupplier {
  private static final Pattern POINT_COORDINATE_EXTRACTOR =
      Pattern.compile("^(?<x>\\d+),(?<y>\\d+),(?<z>\\d+)$");

  @Getter
  private final Set<Point3D> points;

  /**
   * Reads a {@link String}-representation of {@link Point3D}.
   *
   * @param inputAsString the {@link String}-representation of {@link Point3D}s
   */
  public FromStringPointsSupplier(String inputAsString) {
    points = Arrays.stream(inputAsString.split(System.lineSeparator())).map(this::parseLineAsPoint)
        .collect(Collectors.toSet());
  }

  private Point3D parseLineAsPoint(String line) {
    Matcher matcher = POINT_COORDINATE_EXTRACTOR.matcher(line);
    if (!matcher.matches()) {
      throw new IllegalStateException("line \"%s\" cannot be parsed".formatted(line));
    }
    long x = Long.parseLong(matcher.group("x"));
    long y = Long.parseLong(matcher.group("y"));
    long z = Long.parseLong(matcher.group("z"));
    return new Point3D(x, y, z);
  }
}
