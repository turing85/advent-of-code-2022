package de.turing85.advent.of.code.tttt.day.four.model;

import static com.google.common.truth.Truth.assertThat;

import de.turing85.advent.of.code.tttt.day.four.supplier.impl.FromFileIntervalPairSupplier;
import java.net.URI;
import java.nio.file.Path;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("IntervalContainChecker tests")
class IntervalContainCheckerTest {
  static final URI COMMON_INPUT_URI = Path.of("src/test/resources/commonInput.txt").toUri();
  private static final int COMMON_INPUT_CONTAIN_COUNT = 2;

  protected static final URI PERSONAL_INPUT_URI =
      Path.of("src/test/resources/personalInput.txt").toUri();
  private static final int PERSONAL_INPUT_CONTAIN_COUNT = 556;

  private final IntervalContainChecker UNDER_TEST = IntervalContainChecker.instance();

  @Test
  @DisplayName("correct contain on common input")
  void correctOnCommonInput() throws Exception {
    // GIVEN: defaults

    // WHEN
    int actual = UNDER_TEST
        .countIntervalContainment(new FromFileIntervalPairSupplier(COMMON_INPUT_URI).get());

    // THEN
    assertThat(actual).isEqualTo(COMMON_INPUT_CONTAIN_COUNT);
  }

  @Test
  @DisplayName("correct contain on personal input")
  void correctOnPersonalInput() throws Exception {
    // GIVEN: defaults

    // WHEN
    int actual = UNDER_TEST
        .countIntervalContainment(new FromFileIntervalPairSupplier(PERSONAL_INPUT_URI).get());

    // THEN
    assertThat(actual).isEqualTo(PERSONAL_INPUT_CONTAIN_COUNT);
  }
}
