package de.turing85.advent.of.code.tttt.day.eighteen.supplier.impl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("FromStringPointsSupplier tests")
class FromStringPointsSupplierTest {
  @Test
  @DisplayName("throws on illegal input")
  void throwsOnIllegalInput() {
    // GIVEN
    String input = "ouch";

    // WHEN & THEN
    assertThrows(IllegalStateException.class, () -> new FromStringPointsSupplier(input));
  }
}
