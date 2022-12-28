package de.turing85.advent.of.code.tttt.day.twentytwo.model;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("Direction tests")
class DirectionTest {
  @Nested
  @DisplayName("factory method tests")
  class FactoryMethodTest {
    @DisplayName("works on legal input")
    @ParameterizedTest(name = "{0} -> {1}")
    @CsvSource({"U,UP", "D,DOWN", "R,RIGHT", "L,LEFT"})
    void worksOnLegalInput(char directionAsChar, Direction expected) {
      // GIVEN

      // WHEN
      Direction actual = Direction.of(directionAsChar);

      // THEN
      assertThat(actual).isSameInstanceAs(expected);
    }

    @DisplayName("throws on illegal parameter")
    @Test
    void throwsOnIllegalParameter() {
      // GIVEN

      // WHEN & THEN
      assertThrows(IllegalArgumentException.class, () -> Direction.of('?'));
    }
  }

  @Nested
  @DisplayName("rotation tests")
  class RotationTest {
    @DisplayName("works as expected")
    @ParameterizedTest(name = "{0} {1} -> {2}")
    @CsvSource({"UP,LEFT,LEFT", "DOWN,LEFT,RIGHT", "RIGHT,LEFT,UP", "LEFT,LEFT,DOWN",
        "UP,RIGHT,RIGHT", "DOWN,RIGHT,LEFT", "RIGHT,RIGHT,DOWN", "LEFT,RIGHT,UP"})
    void worksAsExpected(Direction initial, Direction rotation, Direction expected) {
      // GIVEN

      // WHEN
      Direction actual = initial.rotate(rotation);

      // THEN
      assertThat(actual).isSameInstanceAs(expected);
    }

    @DisplayName("throws on illegal parameter")
    @Test
    void throwsOnIllegalParameter() {
      // GIVEN

      // WHEN & THEN
      assertThrows(IllegalArgumentException.class, () -> Direction.RIGHT.rotate(Direction.UP));
    }

  }
}
