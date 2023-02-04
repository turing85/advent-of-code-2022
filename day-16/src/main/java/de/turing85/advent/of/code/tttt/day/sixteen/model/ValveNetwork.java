package de.turing85.advent.of.code.tttt.day.sixteen.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import lombok.Getter;

/** A network of valves */
public final class ValveNetwork {
  private final Map<String, Valve> valvesByName;
  private final ArrayList<Valve> valves;

  /**
   * Constructor.
   *
   * @param valveDescriptions descriptions for the valve in the network.
   */
  public ValveNetwork(Set<ValveDescription> valveDescriptions) {
    validateAllValveDefinitionsArePresent(valveDescriptions);
    validateAllFlowRatesArePositive(valveDescriptions);
    valvesByName = initializeNeighbours(valveDescriptions, initializeValves(valveDescriptions));
    valves = new ArrayList<>(valvesByName.values());
  }

  private static void validateAllValveDefinitionsArePresent(
      Set<ValveDescription> valveDescriptions) {
    Set<String> valveNames =
        valveDescriptions.stream().map(ValveDescription::name).collect(Collectors.toSet());
    Set<String> valveNeighbours =
        valveDescriptions.stream()
            .map(ValveDescription::neighbours)
            .flatMap(Collection::stream)
            .collect(Collectors.toSet());
    if (!valveNames.containsAll(valveNeighbours)) {
      Set<String> missingValveDefinitions = new HashSet<>(valveNeighbours);
      missingValveDefinitions.retainAll(valveNames);
      throw new IllegalStateException(
          "Missing definitions for the following valves: %s".formatted(missingValveDefinitions));
    }
  }

  private static void validateAllFlowRatesArePositive(Set<ValveDescription> valveDescriptions) {
    Set<String> valvesWithNegativeFlowRate =
        valveDescriptions.stream()
            .filter(valveDescription -> valveDescription.flowRate() < 0)
            .map(ValveDescription::name)
            .collect(Collectors.toSet());
    if (!valvesWithNegativeFlowRate.isEmpty()) {
      throw new IllegalStateException(
          "valves %s have negative flow rate".formatted(valvesWithNegativeFlowRate));
    }
  }

  private static Map<String, Valve> initializeValves(Set<ValveDescription> valveDescriptions) {
    return valveDescriptions.stream()
        .map(description -> new Valve(description.name(), description.flowRate()))
        .collect(Collectors.toMap(Valve::name, Function.identity()));
  }

  private static Map<String, Valve> initializeNeighbours(
      Set<ValveDescription> valveDescriptions, Map<String, Valve> valvesByName) {
    for (ValveDescription description : valveDescriptions) {
      Valve valve = valvesByName.get(description.name());
      Set<Valve> neighbours =
          valveDescriptions.stream()
              .map(ValveDescription::name)
              .map(valvesByName::get)
              .filter(
                  possibleNeighbour -> description.neighbours().contains(possibleNeighbour.name()))
              .collect(Collectors.toSet());
      valve.addNeighbours(neighbours);
    }
    return valvesByName;
  }

  /**
   * Finds a route through the network to open valves in a way such that the maximum pressure is
   * released until time is 0.
   *
   * @param startValve the name of the start valve
   * @param timeLimit time limit
   * @param n number of actors acting at the same time
   * @return the pressure released
   */
  public int releaseMaximumPressure(String startValve, int timeLimit, int n) {
    return releaseMaximumPressure(startValve, timeLimit, n, 100_000);
  }

