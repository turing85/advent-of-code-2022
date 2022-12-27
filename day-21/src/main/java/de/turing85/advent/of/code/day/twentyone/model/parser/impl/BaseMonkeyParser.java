package de.turing85.advent.of.code.day.twentyone.model.parser.impl;

import de.turing85.advent.of.code.day.twentyone.model.Monkey;
import de.turing85.advent.of.code.day.twentyone.model.impl.BaseMonkey;
import de.turing85.advent.of.code.day.twentyone.model.parser.MonkeyParser;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parses a {@link BaseMonkey}.
 */
public class BaseMonkeyParser implements MonkeyParser {
  private static final Pattern BASE_MONKEY_EXTRACTOR =
      Pattern.compile("^(?<name>[a-zA-Z]+):\\s*(?<value>-?\\d+)$");

  @Override
  public Monkey parse(String monkeyAsString, Map<String, Monkey> alreadyExistingMonkeys) {
    Matcher matcher = BASE_MONKEY_EXTRACTOR.matcher(monkeyAsString);
    if (!matcher.matches()) {
      throw new IllegalStateException(
          "monkey-representation \"%s\" cannot be parsed".formatted(monkeyAsString));
    }
    String name = matcher.group("name");
    long value = Long.parseLong(matcher.group("value"));
    return new BaseMonkey(name, value);
  }
}
