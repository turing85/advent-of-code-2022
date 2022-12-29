package de.turing85.advent.of.code.tttt.day.twentytwo.model.impl;

import de.turing85.advent.of.code.tttt.day.twentytwo.model.Direction;
import de.turing85.advent.of.code.tttt.day.twentytwo.model.Field;
import de.turing85.advent.of.code.tttt.day.twentytwo.model.NeighbourLinker;
import de.turing85.advent.of.code.tttt.day.twentytwo.model.Point;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

/**
 * Linker that links the map as cube.
 */
public class CubeNeighbourLinker implements NeighbourLinker {
  @Override
  public Set<Field> linkNeighbours(Set<Field> fields) {
    Set<Point> points = fields.stream().map(Field::point).collect(Collectors.toSet());
    int cubeEdgeLength = calculateCubeEdgeLength(fields);
    Map<Point, Field> fieldsByPoint = calculateFieldsByPoints(fields);
    Set<Point> quadrants = calculateQuadrants(fields, cubeEdgeLength);
    Map<Point, Set<Direction>> sewedSides = new HashMap<>();
    quadrants.forEach(quadrant -> sewedSides.put(quadrant, new HashSet<>()));
    for (int quadrantY = 1; quadrantY <= 4; ++quadrantY) {
      for (int quadrantX = 1; quadrantX <= 4; ++quadrantX) {
        if (quadrantExists(points, cubeEdgeLength, quadrantX, quadrantY)) {
          sewQuadrant(fields, fieldsByPoint, cubeEdgeLength, quadrantX, quadrantY);
          sewUpperEdge(fieldsByPoint, sewedSides, quadrants, cubeEdgeLength, quadrantX, quadrantY);
          sewLowerEdge(fieldsByPoint, sewedSides, quadrants, cubeEdgeLength, quadrantX, quadrantY);
          sewRightEdge(fieldsByPoint, sewedSides, quadrants, cubeEdgeLength, quadrantX, quadrantY);
          sewLeftEdge(fieldsByPoint, sewedSides, quadrants, cubeEdgeLength, quadrantX, quadrantY);
        }
      }
    }
    return fields;
  }

  private static int calculateCubeEdgeLength(Set<Field> fields) {
    int maxX = fields.stream().mapToInt(Field::x).max().orElse(Integer.MIN_VALUE);
    int minX = fields.stream().mapToInt(Field::x).min().orElse(Integer.MAX_VALUE);
    int deltaX = maxX - minX + 1;
    int maxY = fields.stream().mapToInt(Field::y).max().orElse(Integer.MIN_VALUE);
    int minY = fields.stream().mapToInt(Field::y).min().orElse(Integer.MAX_VALUE);
    int deltaY = maxY - minY + 1;
    return Math.max(deltaX, deltaY) / 4;
  }


  private static Map<Point, Field> calculateFieldsByPoints(Set<Field> fields) {
    return fields.stream().collect(Collectors.toMap(Field::point, Function.identity()));
  }



  private static Set<Point> calculateQuadrants(Set<Field> fields, int cubeEdgeLength) {
    Set<Point> existingQuadrants = new HashSet<>();
    Set<Point> points = fields.stream().map(Field::point).collect(Collectors.toSet());
    for (int y = 1; y <= 4; ++y) {
      for (int x = 1; x <= 4; ++x) {
        if (quadrantExists(points, cubeEdgeLength, x, y)) {
          existingQuadrants.add(new Point(x, y));
        }
      }
    }
    return existingQuadrants;
  }

  private static boolean quadrantExists(Set<Point> points, int cubeEdgeLength, int x, int y) {
    return points.contains(new Point(x * cubeEdgeLength, y * cubeEdgeLength));
  }

