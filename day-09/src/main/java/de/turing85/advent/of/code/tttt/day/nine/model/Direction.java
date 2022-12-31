package de.turing85.advent.of.code.tttt.day.nine.model;

import java.util.Locale;

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
   * Get a direction form a {@code String}-representation.
   *
   * <p>This method convert {@code s} to uppercase, and passes the 1st character to {@link
   * #of(char)}. Thus, {@code of("D'oh!")} will return {@link #DOWN}.
   *
   * @param s input
   * @return corresponding {@link Direction}
   */
  public static Direction of(String s) {
    return of(s.toUpperCase(Locale.ROOT).charAt(0));
  }

  /**
   * Gets a direction by a {@code char}-representation.
   *
   * <p>The output is defined as follows:
   *
   * <ul>
   *   <li>For {@code 'U'}, {@link #UP} is returned.
   *   <li>For {@code 'D'}, {@link #DOWN} is returned.
   *   <li>For {@code 'R'}, {@link #RIGHT} is returned.
   *   <li>For {@code 'L'}, {@link #LEFT} is returned.
   * </ul>
   *
   * @param c input
   * @return corresponding {@link Direction}
   */
  public static Direction of(char c) {
    return switch (c) {
      case 'U' -> UP;
      case 'D' -> DOWN;
      case 'L' -> LEFT;
      case 'R' -> RIGHT;
      default -> throw new IllegalArgumentException("Parameter must be in ['U', 'D', 'L', 'R']");
    };
  }
}
