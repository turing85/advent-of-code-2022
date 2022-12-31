package de.turing85.advent.of.code.tttt.day.twentytwo.model;

/**
 * Represents a position, defined by a {@link Field} and an orientation ({@link Direction#RIGHT} or
 * {@link Direction#LEFT}).
 *
 * @param field the {@link Field}
 * @param orientation the orientation (either {@link Direction#RIGHT} or {@link Direction#LEFT})
 */
public record Position(Field field, Direction orientation) {
  /**
   * Executes a single command, and returns the new {@link Position}.
   *
   * @param command to execute
   * @return the new {@link Position}
   */
  public Position execute(Command command) {
    int steps = command.steps();
    Field newField = field();
    Direction orientation = orientation();
    while (steps > 0 && !newField.neighbour(orientation).hasRock()) {
      Direction oldOrientation = orientation;
      orientation = newField.rotationFunction(orientation).apply(orientation);
      newField = newField.neighbour(oldOrientation);
      --steps;
    }
    Direction commandRotation = command.rotation();
    Direction newOrientation =
        commandRotation != null ? orientation.rotate(commandRotation) : orientation;
    return new Position(newField, newOrientation);
  }
}
