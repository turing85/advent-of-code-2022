package de.turing85.adventure.of.code.tttt.day.seventeen.model;

import java.util.Set;

/**
 * A tile is a {@link Set} of {@link Point}s.
 */
public interface Tile {
  /**
   * Getter
   *
   * @return The {@link Point}s that represent this {@link Tile}
   */
  Set<Point> pointsOfTile();
}