  /**
   * Finds a route through the network to open valves in a way such that the maximum pressure is
   * released until time is 0.
   *
   * @param startValve the name of the start valve
   * @param timeLimit time limit
   * @param n number of actors acting at the same time
   * @param logRegularity how often a log should be written
   * @return the pressure released
   */
  public int releaseMaximumPressure(String startValve, int timeLimit, int n, int logRegularity) {
    int maxPressureReleased = Integer.MIN_VALUE;
    Queue<State> queue =
        new PriorityQueue<>(Comparator.comparing(State::pressureReleased).reversed());
    State initialState =
        new State(
            Collections.nCopies(
                n, new ActorState(valvesByName.get(startValve), Collections.emptyList())),
            0,
            Collections.emptySet(),
            timeLimit);
    queue.add(initialState);
    int iteration = 1;
    while (!queue.isEmpty()) {
      maxPressureReleased = processNextElement(maxPressureReleased, queue);
      if (iteration % logRegularity == 0 || queue.isEmpty()) {
        Logger.getGlobal()
            .info(
                "iteration: %d, queue size: %d, current best: %d"
                    .formatted(iteration, queue.size(), maxPressureReleased));
      }
      ++iteration;
    }
    return maxPressureReleased;
  }

  private int processNextElement(int maxPressureReleased, Queue<State> queue) {
    State currentState = queue.poll();
    if (canBeCut(currentState, maxPressureReleased)) {
      return maxPressureReleased;
    }
    Set<State> followupStates = currentState.followupStates();
    queue.addAll(followupStates);
    return Math.max(maxPressureReleased, currentState.pressureReleased());
  }

  private boolean canBeCut(State state, int maxPressureReleased) {
    Set<Valve> notYetOpened = new HashSet<>(valves);
    notYetOpened.removeAll(state.valvesOpened());
    LinkedList<Integer> notYetOpenedFlowRates =
        notYetOpened.stream()
            .map(Valve::flowRate)
            .sorted()
            .collect(Collectors.toCollection(LinkedList::new));
    int maxPossibleRelease = state.pressureReleased();
    int timeLeft = state.timeLeft() - 1;
    while (timeLeft > 0) {
      for (int index = 0;
          index < state.actorStates().size() && !notYetOpenedFlowRates.isEmpty();
          ++index) {
        maxPossibleRelease += notYetOpenedFlowRates.removeLast() * timeLeft;
        if (maxPossibleRelease > maxPressureReleased) {
          return false;
        }
      }
      timeLeft -= 2;
    }
    return maxPossibleRelease < maxPressureReleased;
  }

  /** A valve */
  public static class Valve {
    @Getter private final String name;

    @Getter private final int flowRate;

    private final Set<Valve> directNeighbours;

    private Valve(String name, int flowRate) {
      this.name = name;
      this.flowRate = flowRate;
      this.directNeighbours = new HashSet<>();
    }

    private void addNeighbours(Collection<Valve> valves) {
      this.directNeighbours.addAll(valves);
    }

    /**
     * Getter.
     *
     * @return direct neighbours
     */
    public Set<Valve> directNeighbours() {
      return Collections.unmodifiableSet(directNeighbours);
    }
  }

