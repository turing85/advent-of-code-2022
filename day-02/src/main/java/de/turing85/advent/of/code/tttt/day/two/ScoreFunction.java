package de.turing85.advent.of.code.tttt.day.two;

import de.turing85.advent.of.code.tttt.day.two.model.GameSelection;
import de.turing85.advent.of.code.tttt.day.two.model.Selection;
import java.util.Map;
import java.util.function.ToIntFunction;

/**
 * Function to store a single {@link GameSelection}.
 */
public class ScoreFunction implements ToIntFunction<GameSelection> {
  private static final ScoreFunction INSTANCE = new ScoreFunction();

  private static final int LOSE_SCORE = 0;
  private static final int DRAW_SCORE = 3;
  private static final int WIN_SCORE = 6;

  private static final int ROCK_SCORE = 1;
  private static final int PAPER_SCORE = 2;
  private static final int SCISSORS_SCORE = 3;

  private static final Map<GameSelection, Integer> scoreTable =
      Map.of(new GameSelection(Selection.ROCK, Selection.PAPER), LOSE_SCORE + ROCK_SCORE,
          new GameSelection(Selection.PAPER, Selection.SCISSORS), LOSE_SCORE + PAPER_SCORE,
          new GameSelection(Selection.SCISSORS, Selection.ROCK), LOSE_SCORE + SCISSORS_SCORE,
          new GameSelection(Selection.ROCK, Selection.ROCK), DRAW_SCORE + ROCK_SCORE,
          new GameSelection(Selection.PAPER, Selection.PAPER), DRAW_SCORE + PAPER_SCORE,
          new GameSelection(Selection.SCISSORS, Selection.SCISSORS), DRAW_SCORE + SCISSORS_SCORE,
          new GameSelection(Selection.ROCK, Selection.SCISSORS), WIN_SCORE + ROCK_SCORE,
          new GameSelection(Selection.PAPER, Selection.ROCK), WIN_SCORE + PAPER_SCORE,
          new GameSelection(Selection.SCISSORS, Selection.PAPER), WIN_SCORE + SCISSORS_SCORE);

  private ScoreFunction() {}


  /**
   * Instance-getter.
   *
   * @return the singleton-instance of this class.
   */
  public static ScoreFunction instance() {
    return INSTANCE;
  }

  @Override
  public int applyAsInt(GameSelection gameSelection) {
    return score(gameSelection);
  }

  /**
   * Scores a {@link GameSelection}.
   *
   * @param gameSelection the {@link GameSelection}.
   *
   * @return the score we get.
   */
  public int score(GameSelection gameSelection) {
    return scoreTable.get(gameSelection);
  }
}
