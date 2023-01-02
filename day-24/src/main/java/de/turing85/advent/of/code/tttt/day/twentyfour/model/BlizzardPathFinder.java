package de.turing85.advent.of.code.tttt.day.twentyfour.model;

import java.util.Collection;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/** Finds a path from a start {@link Point} to an end {@link Point} through deadly blizzards. */
public class BlizzardPathFinder {
  /**
   * Finds the length of the shortest path from a {@code start} {@link Point} to an {@code end}
   * {@link Point} through deadly blizzards.
   *
   * @param initialBlizzardMap initial blizzard map
   * @param start start
   * @param end end
   * @return the length of the shortest path.
   */
  public int shortestPathLength(BlizzardMap initialBlizzardMap, Point start, Point end) {
    return shortestPathLength(initialBlizzardMap, start, List.of(end));
  }

  /**
   * Finds the length of the shortest path from a {@code start} {@link Point} to an {@code end}
   * {@link Point} through deadly blizzards.
   *
   * @param initialBlizzardMap initial blizzard map
   * @param start start
   * @param ends ends
   * @return the length of the shortest path.
   */
  public int shortestPathLength(BlizzardMap initialBlizzardMap, Point start, List<Point> ends) {
    List<BlizzardMap> blizzardMapsByTimeSteps =
        BlizzardMapCalculator.precalculateMapsByTimeSteps(initialBlizzardMap);
    int width = initialBlizzardMap.width();
    int height = initialBlizzardMap.height();
    int currentTimeStep = 0;
    Position currentStart = new Position(start, width, height);
    for (Point end : ends) {
      Position currentEnd = new Position(end, width, height);
      currentTimeStep =
          findShortestPath(currentStart, currentEnd, blizzardMapsByTimeSteps, currentTimeStep)
              .timeStep();
      currentStart = currentEnd;
    }
    return currentTimeStep;
  }

  private static State findShortestPath(
      Position start, Position end, List<BlizzardMap> blizzardMapsByTimeSteps, int timeStep) {
    Set<State> visited = new HashSet<>();
    State currentState = new State(start, timeStep);
    visited.add(currentState);
    int width = currentState.position().width();
    int height = currentState.position().height();

    Deque<State> queue = new LinkedList<>();
    queue.addLast(currentState);
    while (true) {
      currentState = queue.removeFirst();
      Point downNeighbour = currentState.position().point().neighbour(Direction.DOWN);
      if (downNeighbour.equals(end.point())) {

        return new State(new Position(downNeighbour, width, height), currentState.timeStep() + 1);
      }
      Point upNeighbour = currentState.position().point().neighbour(Direction.UP);
      if (upNeighbour.equals(end.point())) {
        return new State(new Position(upNeighbour, width, height), currentState.timeStep() + 1);
      }
      Set<State> nextStates = getNextStates(blizzardMapsByTimeSteps, visited, currentState);
      visited.addAll(nextStates);
      nextStates.forEach(queue::addLast);
    }
  }

  private static Set<State> getNextStates(
      List<BlizzardMap> blizzardMapsByTimeSteps, Set<State> visited, State currentState) {
    int nextTimeStep = currentState.timeStep() + 1;
    BlizzardMap currentBlizzardMap =
        blizzardMapsByTimeSteps.get(nextTimeStep % blizzardMapsByTimeSteps.size());
    Set<Position> nextPositions = currentState.position().nextPositions(currentBlizzardMap);
    return nextPositions.stream()
        .map(position -> new State(position, nextTimeStep))
        .filter(Predicate.not(visited::contains))
        .collect(Collectors.toSet());
  }

  private record Position(Point point, int width, int height) {
    Set<Position> nextPositions(BlizzardMap nextBlizzardMap) {
      Set<Point> blockedByBlizzards = nextBlizzardMap.blockedByBlizzards();
      Set<Point> inBoundsNeighbour =
          point.neighbours().stream()
              .filter(point -> point.x() > 0)
              .filter(point -> point.x() < width() - 1)
              .filter(point -> point.y() > 0)
              .filter(point -> point.y() < height() - 1)
              .collect(Collectors.toSet());
      return Stream.of(Set.of(point()), inBoundsNeighbour)
          .flatMap(Collection::stream)
          .filter(Predicate.not(blockedByBlizzards::contains))
          .map(point -> new Position(point, width(), height()))
          .collect(Collectors.toSet());
    }
  }

  private record State(Position position, Integer timeStep) {}
}
