package de.turing85.advent.of.code.tttt.day.six;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

/** Signal marker finder. */
public class MarkerFinder {
  private MarkerFinder() {}

  /**
   * Given a data stream (as {@link Path}), finds the earliest start-of-packet marker
   *
   * @param inputFile the data stream, as file
   * @return the start-of-packet marker
   * @throws IOException if some I/O exception occurs when the file is read.
   */
  public static int findStartOfPacket(Path inputFile) throws IOException {
    return findMarker(Files.readString(inputFile), 4);
  }

  /**
   * Given a data stream (as {@link String}), finds the earliest start-of-packet marker
   *
   * @param dataStream the data stream, as {@link String}
   * @return the start-of-packet marker
   */
  public static int findStartOfPacket(String dataStream) {
    return findMarker(dataStream, 4);
  }

  /**
   * Given a data stream (as {@link Path}), finds the earliest start-of-message marker
   *
   * @param inputFile the data stream, as file
   * @return the start-of-message marker
   * @throws IOException if some I/O exception occurs when the file is read.
   */
  public static int findStartOfMessage(Path inputFile) throws IOException {
    return findMarker(Files.readString(inputFile), 14);
  }

  /**
   * Given a data stream (as {@link String}), finds the earliest start-of-message marker
   *
   * @param dataStream the data stream, as {@link String}
   * @return the start-of-message marker
   */
  public static int findStartOfMessage(String dataStream) {
    return findMarker(dataStream, 14);
  }

  private static int findMarker(String dataStreamString, int n) {
    for (int i = n; i < dataStreamString.length(); ++i) {
      if (n
          == dataStreamString
              .substring(i - n, i)
              .chars()
              .boxed()
              .collect(Collectors.toSet())
              .size()) {
        return i;
      }
    }
    return -1;
  }
}
