package de.turing85.advent.of.code.tttt.day.fourteen.model;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Line test")
class LineTest {
  @Test
  @DisplayName("throws if x and y of points differ")
  void throwsIfXAndYOfPointsDiffer() {
    // GIVEN
    Point first = Point.of(0, 0);
    Point second = Point.of(1, 1);

    // WHEN & THEN
    assertThrows(IllegalArgumentException.class, () -> Line.of(first, second));
  }

  @Test
  @DisplayName("cache works correctly")
  void cacheWorksCorrectly() {
    // GIVEN
    Point first = Point.of(0, 0);
    Point second = Point.of(0, 1);
    Line expected = Line.of(first, second);

    // WHEN
    Line actual = Line.of(first, second);

    // THEN
    assertThat(actual).isSameInstanceAs(expected);
  }
}
