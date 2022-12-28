package de.turing85.advent.of.code.tttt.day.twentytwo.supplier.impl;

import de.turing85.advent.of.code.tttt.day.twentytwo.model.Command;
import de.turing85.advent.of.code.tttt.day.twentytwo.model.Direction;
import de.turing85.advent.of.code.tttt.day.twentytwo.model.Field;
import de.turing85.advent.of.code.tttt.day.twentytwo.model.Point;
import de.turing85.advent.of.code.tttt.day.twentytwo.supplier.FieldsAndCommands;
import de.turing85.advent.of.code.tttt.day.twentytwo.supplier.FieldsAndCommandsSupplier;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.Getter;

/**
 * Reads a {@link String}-representation of a {@link FieldsAndCommands}.
 */
@Getter
public class FromStringFieldsAndCommandsSupplier implements FieldsAndCommandsSupplier {
  private static final Pattern FIELDS_EXTRACTOR =
      Pattern.compile("^(?<leadingBlanks>\\s*)(?<fields>[.#]*)\\s*$");

  private final FieldsAndCommands fieldsAndCommands;

  /**
   * Reads a {@link String}-representation of a {@link FieldsAndCommands}.
   *
   * @param inputAsString the {@link String}-representation of a {@link FieldsAndCommands}
   */
  public FromStringFieldsAndCommandsSupplier(String inputAsString) {
    Set<Field> fields = new HashSet<>();
    List<Command> commands = new ArrayList<>();
    int currentY = 1;
    boolean parseMap = true;
    for (String line : inputAsString.split(System.lineSeparator())) {
      if (line.isBlank()) {
        parseMap = false;
        continue;
      }
      if (parseMap) {
        fields.addAll(parseAsFields(line, currentY));
        ++currentY;
      } else {
        commands = (parseAsCommands(line));
      }
    }
    fieldsAndCommands = new FieldsAndCommands(fields, commands);
  }

  private static Set<Field> parseAsFields(String line, int currentYIndex) {
    Matcher matcher = FIELDS_EXTRACTOR.matcher(line);
    if (!matcher.matches()) {
      throw new IllegalStateException("line \"%s\" cannot be parsed".formatted(line));
    }
    int currentXIndex = matcher.group("leadingBlanks").length() + 1;
    String fieldsAsString = matcher.group("fields");
    Set<Field> fields = new HashSet<>();
    return convertStringToFields(currentYIndex, currentXIndex, fieldsAsString, fields);
  }

  private static Set<Field> convertStringToFields(int currentYIndex, int currentXIndex,
      String fieldsAsString, Set<Field> fields) {
    for (char fieldChar : fieldsAsString.toCharArray()) {
      boolean hasRock = fieldChar == '#';
      fields.add(new Field(new Point(currentXIndex, currentYIndex), hasRock));
      ++currentXIndex;
    }
    return fields;
  }

  private List<Command> parseAsCommands(String line) {
    List<Command> commands = new ArrayList<>();
    List<Integer> characters = line.chars().boxed().toList();
    StringBuilder stepsAsString = new StringBuilder();
    for (Integer codePoint : characters) {
      if (Character.isDigit(codePoint)) {
        stepsAsString.append(Character.toString(codePoint));
      } else if (codePoint == 'L' || codePoint == 'R') {
        int steps = Integer.parseInt(stepsAsString.toString());
        Direction direction = Direction.of(Character.toString(codePoint).charAt(0));
        commands.add(new Command(direction, steps));
        stepsAsString = new StringBuilder();
      } else {
        throw new IllegalStateException("line \"%s\" cannot be parsed".formatted(line));
      }
    }
    commands.add(new Command(null, Integer.parseInt(stepsAsString.toString())));
    return commands;
  }
}
