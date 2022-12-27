package de.turing85.advent.of.code.day.twentyone.model.parser;

import de.turing85.advent.of.code.day.twentyone.model.Monkey;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import lombok.Getter;

/**
 * A class to parse monkeys.
 */
@Getter
public class MonkeysParser implements Supplier<Map<String, Monkey>> {
  private final Map<String, Monkey> monkeysByName;
  private final ServiceLoader<MonkeyParser> parserLoader;

  /**
   * Constructor.
   *
   * @param inputFile a file, holding the {@link String}-representations of {@link Monkey}s.
   *
   * @throws IOException if some I/O exception occurs when the file is read.
   */
  public MonkeysParser(Path inputFile) throws IOException {
    this(Files.readString(inputFile));
  }

  /**
   * Constructor.
   *
   * @param inputAsString a {@link String}-representations of {@link Monkey}s.
   */
  public MonkeysParser(String inputAsString) {
    monkeysByName = new HashMap<>();
    parserLoader = ServiceLoader.load(MonkeyParser.class);
    List<String> lines = Arrays.stream(inputAsString.split(System.lineSeparator()))
        .map(String::trim).collect(Collectors.toCollection(LinkedList::new));
    while (!lines.isEmpty()) {
      lines = tryParse(lines);
    }
  }

  private List<String> tryParse(List<String> lines) {
    Set<String> unparsed = new HashSet<>();
    for (String line : lines) {
      if (!tryParseLineWithAllAvailableParsers(line)) {
        unparsed.add(line);
      }
    }
    if (unparsed.containsAll(lines)) {
      throw new IllegalStateException(
          "Parsing got struck on the following lines: %s".formatted(lines));
    }
    return new ArrayList<>(unparsed);
  }

  private boolean tryParseLineWithAllAvailableParsers(String line) {
    for (MonkeyParser parser : parserLoader()) {
      try {
        Monkey monkey = parser.parse(line, monkeysByName);
        monkeysByName.put(monkey.name(), monkey);
        return true;
      } catch (Exception e) {
        // empty on purpose, is handled later
      }
    }
    return false;
  }

  @Override
  public Map<String, Monkey> get() {
    return monkeysByName();
  }
}
