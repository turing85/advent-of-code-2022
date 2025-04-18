package de.turing85.advent.of.code.tttt.day.thirteen;

import de.turing85.advent.of.code.tttt.day.thirteen.model.Pair;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/** A signal decoder. */
public class Decoder {
  private static final List<Object> dividerPackages =
      List.of(List.of(List.of(2)), List.of(List.of(6)));

  private Decoder() {}

  /**
   * Determines what packages of a given {@code signal} are in the right order.
   *
   * @param signal the signal
   * @return set of indices of package-pairs that are in the right order.
   */
  public static Set<Integer> pairsInRightOrder(List<Pair<Object, Object>> signal) {
    Set<Integer> inRightOrder = new HashSet<>();
    for (int index = 0; index < signal.size(); ++index) {
      Pair<Object, Object> pair = signal.get(index);
      if (compare(pair.first(), pair.second()) < 0) {
        inRightOrder.add(index + 1);
      }
    }
    return inRightOrder;
  }

  private static int compare(Object lhs, Object rhs) {
    if (lhs instanceof Integer leftInt && rhs instanceof Integer rightInt) {
      return leftInt.compareTo(rightInt);
    }
    if (lhs instanceof List<?> leftList && rhs instanceof List<?> rightList) {
      return compare(leftList, rightList);
    }
    if (lhs instanceof Integer leftInt && rhs instanceof List<?> rightList) {
      return compare(List.of(leftInt), rightList);
    }
    if (lhs instanceof List<?> leftList && rhs instanceof Integer rightInt) {
      return compare(leftList, List.of(rightInt));
    }
    throw new IllegalArgumentException(
        "Arguments must be either Integers or list that contain lists and integers");
  }

  private static int compare(List<?> lhs, List<?> rhs) {
    for (int index = 0; index < Math.min(lhs.size(), rhs.size()); ++index) {
      int comparisonResult = compare(lhs.get(index), rhs.get(index));
      if (comparisonResult != 0) {
        return comparisonResult;
      }
    }
    return Integer.compare(lhs.size(), rhs.size());
  }

  /**
   * Calculates the decoder key of a signal
   *
   * @param signal the signal
   * @return the corresponding decoder key
   */
  public static int calculateDecoderKey(List<Pair<Object, Object>> signal) {
    List<Object> flattenedSignal =
        signal.stream()
            .map(pair -> List.of(pair.first(), pair.second()))
            .flatMap(Collection::stream)
            .collect(Collectors.toCollection(ArrayList::new));
    flattenedSignal.addAll(dividerPackages);
    flattenedSignal.sort(Decoder::compare);
    return (flattenedSignal.indexOf(dividerPackages.getFirst()) + 1)
        * (flattenedSignal.indexOf(dividerPackages.get(1)) + 1);
  }
}
