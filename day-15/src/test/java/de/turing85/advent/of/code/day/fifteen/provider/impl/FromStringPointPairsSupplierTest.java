package de.turing85.advent.of.code.day.fifteen.provider.impl;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("FromStringPointPairsSupplier tests")
class FromStringPointPairsSupplierTest {
  @DisplayName("throws on illegal input")
  @Test
  void throwsOnIllegalInput() {
    // GIVEN
    String illegalInput = "ouch";

    // WHEN & THEN
    assertThrows(IllegalStateException.class, () -> new FromStringPointPairsSupplier(illegalInput));
  }
}