  private void sewQuadrant(Set<Field> fields, Map<Point, Field> fieldsByPoint, int cubeEdgeLength,
      int quadrantX, int quadrantY) {
    int minX = cubeEdgeLength * (quadrantX - 1) + 1;
    int maxX = (cubeEdgeLength) * quadrantX;
    int minY = cubeEdgeLength * (quadrantY - 1) + 1;
    int maxY = (cubeEdgeLength) * quadrantY;
    Set<Field> relevantFields =
        fields.stream().filter(field -> minX <= field.x() && field.x() <= maxX)
            .filter(field -> minY <= field.y() && field.y() <= maxY).collect(Collectors.toSet());
    for (Field field : relevantFields) {
      int x = field.x();
      int y = field.y();
      if (x > minX) {
        field.neighbour(Direction.LEFT, fieldsByPoint.get(new Point(x - 1, y)));
      }
      if (x < maxX) {
        field.neighbour(Direction.RIGHT, fieldsByPoint.get(new Point(x + 1, y)));
      }
      if (y > minY) {
        field.neighbour(Direction.UP, fieldsByPoint.get(new Point(x, y - 1)));
      }
      if (y < maxY) {
        field.neighbour(Direction.DOWN, fieldsByPoint.get(new Point(x, y + 1)));
      }
    }
  }

  private void sewUpperEdge(Map<Point, Field> fieldsByPoint, Map<Point, Set<Direction>> sewedSides,
      Set<Point> quadrants, int cubeEdgeLength, int quadrantX, int quadrantY) {
    if (sewedSides.get(new Point(quadrantX, quadrantY)).contains(Direction.UP)) {
      return;
    }
    UnaryOperator<Direction> rotationFunction = null;
    UnaryOperator<Direction> theirRotationFunction = null;
    Direction theirSide = null;
    int theirQuadrantY = quadrantY - 1;
    int theirQuadrantX = Integer.MIN_VALUE;
    IntFunction<Field> upperSideSewingFunction = null;
    boolean readyToSew = false;
    int ourY = (quadrantY - 1) * cubeEdgeLength + 1;
    for (int quadrantXDelta = 0; quadrantXDelta < 4; ++quadrantXDelta) {
      int finalQuadrantXDelta = quadrantXDelta;
      theirQuadrantX = quadrantX + quadrantXDelta;
      if (quadrants.contains(new Point(theirQuadrantX, theirQuadrantY))) {
        rotationFunction = rotation -> rotateNTimes(finalQuadrantXDelta, rotation, Direction.RIGHT);
        theirRotationFunction =
            rotation -> rotateNTimes(finalQuadrantXDelta, rotation, Direction.LEFT);
        theirSide = rotationFunction.apply(Direction.DOWN);
        upperSideSewingFunction = sewingFunction(fieldsByPoint, cubeEdgeLength, theirQuadrantX,
            theirQuadrantY, theirSide, false);
        readyToSew = true;
        break;
      }
      theirQuadrantX = quadrantX - quadrantXDelta;
      if (quadrants.contains(new Point(theirQuadrantX, theirQuadrantY))) {
        rotationFunction = rotation -> rotateNTimes(finalQuadrantXDelta, rotation, Direction.LEFT);
        theirRotationFunction =
            rotation -> rotateNTimes(finalQuadrantXDelta, rotation, Direction.RIGHT);
        theirSide = rotationFunction.apply(Direction.DOWN);
        upperSideSewingFunction = sewingFunction(fieldsByPoint, cubeEdgeLength, theirQuadrantX,
            theirQuadrantY, theirSide, true);
        readyToSew = true;
        break;
      }
    }
    if (!readyToSew) {
      theirQuadrantY = quadrantY + 3;
      for (int deltaX = 0; deltaX <= 2; ++deltaX) {
        theirQuadrantX = quadrantX + deltaX;
        final int finalDeltaX = deltaX;
        if (quadrants.contains(new Point(theirQuadrantX, theirQuadrantY))) {
          if (deltaX == 1) {
            rotationFunction = rotation -> rotateNTimes(finalDeltaX, rotation, Direction.RIGHT);
            theirRotationFunction = rotation -> rotateNTimes(finalDeltaX, rotation, Direction.LEFT);
          } else {
            rotationFunction = rotation -> rotation;
            theirRotationFunction = rotationFunction;
          }
          theirSide = rotationFunction.apply(Direction.DOWN);
          upperSideSewingFunction = sewingFunction(fieldsByPoint, cubeEdgeLength, theirQuadrantX,
              theirQuadrantY, theirSide, true);
          readyToSew = true;
          break;
        }
        theirQuadrantX = quadrantX - deltaX;
        if (quadrants.contains(new Point(theirQuadrantX, theirQuadrantY))) {
          if (deltaX == 1) {
            rotationFunction = rotation -> rotateNTimes(finalDeltaX, rotation, Direction.RIGHT);
            theirRotationFunction = rotation -> rotateNTimes(finalDeltaX, rotation, Direction.LEFT);
          } else {
            rotationFunction = rotation -> rotation;
            theirRotationFunction = rotationFunction;
          }
          theirSide = rotationFunction.apply(Direction.DOWN);
          upperSideSewingFunction = sewingFunction(fieldsByPoint, cubeEdgeLength, theirQuadrantX,
              theirQuadrantY, theirSide, false);
          readyToSew = true;
          break;
        }
      }
    }
    if (readyToSew) {
      sewTopBottom(fieldsByPoint, cubeEdgeLength, quadrantX, upperSideSewingFunction,
          rotationFunction, theirRotationFunction, ourY, Direction.UP, theirSide);
      sewedSides.get(new Point(quadrantX, quadrantY)).add(Direction.UP);
      sewedSides.get(new Point(theirQuadrantX, theirQuadrantY)).add(theirSide);
    }
  }

