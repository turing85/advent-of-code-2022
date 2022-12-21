package de.turing85.advent.of.code.tttt.day.two.model;

/**
 * Represents a selection (round) in a Game of <a
 * href="https://en.wikipedia.org/wiki/Rock_paper_scissors">Rock Paper Scissors</a>.
 *
 * @param ownSelection our {@link Selection}
 * @param opponentSelection the opponent's {@link Selection}
 */
public record GameSelection(Selection ownSelection, Selection opponentSelection) {
}
