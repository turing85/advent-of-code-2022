package de.turing85.advent.of.code.tttt.day.twentytwo.supplier;

import de.turing85.advent.of.code.tttt.day.twentytwo.model.Command;
import de.turing85.advent.of.code.tttt.day.twentytwo.model.Field;
import java.util.List;
import java.util.Set;

/**
 * Groups a {@link Set} of {@link Field}s and a {@link List} of {@link Field}s together.
 * @param fields the {@link Field}s
 * @param commands the {@link Command}s
 */
public record FieldsAndCommands(Set<Field> fields, List<Command> commands) {
}
