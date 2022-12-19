package de.turing85.advent.of.code.tttt.day.ten.model;

import lombok.Getter;

/**
 * A register.
 */
@Getter
public class Register {
  private int x;

  /**
   * Default constructor.
   */
  public Register() {
    this.x = 1;
  }

  /**
   * Adds {@code deltaX} to the {@code X} register
   *
   * @param deltaX delta to add (can be negative)
   *
   * @return {@code this}, for chaining.
   */
  public Register addToX(int deltaX) {
    x += deltaX;
    return this;
  }
}
