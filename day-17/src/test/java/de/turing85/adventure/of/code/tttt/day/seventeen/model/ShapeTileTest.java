package de.turing85.adventure.of.code.tttt.day.seventeen.model;

import static com.google.common.truth.Truth.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("ShapeTile tests")
class ShapeTileTest {
  @Nested
  @DisplayName("move tests")
  class MoveTest {
    @DisplayName("move works")
    @ParameterizedTest(name = "{2} at ({0}, {1}) moving {3} ends up at ({4}, {5})")
    @CsvSource({"0,0,PLUS,UP,0,1", "0,0,H_BAR,DOWN,0,-1", "0,0,V_BAR,RIGHT,1,0",
        "0,0,SQUARE,LEFT,-1,0"})
    void moveWorks(int x, int y, Shape shape, Direction direction, int expectedX, int expectedY) {
      // GIVEN
      ShapeTile shapeTile = new ShapeTile(x, y, shape);

      // WHEN
      ShapeTile actual = shapeTile.move(direction);

      // THEN
      assertThat(actual.lowerLeftCorner().x()).isEqualTo(expectedX);
      assertThat(actual.lowerLeftCorner().y()).isEqualTo(expectedY);
    }
  }
}
