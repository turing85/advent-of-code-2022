package de.turing85.advent.of.code.tttt.day.twentyfour.supplier;

import de.turing85.advent.of.code.tttt.day.twentyfour.model.BlizzardMap;
import de.turing85.advent.of.code.tttt.day.twentyfour.model.BlizzardPathFinder;
import de.turing85.advent.of.code.tttt.day.twentyfour.model.Point;

/**
 * Encapsulates all parameters needed to find the shortest paths by {@link
 * BlizzardPathFinder#shortestPathLength(BlizzardMap, Point, Point)}
 *
 * @param initialBlizzardMap the initial state of the blizzards
 * @param start the start {@link Point}
 * @param end the {@link Point}
 */
public record BlizzardRoutingConfiguration(
    BlizzardMap initialBlizzardMap, Point start, Point end) {}
