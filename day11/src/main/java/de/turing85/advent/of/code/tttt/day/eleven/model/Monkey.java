package de.turing85.advent.of.code.tttt.day.eleven.model;

import java.util.Deque;
import java.util.function.LongUnaryOperator;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * A monkey
 */
@EqualsAndHashCode
public class Monkey {
  @Getter
  private final int id;

  @EqualsAndHashCode.Exclude
  private final Deque<Long> items;

  @EqualsAndHashCode.Exclude
  private final LongUnaryOperator operation;

  @EqualsAndHashCode.Exclude
  @Getter
  private final long testDivider;

  @EqualsAndHashCode.Exclude
  @Setter
  private Monkey throwToIfPositiveTest;

  @EqualsAndHashCode.Exclude
  @Setter
  private Monkey throwToIfNegativeTest;

  @EqualsAndHashCode.Exclude
  @Setter
  private long itemModulo;

  /**
   * Constructs a monkey.
   *
   * @param id this monkey's id
   * @param items the items the monkey starts with
   * @param operation worry-operation
   * @param testDivider the divider used for testing
   */
  public Monkey(int id, Deque<Long> items, LongUnaryOperator operation, long testDivider) {
    this.id = id;
    this.items = items;
    this.operation = operation;
    this.testDivider = testDivider;
  }

  /**
   * Play one round of Keep away.
   *
   * @param stressModifier stress modifier
   * @return the number of items inspected by this monkey
   */
  public int playTurn(LongUnaryOperator stressModifier) {
    int itemsInspected = 0;
    while (!items.isEmpty()) {
      ++itemsInspected;
      long item = items.pollFirst();
      long newItem = (stressModifier.applyAsLong(operation.applyAsLong(item))) % itemModulo;
      if ((newItem % testDivider) == 0) {
        throwToIfPositiveTest.catchItem(newItem);
      } else {
        throwToIfNegativeTest.catchItem(newItem);
      }
    }
    return itemsInspected;
  }

  private void catchItem(long item) {
    items.addLast(item);
  }
}
