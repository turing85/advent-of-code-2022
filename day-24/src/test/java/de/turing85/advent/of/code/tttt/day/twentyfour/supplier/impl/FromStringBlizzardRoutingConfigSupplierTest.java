package de.turing85.advent.of.code.tttt.day.twentyfour.supplier.impl;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("FromStringBlizzardRoutingConfigSupplier tests")
class FromStringBlizzardRoutingConfigSupplierTest {
  @DisplayName("throws on lillegal input")
  @Test
  void throwsOnIllegalInput() {
    // GIVEN

    // WHEN & THEN
    assertThrows(
        IllegalStateException.class, () -> new FromStringBlizzardRoutingConfigSupplier("OUCH"));
  }
}
