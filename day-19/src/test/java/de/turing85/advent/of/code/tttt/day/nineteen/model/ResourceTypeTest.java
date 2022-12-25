package de.turing85.advent.of.code.tttt.day.nineteen.model;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("RobotRecipe tests")
class ResourceTypeTest {
  @Nested
  @DisplayName("factory method tests")
  class FactoryMethodTest {
    @DisplayName("legal inputs work")
    @ParameterizedTest(name = "{0}")
    @CsvSource({"ORE", "CLAY", "OBSIDIAN", "GEODE"})
    void factoryMethodWorks(ResourceType expected) {
      // GIVEN

      // WHEN
      ResourceType actual = ResourceType.of(expected.name());

      // THEN
      assertThat(actual).isSameInstanceAs(expected);
    }

    @DisplayName("throws on illegal input")
    @Test
    void throwsOnIllegalInput() {
      // GIVEN

      // WHEN & THEN
      assertThrows(IllegalArgumentException.class, () -> ResourceType.of("OUCH"));
    }
  }
}
