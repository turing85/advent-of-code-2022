package de.turing85.advent.of.code.tttt.day.twentyone.model.impl;

import de.turing85.advent.of.code.tttt.day.twentyone.model.Monkey;
import java.util.Objects;

/**
 * An expression {@link Monkey}, waiting for some the {@link Monkey}s {@code left} and {@code right}
 * to {@link Monkey#shout()}, feeding the shouted values into {@code operator}.
 *
 * <p>In terms of an <a href="https://en.wikipedia.org/wiki/Abstract_syntax_tree">AST</a>, this is
 * an inner node, representing an operation.
 *
 * @param name the {@link Monkey#name()}
 * @param operator the operator
 * @param left the left value for the {@code #operator()}
 * @param right the right value for the {@code #operator()}
 */
public record ExpressionMonkey(String name, Operator operator, Monkey left, Monkey right)
    implements Monkey {
  @Override
  public Long evaluate() {
    return operator().applyAsLong(left().evaluate(), right().evaluate());
  }

  @Override
  public Long inverseEvaluateOnMonkeyNotContaining(String monkeyName, long value) {
    if (Objects.equals(whoContains(monkeyName), right())) {
      return switch (operator()) {
        case PLUS -> value - left().evaluate();
        case MINUS -> left().evaluate() - value;
        case TIMES -> value / left().evaluate();
        case DIVIDE -> left().evaluate() / value;
      };
    } else {
      return switch (operator()) {
        case PLUS -> value - right().evaluate();
        case MINUS -> right().evaluate() + value;
        case TIMES -> value / right().evaluate();
        case DIVIDE -> value * right().evaluate();
      };
    }
  }

  @Override
  public Monkey whoContains(String monkeyName) {
    if (Objects.nonNull(left().whoContains(monkeyName))) {
      return left();
    }
    if (Objects.nonNull(right().whoContains(monkeyName))) {
      return right;
    }
    return null;
  }

  public Monkey whoDoesNotContain(String monkeyName) {
    if (Objects.isNull(left().whoContains(monkeyName))) {
      return left();
    }
    if (Objects.isNull(right().whoContains(monkeyName))) {
      return right();
    }
    return null;
  }
}
