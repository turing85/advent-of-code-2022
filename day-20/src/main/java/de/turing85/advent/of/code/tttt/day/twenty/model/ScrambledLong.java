package de.turing85.advent.of.code.tttt.day.twenty.model;

/**
 * Represents a scrambled number.
 *
 * @param value the integer value
 * @param initialIndex the initial index of {@code this} {@link ScrambledLong}
 */
public record ScrambledLong(long value, int initialIndex) {
  /**
   * Returns the (always positive) <a
   * href="https://en.wikipedia.org/wiki/Ring_(mathematics)">Ring</a> value of {@link #value()}.
   *
   * <p>The ring value is calculated by
   *
   * <pre>
   * (((int) (value() % (ringSize - 1)) + (ringSize - 1)) % (ringSize - 1)
   * </pre>
   *
   * @param ringSize the size of the ring
   * @return the positive ring value
   */
  public int ringValue(int ringSize) {
    int mod = ringSize - 1;
    return (((int) (value() % mod)) + mod) % mod;
  }
}
