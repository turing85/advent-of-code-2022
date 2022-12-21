package de.turing85.advent.of.code.tttt.day.seven.model;

import java.util.List;

/**
 * Reconstructs a directory tree from a list of commands and responses.
 */
public class CommandsAndResponsesParser {
  private CommandsAndResponsesParser() {}

  /**
   * Reconstructs a file tree from a list of commands and responses.
   *
   * @param commandsAndResponses the commands and responses to construct the directory tree from
   *
   * @return the reconstructed directory tree.
   */
  public static Directory parseCommandsAndResponses(
      List<Pair<String, List<String>>> commandsAndResponses) {
    Directory rootDirectory = new Directory();
    Directory currentDirectory = rootDirectory;
    for (Pair<String, List<String>> commandAndResponses : commandsAndResponses) {
      String command = commandAndResponses.first();
      List<String> responses = commandAndResponses.second();
      if (command.startsWith("cd")) {
        currentDirectory = handleCdCommand(rootDirectory, currentDirectory, command);
      }
      if (command.startsWith("ls")) {
        handleLsCommand(currentDirectory, responses);
      }
    }
    return rootDirectory;
  }

  private static Directory handleCdCommand(Directory rootDirectory, Directory currentDirectory,
      String command) {
    String argument = command.split("\\s+")[1];
    return switch (argument) {
      case ".." -> currentDirectory.parent();
      case "/" -> rootDirectory;
      default -> currentDirectory.goToChild(argument);
    };
  }

  private static void handleLsCommand(Directory currentDirectory, List<String> responses) {
    for (String response : responses) {
      String[] responseSplit = response.split("\\s+");
      String name = responseSplit[1];
      if (response.startsWith("dir")) {
        currentDirectory.addDirectory(name);
      } else {
        int size = Integer.parseInt(responseSplit[0]);
        currentDirectory.addFile(name, size);
      }
    }
  }
}
