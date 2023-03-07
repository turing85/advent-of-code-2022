package de.turing85.advent.of.code.tttt.day.twentyone.model.impl;

import de.turing85.advent.of.code.tttt.day.twentyone.model.Monkey;
import java.util.Objects;

/**
 * A base monkey, not waiting on any other monkey, just shouting a value.
 *
 * <p>In the sense of an <a href="https://en.wikipedia.org/wiki/Abstract_syntax_tree">AST</a>, this
 * is a value. So there is nothing to calculate.
 *
 * @param name the {@link Monkey#name()}
 * @param value the value
 */
public record BaseMonkey(String name, long value) implements Monkey {
  @Override
  public Long evaluate() {
    return value();
  }

  @Override
  public Long inverseEvaluateOnMonkeyNotContaining(String monkeyName, long value) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Monkey whoContains(String monkeyName) {
    if (Objects.equals(name(), monkeyName)) {
      return this;
    }
    return null;
  }

  @Override
  public Monkey whoDoesNotContain(String monkeyName) {
    if (Objects.equals(name(), monkeyName)) {
      return null;
    }
    return this;
  }
}
