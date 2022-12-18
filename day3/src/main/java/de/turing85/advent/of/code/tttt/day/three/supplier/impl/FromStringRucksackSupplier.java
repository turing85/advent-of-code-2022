package de.turing85.advent.of.code.tttt.day.three.supplier.impl;

import de.turing85.advent.of.code.tttt.day.three.model.Rucksack;
import de.turing85.advent.of.code.tttt.day.three.supplier.CharacterToPriorityConverter;
import de.turing85.advent.of.code.tttt.day.three.supplier.RucksacksSupplier;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Reads a {@link String}-representation of {@link Rucksack}s and constructs the corresponding
 * {@link java.util.Collection} of {@link Rucksack}s from it.
 */
public class FromStringRucksackSupplier implements RucksacksSupplier {
  private final List<Rucksack> rucksacks;

  /**
   * * Reads a {@link String}-representation of {@link Rucksack}s and constructs the corresponding *
   * {@link java.util.Collection} of {@link Rucksack}s from it.
   *
   * @param rucksacksAsString the {@link String}-representation fo {@link Rucksack}s
   */
  public FromStringRucksackSupplier(String rucksacksAsString) {
    rucksacks = Arrays.stream(rucksacksAsString.split(System.lineSeparator())).map(String::chars)
        .map(FromStringRucksackSupplier::charsToPriorityList).map(Rucksack::new).toList();
  }

  private static List<Integer> charsToPriorityList(IntStream chars) {
    return chars.map(CharacterToPriorityConverter.instance()::toPriority).boxed().toList();
  }

  @Override
  public List<Rucksack> rucksacks() {
    return rucksacks;
  }
}
