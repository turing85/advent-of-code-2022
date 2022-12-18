package de.turing85.advent.of.code.tttt.day.three.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Rucksack tests")
class RucksackTest {
  @Test
  @DisplayName("throws if priority is too small")
  void throwsIfPriorityIsTooSmall() {
    // GIVEN
    List<Integer> itemPriorities = List.of(1, 0);

    // WHEN & THEN
    assertThrows(IllegalArgumentException.class, () -> new Rucksack(itemPriorities));
  }

  @Test
  @DisplayName("throws if priority is too large")
  void throwsIfPriorityIsTooLarge() {
    // GIVEN
    List<Integer> itemPriorities = List.of(1, 53);

    // WHEN & THEN
    assertThrows(IllegalArgumentException.class, () -> new Rucksack(itemPriorities));
  }
}
