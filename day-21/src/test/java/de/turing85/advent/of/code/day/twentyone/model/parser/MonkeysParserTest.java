package de.turing85.advent.of.code.day.twentyone.model.parser;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import de.turing85.advent.of.code.day.twentyone.model.Monkey;
import de.turing85.advent.of.code.day.twentyone.model.impl.Operator;
import java.nio.file.Path;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("monkey parser tests")
class MonkeysParserTest {
  @DisplayName("throws on illegal input")
  @ParameterizedTest(name = "{0} -> IllegalStateException")
  @CsvSource({"illegalInputMissingId.txt", "illegalInputMalformedValue.txt"})
  void throwsOnIllegalInput(String fileName) {
    // GIVEN
    Path file = Path.of("src/test/resources/", fileName);

    // WHEN & THEN
    assertThrows(IllegalStateException.class, () -> new MonkeysParser(file));
  }

  @DisplayName("constructs expected monkeys")
  @ParameterizedTest(name = "{0} -> OK")
  @CsvSource({"+", "-", "*", "/"})
  void constructExpectedMonkeys(char operatorAsChar) {
    // GIVEN
    Random random = new Random();
    String expectedLeftName = "left";
    long expectedLeftValue = random.nextLong();
    String expectedRightName = "right";
    long expectedRightValue = random.nextLong();
    String expectedRootName = "root";
    Operator expectedRootOperator = Operator.of(operatorAsChar);
    long expectedRootValue =
        expectedRootOperator.applyAsLong(expectedLeftValue, expectedRightValue);
    String input =
        """
          %s: %s %c %s
          %s: %d
          %s: %d
        """
            .formatted(
                expectedRootName,
                expectedLeftName,
                operatorAsChar,
                expectedRightName,
                expectedLeftName,
                expectedLeftValue,
                expectedRightName,
                expectedRightValue);

    // WHEN
    Map<String, Monkey> actual = new MonkeysParser(input).get();

    // THEN
    assertThat(actual).hasSize(3);
    assertThat(actual.keySet())
        .containsAnyIn(Set.of(expectedLeftName, expectedRightName, expectedRootName));
    assertThat(actual.get(expectedLeftName).get()).isEqualTo(expectedLeftValue);
    assertThat(actual.get(expectedRightName).get()).isEqualTo(expectedRightValue);
    assertThat(actual.get(expectedRootName).get()).isEqualTo(expectedRootValue);
  }
}
