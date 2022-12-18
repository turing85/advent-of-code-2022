package de.turing85.advent.of.code.tttt.day.two.supplier.impl.second.colum.game.outcome;

import de.turing85.advent.of.code.tttt.day.two.model.GameSelection;
import de.turing85.advent.of.code.tttt.day.two.model.Selection;
import de.turing85.advent.of.code.tttt.day.two.supplier.GameSelectionsSupplier;
import de.turing85.advent.of.code.tttt.day.two.supplier.GameSelectionsSupplierTest;
import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.DisplayName;

@DisplayName("FromFileGameSelectionsSupplier tests")
class FromFileGameSelectionsSupplierTest extends GameSelectionsSupplierTest {

  @Override
  protected GameSelectionsSupplier gameSelectionsSupplierForIllegalInput() throws IOException {
    return new FromFileGameSelectionsSupplier(ILLEGAL_INPUT_URI);
  }

  @Override
  protected GameSelectionsSupplier gameSelectionsSupplierForCommonInput() throws IOException {
    return new FromFileGameSelectionsSupplier(COMMON_INPUT_URI);
  }

  @Override
  protected List<GameSelection> expectedGameSelectionForCommonInput() {
    return List.of(new GameSelection(Selection.ROCK, Selection.ROCK),
        new GameSelection(Selection.ROCK, Selection.PAPER),
        new GameSelection(Selection.ROCK, Selection.SCISSORS));
  }

  @Override
  protected int commonInputResult() {
    return 12;
  }

  @Override
  protected GameSelectionsSupplier gameSelectionsSupplierForPersonalInput() throws IOException {
    return new FromFileGameSelectionsSupplier(PERSONAL_INPUT_URI);
  }

  @Override
  protected int personalInputResult() {
    return 10_398;
  }
}