  private static Direction rotateNTimes(int n, Direction direction, Direction rotation) {
    if (n == 0) {
      return direction;
    }
    Direction rotated = direction;
    for (int i = 0; i < n; ++i) {
      rotated = rotated.rotate(rotation);
    }
    return rotated;
  }

  private IntFunction<Field> sewingFunction(Map<Point, Field> fieldsByPoint, int cubeEdgeSize,
      int quadrantX, int quadrantY, Direction side, boolean clockwise) {
    int minX = (quadrantX - 1) * cubeEdgeSize + 1;
    int maxX = quadrantX * cubeEdgeSize;
    int minY = (quadrantY - 1) * cubeEdgeSize + 1;
    int maxY = quadrantY * cubeEdgeSize;
    return switch (side) {
      case UP -> upSewingFunction(fieldsByPoint, clockwise, minX, maxX, minY);
      case DOWN -> downSewingFunction(fieldsByPoint, clockwise, minX, maxX, maxY);
      case LEFT -> leftSewingFunction(fieldsByPoint, clockwise, minX, minY, maxY);
      case RIGHT -> rightSewingFunction(fieldsByPoint, clockwise, maxX, minY, maxY);
    };
  }


  private static IntFunction<Field> upSewingFunction(Map<Point, Field> fieldsByPoint,
      boolean clockwise, int minX, int maxX, int minY) {
    if (clockwise) {
      return index -> fieldsByPoint.get(new Point(minX + index, minY));
    } else {
      return index -> fieldsByPoint.get(new Point(maxX - index, minY));
    }
  }

  private static IntFunction<Field> downSewingFunction(Map<Point, Field> fieldsByPoint,
      boolean clockwise, int minX, int maxX, int maxY) {
    if (clockwise) {
      return index -> fieldsByPoint.get(new Point(maxX - index, maxY));
    } else {
      return index -> fieldsByPoint.get(new Point(minX + index, maxY));
    }
  }

  private static IntFunction<Field> leftSewingFunction(Map<Point, Field> fieldsByPoint,
      boolean clockwise, int minX, int minY, int maxY) {
    if (clockwise) {
      return index -> fieldsByPoint.get(new Point(minX, maxY - index));
    } else {
      return index -> fieldsByPoint.get(new Point(minX, minY + index));
    }
  }

  private static IntFunction<Field> rightSewingFunction(Map<Point, Field> fieldsByPoint,
      boolean clockwise, int maxX, int minY, int maxY) {
    if (clockwise) {
      return index -> fieldsByPoint.get(new Point(maxX, minY + index));
    } else {
      return index -> fieldsByPoint.get(new Point(maxX, maxY - index));
    }
  }

