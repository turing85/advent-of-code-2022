package de.turing85.advent.of.code.tttt.day.fifteen.supplier.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Path;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("FromFilePointPairsSupplier tests")
class FromFilePointPairsSupplierTest {
  @DisplayName("illegal input throws")
  @Test
  void illegalInputThrows() {
    // GIVEN
    Path path = Path.of("src/test/resources/illegalInput.txt");

    // WHEN & THEN
    assertThrows(IllegalStateException.class, () -> new FromFilePointPairsSupplier(path));
  }
}
