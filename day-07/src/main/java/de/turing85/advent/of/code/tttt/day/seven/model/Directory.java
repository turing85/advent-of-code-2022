package de.turing85.advent.of.code.tttt.day.seven.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/** A Directory. */
@EqualsAndHashCode
@Getter
public final class Directory {
  private final String name;

  @EqualsAndHashCode.Exclude private final Set<Directory> directories;

  @EqualsAndHashCode.Exclude private final Set<File> files;

  private final Directory parent;

  /**
   * @param name the name of the directory
   * @param directories directories directly contained in this directory
   * @param files files directly contained in this directory
   * @param parent parent-directory, is {@code null} for the root directory
   */
  public Directory(String name, Set<Directory> directories, Set<File> files, Directory parent) {
    this.name = name;
    this.directories = directories;
    this.files = files;
    this.parent = parent;
  }

  /**
   * Constructor
   *
   * @param name the name
   * @param parent the parent
   */
  public Directory(String name, Directory parent) {
    this(name, new HashSet<>(), new HashSet<>(), parent);
  }

  /** Constructor for root directory */
  public Directory() {
    this("/", new HashSet<>(), new HashSet<>(), null);
  }

  /**
   * Add directory in {@code this} directory.
   *
   * @param name the name of the new directory
   * @return {@code this}, for method chaining
   */
  public Directory addDirectory(String name) {
    directories.add(new Directory(name, this));
    return this;
  }

  /**
   * Add file in {@code this} directory.
   *
   * @param name name of the new file
   * @param size size of the new file
   * @return {@code this}. for method chaining
   */
  public Directory addFile(String name, int size) {
    files.add(new File(name, size));
    return this;
  }

  /**
   * Gets the size of the directory (= sum of size in this and all subdirectories)
   *
   * @return the size of the directory.
   */
  public int size() {
    return files().stream().mapToInt(File::size).sum()
        + directories().stream().mapToInt(Directory::size).sum();
  }

  /**
   * Filters, maps and reduces all directories, down from here
   *
   * @param predicate the filter predicate
   * @param mapper the mapper function
   * @param identity the identity, for reduction
   * @param operator the reduction operator
   * @param <T> the type of the operator, identity and result.
   * @return the result
   */
  public <T> T filterMapReduce(
      Predicate<Directory> predicate,
      Function<Directory, T> mapper,
      T identity,
      BinaryOperator<T> operator) {
    return filter(predicate).stream().map(mapper).reduce(identity, operator);
  }

  /**
   * Given a {@link Predicate}, return all directories (down from here) that fulfill the predicate.
   *
   * @param predicate the predicate
   * @return all directories (downward from here) that fulfill the predicate.
   */
  public Set<Directory> filter(Predicate<Directory> predicate) {
    return Stream.concat(
            Stream.of(this).filter(predicate),
            directories().stream()
                .map(directory -> directory.filter(predicate))
                .flatMap(Collection::stream))
        .collect(Collectors.toSet());
  }

  /**
   * Go to the child-directory of the given name.
   *
   * @param name the name of the directory to go to
   * @return the directory (might be {@code null} if the directory cannot be found)
   */
  public Directory goToChild(String name) {
    return directories.stream()
        .filter(directory -> Objects.equals(directory.name(), name))
        .findFirst()
        .orElse(null);
  }
}
