package de.turing85.advent.of.code.tttt.day.twenty.supplier.impl;

import de.turing85.advent.of.code.tttt.day.twenty.supplier.LongListSupplier;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.Getter;

/**
 * Reads a {@link String}-representation of a {@link List} of {@link Long}s.
 */
public class FromStringLongListSupplier implements LongListSupplier {
  private static final Pattern NUMBER_EXTRACTOR = Pattern.compile("^-?\\d+$");
  @Getter
  private final List<Long> longList;

  /**
   * Reads a {@link String}-representation of a {@link List} of {@link Long}s.
   *
   * @param inputAsString the {@link String}-representation of a {@link List} of {@link Long}s
   */
  public FromStringLongListSupplier(String inputAsString) {
    longList = new ArrayList<>();
    for (String line : inputAsString.split(System.lineSeparator())) {
      Matcher matcher = NUMBER_EXTRACTOR.matcher(line);
      if (!matcher.matches()) {
        throw new IllegalStateException("line \"%s\" cannot be parsed".formatted(line));
      }
      longList.add(Long.parseLong(matcher.group()));
    }
  }
}
