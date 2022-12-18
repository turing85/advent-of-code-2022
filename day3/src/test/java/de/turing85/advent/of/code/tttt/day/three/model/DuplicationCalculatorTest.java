package de.turing85.advent.of.code.tttt.day.three.model;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import de.turing85.advent.of.code.tttt.day.three.supplier.RucksacksSupplier;
import de.turing85.advent.of.code.tttt.day.three.supplier.impl.FromFileRucksackSupplier;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Path;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("DuplicationCalculator tests")
public abstract class DuplicationCalculatorTest {

  private static final Path ILLEGAL_INPUT_THREE_PATH =
      Path.of("src/test/resources/illegalInputThree.txt");
  private static final URI ILLEGAL_INPUT_THREE_URI = ILLEGAL_INPUT_THREE_PATH.toUri();

  private static final URI COMMON_INPUT_URI = Path.of("src/test/resources/commonInput.txt").toUri();
  private static final int COMMON_INPUT_DUPLICATION = 157;

  protected static final URI PERSONAL_INPUT_URI =
      Path.of("src/test/resources/personalInput.txt").toUri();
  private static final int PERSONAL_INPUT_DUPLICATION = 7727;

  private static final DuplicationCalculator UNDER_TEST = DuplicationCalculator.instance();

  protected RucksacksSupplier rucksacksSupplierForIllegalInputThree() throws IOException {
    return new FromFileRucksackSupplier(ILLEGAL_INPUT_THREE_URI);
  }

  protected RucksacksSupplier rucksacksSupplierForCommonInput() throws IOException {
    return new FromFileRucksackSupplier(COMMON_INPUT_URI);
  }

  protected RucksacksSupplier rucksacksSupplierForPersonalInput() throws IOException {
    return new FromFileRucksackSupplier(PERSONAL_INPUT_URI);
  }

  @Test
  @DisplayName("throws exception on more than one duplicate")
  void throwsOnMoreThanOneDuplicate() throws Exception {
    // GIVEN
    List<Rucksack> rucksacks = rucksacksSupplierForIllegalInputThree().get();

    // WHEN & THEN
    assertThrows(IllegalStateException.class, () -> UNDER_TEST.calculateDuplication(rucksacks));
  }

  @Test
  @DisplayName("correct duplication on common input")
  void correctOnCommonInput() throws Exception {
    // GIVEN: defaults

    // WHEN
    int actual = UNDER_TEST.calculateDuplication(rucksacksSupplierForCommonInput().get());

    // THEN
    assertThat(actual).isEqualTo(COMMON_INPUT_DUPLICATION);
  }

  @Test
  @DisplayName("correct duplication on personal input")
  void correctOnPersonalInput() throws Exception {
    // GIVEN: defaults

    // WHEN
    int actual = UNDER_TEST.calculateDuplication(rucksacksSupplierForPersonalInput().get());

    // THEN
    assertThat(actual).isEqualTo(PERSONAL_INPUT_DUPLICATION);
  }
}
