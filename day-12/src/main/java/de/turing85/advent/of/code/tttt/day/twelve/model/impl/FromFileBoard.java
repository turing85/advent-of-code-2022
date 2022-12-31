package de.turing85.advent.of.code.tttt.day.twelve.model.impl;

import de.turing85.advent.of.code.tttt.day.twelve.model.Board;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import lombok.experimental.Delegate;

/**
 * A board constructed from a file, represented by a {@link Path}, holding a {@link
 * String}-representation of a board.
 */
public class FromFileBoard implements Board {
  @Delegate private final FromStringBoard fromStringBoard;

  /**
   * Constructor a file, represented by a {@link Path}, holding a {@link String}-representation of a
   * board.
   *
   * @param inputFile the file holding a {@link String}-representation of a board
   * @throws IOException if some I/O exception occurs when the file is read.
   */
  public FromFileBoard(Path inputFile) throws IOException {
    this.fromStringBoard = new FromStringBoard(Files.readString(inputFile));
  }
}
