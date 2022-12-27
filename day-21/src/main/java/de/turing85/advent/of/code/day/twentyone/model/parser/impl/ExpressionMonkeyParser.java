package de.turing85.advent.of.code.day.twentyone.model.parser.impl;

import de.turing85.advent.of.code.day.twentyone.model.Monkey;
import de.turing85.advent.of.code.day.twentyone.model.impl.ExpressionMonkey;
import de.turing85.advent.of.code.day.twentyone.model.impl.Operator;
import de.turing85.advent.of.code.day.twentyone.model.parser.MonkeyParser;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parses an {@link ExpressionMonkey}.
 */
public class ExpressionMonkeyParser implements MonkeyParser {
  private static final Pattern EXPRESSION_MONKEY_EXTRACTOR = Pattern.compile(
      "^(?<name>[a-zA-Z]+):\\s*(?<left>[a-zA-Z]+)\\s*(?<operator>[+\\-*/])\\s*(?<right>[a-zA-Z]+)$");

  @Override
  public Monkey parse(String monkeyAsString, Map<String, Monkey> alreadyExistingMonkeys) {
    Matcher matcher = EXPRESSION_MONKEY_EXTRACTOR.matcher(monkeyAsString);
    if (!matcher.matches()) {
      throw new IllegalStateException(
          "monkey-representation \"%s\" cannot be parsed".formatted(monkeyAsString));
    }
    Monkey left = checkAndGetLeft(alreadyExistingMonkeys, matcher);
    Monkey right = checkAndGetRight(alreadyExistingMonkeys, matcher);
    String name = matcher.group("name");
    Operator operator = Operator.of(matcher.group("operator").charAt(0));
    return new ExpressionMonkey(name, operator, left, right);
  }

  private static Monkey checkAndGetLeft(Map<String, Monkey> alreadyExistingMonkeys,
      Matcher matcher) {
    String leftName = matcher.group("left");
    return checkExistsAndGet(alreadyExistingMonkeys, leftName);
  }

  private static Monkey checkExistsAndGet(Map<String, Monkey> alreadyExistingMonkeys,
      String rightName) {
    Monkey monkey = alreadyExistingMonkeys.get(rightName);
    if (Objects.isNull(monkey)) {
      throw new IllegalStateException(
          "Monkey with name \"%s\" does not yet exist, but is needed".formatted(rightName));
    }
    return monkey;
  }

  private static Monkey checkAndGetRight(Map<String, Monkey> alreadyExistingMonkeys,
      Matcher matcher) {
    String rightName = matcher.group("right");
    return checkExistsAndGet(alreadyExistingMonkeys, rightName);
  }
}
