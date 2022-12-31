package de.turing85.advent.of.code.tttt.day.twelve.model.impl;

import de.turing85.advent.of.code.tttt.day.twelve.model.Board;
import de.turing85.advent.of.code.tttt.day.twelve.model.Field;
import de.turing85.advent.of.code.tttt.day.twelve.model.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import lombok.Getter;

/** A board constructed from a {@link String}. */
public class FromStringBoard implements Board {
  private final Map<Point, Field> fieldsByPoints = new HashMap<>();

  @Getter private Field end;

  private int width = -1;
  private int height = -1;

  /**
   * Constructor.
   *
   * @param inputAsString {@link String}-representation of a board.
   */
  public FromStringBoard(String inputAsString) {
    parseAndValidate(inputAsString);
  }

  private void parseAndValidate(String input) {
    String[] inputLines = input.split(System.lineSeparator());
    height = inputLines.length;
    for (int line = 0; line < inputLines.length; ++line) {
      parseAndValidateLine(inputLines[line], line);
    }
  }

  private void parseAndValidateLine(String inputLine, int line) {
    char[] lineAsChars = inputLine.toCharArray();
    if (this.width < 0) {
      this.width = lineAsChars.length;
    } else {
      validateWidth(inputLine.length());
    }
    for (int row = 0; row < lineAsChars.length; ++row) {
      char c = lineAsChars[row];
      addFieldToBoard(row, line, c);
    }
  }

  private void validateWidth(int lineWidth) {
    if (this.width != lineWidth) {
      throw new IllegalStateException("all lines must have same length!");
    }
  }

  private void addFieldToBoard(int x, int y, char c) {
    validateCharacterIsInRange(
        c, () -> "char %c at line %d, colum %d: must be a lowercase letter".formatted(c, y, x));
    Field field = new Field(c, new Point(x, y), convertToValue(c));
    if (c == 'E') {
      end(field);
    }
    fieldsByPoints.put(field.point(), field);
  }

  private static void validateCharacterIsInRange(char c, Supplier<String> errorMessageSupplier) {
    if (!Character.isLowerCase(c) && c != 'S' && c != 'E') {
      throw new IllegalStateException(errorMessageSupplier.get());
    }
  }

  private static int convertToValue(char c) {
    return switch (c) {
      case 'S' -> 0;
      case 'E' -> 'z' - 'a';
      default -> c - 'a';
    };
  }

  private void end(Field end) {
    if (this.end != null) {
      throw new IllegalStateException(
          "Input has two ends, at line %d, column %d and at line %d, colum %d"
              .formatted(end().point().y(), end().point().x(), end.point().y(), end.point().x()));
    }
    this.end = end;
  }

  @Override
  public Collection<Field> reachableFromNeighbours(Field field) {
    int x = field.point().x();
    int y = field.point().y();
    Collection<Field> neighboursThatCanReachField = new ArrayList<>();
    if (x > 0) {
      Field neighbour = fieldsByPoints.get(new Point(x - 1, y));
      if (field.height() - neighbour.height() <= 1) {
        neighboursThatCanReachField.add(neighbour);
      }
    }
    if (x < width - 1) {
      Field neighbour = fieldsByPoints.get(new Point(x + 1, y));
      if (field.height() - neighbour.height() <= 1) {
        neighboursThatCanReachField.add(neighbour);
      }
    }
    if (y > 0) {
      Field neighbour = fieldsByPoints.get(new Point(x, y - 1));
      if (field.height() - neighbour.height() <= 1) {
        neighboursThatCanReachField.add(neighbour);
      }
    }
    if (y < height - 1) {
      Field neighbour = fieldsByPoints.get(new Point(x, y + 1));
      if (field.height() - neighbour.height() <= 1) {
        neighboursThatCanReachField.add(neighbour);
      }
    }
    return neighboursThatCanReachField;
  }
}
