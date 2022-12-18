package de.turing85.advent.of.code.tttt.day.one.model;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Backpack tests")
class ExpeditionTest {

  private static final Random RANDOM = new Random();
  private static final Supplier<List<Integer>> RANDOM_VALUE_SUPPLIER =
      () -> RANDOM.ints(10, 0, Integer.MAX_VALUE).boxed().toList();

  @Test
  @DisplayName("clone clones correctly")
  void cloneWorks() {
    // GIVEN
    List<Backpack> backpacks = List.of(new Backpack(RANDOM_VALUE_SUPPLIER.get()),
        new Backpack(RANDOM_VALUE_SUPPLIER.get()));
    Expedition expected = new Expedition(backpacks);

    // WHEN
    Expedition actual = new Expedition(expected);

    // THEN
    assertThat(actual.getHighestCaloriesFromAllBackpacks())
        .isEqualTo(expected.getHighestCaloriesFromAllBackpacks());
  }
}