  private record State(
      List<ActorState> actorStates, int pressureReleased, Set<Valve> valvesOpened, int timeLeft) {
    private Set<State> followupStates() {
      if (timeLeft() == 0) {
        return Collections.emptySet();
      }
      Map<Valve, Set<ActorStateConfig>> followupsPerValve = new HashMap<>();
      for (ActorState actorState : actorStates) {
        Valve currentValve = actorState.currentValve();
        Set<ActorStateConfig> followups = constructFollowupConfigs(actorState, currentValve);
        followupsPerValve.put(currentValve, followups);
      }
      return constructFollowupStates(crossProduct(followupsPerValve));
    }

    private Set<ActorStateConfig> constructFollowupConfigs(
        ActorState actorState, Valve currentValve) {
      Set<ActorStateConfig> followups = new HashSet<>();
      if (currentValve.flowRate() > 0 && !valvesOpened().contains(currentValve)) {
        followups.add(new ActorStateConfig(Action.OPEN, currentValve, List.of(currentValve)));
      }
      followups.addAll(constructMoveFollowupConfigs(actorState, currentValve));
      return followups;
    }

    private Set<ActorStateConfig> constructMoveFollowupConfigs(
        ActorState state, Valve currentValve) {
      Set<ActorStateConfig> moveFollowups = new HashSet<>();
      List<Valve> visitedSinceLastValveOpening = state.visitedSinceLastValveOpening();
      List<Valve> unvisitedNeighbours =
          currentValve.directNeighbours().stream()
              .filter(Predicate.not(visitedSinceLastValveOpening::contains))
              .toList();
      for (Valve neighbour : unvisitedNeighbours) {
        List<Valve> newVisitedSinceLastValveOpening = new ArrayList<>(visitedSinceLastValveOpening);
        newVisitedSinceLastValveOpening.add(neighbour);
        moveFollowups.add(
            new ActorStateConfig(Action.MOVE, neighbour, newVisitedSinceLastValveOpening));
      }
      return moveFollowups;
    }

    private List<List<ActorStateConfig>> crossProduct(
        Map<Valve, Set<ActorStateConfig>> followupsPerValve) {
      List<List<ActorStateConfig>> nextActions = List.of();
      for (ActorState currentState : actorStates()) {
        if (nextActions.isEmpty()) {
          nextActions =
              followupsPerValve.get(currentState.currentValve()).stream().map(List::of).toList();
        } else {
          nextActions = crossProduct(followupsPerValve, nextActions, currentState.currentValve());
        }
      }
      return nextActions;
    }

    private static List<List<ActorStateConfig>> crossProduct(
        Map<Valve, Set<ActorStateConfig>> followupsPerValve,
        List<List<ActorStateConfig>> nextActions,
        Valve currentValue) {
      List<List<ActorStateConfig>> nextActionsNew = new ArrayList<>();
      for (List<ActorStateConfig> nextAction : nextActions) {
        for (ActorStateConfig followup : followupsPerValve.get(currentValue)) {
          List<ActorStateConfig> nextActionNew = new ArrayList<>(nextAction);
          nextActionNew.add(followup);
          nextActionsNew.add(nextActionNew);
        }
      }
      return nextActionsNew;
    }

    private Set<State> constructFollowupStates(List<List<ActorStateConfig>> actorStateConfigs) {
      Set<State> followupStates = new HashSet<>();
      for (List<ActorStateConfig> stateConfig : actorStateConfigs) {
        Set<Valve> toBeOpened = new HashSet<>();
        boolean isValid = true;
        for (ActorStateConfig actorStateConfig : stateConfig) {
          Valve valve = actorStateConfig.currentValve();
          if (actorStateConfig.action().equals(Action.OPEN) && toBeOpened.contains(valve)) {
            isValid = false;
            break;
          }
          toBeOpened.add(valve);
        }
        if (isValid) {
          followupStates.add(constructFollowupState(stateConfig));
        }
      }
      return followupStates;
    }

    private State constructFollowupState(List<ActorStateConfig> actorStateConfig) {
      Set<Valve> toBeOpened =
          actorStateConfig.stream()
              .filter(config -> config.action.equals(Action.OPEN))
              .map(ActorStateConfig::currentValve)
              .collect(Collectors.toSet());
      int newTimeLeft = timeLeft() - 1;
      int newPressureReleased =
          pressureReleased() + toBeOpened.stream().mapToInt(Valve::flowRate).sum() * newTimeLeft;
      Set<Valve> newValvesOpened = new HashSet<>(valvesOpened());
      newValvesOpened.addAll(toBeOpened);
      List<ActorState> newActorStates =
          actorStateConfig.stream()
              .map(
                  config ->
                      new ActorState(config.currentValve(), config.visitedSinceLastValveOpening()))
              .toList();
      return new State(newActorStates, newPressureReleased, newValvesOpened, newTimeLeft);
    }
  }

  private record ActorState(Valve currentValve, List<Valve> visitedSinceLastValveOpening) {}

  private record ActorStateConfig(
      Action action, Valve currentValve, List<Valve> visitedSinceLastValveOpening) {}

  private enum Action {
    MOVE,
    OPEN
  }
}