  private void sewLowerEdge(Map<Point, Field> fieldsByPoint, Map<Point, Set<Direction>> sewedSides,
      Set<Point> quadrants, int cubeEdgeLength, int quadrantX, int quadrantY) {
    if (sewedSides.get(new Point(quadrantX, quadrantY)).contains(Direction.DOWN)) {
      return;
    }
    UnaryOperator<Direction> rotationFunction = null;
    UnaryOperator<Direction> theirRotationFunction = null;
    Direction theirSide = null;
    int theirQuadrantY = quadrantY + 1;
    int theirQuadrantX = Integer.MIN_VALUE;
    IntFunction<Field> lowerSideSewingFunction = null;
    boolean readyToSew = false;
    int ourY = quadrantY * cubeEdgeLength;
    for (int quadrantXDelta = 0; quadrantXDelta < 4; ++quadrantXDelta) {
      theirQuadrantX = quadrantX - quadrantXDelta;
      int finalQuadrantXDelta = quadrantXDelta;
      if (quadrants.contains(new Point(theirQuadrantX, theirQuadrantY))) {
        rotationFunction = rotation -> rotateNTimes(finalQuadrantXDelta, rotation, Direction.RIGHT);
        theirRotationFunction =
            rotation -> rotateNTimes(finalQuadrantXDelta, rotation, Direction.LEFT);
        theirSide = rotationFunction.apply(Direction.UP);
        lowerSideSewingFunction = sewingFunction(fieldsByPoint, cubeEdgeLength, theirQuadrantX,
            theirQuadrantY, theirSide, true);
        readyToSew = true;
        break;
      }
      theirQuadrantX = quadrantX + quadrantXDelta;
      if (quadrants.contains(new Point(theirQuadrantX, theirQuadrantY))) {
        rotationFunction = rotation -> rotateNTimes(finalQuadrantXDelta, rotation, Direction.LEFT);
        theirRotationFunction =
            rotation -> rotateNTimes(finalQuadrantXDelta, rotation, Direction.RIGHT);
        theirSide = rotationFunction.apply(Direction.UP);
        lowerSideSewingFunction = sewingFunction(fieldsByPoint, cubeEdgeLength, theirQuadrantX,
            theirQuadrantY, theirSide, true);
        readyToSew = true;
        break;
      }
    }
    if (!readyToSew && quadrants.contains(new Point(quadrantX, quadrantY + 3))) {
      theirQuadrantX = quadrantX;
      theirQuadrantY = quadrantY + 3;
      lowerSideSewingFunction =
          index -> fieldsByPoint.get(new Point(index, (quadrantY + 3) * cubeEdgeLength));
      rotationFunction = direction -> direction;
      theirRotationFunction = rotationFunction;
      theirSide = Direction.DOWN;
      readyToSew = true;
    }
    if (readyToSew) {
      sewTopBottom(fieldsByPoint, cubeEdgeLength, quadrantX, lowerSideSewingFunction,
          rotationFunction, theirRotationFunction, ourY, Direction.DOWN, theirSide);
      sewedSides.get(new Point(quadrantX, quadrantY)).add(Direction.DOWN);
      sewedSides.get(new Point(theirQuadrantX, theirQuadrantY)).add(theirSide);
    }
  }

  private static void sewTopBottom(Map<Point, Field> fieldsByPoint, int cubeEdgeLength,
      int quadrantX, IntFunction<Field> getTopBottomEdge, UnaryOperator<Direction> rotationFunction,
      UnaryOperator<Direction> theirRotationFunction, int ourY, Direction ourSide,
      Direction theirSide) {
    for (int deltaX = 0; deltaX < cubeEdgeLength; ++deltaX) {
      int x = (quadrantX - 1) * cubeEdgeLength + 1 + deltaX;
      sew(fieldsByPoint, getTopBottomEdge, rotationFunction, theirRotationFunction, x, ourY, deltaX,
          ourSide, theirSide);
    }
  }

  private static void sew(Map<Point, Field> fieldsByPoint, IntFunction<Field> getTopBottomEdge,
      UnaryOperator<Direction> rotationFunction, UnaryOperator<Direction> theirRotationFunction,
      int ourX, int ourY, int index, Direction ourSide, Direction theirSide) {
    Field field = fieldsByPoint.get(new Point(ourX, ourY));
    Field topBottomNeighbour = getTopBottomEdge.apply(index);
    field.neighbour(ourSide, topBottomNeighbour);
    field.rotationFunction(ourSide, rotationFunction);
    topBottomNeighbour.neighbour(theirSide, field);
    topBottomNeighbour.rotationFunction(theirSide, theirRotationFunction);
  }

