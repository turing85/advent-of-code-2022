package de.turing85.advent.of.code.tttt.day.sixteen.model;

import java.util.Set;

/**
 * Describes a single valve.
 *
 * @param name the name of the valve
 * @param flowRate its flow rate
 * @param neighbours the names of its neighbours
 */
public record ValveDescription(String name, int flowRate, Set<String> neighbours) {
}
