package de.turing85.advent.of.code.tttt.day.nine.model;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("Direction tests")
class DirectionTest {
  @Test
  @DisplayName("throws on illegal argument")
  void throwsOnIllegalArgument() {
    // GIVEN

    // WHEN & THEN
    assertThrows(IllegalArgumentException.class, () -> Direction.of('?'));
  }

  @ParameterizedTest(name = "char {0} is mapped to Direction.{1}")
  @DisplayName("from char conversion works on legal input")
  @CsvSource({"U,UP", "D,DOWN", "R,RIGHT", "L,LEFT"})
  void worksOnLegalInput(char c, Direction expected) {
    // GIVEN

    // WHEN
    Direction actual = Direction.of(c);

    // THEN
    assertThat(actual).isEqualTo(expected);
  }
}
