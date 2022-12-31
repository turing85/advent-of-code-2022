package de.turing85.advent.of.code.tttt.day.twentythree.model;

import java.util.Set;

/** Direction. */
public sealed interface Direction permits Direction.Cardinal, Direction.Diagonal {

  /**
   * All direction values as Set.
   *
   * @return all direction values.
   */
  static Set<Direction> values() {
    return Set.of(
        Cardinal.UP,
        Diagonal.UP_RIGHT,
        Cardinal.RIGHT,
        Diagonal.DOWN_RIGHT,
        Cardinal.DOWN,
        Diagonal.DOWN_LEFT,
        Cardinal.LEFT,
        Diagonal.UP_LEFT);
  }

  /** Cardinal direction */
  enum Cardinal implements Direction {
    /** Up cardinal direction. */
    UP,
    /** Right cardinal direction. */
    RIGHT,
    /** Down cardinal direction. */
    DOWN,
    /** Left cardinal direction. */
    LEFT;

    /**
     * Get all {@link Direction.Cardinal} and {@link Direction.Diagonal}, that "use" this direction.
     *
     * @return all directions that are composed of {@code this} {@link Direction.Cardinal}
     */
    public Set<Direction> composes() {
      return switch (this) {
        case UP -> Set.of(UP, Diagonal.UP_RIGHT, Diagonal.UP_LEFT);
        case RIGHT -> Set.of(Diagonal.UP_RIGHT, RIGHT, Diagonal.DOWN_RIGHT);
        case DOWN -> Set.of(Diagonal.DOWN_RIGHT, DOWN, Diagonal.DOWN_LEFT);
        case LEFT -> Set.of(Diagonal.DOWN_LEFT, LEFT, Diagonal.UP_LEFT);
      };
    }
  }

  /** Diagonal direction. */
  enum Diagonal implements Direction {
    /** Up right diagonal direction. */
    UP_RIGHT,
    /** Down right diagonal direction. */
    DOWN_RIGHT,
    /** Down left diagonal direction. */
    DOWN_LEFT,
    /** Up left diagonal direction. */
    UP_LEFT
  }
}
