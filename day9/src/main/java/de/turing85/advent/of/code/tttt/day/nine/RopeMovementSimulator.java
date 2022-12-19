package de.turing85.advent.of.code.tttt.day.nine;

import de.turing85.advent.of.code.tttt.day.nine.model.Direction;
import de.turing85.advent.of.code.tttt.day.nine.model.MovementCommand;
import de.turing85.advent.of.code.tttt.day.nine.model.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Simulates rope movement.
 */
public class RopeMovementSimulator {
  private RopeMovementSimulator() {}

  /**
   * Simulates the movement of head, given by the {@code movementCommands}, and counts the points
   * visited by the tail.
   *
   * @param movementCommands the movement commands to simulate on head
   *
   * @return the number of points visited by tail
   */
  public static int simulateMovementAndCountCoordinatesVisitedByTail(
      List<MovementCommand> movementCommands) {
    return simulateMovementOfNKnotsAndCountCoordinatesVisitedByTail(movementCommands, 2);
  }

  /**
   * Simulates the movement of n knots, given by the {@code movementCommands}, and counts the points
   * visited by the last knot.
   *
   * <p>
   * The movement is executed on the 1st know (the head). The tail is the last knot.
   *
   * @param movementCommands the movement commands to simulate on head
   * @param n the number of knots to simulate
   *
   * @return the number of points visited by tail
   */
  public static int simulateMovementOfNKnotsAndCountCoordinatesVisitedByTail(
      List<MovementCommand> movementCommands, int n) {
    List<Point> knots = new ArrayList<>();
    for (int i = 0; i < n; ++i) {
      knots.add(new Point());
    }
    int headIndex = 0;
    int tailIndex = n - 1;
    Set<Point> visitedByTail = new HashSet<>();
    visitedByTail.add(knots.get(tailIndex));
    for (MovementCommand movementCommand : movementCommands) {
      Direction direction = movementCommand.direction();
      for (int step = 0; step < movementCommand.steps(); ++step) {
        knots = processMovementForAllKnots(knots, headIndex, tailIndex, visitedByTail, direction);
      }
    }
    return visitedByTail.size();
  }

  private static List<Point> processMovementForAllKnots(List<Point> knots, int headIndex,
      int tailIndex, Set<Point> visitedByTail, Direction direction) {
    List<Point> newKnots = new ArrayList<>();
    for (int index = 0; index < knots.size(); ++index) {
      if (index == headIndex) {
        newKnots.add(knots.get(index).move(direction));
      } else {
        Point newKnot = followPredecessor(knots, newKnots, index);
        newKnots.add(newKnot);
        if (index == tailIndex) {
          visitedByTail.add(newKnot);
        }
      }
    }
    return newKnots;
  }

  private static Point followPredecessor(List<Point> knots, List<Point> newKnots, int index) {
    Point currentKnot = knots.get(index);
    Point knotToFollow = newKnots.get(index - 1);
    return letFirstFollowSecond(currentKnot, knotToFollow);
  }

  private static Point letFirstFollowSecond(Point first, Point second) {
    if (!first.isAdjacentTo(second)) {
      return first.moveOneStepTowards(second);
    }
    return first;
  }
}
