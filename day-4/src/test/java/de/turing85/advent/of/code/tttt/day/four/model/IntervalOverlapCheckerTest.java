package de.turing85.advent.of.code.tttt.day.four.model;

import static com.google.common.truth.Truth.assertThat;
import static de.turing85.advent.of.code.tttt.day.four.model.IntervalContainCheckerTest.COMMON_INPUT;
import static de.turing85.advent.of.code.tttt.day.four.model.IntervalContainCheckerTest.PERSONAL_INPUT;

import de.turing85.advent.of.code.tttt.day.four.supplier.impl.FromFileIntervalPairSupplier;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("IntervalOverlapChecker tests")
class IntervalOverlapCheckerTest {
  private static final int COMMON_INPUT_OVERLAP_COUNT = 4;
  private static final int PERSONAL_INPUT_OVERLAP_COUNT = 876;

  private static final IntervalOverlapChecker UNDER_TEST = IntervalOverlapChecker.instance();

  @Test
  @DisplayName("correct overlap on common input")
  void correctOnCommonInput() throws Exception {
    // GIVEN

    // WHEN
    int actual =
        UNDER_TEST.countIntervalOverlaps(new FromFileIntervalPairSupplier(COMMON_INPUT).get());

    // THEN
    assertThat(actual).isEqualTo(COMMON_INPUT_OVERLAP_COUNT);
  }

  @Test
  @DisplayName("correct overlap on personal input")
  void correctOnPersonalInput() throws Exception {
    // GIVEN

    // WHEN
    int actual =
        UNDER_TEST.countIntervalOverlaps(new FromFileIntervalPairSupplier(PERSONAL_INPUT).get());

    // THEN
    assertThat(actual).isEqualTo(PERSONAL_INPUT_OVERLAP_COUNT);
  }
}
