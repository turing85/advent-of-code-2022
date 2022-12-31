package de.turing85.advent.of.code.tttt.day.eight.supplier.impl;

import de.turing85.advent.of.code.tttt.day.eight.supplier.TreeMapSupplier;

/** Reads a {@link String}-representation of a tree map. */
public class FromStringTreeMapSupplier implements TreeMapSupplier {
  private final int[][] treeMap;

  /**
   * Reads a {@link String}-representation of a tree map.
   *
   * @param inputAsString the {@link String}-representation of a tree map.
   */
  public FromStringTreeMapSupplier(String inputAsString) {
    String[] inputLines = inputAsString.split(System.lineSeparator());
    int height = inputLines.length;
    int width = inputLines[0].length();
    treeMap = new int[height][];
    for (int lineIndex = 0; lineIndex < inputLines.length; ++lineIndex) {
      String inputLine = inputLines[lineIndex];
      int[] treeMapLine = new int[width];
      for (int columnIndex = 0; columnIndex < width; ++columnIndex) {
        treeMapLine[columnIndex] = inputLine.charAt(columnIndex) - '0';
      }
      treeMap[lineIndex] = treeMapLine;
    }
  }

  @Override
  public int[][] treeMap() {
    return treeMap;
  }
}
