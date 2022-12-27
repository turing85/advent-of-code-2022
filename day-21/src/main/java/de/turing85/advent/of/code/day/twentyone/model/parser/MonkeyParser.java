package de.turing85.advent.of.code.day.twentyone.model.parser;

import de.turing85.advent.of.code.day.twentyone.model.Monkey;
import java.util.Map;

/**
 * A parser that parses a single {@link String}-representation of a specific {@link Monkey}-type.
 */
public interface MonkeyParser {
  /**
   * Parses a single {@link String}-representation of a specific {@link Monkey}-type.
   *
   * @param monkeyAsString the {@link String}-representation of a {@link Monkey}
   * @param alreadyExistingMonkeys already constructed {@link Monkey}s
   * @return the newly constructed {@link Monkey}
   */
  Monkey parse(String monkeyAsString, Map<String, Monkey> alreadyExistingMonkeys);
}