  private void sewRightEdge(Map<Point, Field> fieldsByPoint, Map<Point, Set<Direction>> sewedSides,
      Set<Point> quadrants, int cubeEdgeLength, int quadrantX, int quadrantY) {
    if (sewedSides.get(new Point(quadrantX, quadrantY)).contains(Direction.RIGHT)) {
      return;
    }
    UnaryOperator<Direction> rotationFunction = null;
    UnaryOperator<Direction> theirRotationFunction = null;
    Direction theirSide = null;
    int theirQuadrantX = quadrantX + 1;
    int theirQuadrantY = Integer.MIN_VALUE;
    IntFunction<Field> rightSideSewingFunction = null;
    boolean readyToSew = false;
    int ourX = quadrantX * cubeEdgeLength;
    for (int quadrantYDelta = 0; quadrantYDelta < 4; ++quadrantYDelta) {
      int finalQuadrantYDelta = quadrantYDelta;
      theirQuadrantY = quadrantY + quadrantYDelta;
      if (quadrants.contains(new Point(theirQuadrantX, theirQuadrantY))) {
        rotationFunction = rotation -> rotateNTimes(finalQuadrantYDelta, rotation, Direction.RIGHT);
        theirRotationFunction =
            rotation -> rotateNTimes(finalQuadrantYDelta, rotation, Direction.LEFT);
        theirSide = rotationFunction.apply(Direction.LEFT);
        rightSideSewingFunction = sewingFunction(fieldsByPoint, cubeEdgeLength, theirQuadrantX,
            theirQuadrantY, theirSide, false);
        readyToSew = true;
        break;
      }
      theirQuadrantY = quadrantY - quadrantYDelta;
      if (quadrants.contains(new Point(theirQuadrantX, theirQuadrantY))) {
        rotationFunction = rotation -> rotateNTimes(finalQuadrantYDelta, rotation, Direction.LEFT);
        theirRotationFunction =
            rotation -> rotateNTimes(finalQuadrantYDelta, rotation, Direction.RIGHT);
        theirSide = rotationFunction.apply(Direction.LEFT);
        rightSideSewingFunction = sewingFunction(fieldsByPoint, cubeEdgeLength, theirQuadrantX,
            theirQuadrantY, theirSide, false);
        readyToSew = true;
        break;
      }
    }
    if (!readyToSew && quadrants.contains(new Point(quadrantX - 3, quadrantY))) {
      theirQuadrantX = quadrantX - 3;
      theirQuadrantY = quadrantY;
      rightSideSewingFunction =
          y -> fieldsByPoint.get(new Point((quadrantX - 4) * cubeEdgeLength + 1, y));
      rotationFunction = direction -> direction;
      theirRotationFunction = rotationFunction;
      theirSide = Direction.LEFT;
      readyToSew = true;
    }
    if (readyToSew) {
      sewSide(fieldsByPoint, cubeEdgeLength, quadrantY, rightSideSewingFunction, rotationFunction,
          theirRotationFunction, ourX, Direction.RIGHT, theirSide);
      sewedSides.get(new Point(quadrantX, quadrantY)).add(Direction.RIGHT);
      sewedSides.get(new Point(theirQuadrantX, theirQuadrantY)).add(theirSide);
    }
  }

  private static void sewSide(Map<Point, Field> fieldsByPoint, int cubeEdgeLength, int quadrantY,
      IntFunction<Field> getSideEdge, UnaryOperator<Direction> rotationFunction,
      UnaryOperator<Direction> theirRotationFunction, int ourX, Direction ourSide,
      Direction theirSide) {
    for (int deltaY = 0; deltaY < cubeEdgeLength; ++deltaY) {
      int y = (quadrantY - 1) * cubeEdgeLength + 1 + deltaY;
      sew(fieldsByPoint, getSideEdge, rotationFunction, theirRotationFunction, ourX, y, deltaY,
          ourSide, theirSide);
    }
  }

