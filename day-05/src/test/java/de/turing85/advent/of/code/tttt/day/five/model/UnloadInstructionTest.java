package de.turing85.advent.of.code.tttt.day.five.model;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("UnloadInstruction tests")
class UnloadInstructionTest {
  private static final Random RANDOM = new Random();

  @DisplayName("throws on times smaller 1")
  @Test
  void throwsOnTimesSmallerOne() {
    // GIVEN

    // WHEN & THEN
    assertThrows(IllegalArgumentException.class, () -> new UnloadInstruction(0, 2, 3));
  }

  @DisplayName("throws on from smaller 1")
  @Test
  void throwsOnFromSmallerOne() {
    // GIVEN

    // WHEN & THEN
    assertThrows(IllegalArgumentException.class, () -> new UnloadInstruction(1, 0, 3));
  }

  @DisplayName("throws on from larger 9")
  @Test
  void throwsOnFromLargerNine() {
    // GIVEN

    // WHEN & THEN
    assertThrows(IllegalArgumentException.class, () -> new UnloadInstruction(1, 10, 3));
  }

  @DisplayName("throws on to smaller 1")
  @Test
  void throwsOnToSmallerOne() {
    // GIVEN

    // WHEN & THEN
    assertThrows(IllegalArgumentException.class, () -> new UnloadInstruction(1, 2, 0));
  }

  @DisplayName("throws on to larger 9")
  @Test
  void throwsOnToLargerNine() {
    // GIVEN

    // WHEN & THEN
    assertThrows(IllegalArgumentException.class, () -> new UnloadInstruction(1, 2, 10));
  }

  @DisplayName("creates expected UnloadInstruction")
  @Test
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
