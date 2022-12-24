package de.turing85.advent.of.code.tttt.day.nine.supplier.impl;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.nio.file.Path;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("FromFileMovementCommandsSupplier tests")
class FromFileMovementCommandsSupplierTest {
  @DisplayName("throws on malformed input line")
  @Test
  void throwsOnMalformedInputLine() {
    // GIVEN
    Path input = Path.of("src/test/resources/illegalInputOne.txt");

    // WHEN & THEN
    assertThrows(IllegalStateException.class, () -> new FromFileMovementCommandsSupplier(input));
  }

  @DisplayName("throws on unknown direction")
  @Test
  void throwsOnUnknownDirection() {
    // GIVEN
    Path input = Path.of("src/test/resources/illegalInputTwo.txt");

    // WHEN & THEN
    assertThrows(IllegalStateException.class, () -> new FromFileMovementCommandsSupplier(input));
  }
}
