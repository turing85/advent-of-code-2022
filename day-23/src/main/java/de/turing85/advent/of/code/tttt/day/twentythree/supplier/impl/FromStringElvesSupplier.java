package de.turing85.advent.of.code.tttt.day.twentythree.supplier.impl;

import de.turing85.advent.of.code.tttt.day.twentythree.model.Elf;
import de.turing85.advent.of.code.tttt.day.twentythree.model.Point;
import de.turing85.advent.of.code.tttt.day.twentythree.supplier.ElvesSupplier;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import lombok.Getter;

/**
 * Reads a {@link String}-representation of a {@link Set} of {@link Elf}s.
 */
@Getter
public class FromStringElvesSupplier implements ElvesSupplier {
  private static final Pattern VALIDATION_PATTERN = Pattern.compile("^[.#]*$");

  private final Set<Elf> elves;

  /**
   * Reads a {@link String}-representation of a {@link Set} of {@link Elf}s.
   *
   * @param inputAsString the {@link String}-representation of a {@link Set} of {@link Elf}s
   */
  public FromStringElvesSupplier(String inputAsString) {
    elves = new HashSet<>();
    List<String> lines = Arrays.asList(inputAsString.split(System.lineSeparator()));
    for (int lineIndex = 0; lineIndex < lines.size(); ++lineIndex) {
      String line = lines.get(lineIndex);
      validateLine(line);
      elves.addAll(parseElvesFromLine(line, lineIndex));
    }
  }

  private Set<Elf> parseElvesFromLine(String line, int lineIndex) {
    Set<Elf> elvesFromLine = new HashSet<>();
    for (int colIndex = 0; colIndex < line.length(); ++colIndex) {
      char current = line.charAt(colIndex);
      if (current == '#') {
        elvesFromLine.add(new Elf(new Point(colIndex, lineIndex)));
      }
    }
    return elvesFromLine;
  }

  private void validateLine(String line) {
    if (!VALIDATION_PATTERN.matcher(line).matches()) {
      throw new IllegalStateException("line \"%s\" cannot be parsed".formatted(line));
    }
  }
}
