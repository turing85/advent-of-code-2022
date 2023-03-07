import de.turing85.advent.of.code.tttt.day.twentyone.model.parser.MonkeyParser;
import de.turing85.advent.of.code.tttt.day.twentyone.model.parser.impl.BaseMonkeyParser;
import de.turing85.advent.of.code.tttt.day.twentyone.model.parser.impl.ExpressionMonkeyParser;

/** the module to Advent of Codes's 2022 day 21 challenge */
module de.turing85.advent.of.code.day.twentyone {
  requires static lombok;

  exports de.turing85.advent.of.code.tttt.day.twentyone.model;
  exports de.turing85.advent.of.code.tttt.day.twentyone.model.parser;

  uses MonkeyParser;

  provides MonkeyParser with
      BaseMonkeyParser,
      ExpressionMonkeyParser;
}