  private void sewLeftEdge(Map<Point, Field> fieldsByPoint, Map<Point, Set<Direction>> sewedSides,
      Set<Point> quadrants, int cubeEdgeLength, int quadrantX, int quadrantY) {
    if (sewedSides.get(new Point(quadrantX, quadrantY)).contains(Direction.LEFT)) {
      return;
    }
    UnaryOperator<Direction> rotationFunction = null;
    UnaryOperator<Direction> theirRotationFunction = null;
    Direction theirSide = null;
    int theirQuadrantX = quadrantX - 1;
    int theirQuadrantY = Integer.MIN_VALUE;
    IntFunction<Field> leftSideSewingFunction = null;
    boolean readyToSew = false;
    int ourX = (theirQuadrantX) * cubeEdgeLength + 1;
    for (int quadrantYDelta = 0; quadrantYDelta < 4; ++quadrantYDelta) {
      int finalQuadrantYDelta = quadrantYDelta;
      theirQuadrantY = quadrantY + quadrantYDelta;
      if (quadrants.contains(new Point(theirQuadrantX, theirQuadrantY))) {
        rotationFunction = rotation -> rotateNTimes(finalQuadrantYDelta, rotation, Direction.LEFT);
        theirRotationFunction =
            rotation -> rotateNTimes(finalQuadrantYDelta, rotation, Direction.RIGHT);
        theirSide = rotationFunction.apply(Direction.RIGHT);
        leftSideSewingFunction = sewingFunction(fieldsByPoint, cubeEdgeLength, theirQuadrantX,
            theirQuadrantY, theirSide, true);
        readyToSew = true;
        break;
      }
      theirQuadrantY = quadrantY - quadrantYDelta;
      if (quadrants.contains(new Point(theirQuadrantX, theirQuadrantY))) {
        rotationFunction = rotation -> rotateNTimes(finalQuadrantYDelta, rotation, Direction.RIGHT);
        theirRotationFunction =
            rotation -> rotateNTimes(finalQuadrantYDelta, rotation, Direction.LEFT);
        theirSide = rotationFunction.apply(Direction.RIGHT);
        leftSideSewingFunction = sewingFunction(fieldsByPoint, cubeEdgeLength, theirQuadrantX,
            theirQuadrantY, theirSide, true);
        readyToSew = true;
        break;
      }
    }
    if (!readyToSew) {
      theirQuadrantX = quadrantX + 3;
      for (int deltaY = 0; deltaY <= 1; ++deltaY) {
        theirQuadrantY = quadrantY + deltaY;
        if (quadrants.contains(new Point(theirQuadrantX, theirQuadrantY))) {
          rotationFunction = rotation -> rotateNTimes(1, rotation, Direction.RIGHT);
          theirRotationFunction = rotation -> rotateNTimes(1, rotation, Direction.LEFT);
          theirSide = rotationFunction.apply(Direction.RIGHT);
          leftSideSewingFunction = sewingFunction(fieldsByPoint, cubeEdgeLength, theirQuadrantX,
              theirQuadrantY, theirSide, true);
          readyToSew = true;
          break;
        }
        theirQuadrantY = quadrantY - deltaY;
        if (quadrants.contains(new Point(theirQuadrantX, theirQuadrantY))) {
          rotationFunction = rotation -> rotateNTimes(1, rotation, Direction.LEFT);
          theirRotationFunction = rotation -> rotateNTimes(1, rotation, Direction.RIGHT);
          theirSide = rotationFunction.apply(Direction.RIGHT);
          leftSideSewingFunction = sewingFunction(fieldsByPoint, cubeEdgeLength, theirQuadrantX,
              theirQuadrantY, theirSide, false);
          readyToSew = true;
          break;
        }
      }
    }
    if (readyToSew) {
      sewSide(fieldsByPoint, cubeEdgeLength, quadrantY, leftSideSewingFunction, rotationFunction,
          theirRotationFunction, ourX, Direction.LEFT, theirSide);
      sewedSides.get(new Point(quadrantX, quadrantY)).add(Direction.LEFT);
      sewedSides.get(new Point(theirQuadrantX, theirQuadrantY)).add(theirSide);
    }
  }
}
