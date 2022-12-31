package de.turing85.advent.of.code.tttt.day.five.supplier;

import java.util.Deque;
import java.util.Map;
import java.util.function.Supplier;

/**
 * A supplier, supplying some numbered {@link Deque}s in the form of {@link Map}s with {@link
 * Integer}-keys.
 */
public interface NumberedStacksSupplier extends Supplier<Map<Integer, Deque<Character>>> {
  @Override
  default Map<Integer, Deque<Character>> get() {
    return numberedStacks();
  }

  /**
   * The numbered stacks to supply.
   *
   * @return the numbered stacks to supply
   */
  Map<Integer, Deque<Character>> numberedStacks();
}
