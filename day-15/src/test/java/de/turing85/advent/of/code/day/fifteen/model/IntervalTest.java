package de.turing85.advent.of.code.day.fifteen.model;

import static com.google.common.truth.Truth.assertThat;

import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("Interval tests")
class IntervalTest {
  @DisplayName("fullyContains test")
  @ParameterizedTest(name = "({0},{1}) fully contains ({2},{3}) -> {4}")
  @CsvSource({"0,2,1,1,true", "0,1,2,3,false", "1,1,0,2,false"})
  void fullyContainsTest(long firstLower, long firstUpper, long secondLower, long secondUpper,
      boolean expected) {
    // GIVEN
    Interval first = new Interval(firstLower, firstUpper);
    Interval second = new Interval(secondLower, secondUpper);

    // WHEN
    boolean actual = first.fullyContains(second);

    // THEN
    assertThat(actual).isEqualTo(expected);
  }

  @DisplayName("canMergeTest")
  @ParameterizedTest(name = "({0},{1}) and ({2},{3}) can merge -> {4}")
  @CsvSource({"0,2,1,3,true", "0,1,2,3,true", "0,1,3,4,false", "1,3,0,2,true", "2,3,0,1,true"})
  void canMergeTest(long firstLower, long firstUpper, long secondLower, long secondUpper,
      boolean expected) {
    // GIVEN
    Interval first = new Interval(firstLower, firstUpper);
    Interval second = new Interval(secondLower, secondUpper);

    // WHEN
    boolean actual = first.canMergeWith(second);

    // THEN
    assertThat(actual).isEqualTo(expected);
  }

  @DisplayName("no merge possible")
  @Test
  void testNoMergePossible() {
    // GIVEN
    Interval first = new Interval(0, 1);
    Interval second = new Interval(3, 4);
    Set<Interval> expected = Set.of(first, second);

    // WHEN
    Set<Interval> actual = Interval.merge(expected);

    // THEN
    assertThat(actual).isEqualTo(expected);
  }
}
