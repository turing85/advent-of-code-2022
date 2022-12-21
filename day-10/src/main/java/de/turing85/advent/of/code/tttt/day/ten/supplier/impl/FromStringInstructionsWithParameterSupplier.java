package de.turing85.advent.of.code.tttt.day.ten.supplier.impl;

import de.turing85.advent.of.code.tttt.day.ten.model.InstructionWithParameter;
import de.turing85.advent.of.code.tttt.day.ten.supplier.InstructionsWithParameterSupplier;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Reads a {@link String}-representation {@link InstructionWithParameter}s.
 */
public class FromStringInstructionsWithParameterSupplier
    implements InstructionsWithParameterSupplier {
  private static final Pattern COMMAND_EXTRACTOR =
      Pattern.compile("^(?<command>addx|noop)\\s*(?<parameter>-?\\d+)?$");
  private final List<InstructionWithParameter> instructionsWithParameter;

  /**
   * Reads a {@link String}-representation of {@link InstructionWithParameter}s.
   *
   * @param inputAsString the {@link String}-representation of {@link InstructionWithParameter}s.
   */
  public FromStringInstructionsWithParameterSupplier(String inputAsString) {
    instructionsWithParameter = new ArrayList<>();
    for (String line : inputAsString.split(System.lineSeparator())) {
      instructionsWithParameter.add(validateAndParseLine(line));
    }
  }

  private static InstructionWithParameter validateAndParseLine(String line) {
    InstructionWithParameter toAdd;
    Matcher matcher = COMMAND_EXTRACTOR.matcher(line);
    if (matcher.matches()) {
      String command = matcher.group("command");
      if (Objects.equals("addx", command)) {
        toAdd = validateAndParseAddXInstruction(matcher.group("parameter"));
      } else {
        toAdd = InstructionWithParameter.noOp();
      }
    } else {
      throw new IllegalStateException("line \"%s}\" is malformed".formatted(line));
    }
    return toAdd;
  }

  private static InstructionWithParameter validateAndParseAddXInstruction(
      String parameterAsString) {
    if (Objects.isNull(parameterAsString)) {
      throw new IllegalStateException("command \"addx\" needs a parameter");
    }
    return InstructionWithParameter.addX(Integer.parseInt(parameterAsString));
  }

  @Override
  public List<InstructionWithParameter> instructionsWithParameter() {
    return instructionsWithParameter;
  }
}
