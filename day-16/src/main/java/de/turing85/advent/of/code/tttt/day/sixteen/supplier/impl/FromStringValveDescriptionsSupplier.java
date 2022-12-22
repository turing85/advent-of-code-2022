package de.turing85.advent.of.code.tttt.day.sixteen.supplier.impl;

import de.turing85.advent.of.code.tttt.day.sixteen.model.ValveDescription;
import de.turing85.advent.of.code.tttt.day.sixteen.supplier.ValveDescriptionsSupplier;
import java.util.Arrays;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import lombok.Getter;

/**
 * Reads a {@link String}-representation {@link ValveDescription}s.
 */
@Getter
public class FromStringValveDescriptionsSupplier implements ValveDescriptionsSupplier {
  private static final Pattern DESCRIPTION_EXTRACTOR_PATTERN = Pattern.compile(
      "^Valve (?<name>[a-zA-Z0-9]+) has flow rate=(?<flowRate>\\d+); tunnels? leads? to valves? (?<neighbours>.*)$");

  private final Set<ValveDescription> valveDescriptions;

  /**
   * Reads a {@link String}-representation of {@link ValveDescription}s.
   *
   * @param inputAsString the {@link String}-representation of {@link ValveDescription}s
   */
  public FromStringValveDescriptionsSupplier(String inputAsString) {
    valveDescriptions = Arrays.stream(inputAsString.split(System.lineSeparator()))
        .map(FromStringValveDescriptionsSupplier::validateAndParse).collect(Collectors.toSet());
  }

  private static ValveDescription validateAndParse(String line) {
    Matcher matcher = DESCRIPTION_EXTRACTOR_PATTERN.matcher(line);
    if (!matcher.matches()) {
      throw new IllegalStateException("line \"%s\" cannot be parsed".formatted(line));
    }
    String name = matcher.group("name");
    int flowRate = Integer.parseInt(matcher.group("flowRate"));
    Set<String> neighbours = Set.of(matcher.group("neighbours").split(",\\s*"));
    return new ValveDescription(name, flowRate, neighbours);
  }
}
