package de.turing85.advent.of.code.tttt.day.twentyfour.model;

/** A cardinal direction. */
public enum Direction {
  /** Up direction */
  UP,

  /** Down direction */
  DOWN,

  /** Left direction */
  LEFT,

  /** Right direction */
  RIGHT;

  /**
   * Gets a direction by a {@code char}-representation.
   *
   * <p>The output is defined as follows:
   *
   * <ul>
   *   <li>For {@code '^'}, {@link #UP} is returned.
   *   <li>For {@code 'v'}, {@link #DOWN} is returned.
   *   <li>For {@code '>'}, {@link #RIGHT} is returned.
   *   <li>For {@code '<'}, {@link #LEFT} is returned.
   * </ul>
   *
   * @param c input
   * @return corresponding {@link Direction}
   */
  public static Direction of(char c) {
    return switch (c) {
      case '^' -> UP;
      case 'v' -> DOWN;
      case '<' -> LEFT;
      case '>' -> RIGHT;
      default -> throw new IllegalArgumentException("Parameter must be in ['^', 'v', '<', '>']");
    };
  }
}
