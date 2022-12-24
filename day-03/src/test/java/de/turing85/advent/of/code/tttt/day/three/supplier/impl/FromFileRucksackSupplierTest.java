package de.turing85.advent.of.code.tttt.day.three.supplier.impl;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.nio.file.Path;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("FromFileRucksackSupplier tests")
class FromFileRucksackSupplierTest {
  public static final Path ILLEGAL_INPUT_ONE = Path.of("src/test/resources/illegalInputOne.txt");
  public static final Path ILLEGAL_INPUT_TWO = Path.of("src/test/resources/illegalInputTwo.txt");

  @DisplayName("throws exception on odd input length")
  @Test
  void throwsOnOddInputLength() {
    // GIVEN

    // WHEN & THEN
    assertThrows(IllegalArgumentException.class,
        () -> new FromFileRucksackSupplier(ILLEGAL_INPUT_ONE));
  }

  @DisplayName("throws exception on odd input length")
  @Test
  void throwsOnIllegalCharacters() {
    // GIVEN

    // WHEN & THEN
    assertThrows(IllegalArgumentException.class,
        () -> new FromFileRucksackSupplier(ILLEGAL_INPUT_TWO));
  }
}
