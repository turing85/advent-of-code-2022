package de.turing85.advent.of.code.tttt.day.three.supplier;

/**
 * Converts a {@code char}, represented by its {@code codePoint}, to a priority.
 *
 * <p>
 * Rules for conversion:
 * <ul>
 * <li>lowercase letters ({@code [a-z]}) are mopped to {@code [1, 26]}</li>
 * <li>uppercase letters ({@code [A-Z]}) are mapped to {@code [27, 52]}</li>
 * </ul>
 */
public class CharacterToPriorityConverter {
  private static final CharacterToPriorityConverter INSTANCE = new CharacterToPriorityConverter();

  private CharacterToPriorityConverter() {}

  /**
   * Instance-getter.
   *
   * @return the singleton-instance of this class.
   */
  public static CharacterToPriorityConverter instance() {
    return INSTANCE;
  }

  /**
   * Converts a {@code char}, represented by its {@code codePoint}, to a priority.
   *
   * @param charAsCodePoint the char
   * @return its priority
   */
  public int toPriority(int charAsCodePoint) {
    if (Character.isLowerCase(charAsCodePoint)) {
      return charAsCodePoint - 'a' + 1;
    } else if (Character.isUpperCase(charAsCodePoint)) {
      return charAsCodePoint - 'A' + 27;
    } else {
      throw new IllegalArgumentException(
          "Character %c must be in [a-zA-Z]".formatted(charAsCodePoint));
    }
  }
}
