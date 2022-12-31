package de.turing85.advent.of.code.tttt.day.eleven.model;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.LongUnaryOperator;

/** Simulating monkey business of monkeys, playing Keep Away with you and your items. */
public class MonkeyBusiness {
  private final List<Monkey> monkeys;

  /**
   * Constructors
   *
   * @param monkeys the crew
   */
  public MonkeyBusiness(List<Monkey> monkeys) {
    this.monkeys = monkeys;
  }

  /**
   * Play {@code n} rounds and calculate the monkey business of the {@code m} most-business monkeys.
   *
   * @param n rounds to play
   * @param m most-business monkeys
   * @param stressModifier stress modifier
   * @return monkey business
   */
  public long playNRoundsAndCalculateTopMMonkeyBusiness(
      int n, int m, LongUnaryOperator stressModifier) {
    return playNRoundsAndRecordPerMonkeyBusiness(n, stressModifier).values().stream()
        .sorted(Comparator.comparing(Number::intValue).reversed())
        .mapToLong(value -> value)
        .limit(m)
        .reduce(1, (lhs, rhs) -> lhs * rhs);
  }

  /**
   * Play {@code n} rounds and record the per-monkey monkey business.
   *
   * @param n rounds to play
   * @param stressModifier stress modifier
   * @return per-monkey monkey business
   */
  public Map<Integer, Long> playNRoundsAndRecordPerMonkeyBusiness(
      int n, LongUnaryOperator stressModifier) {
    Map<Integer, Long> monkeyBusiness = new HashMap<>();
    for (int round = 0; round < n; ++round) {
      for (Monkey monkey : monkeys) {
        int id = monkey.id();
        long currentBusiness = monkeyBusiness.getOrDefault(id, 0L);
        long newMonkeyBusiness = monkey.playTurn(stressModifier) + currentBusiness;
        monkeyBusiness.put(id, newMonkeyBusiness);
      }
    }
    return monkeyBusiness;
  }
}
