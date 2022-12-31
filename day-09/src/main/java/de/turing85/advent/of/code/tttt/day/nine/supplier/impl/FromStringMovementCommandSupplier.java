package de.turing85.advent.of.code.tttt.day.nine.supplier.impl;

import de.turing85.advent.of.code.tttt.day.nine.model.Direction;
import de.turing85.advent.of.code.tttt.day.nine.model.MovementCommand;
import de.turing85.advent.of.code.tttt.day.nine.supplier.MovementCommandsSupplier;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** Reads a {@link String}-representation {@link MovementCommand}s. */
public class FromStringMovementCommandSupplier implements MovementCommandsSupplier {
  private static final Pattern MOVEMENT_AND_STEP_EXTRACTOR =
      Pattern.compile("^(?<direction>[UDRL])\\s(?<steps>\\d+)$");
  private final List<MovementCommand> movementCommands;

  /**
   * Reads a {@link String}-representation of {@link MovementCommand}s.
   *
   * @param inputAsString the {@link String}-representation of {@link MovementCommand}s.
   */
  public FromStringMovementCommandSupplier(String inputAsString) {
    movementCommands = new ArrayList<>();
    for (String line : inputAsString.split(System.lineSeparator())) {
      movementCommands.add(verifyAndParseLine(line));
    }
  }

  private static MovementCommand verifyAndParseLine(String line) {
    Matcher matcher = MOVEMENT_AND_STEP_EXTRACTOR.matcher(line);
    if (matcher.matches()) {
      Direction direction = Direction.of(matcher.group("direction"));
      int steps = Integer.parseInt(matcher.group("steps"));
      return new MovementCommand(direction, steps);
    } else {
      throw new IllegalStateException("line [%s] is malformed".formatted(line));
    }
  }

  @Override
  public List<MovementCommand> movementCommands() {
    return movementCommands;
  }
}
