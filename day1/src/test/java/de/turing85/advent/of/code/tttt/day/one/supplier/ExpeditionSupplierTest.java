package de.turing85.advent.of.code.tttt.day.one.supplier;

import com.google.common.truth.Truth;
import de.turing85.advent.of.code.tttt.day.one.model.Expedition;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public abstract class ExpeditionSupplierTest {
  public static final Path ILLEGAL_INPUT_PATH = Path.of("src/test/resources/illegalExpedition.txt");
  public static final URI ILLEGAL_INPUT_URI = ILLEGAL_INPUT_PATH.toUri();
  protected static String ILLEGAL_INPUT;

  public static final Path COMMON_INPUT_PATH = Path.of("src/test/resources/commonExpedition.txt");
  public static final URI COMMON_INPUT_URI = COMMON_INPUT_PATH.toUri();
  public static String COMMON_INPUT;

  public static final Path PERSONAL_INPUT_PATH =
      Path.of("src/test/resources/personalExpedition.txt");
  protected static final URI PERSONAL_INPUT_URI = PERSONAL_INPUT_PATH.toUri();
  protected static String PERSONAL_INPUT;

  @BeforeAll
  static void setup() throws IOException {
    ILLEGAL_INPUT = Files.readString(ILLEGAL_INPUT_PATH);
    COMMON_INPUT = Files.readString(COMMON_INPUT_PATH);
    PERSONAL_INPUT = Files.readString(PERSONAL_INPUT_PATH);
  }

  protected abstract ExpeditionSupplier expeditionSupplierForIllegalInput() throws Exception;

  protected abstract ExpeditionSupplier expeditionSupplierForCommonInput() throws Exception;

  protected abstract ExpeditionSupplier expeditionSupplierForPersonalInput() throws Exception;

  @Test
  @DisplayName("throws execption on invalid input")
  void throwsOnNegativeBackpackValue() {
    // GIVEN: defaults

    // WHEN & THEN
    Assertions.assertThrows(IllegalArgumentException.class,
        this::expeditionSupplierForIllegalInput);
  }

  @Nested
  @DisplayName("getHighestCaloriesFromAllBackpacks tests")
  class GetHighestCaloriesFromAllBackpacksTest {
    public static final int COMMON_INPUT_RESULT = 24_000;
    public static final int PERSONAL_INPUT_RESULT = 70_296;

    @Test
    void testOnCommonInput() throws Exception {
      // GIVEN: defaults

      // WHEN
      Expedition expedition = expeditionSupplierForCommonInput().get();

      // THEN
      Truth.assertThat(expedition.getHighestCaloriesFromAllBackpacks())
          .isEqualTo(COMMON_INPUT_RESULT);
    }

    @Test
    void testOnPersonalInput() throws Exception {
      // GIVEN: defaults

      // WHEN
      Expedition expedition = expeditionSupplierForPersonalInput().get();

      // THEN
      Truth.assertThat(expedition.getHighestCaloriesFromAllBackpacks())
          .isEqualTo(PERSONAL_INPUT_RESULT);
    }
  }

  @Nested
  @DisplayName("getHighestCaloriesFromNHighestBackpacks tests")
  class GetHighestCaloriesFromNHighestBackpacksTest {
    public static final int COMMON_INPUT_RESULT = 45_000;
    public static final int PERSONAL_INPUT_RESULT = 205_381;

    @Test
    void testOnCommonInput() throws Exception {
      // GIVEN: defaults

      // WHEN
      Expedition expedition = expeditionSupplierForCommonInput().get();

      // THEN
      Truth.assertThat(expedition.getHighestCaloriesFromNHighestBackpacks(3))
          .isEqualTo(COMMON_INPUT_RESULT);
    }

    @Test
    void testOnPersonalInput() throws Exception {
      // GIVEN: defaults

      // WHEN
      Expedition expedition = expeditionSupplierForPersonalInput().get();

      // THEN
      Truth.assertThat(expedition.getHighestCaloriesFromNHighestBackpacks(3))
          .isEqualTo(PERSONAL_INPUT_RESULT);
    }
  }
}
