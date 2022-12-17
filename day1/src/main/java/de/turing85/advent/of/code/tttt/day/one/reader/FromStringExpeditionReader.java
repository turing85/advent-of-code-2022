package de.turing85.advent.of.code.tttt.day.one.reader;

import de.turing85.advent.of.code.tttt.day.one.model.Backpack;
import de.turing85.advent.of.code.tttt.day.one.model.Expedition;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Reads a {@link String}-representation of an expedition and constructs the corresponding
 * expedition from it.
 *
 * <p>
 * Backpacks in an expedition are separate by two {@link System#lineSeparator()}s, while items
 * within a backpack are separated by only one {@link System#lineSeparator()}.
 *
 */
public class FromStringExpeditionReader {
  private final Expedition expedition;

  /**
   * Given a {@link String}-representation of an expedition, constructs the corresponding expedition
   * from it.
   *
   * @param expeditionAsString a {@link String}-representation of an expedition.
   */
  public FromStringExpeditionReader(String expeditionAsString) {
    List<Backpack> backpacks = parseAndValidateBackpacks(expeditionAsString);
    this.expedition = new Expedition(backpacks);
  }

  private List<Backpack> parseAndValidateBackpacks(String expeditionAsString) {
    List<Backpack> backpacks = new ArrayList<>();
    for (String backpackContentAsString : expeditionAsString
        .split(System.lineSeparator() + System.lineSeparator())) {
      backpacks.add(parseAndValidateBackpack(backpackContentAsString));
    }
    return backpacks;
  }

  private Backpack parseAndValidateBackpack(String backpackContentAsString) {
    List<Integer> itemCalories =
        Arrays.stream(backpackContentAsString.split(System.lineSeparator())).map(Integer::parseInt)
            .toList();
    return new Backpack(itemCalories);
  }

  /**
   * Gets the expedition that was obtained by parsing the {@link String}-representation given to the
   * constructor.
   *
   * @return the {@link Expedition} that was obtained by parsing the {@link String}-representation
   *         given to the constructor.
   */
  public Expedition getExpedition() {
    return expedition;
  }
}
