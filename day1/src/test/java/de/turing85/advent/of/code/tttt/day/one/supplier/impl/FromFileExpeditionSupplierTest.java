package de.turing85.advent.of.code.tttt.day.one.supplier.impl;

import com.google.common.truth.Truth;
import de.turing85.advent.of.code.tttt.day.one.model.Expedition;
import de.turing85.advent.of.code.tttt.day.one.supplier.ExpeditionSupplier;
import java.io.IOException;
import java.nio.file.Path;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("FromFileExpeditionSupplier tests")
class FromFileExpeditionSupplierTest {
  public static final Path ILLEGAL_INPUT = Path.of("src/test/resources/illegalInput.txt");
  public static final Path COMMON_INPUT = Path.of("src/test/resources/commonInput.txt");
  public static final Path PERSONAL_INPUT = Path.of("src/test/resources/personalInput.txt");

  private ExpeditionSupplier expeditionSupplierForCommonInput() throws IOException {
    return new FromFileExpeditionSupplier(COMMON_INPUT);
  }

  private ExpeditionSupplier expeditionSupplierForPersonalInput() throws IOException {
    return new FromFileExpeditionSupplier(PERSONAL_INPUT);
  }

  @Test
  @DisplayName("throws exception on invalid input")
  void throwsOnNegativeBackpackValue() {
    // GIVEN

    // WHEN & THEN
    Assertions.assertThrows(IllegalArgumentException.class,
        () -> new FromFileExpeditionSupplier(ILLEGAL_INPUT));
  }

  @Nested
  @DisplayName("getHighestCaloriesFromAllBackpacks tests")
  class GetHighestCaloriesFromAllBackpacksTest {
    public static final int COMMON_INPUT_RESULT = 24_000;
    public static final int PERSONAL_INPUT_RESULT = 70_296;

    @Test
    void testOnCommonInput() throws Exception {
      // GIVEN

      // WHEN
      Expedition expedition = expeditionSupplierForCommonInput().get();

      // THEN
      Truth.assertThat(expedition.getHighestCaloriesFromAllBackpacks())
          .isEqualTo(COMMON_INPUT_RESULT);
    }

    @Test
    void testOnPersonalInput() throws Exception {
      // GIVEN

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
      // GIVEN

      // WHEN
      Expedition expedition = expeditionSupplierForCommonInput().get();

      // THEN
      Truth.assertThat(expedition.getHighestCaloriesFromNHighestBackpacks(3))
          .isEqualTo(COMMON_INPUT_RESULT);
    }

    @Test
    void testOnPersonalInput() throws Exception {
      // GIVEN

      // WHEN
      Expedition expedition = expeditionSupplierForPersonalInput().get();

      // THEN
      Truth.assertThat(expedition.getHighestCaloriesFromNHighestBackpacks(3))
          .isEqualTo(PERSONAL_INPUT_RESULT);
    }
  }
}
