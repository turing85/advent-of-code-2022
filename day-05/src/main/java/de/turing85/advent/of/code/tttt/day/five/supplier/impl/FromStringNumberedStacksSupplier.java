package de.turing85.advent.of.code.tttt.day.five.supplier.impl;

import de.turing85.advent.of.code.tttt.day.five.supplier.NumberedStacksSupplier;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** Reads a {@link String}-representation of numbered stacks. */
public class FromStringNumberedStacksSupplier implements NumberedStacksSupplier {
  private static final Pattern STACK_ENTRY_EXTRACTOR =
      Pattern.compile("(?<stackContent>\\[[A-Z]]\\s?|\\s{4})");
  private static final Pattern STACK_CONTENT_TERMINATION_PATTERN =
      Pattern.compile("^(?:\\s\\d\\s{0,2})+$");

  private final Map<Integer, Deque<Character>> numberedStacks;

  /**
   * Reads a {@link String}-representation of numbered stacks.
   *
   * @param inputAsString a {@link String}-representation of numbered stacks.
   */
  public FromStringNumberedStacksSupplier(String inputAsString) {
    numberedStacks = new HashMap<>();
    Set<Integer> hasBox = new HashSet<>();
    for (String inputLine : inputAsString.split(System.lineSeparator())) {
      if (STACK_CONTENT_TERMINATION_PATTERN.matcher(inputLine).matches()) {
        break;
      }
      validateAndParseCargoLine(inputLine, hasBox);
    }
  }

  private void validateAndParseCargoLine(String inputLine, Set<Integer> hasBox) {
    Matcher entryMatcher = STACK_ENTRY_EXTRACTOR.matcher(inputLine);
    int index = 1;
    Set<Integer> mustHaveItem = new HashSet<>(hasBox);
    while (entryMatcher.find()) {
      String match = entryMatcher.group("stackContent");
      if (!match.isBlank()) {
        Character label = match.charAt(1);
        Deque<Character> stack = numberedStacks.getOrDefault(index, new LinkedList<>());
        stack.addFirst(label);
        numberedStacks.put(index, stack);
        hasBox.add(index);
        mustHaveItem.remove(index);
      }
      ++index;
    }
    if (!mustHaveItem.isEmpty()) {
      throw new IllegalStateException(
          "Stack(s) %s already has a box, boxes cannot float".formatted(mustHaveItem));
    }
  }

  @Override
  public Map<Integer, Deque<Character>> numberedStacks() {
    return numberedStacks;
  }
}
