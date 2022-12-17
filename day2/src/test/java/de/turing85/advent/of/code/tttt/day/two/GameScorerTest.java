package de.turing85.advent.of.code.tttt.day.two;

import static com.google.common.truth.Truth.assertThat;

import de.turing85.advent.of.code.tttt.day.two.supplier.GameSelectionsSupplier;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public abstract class GameScorerTest {
  public static final Path ILLEGAL_INPUT_PATH = Path.of("src/test/resources/illegalInput.txt");
  public static final URI ILLEGAL_INPUT_URI = ILLEGAL_INPUT_PATH.toUri();
  protected static String ILLEGAL_INPUT;

  public static final Path COMMON_INPUT_PATH = Path.of("src/test/resources/commonInput.txt");
  public static final URI COMMON_INPUT_URI = COMMON_INPUT_PATH.toUri();
  public static String COMMON_INPUT;

  public static final Path PERSONAL_INPUT_PATH = Path.of("src/test/resources/personalInput.txt");
  protected static final URI PERSONAL_INPUT_URI = PERSONAL_INPUT_PATH.toUri();
  protected static String PERSONAL_INPUT;

  protected abstract GameSelectionsSupplier gameSelectionsSupplierForIllegalInput()
      throws Exception;

  protected abstract GameSelectionsSupplier gameSelectionsSupplierForCommonInput() throws Exception;

  protected abstract int commonInputResult();

  protected abstract GameSelectionsSupplier gameSelectionsSupplierForPersonalInput()
      throws Exception;

  protected abstract int personalInputResult();

  @BeforeAll
  static void setup() throws IOException {
    ILLEGAL_INPUT = Files.readString(ILLEGAL_INPUT_PATH);
    COMMON_INPUT = Files.readString(COMMON_INPUT_PATH);
    PERSONAL_INPUT = Files.readString(PERSONAL_INPUT_PATH);
  }

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
