package de.turing85.advent.of.code.tttt.day.twentytwo.supplier.impl;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("FromStringFieldsAndCommandsSupplier tests")
class FromStringFieldsAndCommandsSupplierTest {
  @DisplayName("throw on malformed map")
  @Test
  void throwOnMalformedMap() {
    // GIVEN
    String malformedInput =
        """
        . .

        1U1
        """;

    // WHEN & THEN
    assertThrows(
        IllegalStateException.class, () -> new FromStringFieldsAndCommandsSupplier(malformedInput));
  }

  @DisplayName("throw on missing last steps in command")
  @Test
  void throwOnMissingLastStepsInCommand() {
    // GIVEN
    String malformedInput =
        """
        .

        1U
        """;

    // WHEN & THEN
    assertThrows(
        IllegalStateException.class, () -> new FromStringFieldsAndCommandsSupplier(malformedInput));
  }

  @DisplayName("throw on malformed command")
  @Test
  void throwOnMalformedCommand() {
    // GIVEN
    String malformedInput =
        """
        .

        1UU1
        """;

    // WHEN & THEN
    assertThrows(
        IllegalStateException.class, () -> new FromStringFieldsAndCommandsSupplier(malformedInput));
  }
}
