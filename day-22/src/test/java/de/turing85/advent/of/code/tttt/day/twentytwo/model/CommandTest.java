package de.turing85.advent.of.code.tttt.day.twentytwo.model;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("Command tests")
class CommandTest {
  @DisplayName("throws on illegal parameter")
  @ParameterizedTest(name = "{0} -> IllegalArgumentException")
  @CsvSource({"UP", "DOWN"})
  void throwsOnIllegalParameter(Direction illegalRotation) {
    // GIVEN

    // WHEN & THEN
    assertThrows(IllegalArgumentException.class, () -> new Command(illegalRotation, 0));
  }
}
