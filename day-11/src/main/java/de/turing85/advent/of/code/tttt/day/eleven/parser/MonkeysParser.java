package de.turing85.advent.of.code.tttt.day.eleven.parser;

import de.turing85.advent.of.code.tttt.day.eleven.model.Monkey;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.LongUnaryOperator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parser to parse a {@link String}-representation of a monkey to a {@link Monkey}-object.
 */
public class MonkeysParser {
  private static final String LINE_SEPARATOR = System.lineSeparator();
  private static final Pattern ID_PATTERN = Pattern.compile("^Monkey (?<id>\\d+):$");
  private static final Pattern STARTING_ITEMS_PATTERN =
      Pattern.compile("^\\s*Starting items:\\s?(?<items>(?:\\d+(?:, \\d+)*)?)");
  private static final Pattern OPERATOR_PATTERN =
      Pattern.compile("^\\s*Operation: new = old (?<operator>[+|*]) (?<operand>\\d+|old)$");
  private static final Pattern TEST_PATTERN =
      Pattern.compile("^\\s*Test: divisible by (?<operand>\\d+)$");
  private static final Pattern IF_TRUE_PATTERN =
      Pattern.compile("^\\s*If true: throw to monkey (?<id>\\d+)$");
  private static final Pattern IF_FALSE_PATTERN =
      Pattern.compile("^\\s*If false: throw to monkey (?<id>\\d+)$");

  private MonkeysParser() {}

  /**
   * * Parses {@link Monkey}s, represented as {@link String} from a file, represented as
   * {@link Path}.
   *
   * @param inputFile file holding the monkeys to parse.
   *
   * @return all monkeys parsed
   * @throws IOException if some I/O exception occurs when the file is read.
   */
  public static List<Monkey> parse(Path inputFile) throws IOException {
    return parse(Files.readString(inputFile));
  }

  /**
   * Parses {@link Monkey}s, represented as {@link String}.
   *
   * <p>
   * The {@link String} may (must) contain multiple monkeys. Each monkey is separated by the next
   * monkey by a blank line.
   *
   * <p>
   * A single monkey is composed of six lines, in the following form and order:
   *
   * <pre>
   *   Monkey 0:
   *   Starting items: 79, 98
   *   Operation: new = old * 19
   *   Test: divisible by 23
   *     If true: throw to monkey 2
   *     If false: throw to monkey 3
   * </pre>
   *
   * @param inputAsString monkeys to parse
   *
   * @return all monkeys parsed
   */
  public static List<Monkey> parse(String inputAsString) {
    MonkeyMaps monkeyMaps = validateAndConstructMonkey(inputAsString);
    return monkeyMaps.monkeyMap().values().stream().sorted(Comparator.comparing(Monkey::id))
        .toList();
  }

  private static MonkeyMaps validateAndConstructMonkey(String inputAsString) {
    Map<Integer, Monkey> monkeyMap = new HashMap<>();
    Map<Integer, Integer> trueFromToMap = new HashMap<>();
    Map<Integer, Integer> falseFromToMap = new HashMap<>();
    for (String monkeyAsString : inputAsString.split(LINE_SEPARATOR + LINE_SEPARATOR)) {
      List<String> monkeyLines = Arrays.asList(monkeyAsString.split(LINE_SEPARATOR));
      MonkeyWithTrueAndFalseId parseResult = validateAndExtractMonkeyData(monkeyLines);
      Monkey monkey = parseResult.monkey();
      int id = monkey.id();
      monkeyMap.put(id, monkey);
      trueFromToMap.put(id, parseResult.trueId());
      falseFromToMap.put(id, parseResult.falseId());
    }
    MonkeyMaps monkeyMaps = new MonkeyMaps(monkeyMap, trueFromToMap, falseFromToMap);
    doTheMonkeyConnect(monkeyMaps);
    return monkeyMaps;
  }

