package de.turing85.advent.of.code.tttt.day.seven.model;

/**
 * A file.
 *
 * @param name the name of the file
 * @param size the size of the file ({@code >= 0}}
 */
public record File(String name, int size) {
  /**
   * Constructor
   *
   * @param name the name of the file
   * @param size the size of the file ({@code >= 0}}
   */
  public File {
    if (size < 0) {
      throw new IllegalArgumentException("File size cannot be < 0");
    }
  }
}
