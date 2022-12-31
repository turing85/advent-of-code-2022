package de.turing85.advent.of.code.tttt.day.thirteen.parser;

import de.turing85.advent.of.code.tttt.day.thirteen.model.Pair;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * A parser that can parse signal-pairs in {@code String}-representation from a file, represented as
 * {@code Path}, into a {@link List} of {@link Pair}s of {@link Object}s.
 *
 * <p>The fields {@link Pair#first()} and {@link Pair#second()} will be of type {@link Integer} or
 * {@link List} that contains only {@link Integer}s (possibly indirect, allowing nested lists).
 */
public class FromFileParser {
  private FromFileParser() {}

  /**
   * Parses the content form {@code inputAsFile}.
   *
   * @param inputFile the file, holding the {@link String}-representation to parse
   * @return the resulting {@link List} of {@link Pair}s of {@link Object}s.
   * @throws IOException if some I/O exception occurs when the file is read.
   */
  public static List<Pair<Object, Object>> parse(Path inputFile) throws IOException {
    return FromStringParser.parse(Files.readString(inputFile));
  }
}
