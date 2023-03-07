package de.turing85.advent.of.code.tttt.day.twentyone.model.impl;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import de.turing85.advent.of.code.tttt.day.twentyone.model.Monkey;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("BaseMonkey tests")
class BaseMonkeyTest {
  @DisplayName("throws on inverseEvaluateOnMonkeyNotContaining")
  @Test
  void throwsOnInverseEvaluateOnMonkeyNotContaining() {
    // GIVEN
    BaseMonkey underTest = new BaseMonkey("name", 1);

    // WHEN & THEN
    assertThrows(
        UnsupportedOperationException.class,
        () -> underTest.inverseEvaluateOnMonkeyNotContaining("name", 0L));
  }

  @DisplayName("whoDoesNotContain works when found")
  @Test
  void whoDoesNotContainWorksCorrectlyWhenFound() {
    // GIVEN
    BaseMonkey expected = new BaseMonkey("name", 1);

    // WHEN
    Monkey actual = expected.whoDoesNotContain(expected.name());

    // THEN
    assertThat(actual).isNull();
  }

  @DisplayName("whoDoesNotContain works when found")
  @Test
  void whoDoesNotContainWorksCorrectlyWhenNotFound() {
    // GIVEN
    BaseMonkey expected = new BaseMonkey("name", 1);

    // WHEN
    Monkey actual = expected.whoDoesNotContain("other");

    // THEN
    assertThat(actual).isSameInstanceAs(expected);
  }
}
