package de.turing85.advent.of.code.day.twentyone.model.impl;

import static com.google.common.truth.Truth.assertThat;

import de.turing85.advent.of.code.day.twentyone.model.Monkey;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("ExpressionMonkey tests")
class ExpressionMonkeyTest {
  @DisplayName("inverse evaluation works")
  @ParameterizedTest(name = "{0},{1} -> {2}")
  @CsvSource({"PLUS,left,1", "PLUS,right,2", "MINUS,left,5", "MINUS,right,-2", "TIMES,left,1",
      "TIMES,right,3", "DIVIDE,left,6", "DIVIDE,right,0"})
  void inverseEvaluationWorks(Operator operator, String monkeyName, long expected) {
    // GIVEN
    Monkey left = new BaseMonkey("left", 1L);
    Monkey right = new BaseMonkey("right", 2L);
    ExpressionMonkey underTest = new ExpressionMonkey("root", operator, left, right);

    // WHEN
    long actual = underTest.inverseEvaluateOnMonkeyNotContaining(monkeyName, 3L);

    // THEN
    assertThat(actual).isEqualTo(expected);
  }

  @Nested
  @DisplayName("whoDoesNotContain tests")
  class WhoDoesNotContainTest {
    @DisplayName("whoDoesNotContainWorks")
    @ParameterizedTest(name = "{0} -> {1}")
    @CsvSource({"notInLeft,left", "notInRight,right", "inBoth,null"})
    void whoDoesNotContainWorks(String monkeyName, String expectedMonkey) {
      // GIVEN
      Monkey notInLeft = new BaseMonkey("notInLeft", 1);
      Monkey notInRight = new BaseMonkey("notInRight", 2);
      Monkey inBoth = new BaseMonkey("inBoth", 3);
      Monkey left = new ExpressionMonkey("left", Operator.PLUS, notInRight, inBoth);
      Monkey right = new ExpressionMonkey("right", Operator.PLUS, notInLeft, inBoth);
      ExpressionMonkey underTest = new ExpressionMonkey("root", Operator.PLUS, left, right);
      Monkey expected =
          (expectedMonkey.equals("left") ? left : expectedMonkey.equals("right") ? right : null);

      // WHEN
      Monkey actual = underTest.whoDoesNotContain(monkeyName);

      // THEN
      assertThat(actual).isEqualTo(expected);
    }
  }
}
