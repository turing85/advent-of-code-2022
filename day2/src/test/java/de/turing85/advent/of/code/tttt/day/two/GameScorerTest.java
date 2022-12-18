package de.turing85.advent.of.code.tttt.day.two;

import static com.google.common.truth.Truth.assertThat;

import de.turing85.advent.of.code.tttt.day.two.supplier.GameSelectionsSupplier;
import java.net.URI;
import java.nio.file.Path;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("GameScorer tests")
public abstract class GameScorerTest {
  public static final URI ILLEGAL_INPUT_URI =
      Path.of("src/test/resources/illegalInput.txt").toUri();

  public static final URI COMMON_INPUT_URI = Path.of("src/test/resources/commonInput.txt").toUri();

  protected static final URI PERSONAL_INPUT_URI =
      Path.of("src/test/resources/personalInput.txt").toUri();

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
