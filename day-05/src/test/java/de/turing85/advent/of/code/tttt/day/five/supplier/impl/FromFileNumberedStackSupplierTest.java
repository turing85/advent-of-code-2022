package de.turing85.advent.of.code.tttt.day.five.supplier.impl;

import static com.google.common.truth.Truth.assertThat;
import static de.turing85.advent.of.code.tttt.day.five.model.CargoBayTest.COMMON_INPUT;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("FromFileNumberedStackSupplier tests")
class FromFileNumberedStackSupplierTest {
  private static final Path ILLEGAL_INPUT = Path.of("src/test/resources/illegalInput.txt");
  private static final Path SIMPLE_INPUT = Path.of("src/test/resources/simpleInput.txt");

  @DisplayName("throws on floating box")
  @Test
  void throwsOnFloatingBox() {
    // GIVEN

    // WHEN & THEN
    assertThrows(IllegalStateException.class,
        () -> new FromFileNumberedStackSupplier(ILLEGAL_INPUT));
  }

  @DisplayName("correct cargo on simple input")
  @Test
  void correctCargoOnSimpleInput() throws IOException {
    // GIVEN
    final Map<Integer, Deque<Character>> expected =
        Map.of(1, new LinkedList<>(List.of('C', 'B', 'A')));

    // WHEN
    final Map<Integer, Deque<Character>> actual =
        new FromFileNumberedStackSupplier(SIMPLE_INPUT).numberedStacks();

    // THEN
    assertThat(actual).isEqualTo(expected);
  }

  @DisplayName("correct cargo on common input")
  @Test
  void correctCargoOnCommonInput() throws IOException {
    // GIVEN
    final Map<Integer, Deque<Character>> expected = Map.of(1, new LinkedList<>(List.of('Z', 'N')),
        2, new LinkedList<>(List.of('M', 'C', 'D')), 3, new LinkedList<>(List.of('P')));

    // WHEN
    final Map<Integer, Deque<Character>> actual =
        new FromFileNumberedStackSupplier(COMMON_INPUT).numberedStacks();

    // THEN
    assertThat(actual).isEqualTo(expected);
  }
}
