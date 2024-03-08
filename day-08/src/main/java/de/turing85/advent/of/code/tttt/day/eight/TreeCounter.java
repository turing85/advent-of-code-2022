package de.turing85.advent.of.code.tttt.day.eight;

/** Counts trees. */
public class TreeCounter {
  private TreeCounter() {}

  /**
   * Given a tree map, counts the trees visible from the outside of the map.
   *
   * @param treeMap the tree map
   * @return the number of trees visible.
   */
  public static int countVisibleTreesFromOutside(int[][] treeMap) {
    int visibleTreeCount = 0;
    int height = treeMap.length;
    int width = treeMap[0].length;
    for (int i = 0; i < height; ++i) {
      for (int j = 0; j < width; ++j) {
        if (isVisibleFromLeft(treeMap, i, j)
            || isVisibleFromRight(treeMap, i, j)
            || isVisibleFromTop(treeMap, i, j)
            || isVisibleFromBottom(treeMap, i, j)) {
          ++visibleTreeCount;
        }
      }
    }

    return visibleTreeCount;
  }

  private static boolean isVisibleFromLeft(int[][] treeMap, int currentI, int currentJ) {
    int currentTreeHeight = treeMap[currentI][currentJ];
    for (int j = currentJ - 1; j >= 0; --j) {
      if (treeMap[currentI][j] >= currentTreeHeight) {
        return false;
      }
    }
    return true;
  }

  private static boolean isVisibleFromRight(int[][] treeMap, int currentI, int currentJ) {
    int currentTreeHeight = treeMap[currentI][currentJ];
    for (int j = currentJ + 1; j < treeMap[currentI].length; ++j) {
      if (treeMap[currentI][j] >= currentTreeHeight) {
        return false;
      }
    }
    return true;
  }

  private static boolean isVisibleFromTop(int[][] treeMap, int currentI, int currentJ) {
    int currentTreeHeight = treeMap[currentI][currentJ];
    for (int i = currentI - 1; i >= 0; --i) {
      if (treeMap[i][currentJ] >= currentTreeHeight) {
        return false;
      }
    }
    return true;
  }

  private static boolean isVisibleFromBottom(int[][] treeMap, int currentI, int currentJ) {
    int currentTreeHeight = treeMap[currentI][currentJ];
    for (int i = currentI + 1; i < treeMap.length; ++i) {
      if (treeMap[i][currentJ] >= currentTreeHeight) {
        return false;
      }
    }
    return true;
  }

  /**
   * Calculates the highest scenic score of all trees in the map.
   *
   * @param treeMap the tree map
   * @return the highest scenic score
   */
  public static int calculateHighestScenicScore(int[][] treeMap) {
    int highestScenicScore = 0;
    int height = treeMap.length;
    int width = treeMap[0].length;
    for (int i = 0; i < height; ++i) {
      for (int j = 0; j < width; ++j) {
        highestScenicScore =
            Math.max(
                highestScenicScore,
                calculateScenicScoreLeft(treeMap, i, j)
                    * calculateScenicScoreRight(treeMap, i, j)
                    * calculateScenicScoreUp(treeMap, i, j)
                    * calculateScenicScoreDown(treeMap, i, j));
      }
    }
    return highestScenicScore;
  }

  private static int calculateScenicScoreLeft(int[][] treeMap, int currentI, int currentJ) {
    int currentTreeHeight = treeMap[currentI][currentJ];
    int visibleTrees = 0;
    for (int j = currentJ - 1; j >= 0; --j) {
      visibleTrees++;
      if (treeMap[currentI][j] >= currentTreeHeight) {
        break;
      }
    }
    return visibleTrees;
  }

  private static int calculateScenicScoreRight(int[][] treeMap, int currentI, int currentJ) {
    int currentTreeHeight = treeMap[currentI][currentJ];
    int visibleTrees = 0;
    for (int j = currentJ + 1; j < treeMap[currentI].length; ++j) {
      visibleTrees++;
      if (treeMap[currentI][j] >= currentTreeHeight) {
        break;
      }
    }
    return visibleTrees;
  }

  private static int calculateScenicScoreUp(int[][] treeMap, int currentI, int currentJ) {
    int currentTreeHeight = treeMap[currentI][currentJ];
    int visibleTrees = 0;
    for (int i = currentI - 1; i >= 0; --i) {
      visibleTrees++;
      if (treeMap[i][currentJ] >= currentTreeHeight) {
        break;
      }
    }
    return visibleTrees;
  }

  private static int calculateScenicScoreDown(int[][] treeMap, int currentI, int currentJ) {
    int currentTreeHeight = treeMap[currentI][currentJ];
    int visibleTrees = 0;
    for (int i = currentI + 1; i < treeMap.length; ++i) {
      visibleTrees++;
      if (treeMap[i][currentJ] >= currentTreeHeight) {
        break;
      }
    }
    return visibleTrees;
  }
}
