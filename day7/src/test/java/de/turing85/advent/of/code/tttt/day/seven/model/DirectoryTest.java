package de.turing85.advent.of.code.tttt.day.seven.model;

import static com.google.common.truth.Truth.assertThat;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("Directory tests")
class DirectoryTest {

  @Nested
  @DisplayName("Constructor tests")
  class ConstructorTest {
    @Test
    @DisplayName("NoArgsConstructor creates root directory")
    void noArgsConstructorCreatesRootDirectory() {
      // GIVEN: nothing

      // WHEN
      Directory actual = new Directory();

      // THEN
      assertThat(actual.name()).isEqualTo("/");
      assertThat(actual.directories()).isEmpty();
      assertThat(actual.files()).isEmpty();
    }

    @Test
    @DisplayName("Constructor creates empty directory with parent")
    void constructorCreatesEmptyDirectorywithParent() {
      // GIVEN
      Directory expectedParent = new Directory();
      String expectedName = "expectedName";

      // WHEN
      Directory actual = new Directory(expectedName, expectedParent);

      // THEN
      assertThat(actual.name()).isEqualTo(expectedName);
      assertThat(actual.directories()).isEmpty();
      assertThat(actual.files()).isEmpty();
      assertThat(actual.parent()).isEqualTo(expectedParent);
    }

    @Test
    @DisplayName("Constructs expected dorectory")
    void constructsExpectedDirectory() {
      // GIVEN
      Directory expectedParent = new Directory();
      String expectedName = "expectedName";
      Set<File> expectedFiles = new HashSet<>();
      Set<Directory> expectedDirectories = new HashSet<>();

      // WHEN
      Directory actual =
          new Directory(expectedName, expectedDirectories, expectedFiles, expectedParent);

      // THEN
      assertThat(actual.name()).isEqualTo(expectedName);
      assertThat(actual.directories()).isSameInstanceAs(expectedDirectories);
      assertThat(actual.files()).isSameInstanceAs(expectedFiles);
      assertThat(actual.parent()).isEqualTo(expectedParent);
    }
  }

  @Nested
  @DisplayName("Mutator tests")
  class MutatorTests {
    @Test
    @DisplayName("add directory works")
    void addDirectoryWorks() {
      // GIVEN
      Directory directory = new Directory("directory", new Directory());
      String someDirectoryName = "someDirectoryName";

      // WHEN
      directory.addDirectory(someDirectoryName);

      // THEN
      Directory someDirectory = directory.goToChild(someDirectoryName);
      assertThat(someDirectory).isNotNull();
      assertThat(someDirectory.name()).isEqualTo(someDirectoryName);
    }

    @Test
    @DisplayName("same directory can be edit multiple times, but without consequence")
    void addDirectoryMultipleTimesWorks() {
      // GIVEN
      Directory directory = new Directory("directory", new Directory());
      String someDirectoryName = "someDirectoryName";

      // WHEN
      directory.addDirectory(someDirectoryName).addDirectory(someDirectoryName);

      // THEN
      assertThat(directory.directories()).hasSize(1);
      Directory someDirectory = directory.goToChild(someDirectoryName);
      assertThat(someDirectory).isNotNull();
      assertThat(someDirectory.name()).isEqualTo(someDirectoryName);
    }

    @Test
    @DisplayName("add file works")
    void addFileWorks() {
      // GIVEN
      Directory directory = new Directory("directory", new Directory());
      String someFileName = "someFileName";
      int someFileSize = new Random().nextInt(Integer.MAX_VALUE);

      // WHEN
      directory.addFile(someFileName, someFileSize);

      // THEN
      assertThat(directory.files()).contains(new File(someFileName, someFileSize));
    }

    @Test
    @DisplayName("same file can be added multiple times, but with no consequence")
    void addDFileMultipleTimesWorks() {
      // GIVEN
      Directory directory = new Directory("directory", new Directory());
      String someFileName = "someFileName";
      int someFileSize = new Random().nextInt(Integer.MAX_VALUE);

      // WHEN
      directory.addFile(someFileName, someFileSize).addFile(someFileName, someFileSize);

      // THEN
      assertThat(directory.files()).hasSize(1);
      assertThat(directory.files()).contains(new File(someFileName, someFileSize));
    }
  }
}
