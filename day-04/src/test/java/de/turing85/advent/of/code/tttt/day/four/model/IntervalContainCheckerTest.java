package de.turing85.advent.of.code.tttt.day.four.model;

import static com.google.common.truth.Truth.assertThat;

import de.turing85.advent.of.code.tttt.day.four.supplier.impl.FromFileIntervalPairSupplier;
import java.nio.file.Path;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("IntervalContainChecker tests")
class IntervalContainCheckerTest {
  public static final Path COMMON_INPUT = Path.of("src/test/resources/commonInput.txt");
  private static final int COMMON_INPUT_CONTAIN_COUNT = 2;
  public static final Path PERSONAL_INPUT = Path.of("src/test/resources/personalInput.txt");
  private static final int PERSONAL_INPUT_CONTAIN_COUNT = 556;

  private final IntervalContainChecker UNDER_TEST = IntervalContainChecker.instance();

  @DisplayName("correct contain on common input")
  @Test
  void correctOnCommonInput() throws Exception {
    // GIVEN

    // WHEN
    int actual =
        UNDER_TEST.countIntervalContainment(new FromFileIntervalPairSupplier(COMMON_INPUT).get());

    // THEN
    assertThat(actual).isEqualTo(COMMON_INPUT_CONTAIN_COUNT);
  }

  @DisplayName("correct contain on personal input")
  @Test
  void correctOnPersonalInput() throws Exception {
    // GIVEN

    // WHEN
    int actual =
        UNDER_TEST.countIntervalContainment(new FromFileIntervalPairSupplier(PERSONAL_INPUT).get());

    // THEN
    assertThat(actual).isEqualTo(PERSONAL_INPUT_CONTAIN_COUNT);
  }
}
