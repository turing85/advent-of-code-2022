package de.turing85.advent.of.code.tttt.day.two;

import static com.google.common.truth.Truth.assertThat;

import de.turing85.advent.of.code.tttt.day.two.supplier.GameSelectionsSupplier;
import java.nio.file.Path;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("GameScorer tests")
public abstract class GameScorerTest {
  public static final Path ILLEGAL_INPUT = Path.of("src/test/resources/illegalInput.txt");
  public static final Path COMMON_INPUT = Path.of("src/test/resources/commonInput.txt");
  public static final Path PERSONAL_INPUT = Path.of("src/test/resources/personalInput.txt");

  protected abstract void gameSelectionsSupplierForIllegalInput() throws Exception;

  protected abstract GameSelectionsSupplier gameSelectionsSupplierForCommonInput() throws Exception;

  protected abstract int commonInputResult();

  protected abstract GameSelectionsSupplier gameSelectionsSupplierForPersonalInput()
      throws Exception;

  protected abstract int personalInputResult();

  @Test
  @DisplayName("correct score on common input")
  void commonInput() throws Exception {
    // GIVEN: defaults

    // WHEN
    int actual = GameScorer.instance().score(gameSelectionsSupplierForCommonInput().get());

    // THEN
    assertThat(actual).isEqualTo(commonInputResult());
  }

  @Test
  @DisplayName("correct score on personal input")
  void personalInput() throws Exception {
    // GIVEN: defaults

    // WHEN
    int actual = GameScorer.instance().score(gameSelectionsSupplierForPersonalInput().get());

    // THEN
    assertThat(actual).isEqualTo(personalInputResult());
  }
}
