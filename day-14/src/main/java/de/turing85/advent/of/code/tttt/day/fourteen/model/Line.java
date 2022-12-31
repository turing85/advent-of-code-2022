package de.turing85.advent.of.code.tttt.day.fourteen.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * A line, represented by two points.
 *
 * <p>The implementation will guarantee that {@link #first} is always the "smaller" (with respect to
 * {@link Point#compareTo(Point)}) point, and {@link #second} is the larger point.
 */
@EqualsAndHashCode
@Getter
@ToString
public final class Line {
  private static final Map<Point, Map<Point, Line>> CACHE = new HashMap<>();
  private final Point first;
  private final Point second;

  /**
   * Factory-method.
   *
   * <p>The factory-method will enforce that {@link Line#first} is always the "smaller" (with
   * respect to {@link Point#compareTo(Point)}) {@link Point}, and {@link #second} is the "larger"
   * {@link Point}.
   *
   * @param first first {@code Point}, defining the line
   * @param second second {@code Point}, defining the line
   * @return the {@link Line}, defined by {@code first} and {@code second}.
   */
  public static Line of(Point first, Point second) {
    if (first.compareTo(second) <= 0) {
      Point tmp = first;
      first = second;
      second = tmp;
    }
    if (Objects.isNull(CACHE.get(first))) {
      CACHE.put(first, new HashMap<>());
    }
    Line line = CACHE.get(first).get(second);
    if (Objects.isNull(line)) {
      line = new Line(first, second);
      CACHE.get(first).put(second, line);
    }
    return line;
  }

  private Line(Point first, Point second) {
    if (first.x() - second.x() != 0 && first.y() - second.y() != 0) {
      throw new IllegalArgumentException(
          "Points may only differ in their x- or y-value, not in both");
    }
    this.first = first;
    this.second = second;
  }

  /**
   * Returns all {@link Point}s on the line, including both {@link #first} and {@link #second}.
   *
   * @return all {@link Point}s on this line.
   */
  public Set<Point> allPointsOnLine() {
    Set<Point> pointsOnLine = new HashSet<>();
    Point toAdd = first();
    while (!Objects.equals(toAdd, second())) {
      pointsOnLine.add(toAdd);
      toAdd = toAdd.moveOneStepTowards(second());
    }
    pointsOnLine.add(second);
    return pointsOnLine;
  }
}
