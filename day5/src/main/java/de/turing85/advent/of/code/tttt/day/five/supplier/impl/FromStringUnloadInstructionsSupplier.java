package de.turing85.advent.of.code.tttt.day.five.supplier.impl;

import de.turing85.advent.of.code.tttt.day.five.model.UnloadInstruction;
import de.turing85.advent.of.code.tttt.day.five.supplier.UnloadInstructionsSupplier;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Reads a {@link String}-representation of {@link UnloadInstruction}s.
 */
public class FromStringUnloadInstructionsSupplier implements UnloadInstructionsSupplier {
  private static final Pattern UNLOAD_INSTRUCTION_EXTRACTOR =
      Pattern.compile("move (?<times>\\d+) from (?<from>\\d) to (?<to>\\d)");

  private final List<UnloadInstruction> unloadInstructions;

  /**
   * Reads a {@link String}-representation of {@link UnloadInstruction}s.
   *
   * @param inputAsString a {@link String}-representation of {@link UnloadInstruction}s.
   */
  public FromStringUnloadInstructionsSupplier(String inputAsString) {
    unloadInstructions = new ArrayList<>();
    for (String inputLine : inputAsString.split(System.lineSeparator())) {
      extractInstuctionFromLine(inputLine).ifPresent(unloadInstructions::add);
    }
  }

  private Optional<UnloadInstruction> extractInstuctionFromLine(String inputLine) {
    Matcher matcher = UNLOAD_INSTRUCTION_EXTRACTOR.matcher(inputLine);
    if (matcher.matches()) {
      int times = Integer.parseInt(matcher.group("times"));
      int from = Integer.parseInt(matcher.group("from"));
      int to = Integer.parseInt(matcher.group("to"));
      return Optional.of(new UnloadInstruction(times, from, to));
    }
    return Optional.empty();
  }

  @Override
  public List<UnloadInstruction> unloadInstructions() {
    return unloadInstructions;
  }
}
