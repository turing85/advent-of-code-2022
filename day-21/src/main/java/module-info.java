/** the module to Advent of Codes's 2022 day 21 challenge */
module de.turing85.advent.of.code.day.twentyone {
  requires static lombok;

  exports de.turing85.advent.of.code.day.twentyone.model;
  exports de.turing85.advent.of.code.day.twentyone.model.parser;

  uses de.turing85.advent.of.code.day.twentyone.model.parser.MonkeyParser;

  provides de.turing85.advent.of.code.day.twentyone.model.parser.MonkeyParser with
      de.turing85.advent.of.code.day.twentyone.model.parser.impl.BaseMonkeyParser,
      de.turing85.advent.of.code.day.twentyone.model.parser.impl.ExpressionMonkeyParser;
}
