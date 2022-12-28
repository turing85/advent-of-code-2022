package de.turing85.advent.of.code.tttt.day.twentytwo.model;

import java.util.Set;
import java.util.function.UnaryOperator;

/**
 * Links fields together, i.e. sets the {@link Field#neighbour(Direction, Field)} relationships
 */
public interface NeighbourLinker extends UnaryOperator<Set<Field>> {
  @Override
  default Set<Field> apply(Set<Field> fields) {
    return linkNeighbours(fields);
  }

  /**
   * Links fields together, i.e. sets the {@link Field#neighbour(Direction, Field)} relationships
   *
   * @param fields to link
   *
   * @return linked {@link Field}s
   */
  Set<Field> linkNeighbours(Set<Field> fields);
}
