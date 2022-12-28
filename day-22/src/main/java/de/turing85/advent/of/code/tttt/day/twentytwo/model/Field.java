package de.turing85.advent.of.code.tttt.day.twentytwo.model;

import java.util.EnumMap;
import java.util.function.UnaryOperator;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Delegate;

/**
 * Represents a field on the map, with its neighbours in the four cardinal {@link Direction}s.
 */
@Getter
@Setter
@EqualsAndHashCode
public class Field {
  @Delegate
  private final Point point;
  private final boolean hasRock;

  @Getter(AccessLevel.PRIVATE)
  @EqualsAndHashCode.Exclude
  private final EnumMap<Direction, Field> neighbours;

  @Getter(AccessLevel.PRIVATE)
  @EqualsAndHashCode.Exclude
  private final EnumMap<Direction, UnaryOperator<Direction>> rotationFunctions;

  /**
   * Constructor.
   *
   * @param point the {@link Point} of this {@link Field}
   * @param hasRock whether this field has a rock or not
   */
  public Field(Point point, boolean hasRock) {
    this.point = point;
    this.hasRock = hasRock;
    this.neighbours = new EnumMap<>(Direction.class);
    this.rotationFunctions = new EnumMap<>(Direction.class);
    for (Direction from : Direction.values()) {
      rotationFunctions.put(from, direction -> direction);
    }
  }

  /**
   * Gets a direct neighbouring {@link Field} in the specified {@code direction}.
   *
   * @param direction the {@link Direction}
   * @return the neighbouring {@link Field}
   */
  public Field neighbour(Direction direction) {
    return neighbours.get(direction);
  }

  /**
   * Sets a direct neighbouring {@link Field} in the specified {@code direction}.
   *
   * @param direction the {@link Direction} the neighbouring {@link Field} lies in
   * @param field the neighbouring {@link Field}
   * @return {@code this}, for changing
   */
  public Field neighbour(Direction direction, Field field) {
    neighbours.put(direction, field);
    return this;
  }

  /**
   * Get the rotation function to execute when moving in the current {@link Direction}.
   *
   * @param direction to move in
   * @return the rotation function
   */
  public UnaryOperator<Direction> rotationFunction(Direction direction) {
    return rotationFunctions.get(direction);
  }

  /**
   * Set the rotation function to execute when moving in the current {@link Direction}.
   *
   * @param direction to move in
   * @param function the rotation function
   */
  public void rotationFunction(Direction direction, UnaryOperator<Direction> function) {
    rotationFunctions.put(direction, function);
  }
}