  private static MonkeyWithTrueAndFalseId validateAndExtractMonkeyData(List<String> monkeyLines) {
    int id = validateAndExtractId(monkeyLines.get(0));
    Deque<Long> startingItems =
        new LinkedList<>(validateAndExtractStartingItems(monkeyLines.get(1)));
    LongUnaryOperator operation = validateAndExtractOperation(monkeyLines.get(2));
    long testDivider = validateAndExtractTest(monkeyLines.get(3));
    Monkey monkey = new Monkey(id, startingItems, operation, testDivider);
    int trueId = validateAndExtractIfTrueId(monkeyLines.get(4));
    int falseId = validateAndExtractIfFalseId(monkeyLines.get(5));
    return new MonkeyWithTrueAndFalseId(monkey, trueId, falseId);
  }

  private static int validateAndExtractId(String headerLine) {
    Matcher matcher = ID_PATTERN.matcher(headerLine);
    if (matcher.matches()) {
      return Integer.parseInt(matcher.group("id"));
    } else {
      throw constructException(headerLine, "header");
    }
  }

  private static Collection<Long> validateAndExtractStartingItems(String startItemsLine) {
    Matcher matcher = STARTING_ITEMS_PATTERN.matcher(startItemsLine);
    if (matcher.matches()) {
      return Arrays.stream(matcher.group("items").split(", ")).map(Long::parseLong).toList();
    } else {
      throw constructException(startItemsLine, "starting-items");
    }
  }

  private static IllegalStateException constructException(String line, String lineType) {
    return new IllegalStateException("line \"%s\": not a monkey %s line".formatted(line, lineType));
  }

  private static LongUnaryOperator validateAndExtractOperation(String operationLine) {
    Matcher matcher = OPERATOR_PATTERN.matcher(operationLine);
    if (matcher.matches()) {
      String operatorAsString = matcher.group("operator");
      String operandAsString = matcher.group("operand");
      if (Objects.equals("old", operandAsString)) {
        return value -> value * value;
      } else {
        return constructOperationWithConstantOperand(operatorAsString, operandAsString);
      }
    } else {
      throw constructException(operationLine, "operation");
    }
  }

  private static LongUnaryOperator constructOperationWithConstantOperand(String operatorAsString,
      String operandAsString) {
    long operand = Long.parseLong(operandAsString);
    if (Objects.equals("+", operatorAsString)) {
      return value -> value + operand;
    }
    return value -> value * operand;
  }

  private static int validateAndExtractTest(String testLine) {
    Matcher matcher = TEST_PATTERN.matcher(testLine);
    if (matcher.matches()) {
      return Integer.parseInt(matcher.group("operand"));
    } else {
      throw constructException(testLine, "test");
    }
  }

  private static int validateAndExtractIfTrueId(String ifTrueLine) {
    Matcher matcher = IF_TRUE_PATTERN.matcher(ifTrueLine);
    if (matcher.matches()) {
      return Integer.parseInt(matcher.group("id"));
    } else {
      throw constructException(ifTrueLine, "if-true");
    }
  }

  private static int validateAndExtractIfFalseId(String ifFalseLine) {
    Matcher matcher = IF_FALSE_PATTERN.matcher(ifFalseLine);
    if (matcher.matches()) {
      return Integer.parseInt(matcher.group("id"));
    } else {
      throw constructException(ifFalseLine, "if-false");
    }
  }

  private static void doTheMonkeyConnect(MonkeyMaps monkeyMaps) {
    Map<Integer, Monkey> monkeyMap = monkeyMaps.monkeyMap();
    long itemModulo = monkeyMap.values().stream().mapToLong(Monkey::testDivider).reduce(1,
        (lhs, rhs) -> lhs * rhs);
    for (Monkey monkey : monkeyMap.values()) {
      int id = monkey.id();
      int trueId = monkeyMaps.trueFromToMap().get(id);
      int falseId = monkeyMaps.falseFromToMap().get(id);
      Monkey trueMonkey = monkeyMap.get(trueId);
      Monkey falseMonkey = monkeyMap.get(falseId);
      monkey.throwToIfPositiveTest(trueMonkey).throwToIfNegativeTest(falseMonkey)
          .itemModulo(itemModulo);
    }
  }

  private record MonkeyWithTrueAndFalseId(Monkey monkey, int trueId, int falseId) {}

  private record MonkeyMaps(Map<Integer, Monkey> monkeyMap, Map<Integer, Integer> trueFromToMap,
      Map<Integer, Integer> falseFromToMap) {}
}
