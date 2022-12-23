package de.turing85.advent.of.code.tttt.day.thirteen.parser;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Path;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("FromFileParser tests")
class FromFileParserTest {
  @ParameterizedTest
  @DisplayName("throws on illegal input")
  @CsvSource({"illegalInputMissingBracket.txt", "illegalInputMissingComma.txt",
      "illegalInputNeitherListNorInt.txt", "illegalInputOneCommaTooMuch.txt",
      "illegalInputIllegalCharacterInList.txt"})
  void throwsOnIllegalInput(String pathAsString) {
    // GIVEN
    Path path = Path.of("src/test/resources", pathAsString);

    // WHEN & THEN
    IllegalStateException e =
        assertThrows(IllegalStateException.class, () -> FromFileParser.parse(path));
    System.out.println(e.getMessage());
  }
}
