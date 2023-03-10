package de.turing85.advent.of.code.tttt.day.four.supplier.impl;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.nio.file.Path;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("FromFileIntervalPairSupplier tests")
class FromFileIntervalPairSupplierTest {
  @DisplayName("show throw if upper bound of interval is smaller than lower bound")
  @Test
  void shouldThrowIfUpperBoundIsSmallerThanLowerBound() {
    // GIVEN
    Path inputFile = Path.of("src/test/resources/illegalInputOne.txt");

    // WHEN & THEN
    assertThrows(IllegalArgumentException.class, () -> new FromFileIntervalPairSupplier(inputFile));
  }

  @DisplayName("show throw if format of file is wrong")
  @Test
  void shouldThrowIfFormatOfFileIsWrong() {
    // GIVEN
    Path inputFile = Path.of("src/test/resources/illegalInputTwo.txt");

    // WHEN & THEN
    assertThrows(IllegalStateException.class, () -> new FromFileIntervalPairSupplier(inputFile));
  }
}
