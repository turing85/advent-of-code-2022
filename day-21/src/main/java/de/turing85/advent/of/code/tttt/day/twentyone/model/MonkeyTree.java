package de.turing85.advent.of.code.tttt.day.twentyone.model;

import java.util.HashMap;
import java.util.Map;

/**
 * A parse tree of {@link Monkey}s, waiting on other {@link Monkey}s results, which is really just
 * an <a href="https://en.wikipedia.org/wiki/Abstract_syntax_tree">AST</a> for arithmetic
 * expressions.
 */
public class MonkeyTree {
  private MonkeyTree() {}

  /**
   * Evaluates a given monkey tree, starting by the {@link Monkey} with name {@code monkeyName}.
   *
   * @param monkeyMap the {@link Monkey}s building the tree
   * @param monkeyName the name of the monkey
   * @return the result of the evaluation
   */
  public static long evaluate(Map<String, Monkey> monkeyMap, String monkeyName) {
    return monkeyMap.get(monkeyName).evaluate();
  }

  /**
   * Solves the tree such that the value of the {@link Monkey} {@code monkeyName} is set to a value
   * so that both sub-trees produce the same value.
   *
   * @param monkeyMap the {@link Monkey}s building the tree
   * @param rootMonkey the {@link Monkey} building the root of the tree
   * @param monkeyToSolveFor the monkey to solve for
   * @return the value for {@code monkeyToSolveFor}
   */
  public static long solveFor(
      Map<String, Monkey> monkeyMap, String rootMonkey, String monkeyToSolveFor) {
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
