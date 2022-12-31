package de.turing85.advent.of.code.tttt.day.four.model;

/**
 * Represents an interval of whole number, both inclusive.
 *
 * @param lower the lower (inclusive) bound of the interval
 * @param upper the upper (inclusive) bound of the interval
 */
public record Interval(int lower, int upper) {
  /**
   * Constructor.
   *
   * <p>{@code lower} must be {@code <= upper}.
   *
   * @param lower the lower (inclusive) bound of the interval
   * @param upper the upper (inclusive) bound of the interval
   */
  public Interval {
    if (lower > upper) {
      throw new IllegalArgumentException("lower must be <! upper");
    }
  }

  /**
   * Checks whether {@code this} {@link Interval} fully contains {@code that} {@link Interval}.
   *
   * <p>If two intervals are equal, they also contain each other.
   *
   * @param that the interval to check if it is contained.
   * @return {@code true}, if and only if {@code this.lower() <= that.lower() && that.upper() <=
   *     this.upper()}.
   */
  public boolean fullyContains(Interval that) {
    return this.lower() <= that.lower && that.upper() <= this.upper();
  }

  /**
   * Returns {@code true}, if {@code this} {@link Interval} overlaps with {@code that} {@link
   * Interval}.
   *
   * @param that the other {@link Interval} to check
   * @return {@code true} if and only if {@code (this.lower() <= that.lower() && that.lower() <=
   *     this.upper()) || (that.lower() <= this.lower() && this.lower() <= that.upper())}
   */
  public boolean overlap(Interval that) {
    return (this.lower() <= that.lower() && that.lower() <= this.upper())
        || (that.lower() <= this.lower() && this.lower() <= that.upper());
  }

  /**
   * Checks whether one {@link Interval} is contained by the other.
   *
   * <p>The order for this operation does not matter, it returns {@code true} if and only of {@code
   * lhs.fullyContains(rhs) || rhs.fullyContains(lhs)}.
   *
   * @param lhs the first {@link Interval} to check
   * @param rhs the second {@link Interval} to check
   * @return {@code true} if and only of {@code lhs.fullyContains(rhs) || rhs.fullyContains(lhs)}
   */
  public static boolean oneContainsTheOther(Interval lhs, Interval rhs) {
    return lhs.fullyContains(rhs) || rhs.fullyContains(lhs);
  }

  /**
   * Returns whether the two {@link Interval}s {@code lhs} and {@code rhs} overlap
   *
   * @param lhs the first {@link Interval}
   * @param rhs the second {@link Interval}
   * @return {@code true} if and only if {@code (lhs.lower() <= rhs.lower() && rhs.lower() <=
   *     lhs.upper()) || (rhs.lower() <= lhs.lower() && lhs.lower() <= rhs.upper())}
   */
  public static boolean overlap(Interval lhs, Interval rhs) {
    return lhs.overlap(rhs);
  }
}
