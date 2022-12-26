package de.turing85.advent.of.code.tttt.day.twenty.model;

import static com.google.common.truth.Truth.assertThat;

import de.turing85.advent.of.code.tttt.day.twenty.supplier.impl.FromFileLongListSupplier;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class UnscramblerTest {
  private static final Path COMMON_INPUT = Path.of("src/test/resources/commonInput.txt");
  private static final Path PERSONAL_INPUT = Path.of("src/test/resources/personalInput.txt");

  @Nested
  @DisplayName("short unscramble test")
  class ShortUnscrambleTest {
    @DisplayName("expected value on common input")
    @Test
    void expectedValueOnCommonInput() throws IOException {
      // GIVEN
      List<Long> input = new FromFileLongListSupplier(COMMON_INPUT).get();

      // WHEN
      long actual = Unscrambler.decodeLocation(input, 1, 1);

      // THEN
      assertThat(actual).isEqualTo(3);
    }

    @DisplayName("expected value on personal input")
    @Test
    void expectedValueOnPersonalInput() throws IOException {
      // GIVEN
      List<Long> input = new FromFileLongListSupplier(PERSONAL_INPUT).get();

      // WHEN
      long actual = Unscrambler.decodeLocation(input, 1, 1);

      // THEN
      assertThat(actual).isEqualTo(7_584);
    }
  }

  @Nested
  @DisplayName("long unscramble test")
  class LongUnscrambleTest {
    @DisplayName("expected value on common input")
    @Test
    void expectedValueOnCommonInput() throws IOException {
      // GIVEN
      List<Long> input = new FromFileLongListSupplier(COMMON_INPUT).get();

      // WHEN
      long actual = Unscrambler.decodeLocation(input, 811_589_153L, 10);

      // THEN
      assertThat(actual).isEqualTo(1_623_178_306);
    }

    @DisplayName("expected value on personal input")
    @Test
    void expectedValueOnPersonalInput() throws IOException {
      // GIVEN
      List<Long> input = new FromFileLongListSupplier(PERSONAL_INPUT).get();

      // WHEN
      long actual = Unscrambler.decodeLocation(input, 811_589_153L, 10);

      // THEN
      assertThat(actual).isEqualTo(4_907_679_608_191L);
    }
  }
}
