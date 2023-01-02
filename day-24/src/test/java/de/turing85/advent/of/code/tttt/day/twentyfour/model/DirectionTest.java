package de.turing85.advent.of.code.tttt.day.twentyfour.model;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("Direction tests")
class DirectionTest {
  @DisplayName("throws on illegal argument")
  @Test
  void throwsOnIllegalArgument() {
    // GIVEN

    // WHEN & THEN
    assertThrows(IllegalArgumentException.class, () -> Direction.of('?'));
  }

  @DisplayName("from char conversion works on legal input")
  @ParameterizedTest(name = "char {0} is mapped to Direction.{1}")
  @CsvSource({"^,UP", "v,DOWN", ">,RIGHT", "<,LEFT"})
  void worksOnLegalInput(char c, Direction expected) {
    // GIVEN

    // WHEN
    Direction actual = Direction.of(c);

    // THEN
    assertThat(actual).isEqualTo(expected);
  }
}
