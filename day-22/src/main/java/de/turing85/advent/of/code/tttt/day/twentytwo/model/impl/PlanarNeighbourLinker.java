package de.turing85.advent.of.code.tttt.day.twentytwo.model.impl;

import de.turing85.advent.of.code.tttt.day.twentytwo.model.Direction;
import de.turing85.advent.of.code.tttt.day.twentytwo.model.Field;
import de.turing85.advent.of.code.tttt.day.twentytwo.model.NeighbourLinker;
import de.turing85.advent.of.code.tttt.day.twentytwo.model.Point;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/** Links neighbouring {@link Field}s in a 2D-fashion. */
public class PlanarNeighbourLinker implements NeighbourLinker {
  @Override
  public Set<Field> linkNeighbours(Set<Field> fields) {
    Map<Point, Field> fieldsByPoint =
        fields.stream().collect(Collectors.toMap(Field::point, Function.identity()));
    for (Field field : fieldsByPoint.values()) {
      Field up = findUp(field, fieldsByPoint);
      Field down = findDown(field, fieldsByPoint);
      Field right = findRight(field, fieldsByPoint);
      Field left = findLeft(field, fieldsByPoint);
      field
          .neighbour(Direction.UP, up)
          .neighbour(Direction.DOWN, down)
          .neighbour(Direction.RIGHT, right)
          .neighbour(Direction.LEFT, left);
    }
    return new HashSet<>(fieldsByPoint.values());
  }

  private static Field findUp(Field currentField, Map<Point, Field> fieldsByPoint) {
    int currentY = currentField.y();
    int currentX = currentField.x();
    Field maybeUp = fieldsByPoint.get(new Point(currentX, currentY - 1));
    if (Objects.nonNull(maybeUp)) {
      return maybeUp;
    }
    int upY =
        fieldsByPoint.values().stream()
            .filter(field -> field.x() == currentX)
            .mapToInt(Field::y)
            .max()
            .orElse(Integer.MIN_VALUE);
    return fieldsByPoint.get(new Point(currentX, upY));
  }

  private static Field findDown(Field currentField, Map<Point, Field> fieldsByPoint) {
    int currentY = currentField.y();
    int currentX = currentField.x();
    Field maybeDown = fieldsByPoint.get(new Point(currentX, currentY + 1));
    if (Objects.nonNull(maybeDown)) {
      return maybeDown;
    }
    int downY =
        fieldsByPoint.values().stream()
            .filter(field -> field.x() == currentX)
            .mapToInt(Field::y)
            .min()
            .orElse(Integer.MAX_VALUE);
    return fieldsByPoint.get(new Point(currentX, downY));
  }

  private static Field findRight(Field currentField, Map<Point, Field> fieldsByPoint) {
    int currentY = currentField.y();
    int currentX = currentField.x();
    Field maybeRight = fieldsByPoint.get(new Point(currentX + 1, currentY));
    if (Objects.nonNull(maybeRight)) {
      return maybeRight;
    }
    int rightX =
        fieldsByPoint.values().stream()
            .filter(field -> field.y() == currentY)
            .mapToInt(Field::x)
            .min()
            .orElse(Integer.MAX_VALUE);
    return fieldsByPoint.get(new Point(rightX, currentY));
  }

  private static Field findLeft(Field currentField, Map<Point, Field> fieldsByPoint) {
    int currentY = currentField.y();
    int currentX = currentField.x();
    Field maybeLeft = fieldsByPoint.get(new Point(currentX - 1, currentY));
    if (Objects.nonNull(maybeLeft)) {
      return maybeLeft;
    }
    int leftX =
        fieldsByPoint.values().stream()
            .filter(field -> field.y() == currentY)
            .mapToInt(Field::x)
            .max()
            .orElse(Integer.MIN_VALUE);
    return fieldsByPoint.get(new Point(leftX, currentY));
  }
}
