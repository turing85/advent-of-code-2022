package de.turing85.advent.of.code.tttt.day.seven.supplier.impl;

import de.turing85.advent.of.code.tttt.day.seven.model.Pair;
import de.turing85.advent.of.code.tttt.day.seven.supplier.CommandsAndResponsesSupplier;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Reads a {@link String}-representation of commands and responses.
 */
public class FromStringCommandsAndResponsesSupplier implements CommandsAndResponsesSupplier {
  private static final Pattern COMMAND_EXTRACTOR = Pattern.compile("^\\$\\s*(?<command>.*)$");
  private final List<Pair<String, List<String>>> commandsAndResponses;

  /**
   * Reads a {@link String}-representation of commands and responses.
   *
   * @param inputAsString the {@link String}-representation of commands and responses
   */
  public FromStringCommandsAndResponsesSupplier(String inputAsString) {
    commandsAndResponses = new ArrayList<>();
    String command = null;
    List<String> responses = new ArrayList<>();
    boolean isFirst = true;
    for (String line : inputAsString.split(System.lineSeparator())) {
      Matcher matcher = COMMAND_EXTRACTOR.matcher(line);
      if (isFirst) {
        isFirst = false;
        if (!matcher.matches()) {
          throw new IllegalStateException("first line must be a command");
        }
        command = matcher.group("command");
      } else if (matcher.matches()) {
        commandsAndResponses.add(new Pair<>(command, responses));
        command = matcher.group("command");
        responses = new ArrayList<>();
      } else {
        responses.add(line);
      }
    }
    commandsAndResponses.add(new Pair<>(command, responses));
  }

  @Override
  public List<Pair<String, List<String>>> commandsWithResponses() {
    return commandsAndResponses;
  }
}
