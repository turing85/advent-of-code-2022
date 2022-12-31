package de.turing85.advent.of.code.day.twentyone.model;

import java.util.function.Supplier;

/** Defines a monkey. */
public interface Monkey extends Supplier<Long> {
  /**
   * Getter.
   *
   * @return the monkey's name
   */
  String name();

  @Override
  default Long get() {
    return shout();
  }

  /**
   * Getter.
   *
   * @return the value (eventually) shouted by the monkey
   */
  default Long shout() {
    return evaluate();
  }

  /**
   * Getter.
   *
   * @return the value (eventually) shouted by the monkey
   */
  Long evaluate();

  /**
   * do the opposite of the actual operation
   *
   * @param monkeyName the monkey to not look for
   * @param value the values to execute the operation on
   * @return the result
   */
  Long inverseEvaluateOnMonkeyNotContaining(String monkeyName, long value);

  /**
   * Returns the (directly connected) {@link Monkey} that does contain the monkey with the {@code
   * monkeyName}. Should return {@code this} is the monkey is the monkey to look for.
   *
   * @param monkeyName the name of the {@link Monkey} to look for
   * @return the directly connected {@link Monkey} that contains the {@link Monkey} to look for
   */
  Monkey whoContains(String monkeyName);

  /**
   * Returns the (directly connected) {@link Monkey} that does not contain the monkey with the
   * {@code monkeyName}.
   *
   * @param monkeyName the name of the {@link Monkey} to look for
   * @return the directly connected {@link Monkey} that does not contain the {@link Monkey} to look
   *     for.
   */
  Monkey whoDoesNotContain(String monkeyName);
}
