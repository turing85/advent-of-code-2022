package de.turing85.advent.of.code.tttt.day.ten.supplier;

import de.turing85.advent.of.code.tttt.day.ten.model.InstructionWithParameter;
import java.util.List;
import java.util.function.Supplier;

/** A supplier, supplying a {@link List} of {@link InstructionWithParameter}s. */
public interface InstructionsWithParameterSupplier
    extends Supplier<List<InstructionWithParameter>> {
  @Override
  default List<InstructionWithParameter> get() {
    return instructionsWithParameter();
  }

  /**
   * Supplies {@link InstructionWithParameter}s.
   *
   * @return the {@link InstructionWithParameter}s to supply.
   */
  List<InstructionWithParameter> instructionsWithParameter();
}
