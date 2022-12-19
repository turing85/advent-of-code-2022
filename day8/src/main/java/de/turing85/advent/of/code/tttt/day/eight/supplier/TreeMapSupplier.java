package de.turing85.advent.of.code.tttt.day.eight.supplier;

import java.util.function.Supplier;

/**
 * A supplier, supplying a tree map as {@code int[][]}.
 */
public interface TreeMapSupplier extends Supplier<int[][]> {
  default int[][] get() {
    return treeMap();
  }

  /**
   * Supplies a tree map.
   *
   * @return the tree map to supply.
   */
  int[][] treeMap();
}
