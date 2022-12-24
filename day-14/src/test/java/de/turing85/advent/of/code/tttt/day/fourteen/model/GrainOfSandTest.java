package de.turing85.advent.of.code.tttt.day.fourteen.model;

import static com.google.common.truth.Truth.assertThat;

import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("GrainOfSalt test")
class GrainOfSandTest {
  @DisplayName("does not move if it cannot fall")
  @Test
  void doesNotMoveIfItCannotFall() {
    // GIVEN
    int x = 1;
    int y = 0;
    Set<Point> blocked = Set.of(Point.of(0, 1), Point.of(1, 1), Point.of(2, 1));
    GrainOfSand underTest = new GrainOfSand(Point.of(x, y), blocked, Integer.MAX_VALUE,
        Integer.MIN_VALUE, Integer.MAX_VALUE);

    // WHEN
    underTest.fall();

    // THEN
    assertThat(underTest.point()).isEqualTo(Point.of(x, y));
  }

  @DisplayName("will fall into the abyss")
  @ParameterizedTest(name = "({0}, {1}) -> falls into the abyss")
  @CsvSource({"-1,0", "0,1", "1,0"})
  void willFallIntoTheAbyss(int x, int y) {
    // GIVEN

    // WHEN
    boolean actual = new GrainOfSand(Point.of(x, y), Set.of(), 0, 0, 0).willFallIntoTheAbyss();

    // THEN
    assertThat(actual).isTrue();
  }
}
