package de.turing85.adventure.of.code.tttt.day.seventeen.supplier.impl;

import de.turing85.adventure.of.code.tttt.day.seventeen.model.Direction;
import de.turing85.adventure.of.code.tttt.day.seventeen.supplier.WindDirectionsSupplier;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

/**
 * Reads a {@link String}-representation of {@link Direction#RIGHT}s (represented by {@code >}) and
 * {@link Direction#LEFT}s (represented by {@code <}).
 */
public class FromStringWindDirectionsSupplier implements WindDirectionsSupplier {
  @Getter private final List<Direction> windDirections;

  /**
   * Reads a {@link String}-representation of {@link Direction#RIGHT}s and {@link Direction#LEFT}s.
   *
   * @param inputAsString the {@link String}-representation of {@link Direction#RIGHT}s and {@link
   *     Direction#LEFT}s
   */
  public FromStringWindDirectionsSupplier(String inputAsString) {
    windDirections = new ArrayList<>();
    for (char c : inputAsString.toCharArray()) {
      windDirections.add(
          switch (c) {
            case '>' -> Direction.RIGHT;
            case '<' -> Direction.LEFT;
            default -> throw new IllegalStateException(
                "String contains character that is neither \">\" nor \"<\"");
          });
    }
  }
}
