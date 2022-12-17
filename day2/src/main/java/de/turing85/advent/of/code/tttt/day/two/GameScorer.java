package de.turing85.advent.of.code.tttt.day.two;

import de.turing85.advent.of.code.tttt.day.two.model.GameSelection;
import java.util.Collection;

/**
 * Scores a game, i.e. a {@link Collection} of {@link GameSelection}s.
 */
public class GameScorer {
  private static final GameScorer INSTANCE = new GameScorer();

  private GameScorer() {}

  /**
   * Instance-getter.
   *
   * @return the singleton-instance of this class.
   */
  public static GameScorer instance() {
    return INSTANCE;
  }

  /**
   * Returns the score of the game.
   *
   * @param gameSelections the {@link GameSelection}s, representing the game.
   * @return the score.
   */
  public int score(Collection<GameSelection> gameSelections) {
    return gameSelections.stream().mapToInt(ScoreFunction.instance()::score).sum();
  }
}
