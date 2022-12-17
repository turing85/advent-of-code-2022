package de.turing85.advent.of.code.tttt.day.two.supplier.impl.second.colum.own.selection;

import de.turing85.advent.of.code.tttt.day.two.GameScorerTest;
import de.turing85.advent.of.code.tttt.day.two.model.GameSelection;
import de.turing85.advent.of.code.tttt.day.two.model.Selection;
import de.turing85.advent.of.code.tttt.day.two.supplier.GameSelectionsSupplier;
import de.turing85.advent.of.code.tttt.day.two.supplier.GameSelectionsSupplierTest;
import java.util.List;
import org.junit.jupiter.api.DisplayName;

@DisplayName("FromStringGameSelectionsSupplier tests")
class FromStringGameSelectionsSupplierTest extends GameSelectionsSupplierTest {

  @Override
  protected GameSelectionsSupplier gameSelectionsSupplierForIllegalInput() {
    return new FromStringGameSelectionsSupplier(GameScorerTest.ILLEGAL_INPUT);
  }

  @Override
  protected GameSelectionsSupplier gameSelectionsSupplierForCommonInput() {
    return new FromStringGameSelectionsSupplier(GameScorerTest.COMMON_INPUT);
  }

  @Override
  protected List<GameSelection> expectedGameSelectionForCommonInput() {
    return List.of(new GameSelection(Selection.PAPER, Selection.ROCK),
        new GameSelection(Selection.ROCK, Selection.PAPER),
        new GameSelection(Selection.SCISSORS, Selection.SCISSORS));
  }

  @Override
  protected int commonInputResult() {
    return 15;
  }

  @Override
  protected GameSelectionsSupplier gameSelectionsSupplierForPersonalInput() {
    return new FromStringGameSelectionsSupplier(GameScorerTest.PERSONAL_INPUT);
  }

  @Override
  protected int personalInputResult() {
    return 13_009;
  }
}
