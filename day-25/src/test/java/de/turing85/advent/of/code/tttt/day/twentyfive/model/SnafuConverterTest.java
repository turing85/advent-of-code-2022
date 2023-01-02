package de.turing85.advent.of.code.tttt.day.twentyfive.model;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import de.turing85.advent.of.code.tttt.day.twentyfive.supplier.impl.FromFileSnafusSupplier;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("SnafuConverter tests")
class SnafuConverterTest {
  private static final Path COMMON_INPUT = Path.of("src/test/resources/commonInput.txt");
  private static final Path PERSONAL_INPUT = Path.of("src/test/resources/personalInput.txt");

  @Nested
  @DisplayName("illegal input tests")
  class IllegalInputTest {
    @DisplayName("throws on illegal input")
    @Test
    void testOnIllegalInput() {
      // GIVEN

      // WHEN & THEN
      assertThrows(IllegalArgumentException.class, () -> SnafuConverter.snafuToLong("?"));
    }
  }

  @Nested
  @DisplayName("From - to - converter tests")
  class FromToConverterTest {
    @DisplayName("correct result on common input")
    @Test
    void correctResultOnCommonInput() throws IOException {
      // GIVEN
      List<String> snafus = new FromFileSnafusSupplier(COMMON_INPUT).get();

      // WHEN
      String actualSnafu =
          SnafuConverter.longToSnafu(snafus.stream().mapToLong(SnafuConverter::snafuToLong).sum());

      // THEN
      assertThat(actualSnafu).isEqualTo("2=-1=0");
    }

    @DisplayName("correct result on personal input")
    @Test
    void correctResultOnPersonalInput() throws IOException {
      // GIVEN
      List<String> snafus = new FromFileSnafusSupplier(PERSONAL_INPUT).get();

      // WHEN
      String actualSnafu =
          SnafuConverter.longToSnafu(snafus.stream().mapToLong(SnafuConverter::snafuToLong).sum());

      // THEN
      assertThat(actualSnafu).isEqualTo("20===-20-020=0001-02");
    }
  }
}
