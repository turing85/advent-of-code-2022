package de.turing85.advent.of.code.tttt.day.seven.model;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("File tests")
class FileTest {
  @Nested
  @DisplayName("Constructor tests")
  class ConstructorTest {
    @Test
    @DisplayName("negative file size leads to IllegalArgumentException")
    void illegalFileSize() {
      // GIVEN

      // WHEN & THEN
      assertThrows(IllegalArgumentException.class, () -> new File(null, -1));
    }

    @Test
    @DisplayName("constructs expected file")
    void constructsExpectedFile() {
      String expectedName = "expectedName";
      int expectedSize = new Random().nextInt(Integer.MAX_VALUE);

      // WHEN
      File actual = new File(expectedName, expectedSize);

      // THEN
      assertThat(actual.name()).isEqualTo(expectedName);
      assertThat(actual.size()).isEqualTo(expectedSize);
    }
  }
}
