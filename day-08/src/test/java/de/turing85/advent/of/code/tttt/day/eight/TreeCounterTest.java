package de.turing85.advent.of.code.tttt.day.eight;

import static com.google.common.truth.Truth.assertThat;

import de.turing85.advent.of.code.tttt.day.eight.supplier.impl.FromFileTreeMapSupplier;
import java.io.IOException;
import java.nio.file.Path;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("TreeCounter tests")
class TreeCounterTest {
  public static final Path COMMON_INPUT = Path.of("src/test/resources/commonInput.txt");
  public static final Path PERSONAL_INPUT = Path.of("src/test/resources/personalInput.txt");

  @Nested
  @DisplayName("countVisibleTreesFromOutside tests")
  class CountVisibleTreesFromOutsideTest {
    @DisplayName("correct count on common input")
    @Test
    void correctCountOnCommonInput() throws IOException {
      // GIVEN

      // WHEN
      int actual =
          TreeCounter.countVisibleTreesFromOutside(new FromFileTreeMapSupplier(COMMON_INPUT).get());

      // THEN
      assertThat(actual).isEqualTo(21);
    }

    @DisplayName("correct count on common input")
    @Test
    void correctCountOnPersonalInput() throws IOException {
      // GIVEN

      // WHEN
      int actual = TreeCounter
          .countVisibleTreesFromOutside(new FromFileTreeMapSupplier(PERSONAL_INPUT).get());

      // THEN
      assertThat(actual).isEqualTo(1_870);
    }
  }

  @Nested
  @DisplayName("calculateHighestScenicScore tests")
  class CalculateHighestScenicScoreTest {
    @DisplayName("correct count on common input")
    @Test
    void correctCountOnCommonInput() throws IOException {
      // GIVEN

      // WHEN
      int actual =
          TreeCounter.calculateHighestScenicScore(new FromFileTreeMapSupplier(COMMON_INPUT).get());

      // THEN
      assertThat(actual).isEqualTo(8);
    }

    @DisplayName("correct count on common input")
    @Test
    void correctCountOnPersonalInput() throws IOException {
      // GIVEN

      // WHEN
      int actual = TreeCounter
          .calculateHighestScenicScore(new FromFileTreeMapSupplier(PERSONAL_INPUT).get());

      // THEN
      assertThat(actual).isEqualTo(517_440);
    }
  }
}
