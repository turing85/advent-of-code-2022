package de.turing85.advent.of.code.tttt.day.twentytwo.model;

/**
 * A command.
 *
 * @param rotation the rotation (either {@link Direction#RIGHT} or {@link Direction#LEFT}) to
 *     execute
 * @param steps the steps to move
 */
public record Command(Direction rotation, int steps) {
  /**
   * Constructor.
   *
   * @param rotation the rotation (either {@link Direction#RIGHT} or {@link Direction#LEFT}) to
   *     execute
   * @param steps the steps to move
   */
  public Command {
    if (rotation != null && rotation != Direction.RIGHT && rotation != Direction.LEFT) {
      throw new IllegalArgumentException("rotation must be null, RIGHT or LEFT");
    }
  }
}
