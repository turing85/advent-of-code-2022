package de.turing85.advent.of.code.tttt.day.five.supplier;

import de.turing85.advent.of.code.tttt.day.five.model.UnloadInstruction;
import java.util.List;
import java.util.function.Supplier;

/**
 * A supplier, supplying a {@link List} of {@link UnloadInstruction}s
 */
public interface UnloadInstructionsSupplier extends Supplier<List<UnloadInstruction>> {
  default List<UnloadInstruction> get() {
    return unloadInstructions();
  }

  /**
   * The {@link UnloadInstruction}s to supply.
   *
   * @return the {@link UnloadInstruction}s to supply
   */
  List<UnloadInstruction> unloadInstructions();
}
