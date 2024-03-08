package de.turing85.advent.of.code.tttt.day.twenty.supplier.impl;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("FromStringLongListSupplier tests")
class FromStringLongListSupplierTest {
  @DisplayName("throws on illegal input")
  @Test
  void throwsOnIllegalInput() {
    // GIVEN
    String input =
        """
        0,
        ouch,
        2,
        3""";

    // WHEN & THEN
    assertThrows(IllegalStateException.class, () -> new FromStringLongListSupplier(input));
  }

  @DisplayName("produces expected output")
  @Test
  void producesExpectedOutput() {
    // GIVEN
    Random random = new Random();
    List<Long> expected =
        List.of(
            random.nextLong(),
            random.nextLong(),
            random.nextLong(),
            random.nextLong(),
            random.nextLong());
    String input =
        expected.stream().map(Object::toString).collect(Collectors.joining(System.lineSeparator()));

    // WHEN
    List<Long> actual = new FromStringLongListSupplier(input).get();

    // THEN
    assertThat(actual).isEqualTo(expected);
  }
}
