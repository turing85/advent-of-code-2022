package de.turing85.advent.of.code.tttt.day.nine.model;

import static com.google.common.truth.Truth.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("Point tests")
class PointTest {
  @ParameterizedTest(name = "move {0} works correctly")
  @DisplayName("move works correctly")
  @CsvSource({"UP,0,1", "DOWN,0,-1", "RIGHT,1,0", "LEFT,-1,0"})
  void moveWorksCorrectly(Direction direction, int expectedX, int expectecY) {
    // GIVEN
    Point expected = new Point(expectedX, expectecY);

    // WHEN
    Point actual = new Point().move(direction);

    // THEN
    assertThat(actual).isEqualTo(expected);
  }
}
