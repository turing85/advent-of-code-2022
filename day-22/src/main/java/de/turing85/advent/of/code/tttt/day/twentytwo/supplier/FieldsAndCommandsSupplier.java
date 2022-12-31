package de.turing85.advent.of.code.tttt.day.twentytwo.supplier;

import java.util.List;
import java.util.function.Supplier;

/** A supplier, supplying a {@link List} of {@link FieldsAndCommands}s. */
public interface FieldsAndCommandsSupplier extends Supplier<FieldsAndCommands> {
  default FieldsAndCommands get() {
    return fieldsAndCommands();
  }

  /**
   * Supplies {@link FieldsAndCommands}
   *
   * @return the {@link FieldsAndCommands} to return
   */
  FieldsAndCommands fieldsAndCommands();
}
