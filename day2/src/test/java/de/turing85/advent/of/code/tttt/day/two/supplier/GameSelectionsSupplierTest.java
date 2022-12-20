package de.turing85.advent.of.code.tttt.day.two.supplier;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import de.turing85.advent.of.code.tttt.day.two.GameScorerTest;
import de.turing85.advent.of.code.tttt.day.two.model.GameSelection;
import java.util.Collection;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("GameSelectionsSupplier tests")
public abstract class GameSelectionsSupplierTest extends GameScorerTest {

  @Test
  @DisplayName("throws on illegal input")
  void throwsOnIllegalInput() {
    // GIVEN

    // WHEN & THEN
    assertThrows(IllegalArgumentException.class, this::gameSelectionsSupplierForIllegalInput);
  }

  @Test
  @DisplayName("creates expected result on common input")
  void validateCommonInput() throws Exception {
    // GIVEN

    // WHEN
    Collection<GameSelection> actual = gameSelectionsSupplierForCommonInput().get();

    // THEN
    assertThat(actual).isEqualTo(expectedGameSelectionForCommonInput());
  }

  protected abstract List<GameSelection> expectedGameSelectionForCommonInput();
}
