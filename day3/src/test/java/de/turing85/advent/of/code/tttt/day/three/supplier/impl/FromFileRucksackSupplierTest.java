package de.turing85.advent.of.code.tttt.day.three.supplier.impl;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.net.URI;
import java.nio.file.Path;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("FromFileRucksackSupplier tests")
class FromFileRucksackSupplierTest {
  public static final Path ILLEGAL_INPUT_ONE_PATH =
      Path.of("src/test/resources/illegalInputOne.txt");
  public static final URI ILLEGAL_INPUT_ONE_URI = ILLEGAL_INPUT_ONE_PATH.toUri();

  public static final Path ILLEGAL_INPUT_TWO_PATH =
      Path.of("src/test/resources/illegalInputTwo.txt");
  public static final URI ILLEGAL_INPUT_TWO_URI = ILLEGAL_INPUT_TWO_PATH.toUri();

  @Test
  @DisplayName("throws exception on odd input length")
  void throwsOnOddInputLength() {
    // GIVEN: defaults

    // WHEN & THEN
    assertThrows(IllegalArgumentException.class,
        () -> new FromFileRucksackSupplier(ILLEGAL_INPUT_ONE_URI));
  }

  @Test
  @DisplayName("throws exception on odd input length")
  void throwsOnIllegalCharacters() {
    // GIVEN: defaults

    // WHEN & THEN
    assertThrows(IllegalArgumentException.class,
        () -> new FromFileRucksackSupplier(ILLEGAL_INPUT_TWO_URI));
  }
}
