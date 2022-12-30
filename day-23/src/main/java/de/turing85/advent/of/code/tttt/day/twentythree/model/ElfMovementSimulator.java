package de.turing85.advent.of.code.tttt.day.twentythree.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Simulate elf movement
 */
public class ElfMovementSimulator {
  private final Set<Elf> initialState;

  /**
   * Constructor.
   *
   * @param initialState the initial position of all elves.
   */
  public ElfMovementSimulator(Set<Elf> initialState) {
    this.initialState = initialState;
  }

  /**
   * Simulate {@code n} steps.
   *
   * @param steps number of steps to simulate
   *
   * @return the position of the elves after the number of {@code steps}
   */
  public Set<Elf> simulate(int steps) {
    ArrayList<Direction.Cardinal> directionsToConsider =
        new ArrayList<>(List.of(Direction.Cardinal.UP, Direction.Cardinal.DOWN,
            Direction.Cardinal.LEFT, Direction.Cardinal.RIGHT));
    Set<Elf> currentState = initialState;
    for (int step = 0; step < steps; ++step) {
      currentState = simulateOneStep(currentState, directionsToConsider);
      directionsToConsider.add(directionsToConsider.remove(0));
    }
    return currentState;
  }

  /**
   * Run the simulation until all elves do not move.
   *
   * @return the number of steps it took to reach the stable state
   */
  public int simulateUntilStableStateIsReached() {
    ArrayList<Direction.Cardinal> directionsToConsider =
        new ArrayList<>(List.of(Direction.Cardinal.UP, Direction.Cardinal.DOWN,
            Direction.Cardinal.LEFT, Direction.Cardinal.RIGHT));
    Set<Elf> currentState = initialState;
    int iteration = 0;
    while (true) {
      ++iteration;
      Set<Elf> newState = simulateOneStep(currentState, directionsToConsider);
      if (currentState.stream().map(Elf::position).collect(Collectors.toSet())
          .equals(newState.stream().map(Elf::position).collect(Collectors.toSet()))) {
        return iteration;
      }
      currentState = newState;
      directionsToConsider.add(directionsToConsider.remove(0));
    }
  }

  private static Set<Elf> simulateOneStep(Set<Elf> currentState,
      List<Direction.Cardinal> directionsToConsiderInOrder) {
    Map<Elf, Point> nextFieldsOfElves =
        simulateFirstPhase(currentState, directionsToConsiderInOrder);
    return simulateSecondPhase(currentState, nextFieldsOfElves);
  }

  private static Map<Elf, Point> simulateFirstPhase(Set<Elf> currentState,
      List<Direction.Cardinal> directionsToConsiderInOrder) {
    Map<Elf, Point> nextFieldsOfElves = new HashMap<>();
    Set<Point> pointsOccupied =
        currentState.stream().map(Elf::position).collect(Collectors.toSet());
    for (Elf elf : currentState) {
      nextFieldsOfElves.put(elf, elf.proposeNextPoint(pointsOccupied, directionsToConsiderInOrder));
    }
    return nextFieldsOfElves;
  }

  private static Set<Elf> simulateSecondPhase(Set<Elf> currentState,
      Map<Elf, Point> nextFieldsOfElves) {
    Map<Point, Integer> numberOfElvesThatWantToMoveToThisPoint = nextFieldsOfElves.values().stream()
        .collect(Collectors.toMap(Function.identity(), point -> 1, Integer::sum));
    currentState = currentState.stream()
        .map(elf -> getFollowupElf(nextFieldsOfElves, numberOfElvesThatWantToMoveToThisPoint, elf))
        .collect(Collectors.toSet());
    return currentState;
  }

  private static Elf getFollowupElf(Map<Elf, Point> nextFieldsOfElves,
      Map<Point, Integer> numberOfElvesThatWantToMoveToThisPoint, Elf elf) {
    if (numberOfElvesThatWantToMoveToThisPoint.get(nextFieldsOfElves.get(elf)) == 1) {
      return new Elf(nextFieldsOfElves.get(elf));
    }
    return elf;
  }
}
