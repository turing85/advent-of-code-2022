package de.turing85.advent.of.code.tttt.day.three.model;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import de.turing85.advent.of.code.tttt.day.three.supplier.RucksacksSupplier;
import de.turing85.advent.of.code.tttt.day.three.supplier.impl.FromFileRucksackSupplier;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("DuplicationCalculator tests")
public abstract class DuplicationCalculatorTest {
  private static final Path ILLEGAL_INPUT_THREE =
      Path.of("src/test/resources/illegalInputThree.txt");
  public static final Path COMMON_INPUT = Path.of("src/test/resources/commonInput.txt");
  private static final int COMMON_INPUT_DUPLICATION = 157;
  public static final Path PERSONAL_INPUT = Path.of("src/test/resources/personalInput.txt");
  private static final int PERSONAL_INPUT_DUPLICATION = 7727;

  private static final DuplicationCalculator UNDER_TEST = DuplicationCalculator.instance();

  protected RucksacksSupplier rucksacksSupplierForIllegalInputThree() throws IOException {
    return new FromFileRucksackSupplier(ILLEGAL_INPUT_THREE);
  }

  protected RucksacksSupplier rucksacksSupplierForCommonInput() throws IOException {
    return new FromFileRucksackSupplier(COMMON_INPUT);
  }

  protected RucksacksSupplier rucksacksSupplierForPersonalInput() throws IOException {
    return new FromFileRucksackSupplier(PERSONAL_INPUT);
  }

  @DisplayName("throws exception on more than one duplicate")
  @Test
  void throwsOnMoreThanOneDuplicate() throws Exception {
    // GIVEN
    List<Rucksack> rucksacks = rucksacksSupplierForIllegalInputThree().get();

    // WHEN & THEN
    assertThrows(IllegalStateException.class, () -> UNDER_TEST.calculateDuplication(rucksacks));
  }

  @DisplayName("correct duplication on common input")
  @Test
  void correctOnCommonInput() throws Exception {
    // GIVEN

    // WHEN
    int actual = UNDER_TEST.calculateDuplication(rucksacksSupplierForCommonInput().get());

    // THEN
    assertThat(actual).isEqualTo(COMMON_INPUT_DUPLICATION);
  }

  @DisplayName("correct duplication on personal input")
  @Test
  void correctOnPersonalInput() throws Exception {
    // GIVEN

    // WHEN
    int actual = UNDER_TEST.calculateDuplication(rucksacksSupplierForPersonalInput().get());

    // THEN
    assertThat(actual).isEqualTo(PERSONAL_INPUT_DUPLICATION);
  }
}
