package de.turing85.advent.of.code.tttt.day.twelve.model;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;

/**
 * Finds length of (one of) the shortest paths on a board.
 */
public class BoardSolver {
  private BoardSolver() {}

  /**
   * Finds the length of the shortest path from any point that satisfies the
   * {@code startFieldCondition} to the {@code endField}.
   *
   * @param board the board to search on
   * @param startFieldCondition condition describing start field(s)
   * @param endField the end field
   * @return the length of the shortest path, or {@code -1} if no path is found.
   */
  public static int findShortestPathLength(Board board, Predicate<Field> startFieldCondition,
      Field endField) {
    Set<Field> visited = new HashSet<>(Set.of(endField));
    Deque<FieldWithDistance> queue = new LinkedList<>();
    queue.addLast(new FieldWithDistance(endField, 0));
    while (true) {
      FieldWithDistance currentFieldWithDistance = Objects.requireNonNull(queue.pollFirst());
      int newDistance = currentFieldWithDistance.distance() + 1;
      List<Field> fields = board.reachableFromNeighbours(currentFieldWithDistance.field()).stream()
          .filter(Predicate.not(visited::contains)).toList();
      for (Field field : fields) {
        if (startFieldCondition.test(field)) {
          return newDistance;
        }
        visited.add(field);
        queue.addLast(new FieldWithDistance(field, newDistance));
      }
    }
  }

  private record FieldWithDistance(Field field, int distance) {}
}
