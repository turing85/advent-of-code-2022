package de.turing85.advent.of.code.tttt.day.twentytwo.model;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Execute movement commands on a map.
 *
 * <p>
 * The map is represented of a {@link Map} of {@link Point}s, mapping to the corresponding
 * {@link Position}.
 */
public class CommandExecutor {
  private final Set<Field> fields;
  private final Position startingPosition;

  /**
   * Constructor.
   *
   * @param fields the fields
   * @param neighbourLinker the {@link NeighbourLinker}
   */
  public CommandExecutor(Set<Field> fields, NeighbourLinker neighbourLinker) {
    this.fields = neighbourLinker.apply(fields);

    this.startingPosition = findStartingPosition();
  }

  /**
   * Run the {@link Command}s and return the final position.
   *
   * @param commands {@link Command}s to run
   *
   * @return final position
   */
  public Position runCommands(List<Command> commands) {
    Position currentPosition = startingPosition;
    for (Command command : commands) {
      currentPosition = currentPosition.execute(command);
    }
    return currentPosition;
  }

  private Position findStartingPosition() {
    int startY = fields.stream().mapToInt(Field::y).min().orElse(Integer.MAX_VALUE);
    int startX = fields.stream().filter(field -> field.y() == startY).mapToInt(Field::x).min()
        .orElse(Integer.MAX_VALUE);
    Field startingfield =
        fields.stream().filter(field -> field.x() == startX && field.y() == startY).findAny()
            .orElseThrow(() -> new IllegalStateException("cannot find any starting field"));
    return new Position(startingfield, Direction.RIGHT);
  }
}
