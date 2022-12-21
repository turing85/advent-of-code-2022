package de.turing85.advent.of.code.tttt.day.fourteen.supplier.impl;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.nio.file.Path;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("FromFileLinesSupplier tests")
class FromFileLinesSupplierTest {
  @ParameterizedTest(name = "{0} -> IllegalStateException")
  @DisplayName("throws on illegal input")
  @CsvSource({"illegalInputNoFirstPoint.txt", "illegalInputNoSecondPoint.txt"})
  void throwsOnIllegalInput(String pathAsString) {
    // GIVEN
    Path path = Path.of("src/test/resources", pathAsString);

    // WHEN & THEN
    assertThrows(IllegalStateException.class, () -> new FromFileLinesSupplier(path));
  }
}
