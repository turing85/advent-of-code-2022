package de.turing85.advent.of.code.tttt.day.three.model;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("BadgeFinder tests")
class BadgeFinderTest extends DuplicationCalculatorTest {
  private static final int COMMON_BADGE_SCORE = 70;
  private static final int PERSONAL_BADGE_SCORE = 2609;
  private static final BadgeFinder UNDER_TEST = BadgeFinder.instance();

  @DisplayName("throws on non-unique badge item")
  @Test
  void throwsOnNonUniqueBadgeItem() throws Exception {
    // GIVEN
    List<Rucksack> rucksacks = rucksacksSupplierForIllegalInputThree().get();

    // WHEN & THEN
    assertThrows(IllegalStateException.class, () -> UNDER_TEST.findBade(rucksacks, 2));
  }

  @DisplayName("throws on incorrect number of backpacks")
  @Test
  void throwsOnIncorrectNumberOfBackpacks() throws Exception {
    // GIVEN
    List<Rucksack> rucksacks = rucksacksSupplierForIllegalInputThree().get();

    // WHEN & THEN
    assertThrows(IllegalStateException.class, () -> UNDER_TEST.findBade(rucksacks, 3));
  }

  @DisplayName("correct badge on common input")
  @Test
  void correctBadgeOnCommonInput() throws Exception {
    // GIVEN

    // WHEN
    int actual = UNDER_TEST.findBade(rucksacksSupplierForCommonInput().get(), 3);

    // THEN
    assertThat(actual).isEqualTo(COMMON_BADGE_SCORE);
  }

  @DisplayName("correct badge on personal input")
  @Test
  void correctBadgeOnPersonalInput() throws Exception {
    // GIVEN

    // WHEN
    int actual = UNDER_TEST.findBade(rucksacksSupplierForPersonalInput().get(), 3);

    // THEN
    assertThat(actual).isEqualTo(PERSONAL_BADGE_SCORE);
  }
}
