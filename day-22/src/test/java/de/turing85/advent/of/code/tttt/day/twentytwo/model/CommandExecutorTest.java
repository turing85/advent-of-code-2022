package de.turing85.advent.of.code.tttt.day.twentytwo.model;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import de.turing85.advent.of.code.tttt.day.twentytwo.model.impl.CubeNeighbourLinker;
import de.turing85.advent.of.code.tttt.day.twentytwo.model.impl.PlanarNeighbourLinker;
import de.turing85.advent.of.code.tttt.day.twentytwo.supplier.FieldsAndCommands;
import de.turing85.advent.of.code.tttt.day.twentytwo.supplier.impl.FromFileFieldsAndCommandsSupplier;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Collections;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("CommandExecutor tests")
class CommandExecutorTest {
  private static final Path COMMON_INPUT = Path.of("src/test/resources/commonInput.txt");
  private static final Path PERSONAL_INPUT = Path.of("src/test/resources/personalInput.txt");
  public static final PlanarNeighbourLinker NEIGHBOUR_LINKER = new PlanarNeighbourLinker();

  @DisplayName("throws on empty map")
  @Test
  void throwsOnEmptyMap() {
    // GIVEN

    // WHEN
    assertThrows(
        IllegalStateException.class,
        () ->
            new CommandExecutor(Collections.emptySet(), NEIGHBOUR_LINKER)
                .runCommands(Collections.emptyList()));
  }

  @DisplayName("planar neighbours tests")
  @Nested
  class PlanarNeighbourTest {
    private final PlanarNeighbourLinker neighbourLinker = NEIGHBOUR_LINKER;

    @DisplayName("correct result on common input")
    @Test
    void correctResultOnCommonInput() throws IOException {
      // GIVEN
      FieldsAndCommands fieldsAndCommands =
          new FromFileFieldsAndCommandsSupplier(COMMON_INPUT).get();

      // WHEN
      Position actual =
          new CommandExecutor(fieldsAndCommands.fields(), NEIGHBOUR_LINKER)
              .runCommands(fieldsAndCommands.commands());

      // THEN
      int actualValue =
          1000 * actual.field().y() + 4 * actual.field().x() + directionToIntValue(actual);
      assertThat(actualValue).isEqualTo(6_032);
    }

    @DisplayName("correct result on personal input")
    @Test
    void correctResultOnPersonalInput() throws IOException {
      // GIVEN
      FieldsAndCommands fieldsAndCommands =
          new FromFileFieldsAndCommandsSupplier(PERSONAL_INPUT).get();

      // WHEN
      Position actual =
          new CommandExecutor(fieldsAndCommands.fields(), neighbourLinker)
              .runCommands(fieldsAndCommands.commands());

      // THEN
      int actualValue =
          1000 * actual.field().y() + 4 * actual.field().x() + directionToIntValue(actual);
      assertThat(actualValue).isEqualTo(77_318);
    }
  }

  @DisplayName("cube neighbours tests")
  @Nested
  class CubeNeighbourTest {
    private static final NeighbourLinker NEIGHBOUR_LINKER = new CubeNeighbourLinker();

    @DisplayName("correct result on common input")
    @Test
    void correctResultOnCommonInput() throws IOException {
      // GIVEN
      FieldsAndCommands fieldsAndCommands =
          new FromFileFieldsAndCommandsSupplier(COMMON_INPUT).get();

      // WHEN
      Position actual =
          new CommandExecutor(fieldsAndCommands.fields(), NEIGHBOUR_LINKER)
              .runCommands(fieldsAndCommands.commands());

      // THEN
      int actualValue =
          1000 * actual.field().y() + 4 * actual.field().x() + directionToIntValue(actual);
      assertThat(actualValue).isEqualTo(5_031);
    }

    @DisplayName("correct result on personal input")
    @Test
    void correctResultOnPersonalInput() throws IOException {
      // GIVEN
      FieldsAndCommands fieldsAndCommands =
          new FromFileFieldsAndCommandsSupplier(PERSONAL_INPUT).get();

      // WHEN
      Position actual =
          new CommandExecutor(fieldsAndCommands.fields(), NEIGHBOUR_LINKER)
              .runCommands(fieldsAndCommands.commands());

      // THEN
      int actualValue =
          1000 * actual.field().y() + 4 * actual.field().x() + directionToIntValue(actual);
      assertThat(actualValue).isEqualTo(126_017);
    }
  }

  private static int directionToIntValue(Position actual) {
    return switch (actual.orientation()) {
      case RIGHT -> 0;
      case DOWN -> 1;
      case LEFT -> 2;
      case UP -> 3;
    };
  }
}
