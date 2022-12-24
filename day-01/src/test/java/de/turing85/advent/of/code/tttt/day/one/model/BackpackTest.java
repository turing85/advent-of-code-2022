package de.turing85.advent.of.code.tttt.day.one.model;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Backpack tests")
class BackpackTest {
  @DisplayName("constructor throws in illegal input")
  @Test
  void throwsOnIllegalInput() {
    // GIVEN
    List<Integer> illegalInput = List.of(-1);

    // WHEN & THEN
    assertThrows(IllegalArgumentException.class, () -> new Backpack(illegalInput));
  }

  @DisplayName("get sum calculates the correct value")
  @Test
  void getSumWorks() {
    // GIVEN
    List<Integer> itemsCalories = List.of(1, 2, 3, 4, 5);
    int expected = 15;

    // WHEN
    Backpack actual = new Backpack(itemsCalories);

    // THEN
    assertThat(actual.getSumOfCalories()).isEqualTo(expected);
  }

  @DisplayName("clone clones correctly")
  @Test
  void cloneWorks() {
    // GIVEN
    List<Integer> randomValues = new Random().ints(10, 0, Integer.MAX_VALUE).boxed().toList();
    Backpack expected = new Backpack(randomValues);

    // WHEN
    Backpack actual = new Backpack(randomValues);

    // THEN
    assertThat(actual.getSumOfCalories()).isEqualTo(expected.getSumOfCalories());
  }
}
