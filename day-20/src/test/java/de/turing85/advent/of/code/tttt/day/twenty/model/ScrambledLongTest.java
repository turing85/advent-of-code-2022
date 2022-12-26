package de.turing85.advent.of.code.tttt.day.twenty.model;

import static com.google.common.truth.Truth.assertThat;

import java.util.Random;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ScrambledLongTest {
  @DisplayName("constructs expected value")
  @Test
  void constructsExpectedValue() {
    // GIVEN
    Random random = new Random();
    long expectedValue = random.nextLong();
    int expectedInitialIndex = random.nextInt(Integer.MAX_VALUE);

    // WHEN
    ScrambledLong actual = new ScrambledLong(expectedValue, expectedInitialIndex);

    // THEN
    assertThat(actual.value()).isEqualTo(expectedValue);
    assertThat(actual.initialIndex()).isEqualTo(expectedInitialIndex);
  }
}
