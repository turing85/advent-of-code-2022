package de.turing85.advent.of.code.tttt.day.thirteen.parser;

import de.turing85.advent.of.code.tttt.day.thirteen.model.Pair;
import java.util.ArrayList;
import java.util.List;

/**
 * A parser that can parse signal-pairs in {@code String}-representation into a {@link List} of
 * {@link Pair}s of {@link Object}s.
 *
 * <p>
 * The fields {@link Pair#first()} and {@link Pair#second()} will be of type {@link Integer} or
 * {@link List} that contains only {@link Integer}s (possibly indirect, allowing nested lists).
 */
public class FromStringParser {
  private FromStringParser() {}

  private static final String LINE_SEPARATOR = System.lineSeparator();

  /**
   * Parses the {@code inputAsString}.
   *
   * @param inputAsString to parse
   *
   * @return the resulting {@link List} of {@link Pair}s of {@link Object}s.
   */
  public static List<Pair<Object, Object>> parse(String inputAsString) {
    List<Pair<Object, Object>> result = new ArrayList<>();
    for (String pairAsString : inputAsString.split(LINE_SEPARATOR + LINE_SEPARATOR)) {
      Pair<Object, Object> pair = parsePair(pairAsString);
      result.add(pair);
    }
    return result;
  }

  private static Pair<Object, Object> parsePair(String pairAsString) {
    String[] pairsAsString = pairAsString.split(LINE_SEPARATOR);

    String firstAsString = pairsAsString[0];
    Object first = parseLine(firstAsString);

    String secondAsString = pairsAsString[1];
    Object second = parseLine(secondAsString);

    return new Pair<>(first, second);
  }

  private static Object parseLine(String line) {
    return parseListOrInt(line, 0).result();
  }

  private static ParseResult parseListOrInt(String line, int index) {
    char currentChar = line.charAt(index);
    if (currentChar == '[') {
      ParseResult listContent = parseListContent(line, index + 1);
      index = listContent.nextIndex();
      if (index >= line.length()) {
        throw constructIllegalStateException(line, index, "]", "end of line");
      }
      currentChar = line.charAt(index);
      if (currentChar != ']') {
        throw constructIllegalStateException(line, index, "]", currentChar);
      }
      return new ParseResult(listContent.result(), index + 1);
    }
    if (Character.isDigit(currentChar)) {
      return parseInt(line, index);
    }
    throw constructIllegalStateException(line, index, "\"[\" or a digit", currentChar);
  }

  private static IllegalStateException constructIllegalStateException(String line, int index,
      String expected, char currentChar) {
    return constructIllegalStateException(line, index, expected, "\"" + currentChar + "\"");
  }

  private static IllegalStateException constructIllegalStateException(String line, int index,
      String expected, String current) {
    return new IllegalStateException("Line: \"%s\", colum %d: expected \"%s\", found %s"
        .formatted(line, index, expected, current));
  }

  private static ParseResult parseListContent(String line, int index) {
    List<Object> parsedListContent = new ArrayList<>();
    boolean isFirst = true;
    while (index < line.length() && isLegalListCharacter(line.charAt(index))) {
      index = skipBlanks(line, index);
      if (!isFirst) {
        index = readListSeparator(line, index);
        index = skipBlanks(line, index);
      }
      ParseResult parsedItemResult = parseListOrInt(line, index);
      parsedListContent.add(parsedItemResult.result());
      index = parsedItemResult.nextIndex();
      isFirst = false;
    }
    return new ParseResult(parsedListContent, index);
  }

  private static boolean isLegalListCharacter(char c) {
    return Character.isDigit(c) || c == ' ' || c == ',' || c == '[';
  }

  private static int readListSeparator(String line, int index) {
    char currentChar = line.charAt(index);
    if (currentChar != ',') {
      throw constructIllegalStateException(line, index, ",", currentChar);
    }
    return index + 1;
  }

  private static int skipBlanks(String line, int index) {
    while (line.charAt(index) == ' ') {
      ++index;
    }
    return index;
  }

  private static ParseResult parseInt(String line, int index) {
    StringBuilder digits = new StringBuilder();
    do {
      digits.append(line.charAt(index));
      ++index;
    } while (index < line.length() && Character.isDigit(line.charAt(index)));
    return new ParseResult(Integer.parseInt(digits.toString()), index);
  }

  private record ParseResult(Object result, int nextIndex) {}
}
