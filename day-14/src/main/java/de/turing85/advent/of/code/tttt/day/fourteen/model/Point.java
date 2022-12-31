package de.turing85.advent.of.code.tttt.day.fourteen.model;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/** A 2D-point. */
@EqualsAndHashCode
@Getter
@ToString
public final class Point implements Comparable<Point> {
  private static final Map<Integer, Map<Integer, Point>> CACHE = new HashMap<>();
  private static final Comparator<Point> DEFAULT_ORDER_COMPARATOR =
      Comparator.comparing(Point::x).thenComparing(Point::y);

  private final int x;
  private final int y;

  /**
   * Factory-method.
   *
   * @param x x-coordinate for the {@link Point}
   * @param y y-coordinate for the {@link Point}
   * @return a {@link Point} at {@code (x, y)}
   */
  public static Point of(int x, int y) {
    if (Objects.isNull(CACHE.get(x))) {
      CACHE.put(x, new HashMap<>());
    }
    Point point = CACHE.get(x).get(y);
    if (Objects.isNull(point)) {
      point = new Point(x, y);
      CACHE.get(x).put(y, point);
    }
    return point;
  }

  /**
   * @param x x-coordinate (left-right direction)
   * @param y y-coordinate (down-up direction)
   */
  private Point(int x, int y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Create a new point that is one step closer to {@code that} point.
   *
   * <p>Diagonal steps are allowed. If the points are equal, the returned point will be the same as
   * {@code this} and {@code that}.
   *
   * @param that {@code Point} to move towards
   * @return a {@code Point}, one step closer to {@code that}.
   */
  Point moveOneStepTowards(Point that) {
    int deltaX = Integer.signum(that.x() - x());
    int deltaY = Integer.signum(that.y() - y());
    return Point.of(x() + deltaX, y() + deltaY);
  }

  @Override
  public int compareTo(Point that) {
    return DEFAULT_ORDER_COMPARATOR.compare(this, Objects.requireNonNull(that));
  }
}
