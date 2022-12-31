package de.turing85.advent.of.code.tttt.day.twentytwo.model;

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

  /**
   * Rotate in the specified {@link Direction}.
   *
   * @param direction the {@link Direction} (either {@link Direction#RIGHT} or {@link
   *     Direction#LEFT}) to rotate in.
   * @return the {@link Direction} rotated to
   */
  public Direction rotate(Direction direction) {
    return switch (direction) {
      case RIGHT -> rotateRight();
      case LEFT -> rotateLeft();
      default -> throw new IllegalArgumentException("Direction must be either LEFT or RIGHT");
    };
  }

  private Direction rotateRight() {
    return switch (this) {
      case UP -> RIGHT;
      case DOWN -> LEFT;
      case RIGHT -> DOWN;
      case LEFT -> UP;
    };
  }

  private Direction rotateLeft() {
    return switch (this) {
      case UP -> LEFT;
      case DOWN -> RIGHT;
      case RIGHT -> UP;
      case LEFT -> DOWN;
    };
  }
}
