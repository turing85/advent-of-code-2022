package de.turing85.advent.of.code.tttt.day.seven.supplier;

import de.turing85.advent.of.code.tttt.day.seven.model.Pair;
import java.util.List;
import java.util.function.Supplier;

/**
 * A supplier, supplying some commands (represented as {@link String}s) and their responses
 * (represented as {@link List} of {@link String}s).
 */
public interface CommandsAndResponsesSupplier extends Supplier<List<Pair<String, List<String>>>> {
  @Override
  default List<Pair<String, List<String>>> get() {
    return commandsWithResponses();
  }

  /**
   * Provides commands and their responses
   *
   * @return commands and responses to provide.
   */
  List<Pair<String, List<String>>> commandsWithResponses();
}
