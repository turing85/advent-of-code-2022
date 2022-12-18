package de.turing85.advent.of.code.tttt.day.three.model;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import de.turing85.advent.of.code.tttt.day.three.supplier.RucksacksSupplier;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("DuplicationCalculator tests")
public abstract class DuplicationCalculatorTest {

  public static final Path ILLEGAL_INPUT_THREE_PATH =
      Path.of("src/test/resources/illegalInputThree.txt");
  public static final URI ILLEGAL_INPUT_THREE_URI = ILLEGAL_INPUT_THREE_PATH.toUri();
  protected static String ILLEGAL_INPUT_THREE;

  public static final Path COMMON_INPUT_PATH = Path.of("src/test/resources/commonInput.txt");
  public static final URI COMMON_INPUT_URI = COMMON_INPUT_PATH.toUri();
  public static String COMMON_INPUT;
  public static final int COMMON_INPUT_DUPLICATION = 157;

  public static final Path PERSONAL_INPUT_PATH = Path.of("src/test/resources/personalInput.txt");
  protected static final URI PERSONAL_INPUT_URI = PERSONAL_INPUT_PATH.toUri();
  protected static String PERSONAL_INPUT;
  public static final int PERSONAL_INPUT_DUPLICATION = 7727;

  public static final DuplicationCalculator UNDER_TEST = DuplicationCalculator.instance();

  @BeforeAll
  public static void calculatorSetup() throws IOException {
    ILLEGAL_INPUT_THREE = Files.readString(ILLEGAL_INPUT_THREE_PATH);
    COMMON_INPUT = Files.readString(COMMON_INPUT_PATH);
    PERSONAL_INPUT = Files.readString(PERSONAL_INPUT_PATH);
  }

  protected abstract RucksacksSupplier rucksacksSupplierForIllegalInputThree() throws Exception;

  protected abstract RucksacksSupplier rucksacksSupplierForCommonInput() throws Exception;

  protected abstract RucksacksSupplier rucksacksSupplierForPersonalInput() throws Exception;

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
