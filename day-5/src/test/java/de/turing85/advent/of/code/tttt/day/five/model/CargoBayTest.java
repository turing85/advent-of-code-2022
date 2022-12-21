package de.turing85.advent.of.code.tttt.day.five.model;

import static com.google.common.truth.Truth.assertThat;

import de.turing85.advent.of.code.tttt.day.five.supplier.NumberedStacksSupplier;
import de.turing85.advent.of.code.tttt.day.five.supplier.impl.FromFileNumberedStackSupplier;
import de.turing85.advent.of.code.tttt.day.five.supplier.impl.FromFileUnloadInstructionsSupplier;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("CargoBay tests")
public class CargoBayTest {
  public static final Path COMMON_INPUT = Path.of("src/test/resources/commonInput.txt");
  public static final Path PERSONAL_INPUT = Path.of("src/test/resources/personalInput.txt");

  @Nested
  @DisplayName("Common Lifter")
  class CommonLifter {
    private static final List<Character> COMMON_RESULT = List.of('C', 'M', 'Z');
    private static final List<Character> PERSONAL_RESULT =
        List.of('Q', 'M', 'B', 'M', 'J', 'D', 'F', 'T', 'D');

    @DisplayName("correct result on common input")
    @Test
    void correctResultOnCommonInput() throws IOException {
      // GIVEN
      NumberedStacksSupplier numberedStacksSupplier =
          new FromFileNumberedStackSupplier(COMMON_INPUT);
      CargoBay cargoBay = new CargoBay(numberedStacksSupplier.get());
      List<UnloadInstruction> unloadInstructions =
          new FromFileUnloadInstructionsSupplier(COMMON_INPUT).get();

      // WHEN
      List<Character> actual = cargoBay.executeUnloadPlan(unloadInstructions);

      // THEN
      assertThat(actual).isEqualTo(COMMON_RESULT);
    }

    @DisplayName("correct result on personal input")
    @Test
    void correctResultOnPersonalInput() throws IOException {
      // GIVEN
      NumberedStacksSupplier numberedStacksSupplier =
          new FromFileNumberedStackSupplier(PERSONAL_INPUT);
      CargoBay cargoBay = new CargoBay(numberedStacksSupplier.get());
      List<UnloadInstruction> unloadInstructions =
          new FromFileUnloadInstructionsSupplier(PERSONAL_INPUT).get();

      // WHEN
      List<Character> actual = cargoBay.executeUnloadPlan(unloadInstructions);

      // THEN
      assertThat(actual).isEqualTo(PERSONAL_RESULT);
    }
  }

  @Nested
  @DisplayName("IT'S OVER 9000!")
  class LifterOver9000 {
    private static final List<Character> COMMON_RESULT_OVER_9000 = List.of('M', 'C', 'D');
    private static final List<Character> PERSONAL_RESULT_OVER_9000 =
        List.of('N', 'B', 'T', 'V', 'T', 'J', 'N', 'F', 'J');

    @DisplayName("correct result on common input")
    @Test
    void correctResultOnCommonInput() throws IOException {
      // GIVEN
      NumberedStacksSupplier numberedStacksSupplier =
          new FromFileNumberedStackSupplier(COMMON_INPUT);
      CargoBay cargoBay = new CargoBay(numberedStacksSupplier.get());
      List<UnloadInstruction> unloadInstructions =
          new FromFileUnloadInstructionsSupplier(COMMON_INPUT).get();

      // WHEN
      List<Character> actual = cargoBay.executeUnloadPlanOver9000(unloadInstructions);

      // THEN
      assertThat(actual).isEqualTo(COMMON_RESULT_OVER_9000);
    }

    @DisplayName("correct result on personal input")
    @Test
    void correctResultOnPersonalInput() throws IOException {
      // GIVEN
      NumberedStacksSupplier numberedStacksSupplier =
          new FromFileNumberedStackSupplier(PERSONAL_INPUT);
      CargoBay cargoBay = new CargoBay(numberedStacksSupplier.get());
      List<UnloadInstruction> unloadInstructions =
          new FromFileUnloadInstructionsSupplier(PERSONAL_INPUT).get();

      // WHEN
      List<Character> actual = cargoBay.executeUnloadPlanOver9000(unloadInstructions);

      // THEN
      assertThat(actual).isEqualTo(PERSONAL_RESULT_OVER_9000);
    }
  }
}
