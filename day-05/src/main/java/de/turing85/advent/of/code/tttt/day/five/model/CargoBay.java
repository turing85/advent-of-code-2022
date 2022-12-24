package de.turing85.advent.of.code.tttt.day.five.model;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * This is a cargo bay with numbered {@link Deque}s, where each {@link Deque} holds labels in form
 * of {@link Character}s, representing the loaded cargo.
 */
public class CargoBay {
  private final Map<Integer, Deque<Character>> numberedStacks;

  /**
   * Constructor.
   *
   * @param numberedStacks The numbered stacks, initialized with the cargo.
   */
  public CargoBay(Map<Integer, Deque<Character>> numberedStacks) {
    this.numberedStacks = numberedStacks;
  }

  /**
   * Executes a {@link List} of {@link UnloadInstruction}s, returns the top element of each stack,
   * as {@link List}.
   *
   * @param unloadInstructions unload instructions to execute
   *
   * @return the top element of each stack, as {@link List}.
   */
  public List<Character> executeUnloadPlan(List<UnloadInstruction> unloadInstructions) {
    Map<Integer, Deque<Character>> copy = makeCopy();
    for (UnloadInstruction instruction : unloadInstructions) {
      for (int times = 0; times < instruction.times(); ++times) {
        copy.get(instruction.to()).addLast(copy.get(instruction.from()).removeLast());
      }
    }
    return constructResult(copy);
  }

  private Map<Integer, Deque<Character>> makeCopy() {
    Map<Integer, Deque<Character>> copy = new HashMap<>();
    for (var entry : numberedStacks.entrySet()) {
      copy.put(entry.getKey(), new LinkedList<>(entry.getValue()));
    }
    return copy;
  }

  private static List<Character> constructResult(Map<Integer, Deque<Character>> copy) {
    int maxStackIndex = copy.keySet().stream().reduce(Integer.MIN_VALUE, Integer::max);
    List<Character> result = new ArrayList<>();
    for (int stack = 1; stack <= maxStackIndex; ++stack) {
      result.add(copy.get(stack).getLast());
    }
    return result;
  }

  /**
   * Executes a {@link List} of {@link UnloadInstruction}s, returns the top element of each stack,
   * as {@link List} OVER 9000!
   *
   * @param unloadInstructions unload instructions to execute
   *
   * @return the top element of each stack, as {@link List}.
   */
  public List<Character> executeUnloadPlanOver9000(List<UnloadInstruction> unloadInstructions) {
    Map<Integer, Deque<Character>> copy = makeCopy();
    for (UnloadInstruction instruction : unloadInstructions) {
      Deque<Character> buffer = new LinkedList<>();
      for (int times = 0; times < instruction.times(); ++times) {
        buffer.addLast(copy.get(instruction.from()).removeLast());
      }
      while (!buffer.isEmpty()) {
        copy.get(instruction.to()).addLast(buffer.removeLast());
      }
    }
    return constructResult(copy);
  }
}
