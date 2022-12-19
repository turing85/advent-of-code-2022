package de.turing85.advent.of.code.tttt.day.ten.supplier.impl;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.nio.file.Path;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("FromFileInstructionsWithParameterSupplier tests")
class FromFileInstructionsWithParameterSupplierTest {
  @Test
  @DisplayName("should throw if command cannot be parsed")
  void shouldThrowIfCommandCannotBeParsed() {
    // GIVEN
    Path path = Path.of("src/test/resources/illegalInputOne.txt");

    // WHEN & THEN
    assertThrows(IllegalStateException.class,
        () -> new FromFileInstructionsWithParameterSupplier(path));
  }

  @Test
  @DisplayName("should throw if addx is missing parameter")
  void shouldThrowIfAddxIsMissingParameter() {
    // GIVEN
    Path path = Path.of("src/test/resources/illegalInputTwo.txt");

    // WHEN & THEN
    assertThrows(IllegalStateException.class,
        () -> new FromFileInstructionsWithParameterSupplier(path));
  }
}
