package de.turing85.advent.of.code.tttt.day.five.model;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("UnloadInstruction tests")
class UnloadInstructionTest {
  private static final Random RANDOM = new Random();

  @Test
  @DisplayName("throws on times smaller 1")
  void throwsOnTimesSmallerOne() {
    // GIVEN: nothing

    // WHEN & THEN
    assertThrows(IllegalArgumentException.class, () -> new UnloadInstruction(0, 2, 3));
  }

  @Test
  @DisplayName("throws on from smaller 1")
  void throwsOnFromSmallerOne() {
    // GIVEN: nothing

    // WHEN & THEN
    assertThrows(IllegalArgumentException.class, () -> new UnloadInstruction(1, 0, 3));
  }

  @Test
  @DisplayName("throws on from larger 9")
  void throwsOnFromLargerNine() {
    // GIVEN: nothing

    // WHEN & THEN
    assertThrows(IllegalArgumentException.class, () -> new UnloadInstruction(1, 10, 3));
  }

  @Test
  @DisplayName("throws on to smaller 1")
  void throwsOnToSmallerOne() {
    // GIVEN: nothing

    // WHEN & THEN
    assertThrows(IllegalArgumentException.class, () -> new UnloadInstruction(1, 2, 0));
  }

  @Test
  @DisplayName("throws on to larger 9")
  void throwsOnToLargerNine() {
    // GIVEN: nothing

    // WHEN & THEN
    assertThrows(IllegalArgumentException.class, () -> new UnloadInstruction(1, 2, 10));
  }

  @Test
  @DisplayName("creates expected UnloadInstruction")
  void createExpectedUnloadInstruction() {
    // GIVEN
    int expectedTimes = RANDOM.nextInt(Integer.MAX_VALUE);
    int expectedFrom = RANDOM.nextInt(9) + 1;
    int expectedTo = RANDOM.nextInt(9) + 1;

    // WHEN
    UnloadInstruction instruction = new UnloadInstruction(expectedTimes, expectedFrom, expectedTo);

    // THEN
    assertThat(instruction.times()).isEqualTo(expectedTimes);
    assertThat(instruction.from()).isEqualTo(expectedFrom);
    assertThat(instruction.to()).isEqualTo(expectedTo);
  }
}
