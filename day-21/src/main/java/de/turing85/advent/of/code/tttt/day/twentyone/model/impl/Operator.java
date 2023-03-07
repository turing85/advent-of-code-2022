package de.turing85.advent.of.code.tttt.day.twentyone.model.impl;

import java.util.function.LongBinaryOperator;

/** Mathematical operators ({@code +}, {@code -}, {@code *}, {@code /}). */
public enum Operator implements LongBinaryOperator {
  /** Plus ({@code +}) operator. */
  PLUS(Long::sum),

  /** Minus ({@code -}) operator. */
  MINUS((left, right) -> left - right),

  /** Times ({@code *}) operator. */
  TIMES((left, right) -> left * right),

  /** Divide ({@code /}) operator. */
  DIVIDE((left, right) -> left / right);

  private final LongBinaryOperator function;

  public static Operator of(Character c) {
    return switch (c) {
      case '+' -> PLUS;
      case '-' -> MINUS;
      case '*' -> TIMES;
      case '/' -> DIVIDE;
      default -> throw new IllegalArgumentException(
          "Character '%c' cannot be converted into an Operator".formatted(c));
    };
  }

  Operator(LongBinaryOperator function) {
    this.function = function;
  }

  @Override
  public long applyAsLong(long left, long right) {
    return function.applyAsLong(left, right);
  }
}
