package de.turing85.advent.of.code.tttt.day.four.supplier.impl;

import de.turing85.advent.of.code.tttt.day.four.model.Interval;
import de.turing85.advent.of.code.tttt.day.four.model.Pair;
import de.turing85.advent.of.code.tttt.day.four.supplier.IntervalPairsSupplier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Reads a {@link String}-representation of {@link Pair}s of {@link Interval}s and constructs the
 * corresponding {@link java.util.Collection} of {@link Pair}s from it.
 */
public class FromStringIntervalPairsSupplier implements IntervalPairsSupplier {
  private static final Pattern INTERVAL_PAIR_EXTRACTOR =
      Pattern.compile(
          "^\\s*(?<firstPairLower>\\d+)\\s*-\\s*(?<firstPairUpper>\\d+)\\s*,"
              + "\\s*(?<secondPairLower>\\d+)\\s*-\\s*(?<secondPairUpper>\\d+)\\s*$");

  private final Collection<Pair<Interval, Interval>> intervalPairs;

  /**
   * Reads a {@link String}-representation of {@link Pair}s of {@link Interval}s and constructs the
   * corresponding {@link java.util.Collection} of {@link Pair}s from it.
   *
   * @param inputAsString the {@link String}-representation of the {@link Pair}s.
   */
  public FromStringIntervalPairsSupplier(String inputAsString) {
    intervalPairs = new ArrayList<>();
    for (String line : inputAsString.split(System.lineSeparator())) {
      intervalPairs.add(validateAndExtractPair(line));
    }
  }

  private Pair<Interval, Interval> validateAndExtractPair(String line) {
    Matcher matcher = INTERVAL_PAIR_EXTRACTOR.matcher(line);
    if (matcher.matches()) {
      int firstLower = Integer.parseInt(matcher.group("firstPairLower"));
      int firstUpper = Integer.parseInt(matcher.group("firstPairUpper"));
      int secondLower = Integer.parseInt(matcher.group("secondPairLower"));
      int secondUpper = Integer.parseInt(matcher.group("secondPairUpper"));
      return new Pair<>(
          new Interval(firstLower, firstUpper), new Interval(secondLower, secondUpper));
    }
    throw new IllegalStateException("Line %s does not match expected format.".formatted(line));
  }

  @Override
  public Collection<Pair<Interval, Interval>> intervalPairs() {
    return intervalPairs;
  }
}
