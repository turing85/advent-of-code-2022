package de.turing85.advent.of.code.tttt.day.twentyfive.supplier.impl;

import de.turing85.advent.of.code.tttt.day.twentyfive.supplier.SnafusSupplier;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import lombok.Getter;

/** Reads {@code snafu} {@link String}s. */
@Getter
public class FromStringSnafusSupplier implements SnafusSupplier {
  private static final Pattern VALIDATION_PATTERN = Pattern.compile("^[210\\-=]+$");

  private final List<String> snafus;

  /**
   * Reads {@code snafu}-{@link String}s.
   *
   * @param inputAsString the {@code snafu}-{@link String}
   */
  public FromStringSnafusSupplier(String inputAsString) {
    snafus =
        Arrays.stream(inputAsString.split(System.lineSeparator()))
            .map(FromStringSnafusSupplier::validate)
            .toList();
  }

  private static String validate(String line) {
    if (!VALIDATION_PATTERN.matcher(line).matches()) {
      throw new IllegalStateException("line \"%s\" cannot be parsed".formatted(line));
    }
    return line;
  }
}
