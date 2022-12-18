package de.turing85.advent.of.code.tttt.day.six;

import static com.google.common.truth.Truth.assertThat;

import java.io.IOException;
import java.nio.file.Path;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("StartOfPacketFinder tests")
class MarkerFinderTest {
  public static final Path PERSONAL_INPUT = Path.of("src/test/resources/personalInput.txt");

  @Nested
  @DisplayName("Start-of-packet tests")
  class StartOfPacketTest {
    @DisplayName("Simple test")
    @ParameterizedTest(name = "datastream {0}, start-of-packet sequence at {1}")
    @CsvSource({"aaaaaaaaaaaaaaaaaaaaaaaaaaaa,-1", "mjqjpqmgbljsphdztnvjfqwrcgsmlb,7",
        "bvwbjplbgvbhsrlpgdmjqwftvncz,5", "nppdvjthqldpwncqszvftbrmjlhg,6",
        "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg,10", "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw,11"})
    void test(String input, int expected) {
      // GIVEN: nothing

      // WHEN
      int actual = MarkerFinder.findStartOfPacket(input);

      // THEN
      assertThat(actual).isEqualTo(expected);
    }


    @Test
    @DisplayName("correct result on personal input")
    void commonInput() throws IOException {
      // GIVEN: nothing

      // WHEN
      int actual = MarkerFinder.findStartOfPacket(PERSONAL_INPUT);

      // THEN
      assertThat(actual).isEqualTo(1_804);
    }
  }

  @Nested
  @DisplayName("Start-of-message tests")
  class StartOfMessageTest {
    @DisplayName("Simple test")
    @ParameterizedTest(name = "datastream {0}, start-of-packet sequence at {1}")
    @CsvSource({"aaaaaaaaaaaaaaaaaaaaaaaaaaaa,-1", "mjqjpqmgbljsphdztnvjfqwrcgsmlb,19",
        "bvwbjplbgvbhsrlpgdmjqwftvncz,23", "nppdvjthqldpwncqszvftbrmjlhg,23",
        "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg,29", "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw,26"})
    void test(String input, int expected) {
      // GIVEN: nothing

      // WHEN
      int actual = MarkerFinder.findStartOfMessage(input);

      // THEN
      assertThat(actual).isEqualTo(expected);
    }


    @Test
    @DisplayName("correct result on personal input")
    void commonInput() throws IOException {
      // GIVEN: nothing

      // WHEN
      int actual = MarkerFinder.findStartOfMessage(PERSONAL_INPUT);

      // THEN
      assertThat(actual).isEqualTo(2_508);
    }
  }
}
