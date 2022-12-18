package de.turing85.advent.of.code.tttt.day.one.supplier.impl;

import com.google.common.truth.Truth;
import de.turing85.advent.of.code.tttt.day.one.model.Expedition;
import de.turing85.advent.of.code.tttt.day.one.supplier.ExpeditionSupplier;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Path;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("FromFileExpeditionSupplier tests")
class FromFileExpeditionSupplierTest {
  public static final URI ILLEGAL_INPUT_URI =
      Path.of("src/test/resources/illegalInput.txt").toUri();

  public static final URI COMMON_INPUT_URI = Path.of("src/test/resources/commonInput.txt").toUri();

  protected static final URI PERSONAL_INPUT_URI =
      Path.of("src/test/resources/personalInput.txt").toUri();

  private ExpeditionSupplier expeditionSupplierForIllegalInput() throws IOException {
    return new FromFileExpeditionSupplier(ILLEGAL_INPUT_URI);
  }

  private ExpeditionSupplier expeditionSupplierForCommonInput() throws IOException {
    return new FromFileExpeditionSupplier(COMMON_INPUT_URI);
  }

  private ExpeditionSupplier expeditionSupplierForPersonalInput() throws IOException {
    return new FromFileExpeditionSupplier(PERSONAL_INPUT_URI);
  }

  @Test
  @DisplayName("throws exception on invalid input")
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
