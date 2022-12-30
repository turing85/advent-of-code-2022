package de.turing85.advent.of.code.tttt.day.twentythree.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * An Elf.
 *
 * @param position the {@link Elf}'s position
 */
public record Elf(Point position) {
  /**
   * Propose the next position {@code this} {@link Elf} wants to move to
   *
   * @param positionOfElves the position of all elves, represented by {@link Point}s
   * @param directionsToConsiderInOrder the {@link Direction.Cardinal}s to consider
   *
   * @return the next position this elf wants to move to
   */
  Point proposeNextPoint(Set<Point> positionOfElves, List<Direction.Cardinal> directionsToConsiderInOrder) {
    if (willNotMove(positionOfElves)) {
      return position();
    }
    for (Direction.Cardinal directionToConsider : directionsToConsiderInOrder) {
      if (canMoveInDirection(directionToConsider, positionOfElves)) {
        return position().neighbour(directionToConsider);
      }
    }
    return position();
  }

  private boolean willNotMove(Set<Point> positionOfElves) {
    return !new HashSet<>(this.position().neighbours()).removeAll(positionOfElves);
  }

  private boolean canMoveInDirection(Direction.Cardinal cardinalDirection,
                                     Set<Point> positionOfElves) {
    Set<Point> mustBeFree =
        cardinalDirection.composes().stream()
            .map(this.position()::neighbour).collect(Collectors.toSet());
    return mustBeFree.stream().map(positionOfElves::contains).map(contained -> !contained)
        .reduce(true, Boolean::logicalAnd);
  }
}
