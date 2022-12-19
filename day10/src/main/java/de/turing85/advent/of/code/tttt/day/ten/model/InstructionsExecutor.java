package de.turing85.advent.of.code.tttt.day.ten.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.IntPredicate;

/**
 * Executes a {@link List} of {@link InstructionWithParameter}s on a freshly initialized CPU.
 */
public class InstructionsExecutor {
  private InstructionsExecutor() {}

  /**
   * Executes the {@link InstructionWithParameter}s.
   *
   * @param instructionsWithParameter to execute
   *
   * @return The {@code cycle} and the corresponding {@code X}-register at this cycle.
   */
  public static Map<Integer, Integer> executeInstructions(
      List<InstructionWithParameter> instructionsWithParameter) {
    Cpu cpu = new Cpu(instructionsWithParameter);
    Map<Integer, Integer> xValuesAtCycle = new HashMap<>();
    while (true) {
      if (!cpu.tick(xValuesAtCycle::put)) {
        break;
      }
    }
    return xValuesAtCycle;
  }

  /**
   * Executes instructions, and filters, maps and reduces the history of {@code X}-register values.
   *
   * <p>
   * In detail, this method calls {@link #executeInstructions(List)} with
   * {@code instructionsWithParameter}. Then, the return-value of this call is filtered by
   * {@code cycleFilter}. As the name suggest, the filter is based on the {@code cycle}s. The
   * remaining {@code cycle}-{@code X}-register pairs are then mapped by the
   * {@code cycleAndXRegisterMapper}. Finally, all resulting values are reduced through
   * {@code identity} and {@code operation}.
   *
   * @param instructionsWithParameter to execute
   * @param cycleFilter {@code cycle}-based filter
   * @param cycleAndXRegisterMapper mapper
   * @param identity identity for reduction
   * @param operation reduction operation
   * @param <T> the type mapped to and returned by this method.
   *
   * @return the reduction result
   */
  public static <T> T executeFilterMapReduce(
      List<InstructionWithParameter> instructionsWithParameter, IntPredicate cycleFilter,
      BiFunction<Integer, Integer, T> cycleAndXRegisterMapper, T identity,
      BinaryOperator<T> operation) {
    return executeInstructions(instructionsWithParameter).entrySet().stream()
        .sorted(Map.Entry.comparingByKey()).filter(entry -> cycleFilter.test(entry.getKey()))
        .map(entry -> cycleAndXRegisterMapper.apply(entry.getKey(), entry.getValue()))
        .reduce(identity, operation);
  }
}
