package de.turing85.advent.of.code.tttt.day.eleven.parser;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.nio.file.Path;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("MonkeyParser test")
class MonkeyParserTest {
  @DisplayName("throws on parse error")
  @ParameterizedTest(name = "{0} -> throws")
  @CsvSource({
    "illegalInputHeaderMalformed.txt",
    "illegalInputItemsMalformed.txt",
    "illegalInputOperationMalformed.txt",
    "illegalInputTestMalformed.txt",
    "illegalInputIfTrueMalformed.txt",
    "illegalInputIfFalseMalformed.txt"
  })
  void throwsIfMalformed(String fileName) {
    // GIVEN
    Path path = Path.of("src/test/resources", fileName);

    // WHEN & THEN
    assertThrows(IllegalStateException.class, () -> MonkeysParser.parse(path));
  }
}
