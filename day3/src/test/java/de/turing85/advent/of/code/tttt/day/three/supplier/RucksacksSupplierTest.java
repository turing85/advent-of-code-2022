package de.turing85.advent.of.code.tttt.day.three.supplier;

import static org.junit.jupiter.api.Assertions.assertThrows;

import de.turing85.advent.of.code.tttt.day.three.model.BadgeFinderTest;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("RucksacksSupplier tests")
public abstract class RucksacksSupplierTest extends BadgeFinderTest {
  public static final Path ILLEGAL_INPUT_ONE_PATH =
      Path.of("src/test/resources/illegalInputOne.txt");
  public static final URI ILLEGAL_INPUT_ONE_URI = ILLEGAL_INPUT_ONE_PATH.toUri();
  protected static String ILLEGAL_INPUT_ONE;

  public static final Path ILLEGAL_INPUT_TWO_PATH =
      Path.of("src/test/resources/illegalInputTwo.txt");
  public static final URI ILLEGAL_INPUT_TWO_URI = ILLEGAL_INPUT_TWO_PATH.toUri();
  protected static String ILLEGAL_INPUT_TWO;

  @BeforeAll
  static void rucksackSetup() throws IOException {
    ILLEGAL_INPUT_ONE = Files.readString(ILLEGAL_INPUT_ONE_PATH);
    ILLEGAL_INPUT_TWO = Files.readString(ILLEGAL_INPUT_TWO_PATH);
  }

  protected abstract RucksacksSupplier rucksacksSupplierForIllegalInputOne() throws Exception;

  protected abstract RucksacksSupplier rucksacksSupplierForIllegalInputTwo() throws Exception;

  @Test
  @DisplayName("throws exception on odd input length")
  void throwsOnOddInputLength() {
    // GIVEN: defaults

    // WHEN & THEN
    assertThrows(IllegalArgumentException.class, this::rucksacksSupplierForIllegalInputOne);
  }

  @Test
  @DisplayName("throws exception on odd input length")
  void throwsOnIllegalCharacters() {
    // GIVEN: defaults

    // WHEN & THEN
    assertThrows(IllegalArgumentException.class, this::rucksacksSupplierForIllegalInputTwo);
  }
}
