package de.turing85.advent.of.code.tttt.day.seventeen.supplier;

import de.turing85.advent.of.code.tttt.day.seventeen.model.Direction;
import java.util.List;
import java.util.function.Supplier;

/**
 * A supplier, supplying a {@link List} of {@link Direction}s.
 *
 * <p>The {@link List} will only include {@link Direction#RIGHT} and {@link Direction#LEFT}.
 */
public interface WindDirectionsSupplier extends Supplier<List<Direction>> {
  @Override
  default List<Direction> get() {
    return windDirections();
  }

  /**
   * Supplies {@link Direction}s
   *
   * @return the {@link Direction}s to return
   */
  List<Direction> windDirections();
}
