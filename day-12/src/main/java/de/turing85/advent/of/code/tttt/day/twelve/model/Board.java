package de.turing85.advent.of.code.tttt.day.twelve.model;

import java.util.Collection;

/**
 * A board of {@link Field}s.
 */
public interface Board {
  /**
   * Getter for end field.
   *
   * @return end field
   */
  Field end();

  /**
   * For a given {@code field}, get all neighbours from which {@code field} can be reached directly.
   *
   * @param field field
   * @return all fields from which {@code field} can be reached directly.
   */
  Collection<Field> reachableFromNeighbours(Field field);
}
