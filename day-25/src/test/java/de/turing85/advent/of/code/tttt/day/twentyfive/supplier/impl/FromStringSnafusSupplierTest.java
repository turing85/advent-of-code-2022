package de.turing85.advent.of.code.tttt.day.twentyfive.supplier.impl;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("FromStringSnafusSupplier tests")
class FromStringSnafusSupplierTest {
  @DisplayName("throw on illegal input")
  @Test
  void throwsOnIllegalInput() {
    // GIVEN

    // WHEN & THEN
    assertThrows(IllegalStateException.class, () -> new FromStringSnafusSupplier("?"));
  }
}
