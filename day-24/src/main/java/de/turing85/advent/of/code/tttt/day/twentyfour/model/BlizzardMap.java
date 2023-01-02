package de.turing85.advent.of.code.tttt.day.twentyfour.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.Getter;

/** Represents all blizzards on the map at one point in time. */
public class BlizzardMap {
  private final Map<Point, Set<BlizzardPoint>> blizzardsByPoint;

  @Getter private final int width;

  @Getter private final int height;

  /**
   * Constructor.
   *
   * @param blizzardsByPoint the {@link Point}s where a blizzard is
   * @param width width of the map
   * @param height height of the map
   */
  public BlizzardMap(Map<Point, Set<BlizzardPoint>> blizzardsByPoint, int width, int height) {
    this.blizzardsByPoint = blizzardsByPoint;
    this.width = width;
    this.height = height;
  }

  /**
   * Get all {@link Point}s blocked by a blizzard.
   *
   * @return all {@link Point}s blocked by a blizzard
   */
  public Set<Point> blockedByBlizzards() {
    return blizzardsByPoint.keySet();
  }

  /**
   * Gets the next {@link BlizzardMap}, one unit of time later.
   *
   * @return the next {@link BlizzardMap}, one unit of time later
   */
  public BlizzardMap nextMap() {
    Map<Point, Set<BlizzardPoint>> nextBlizzardsByPoint =
        blizzardsByPoint.values().stream()
            .flatMap(Collection::stream)
            .map(this::moveBlizzardPoint)
            .collect(Collectors.toMap(BlizzardPoint::point, Set::of, BlizzardMap::joinSets));
    return new BlizzardMap(nextBlizzardsByPoint, width, height);
  }

  private BlizzardPoint moveBlizzardPoint(BlizzardPoint blizzardPoint) {
    Point nextPoint = blizzardPoint.point().neighbour(blizzardPoint.direction());
    if (nextPoint.x() >= width - 1) {
      nextPoint = new Point(1, nextPoint.y());
    } else if (nextPoint.x() <= 0) {
      nextPoint = new Point(width - 2, nextPoint.y());
    } else if (nextPoint.y() >= height - 1) {
      nextPoint = new Point(nextPoint.x(), 1);
    } else if (nextPoint.y() <= 0) {
      nextPoint = new Point(nextPoint.x(), height - 2);
    }
    return new BlizzardPoint(nextPoint, blizzardPoint.direction());
  }

  private static <T> Set<T> joinSets(Set<? extends T> lhs, Set<? extends T> rhs) {
    Set<T> joined = new HashSet<>(lhs);
    joined.addAll(rhs);
    return joined;
  }

  /**
   * A blizzard point on the blizzard map.
   *
   * @param point the {@link Point} of the blizzard
   * @param direction the {@link Direction} the blizzard is moving in
   */
  public record BlizzardPoint(Point point, Direction direction) {}
}
