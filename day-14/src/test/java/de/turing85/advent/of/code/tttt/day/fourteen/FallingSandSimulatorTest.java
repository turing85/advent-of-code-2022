package de.turing85.advent.of.code.tttt.day.fourteen;

import static com.google.common.truth.Truth.assertThat;

import de.turing85.advent.of.code.tttt.day.fourteen.supplier.LinesSupplier;
import de.turing85.advent.of.code.tttt.day.fourteen.supplier.impl.FromFileLinesSupplier;
import java.io.IOException;
import java.nio.file.Path;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("FallingSandSimulator tests")
class FallingSandSimulatorTest {
  private static final Path COMMON_INPUT = Path.of("src/test/resources/commonInput.txt");
  private static final Path PERSONAL_INPUT = Path.of("src/test/resources/personalInput.txt");

  @Nested
  @DisplayName("letSandFallUntilEverythingWillFallIntoTheAbyss tests")
  class LetSandFallUntilEverythingWillFallIntoTheAbyssTest {
    @Test
    @DisplayName("correct result on common input")
    void correctResultOnCommonInput() throws IOException {
      // GIVEN
      LinesSupplier supplier = new FromFileLinesSupplier(COMMON_INPUT);

      // WHEN
      int actual =
          new FallingSandSimulator(supplier.get()).letSandFallUntilEverythingWillFallIntoTheAbyss();

      // THEN
      assertThat(actual).isEqualTo(24);
    }

    @Test
    @DisplayName("correct result on personal input")
    void correctResultOnPersonalInput() throws IOException {
      // GIVEN
      LinesSupplier supplier = new FromFileLinesSupplier(PERSONAL_INPUT);

      // WHEN
      int actual =
          new FallingSandSimulator(supplier.get()).letSandFallUntilEverythingWillFallIntoTheAbyss();

      // THEN
      assertThat(actual).isEqualTo(1_406);
    }
  }

  @Nested
  @DisplayName("letSandFallUntilOriginIsBlocked tests")
  class LetSandFallUntilOriginIsBlockedTest {
    @Test
    @DisplayName("correct result on common input")
    void correctResultOnCommonInput() throws IOException {
      // GIVEN
      LinesSupplier supplier = new FromFileLinesSupplier(COMMON_INPUT);

      // WHEN
      int actual = new FallingSandSimulator(supplier.get()).letSandFallUntilOriginIsBlocked();

      // THEN
      assertThat(actual).isEqualTo(93);
    }

    @Test
    @DisplayName("correct result on personal input")
    void correctResultOnPersonalInput() throws IOException {
      // GIVEN
      LinesSupplier supplier = new FromFileLinesSupplier(PERSONAL_INPUT);

      // WHEN
      int actual = new FallingSandSimulator(supplier.get()).letSandFallUntilOriginIsBlocked();

      // THEN
      assertThat(actual).isEqualTo(20_870);
    }
  }
}
