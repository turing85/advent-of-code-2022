package de.turing85.advent.of.code.tttt.day.twentythree.supplier.impl;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("FromStringElvesSupplier tests")
class FromStringElvesSupplierTest {
  @DisplayName("throws on illegal input")
  @Test
  void throwsOnIllegalInput() {
    // GIVEN

    // WHEN & THEN
    assertThrows(IllegalStateException.class, () -> new FromStringElvesSupplier("?"));
  }
}
