package de.turing85.advent.of.code.tttt.day.five.model;

/**
 * A single unload instruction. How many cargo elements ({@code times}) from which stack
 * ({@code source}) to which stack ({@code to}) should be moved.
 *
 * @param times how many items should be moved ({@code > 0})
 * @param from the source stack (in {@code [1, 9]})
 * @param to the target stack (in {@code  [1, 9]})
 */
public record UnloadInstruction(int times, int from, int to) {
  /**
   * Constructor.
   *
   * @param times how many items should be moved ({@code > 0})
   * @param from the source stack (in {@code [1, 9]})
   * @param to the target stack (in {@code  [1, 9]})
   */
  public UnloadInstruction {
    if (times <= 0) {
      throw new IllegalArgumentException("times must be > 0");
    }
    if (from < 1 || from > 9) {
      throw new IllegalArgumentException("from must be in [0, 9]");
    }
    if (to < 1 || to > 9) {
      throw new IllegalArgumentException("to must be in [0, 9]");
    }
  }
}
