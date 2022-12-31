package de.turing85.advent.of.code.tttt.day.nine.model;

/**
 * A movement command.
 *
 * @param direction direction to move in
 * @param steps steps to take in this direction
 */
public record MovementCommand(Direction direction, int steps) {}
