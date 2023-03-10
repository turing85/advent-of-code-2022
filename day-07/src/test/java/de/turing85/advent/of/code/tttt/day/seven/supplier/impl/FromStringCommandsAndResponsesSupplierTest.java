package de.turing85.advent.of.code.tttt.day.seven.supplier.impl;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.nio.file.Path;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("FromStringCommandsAndResponsesSupplier tests")
class FromStringCommandsAndResponsesSupplierTest {
  @DisplayName("throws illegal state when commands do not start with a command")
  @Test
  void throwsWhenFirstCommandIsNoCommand() {
    // GIVEN
    Path input = Path.of("src/test/resources/illegalInput.txt");

    // WHEN & THEN
    assertThrows(
        IllegalStateException.class, () -> new FromFileCommandsAndResponsesSupplier(input));
  }
}
