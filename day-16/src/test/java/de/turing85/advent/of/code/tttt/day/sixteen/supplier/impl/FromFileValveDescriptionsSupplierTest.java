package de.turing85.advent.of.code.tttt.day.sixteen.supplier.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Path;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("FromFileValveDescriptionsSupplier tests")
class FromFileValveDescriptionsSupplierTest {
  @DisplayName("throw on illegal input")
  @Test
  void throwsOnIllegalInput() {
    // GIVEN
    Path path = Path.of("src/test/resources/illegalInput.txt");

    // WHEN & THEN
    assertThrows(IllegalStateException.class, () -> new FromFileValveDescriptionsSupplier(path));
  }
}
