package de.turing85.adventure.of.code.tttt.day.seventeen.model;

/** A {@link Tile} that is movable in the four cardinal {@link Direction}s. */
public interface MovableTile<T extends MovableTile<T>> extends Tile {
  /**
   * Moves {@code this} in the given direction.
   *
   * <p>if {@code this} cannot be moved, the method returns {@code this} unchanged.
   *
   * @param direction to move in
   * @return a {@link MovableTile} representing the moved tile
   */
  T move(Direction direction);
}
