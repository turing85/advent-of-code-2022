package de.turing85.advent.of.code.tttt.day.thirteen.model;


/**
 * A pair of two things.
 *
 * @param first the first thing of a {@link Pair}.
 * @param second the second thing of a {@link Pair}.
 * @param <T> The type fo the first thing.
 * @param <U> The typ eof the second thing.
 */
public record Pair<T, U>(T first, U second) {
}
