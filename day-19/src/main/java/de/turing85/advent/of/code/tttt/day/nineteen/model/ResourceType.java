package de.turing85.advent.of.code.tttt.day.nineteen.model;

import java.util.Locale;

/** A resource type. */
public enum ResourceType {
  /** Ore */
  ORE,
  /** Clay */
  CLAY,
  /** Obsidian */
  OBSIDIAN,
  /** Geode */
  GEODE;

  /**
   * Gets q {@link ResourceType} from a String.
   *
   * @param resourceTypeAsString String-representation of a {@link ResourceType}
   * @return the corresponding {@link ResourceType}.
   */
  public static ResourceType of(String resourceTypeAsString) {
    return switch (resourceTypeAsString.toUpperCase(Locale.ROOT)) {
      case "ORE" -> ORE;
      case "CLAY" -> CLAY;
      case "OBSIDIAN" -> OBSIDIAN;
      case "GEODE" -> GEODE;
      default ->
          throw new IllegalArgumentException(
              "\"%s\" is not a known ResourceType".formatted(resourceTypeAsString));
    };
  }
}
