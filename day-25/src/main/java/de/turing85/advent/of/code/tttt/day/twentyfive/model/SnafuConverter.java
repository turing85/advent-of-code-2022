package de.turing85.advent.of.code.tttt.day.twentyfive.model;

/** Utility class to convert from and to snafu. */
public class SnafuConverter {
  private SnafuConverter() {}

  /**
   * Converts a snafu-representation to int.
   *
   * @param snafu snafu-representation
   * @return int-value
   */
  public static long snafuToLong(String snafu) {
    long result = 0;
    for (char c : snafu.toCharArray()) {
      int digit = toDigit(c);
      result = result * 5 + digit;
    }
    return result;
  }

  private static int toDigit(char c) {
    return switch (c) {
      case '0' -> 0;
      case '1' -> 1;
      case '2' -> 2;
      case '-' -> -1;
      case '=' -> -2;
      default -> throw new IllegalArgumentException("must be in (2, 1, 0, -, =)");
    };
  }

  /**
   * Converts an int to snafu-representation
   *
   * @param value value
   * @return snafu-representation
   */
  public static String longToSnafu(long value) {
    int digits = findNeededDigits(value);
    StringBuilder result = new StringBuilder();
    while (digits >= 0) {
      long positionValue = positionValue(digits);
      long maxDelta = (digits > 0 ? max(digits - 1) : 0);
      int digit;
      int signum = Long.signum(value);
      long absValue = Math.abs(value);
      if (positionValue - maxDelta <= absValue && absValue <= positionValue + maxDelta) {
        digit = 1;
      } else if (2L * positionValue - maxDelta <= absValue) {
        digit = 2;
      } else {
        digit = 0;
      }
      value -= signum * digit * positionValue;
      result.append(toSnafuDigit(signum * digit));
      --digits;
    }
    return result.toString();
  }

  private static int findNeededDigits(long value) {
    int digit = 0;
    while (true) {
      Range range = range(digit);
      if (value <= range.max()) {
        break;
      }
      digit++;
    }
    return digit;
  }

  private static long positionValue(int position) {
    long power = 1;
    for (int i = 0; i < position; ++i) {
      power *= 5;
    }
    return power;
  }

  private static long max(int position) {
    if (position == 0) {
      return 2;
    }
    return 2 * positionValue(position) + max(position - 1);
  }

  private static long min(int position) {
    if (position == 0) {
      return 0;
    }
    return positionValue(position) - max(position - 1);
  }

  private static Range range(int position) {
    long min = min(position);
    long max = max(position);
    return new Range(min, max);
  }

  private static char toSnafuDigit(int digit) {
    return switch (digit) {
      case -2 -> '=';
      case -1 -> '-';
      case 0 -> '0';
      case 1 -> '1';
      case 2 -> '2';
      default -> throw new IllegalArgumentException("digit must be in [-2, 2]");
    };
  }

  private record Range(long min, long max) {}
}
