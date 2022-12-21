package de.turing85.advent.of.code.tttt.day.twelve.model.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Path;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("FromFileBoard test")
class FromFileBoardTest {
  @ParameterizedTest(name = "{0} -> illegalStateException")
  @DisplayName("throws on illegal input")
  @CsvSource({"illegalInputTwoEnds.txt", "illegalInputIllegalCharacter.txt",
      "illegalInputUnequalLineLengths.txt"})
  void throwsOnIllegalInput(String pathAsString) {
    // GIVEN
    Path path = Path.of("src/test/resources/", pathAsString);

    // WHEN & THEN
    assertThrows(IllegalStateException.class, () -> new FromFileBoard(path));
  }
}
