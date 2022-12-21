package de.turing85.advent.of.code.tttt.day.two.model;

import java.util.Map;
import lombok.Getter;

/**
 * Representation of the possible selections in a
 * <a href="https://en.wikipedia.org/wiki/Rock_paper_scissors">Rock Paper Scissors</a> game.
 */
@Getter
public enum Selection {
  /**
   * Rock selection.
   */
  ROCK,

  /**
   * Paper selection.
   */
  PAPER,

  /**
   * Scissor selection.
   */
  SCISSORS;

  private static final Map<Selection, Selection> beatsMap =
      Map.of(ROCK, SCISSORS, PAPER, ROCK, SCISSORS, PAPER);

  private static final Map<Selection, Selection> beatenByMap =
      Map.of(ROCK, PAPER, PAPER, SCISSORS, SCISSORS, ROCK);

  /**
   * Returns the opponent's {@link Selection} that loses against {@code this} {@link Selection} by
   * us.
   *
   * @return the opponent's {@link Selection} that loses when {@code this} {@link Selection} is
   *         chosen by us.
   */
  public Selection beats() {
    return beatsMap.get(this);
  }

  /**
   * Returns the opponent's {@link Selection} that wins against {@code this} {@link Selection} by
   * us.
   *
   * @return the opponent's {@link Selection} that wins when {@code this} {@link Selection} is
   *         chosen by us.
   */
  public Selection beatenBy() {
    return beatenByMap.get(this);
  }
}
