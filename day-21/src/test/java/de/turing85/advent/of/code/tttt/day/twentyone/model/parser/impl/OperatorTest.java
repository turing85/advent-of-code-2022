package de.turing85.advent.of.code.tttt.day.twentyone.model.parser.impl;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import de.turing85.advent.of.code.tttt.day.twentyone.model.impl.Operator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("Operator tests")
class OperatorTest {
  @Nested
  @DisplayName("factory method tests")
  class FactoryMethodTest {
    @DisplayName("returns expected value")
    @ParameterizedTest(name = "{0} = {1} -> OK")
    @CsvSource({"+,PLUS", "-,MINUS", "*,TIMES", "/,DIVIDE"})
    void returnsExpectedValue(char operatorAsChar, Operator expected) {
      // GIVEN

      // WHEN
      Operator actual = Operator.of(operatorAsChar);

      // THEN
      assertThat(actual).isSameInstanceAs(expected);
    }

    @DisplayName("throws on illegal input")
    @Test
    void throwsOnIllegalInput() {
      // GIVEN

      // WHEN & THE
      assertThrows(IllegalArgumentException.class, () -> Operator.of('?'));
    }
  }
}
