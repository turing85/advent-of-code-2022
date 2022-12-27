package de.turing85.advent.of.code.day.twentyone.model;

import java.util.HashMap;
import java.util.Map;

/**
 * A parse tree of {@link Monkey}s, waiting on other {@link Monkey}s results, which is really just
 * an <a href="https://en.wikipedia.org/wiki/Abstract_syntax_tree">AST</a> for arithmetic
 * expressions.
 */
public class MonkeyTree {
  private MonkeyTree() {}

  public static long evaluate(Map<String, Monkey> monkeyMap, String monkeyName) {
    return monkeyMap.get(monkeyName).evaluate();
  }

  public static long solveFor(Map<String, Monkey> monkeyMap, String rootMonkey,
      String monkeyToSolveFor) {
    monkeyMap = new HashMap<>(monkeyMap);
    Monkey toSolveFor = monkeyMap.get(monkeyToSolveFor);
    Monkey root = monkeyMap.get(rootMonkey);
    Monkey follow = root.whoContains(monkeyToSolveFor);
    long value = root.whoDoesNotContain(monkeyToSolveFor).evaluate();
    root = follow;
    while (root != toSolveFor) {
      follow = root.whoContains(monkeyToSolveFor);
      value = root.inverseEvaluateOnMonkeyNotContaining(monkeyToSolveFor, value);
      root = follow;
    }
    return value;
  }
}
