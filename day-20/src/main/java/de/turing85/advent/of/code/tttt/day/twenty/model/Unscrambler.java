package de.turing85.advent.of.code.tttt.day.twenty.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Unscrambles a scrambled {@link List} of {@link Long}s.
 */
public class Unscrambler {
  private Unscrambler() {}

  /**
   * Decodes a coordinate from a scrambled {@link List} of {@link Long}s.
   *
   * @param scrambled the scrambled list
   * @param multiplier the multiplier to multiply each value in {@code scrambled} by
   * @param iterations the number of unscrambles to perform
   *
   * @return the coordinate encoded in this scrambled list.
   */
  public static long decodeLocation(List<Long> scrambled, long multiplier, int iterations) {
    List<Long> decoded = unscramble(scrambled, multiplier, iterations);
    int size = decoded.size();
    int zeroPosition = decoded.indexOf(0L);
    long firstValue = decoded.get((zeroPosition + 1000) % size);
    long secondValue = decoded.get((zeroPosition + 2000) % size);
    long thirdValue = decoded.get((zeroPosition + 3000) % size);
    return (firstValue + secondValue + thirdValue);
  }

  private static List<Long> unscramble(List<Long> scrambled, long multiplier, int iterations) {
    List<ScrambledLong> result = constructScramblelist(scrambled, multiplier);
    Map<Integer, ScrambledLong> initialOrder = calculateInitialOrder(result);
    for (int iteration = 0; iteration < iterations; ++iteration) {
      result = unscrambleOnce(result, initialOrder);
    }
    return result.stream().map(ScrambledLong::value).toList();
  }

  private static List<ScrambledLong> unscrambleOnce(List<ScrambledLong> scrambled,
      Map<Integer, ScrambledLong> initialOrder) {
    List<ScrambledLong> unscrambled = new ArrayList<>(scrambled);
    int size = scrambled.size();
    for (int index = 0; index < size; ++index) {
      ScrambledLong entry = initialOrder.get(index);
      int entryIndex = unscrambled.indexOf(entry);
      unscrambled.remove(entry);
      int newIndex = (entryIndex + entry.ringValue(size)) % (size - 1);
      unscrambled.add(newIndex, entry);
    }
    return unscrambled;
  }

  private static Map<Integer, ScrambledLong> calculateInitialOrder(List<ScrambledLong> result) {
    Map<Integer, ScrambledLong> initialOrder = new HashMap<>();
    for (int index = 0; index < result.size(); ++index) {
      initialOrder.put(index, result.get(index));
    }
    return initialOrder;
  }

  private static List<ScrambledLong> constructScramblelist(List<Long> scrambled, long multiplier) {
    List<ScrambledLong> result = new ArrayList<>();
    for (int index = 0; index < scrambled.size(); ++index) {
      result.add(new ScrambledLong(scrambled.get(index) * multiplier, index));
    }
    return result;
  }
}
