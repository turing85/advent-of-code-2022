package de.turing85.advent.of.code.tttt.day.nine.supplier;

import de.turing85.advent.of.code.tttt.day.nine.model.MovementCommand;
import java.util.List;
import java.util.function.Supplier;

/**
 * A supplier, supplying a {@link List} of {@link MovementCommand}s.
 */
public interface MovementCommandsSupplier extends Supplier<List<MovementCommand>> {
  @Override
  default List<MovementCommand> get() {
    return movementCommands();
  }

  /**
   * Supplies {@link MovementCommand}s.
   *
   * @return the {@link MovementCommand}s to supply.
   */
  List<MovementCommand> movementCommands();
}
