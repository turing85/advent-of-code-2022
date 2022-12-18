package de.turing85.advent.of.code.tttt.day.seven.supplier.impl;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.nio.file.Path;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("FromStringCommandsAndResponsesSupplier tests")
class FromStringCommandsAndResponsesSupplierTest {
  @Test
  @DisplayName("throws illegal state when commands do not start with a command")
  void throwsWhenFirstCommandIsNoCommand() {
    // GIVEN: nothing
    Path input = Path.of("src/test/resources/illegalInput.txt");

    // WHEN & THEN
    assertThrows(IllegalStateException.class,
        () -> new FromFileCommandsAndResponsesSupplier(input));
  }
}
