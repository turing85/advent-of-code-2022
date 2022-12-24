package de.turing85.advent.of.code.tttt.day.seven.model;

import static com.google.common.truth.Truth.assertThat;

import de.turing85.advent.of.code.tttt.day.seven.supplier.CommandsAndResponsesSupplier;
import de.turing85.advent.of.code.tttt.day.seven.supplier.impl.FromFileCommandsAndResponsesSupplier;
import java.io.IOException;
import java.nio.file.Path;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("CommandsAndResponsesParser tests")
class CommandsAndResponsesParserTest {
  public static final Path COMMON_INPUT = Path.of("src/test/resources/commonInput.txt");
  public static final Path PERSONAL_INPUT = Path.of("src/test/resources/personalInput.txt");

  @DisplayName("cumulated size of all directories < 100_000 on common input")
  @Test
  void cumulatedSizeOfAllDirectoriesLargerThantOneHundredThousandOnCommon() throws IOException {
    // GIVEN
    CommandsAndResponsesSupplier supplier = new FromFileCommandsAndResponsesSupplier(COMMON_INPUT);
    Directory directory = CommandsAndResponsesParser.parseCommandsAndResponses(supplier.get());

    // WHEN
    int size =
        directory.filterMapReduce(dir -> dir.size() < 100_000, Directory::size, 0, Integer::sum);

    // THEN
    assertThat(size).isEqualTo(95_437);
  }

  @DisplayName("Delete smallest directory such that update can be installed on common")
  @Test
  void deleteSmallestToInstallUpdateOnCommon() throws IOException {
    // GIVEN
    CommandsAndResponsesSupplier supplier = new FromFileCommandsAndResponsesSupplier(COMMON_INPUT);
    Directory directory = CommandsAndResponsesParser.parseCommandsAndResponses(supplier.get());
    int free = 70_000_000 - directory.size();
    int toFree = 30_000_000 - free;

    // WHEN
    int size = directory.filterMapReduce(dir -> dir.size() > toFree, Directory::size,
        Integer.MAX_VALUE, Integer::min);

    // THEN
    assertThat(size).isEqualTo(24_933_642);
  }

  @DisplayName("cumulated size of all directories < 100_000 on personal input")
  @Test
  void cumulatedSizeOfAllDirectoriesLargerThantOneHundredThousandOnPersonal() throws IOException {
    // GIVEN
    CommandsAndResponsesSupplier supplier =
        new FromFileCommandsAndResponsesSupplier(PERSONAL_INPUT);
    Directory directory = CommandsAndResponsesParser.parseCommandsAndResponses(supplier.get());

    // WHEN
    int size =
        directory.filterMapReduce(dir -> dir.size() < 100_000, Directory::size, 0, Integer::sum);

    // THEN
    assertThat(size).isEqualTo(1086_293);
  }

  @DisplayName("Delete smallest directory such that update can be installed on personal")
  @Test
  void deleteSmallestToInstallUpdateOnPersonal() throws IOException {
    // GIVEN
    CommandsAndResponsesSupplier supplier =
        new FromFileCommandsAndResponsesSupplier(PERSONAL_INPUT);
    Directory directory = CommandsAndResponsesParser.parseCommandsAndResponses(supplier.get());
    int free = 70_000_000 - directory.size();
    int toFree = 30_000_000 - free;

    // WHEN
    int size = directory.filterMapReduce(dir -> dir.size() > toFree, Directory::size,
        Integer.MAX_VALUE, Integer::min);

    // THEN
    assertThat(size).isEqualTo(366_028);
  }
}
