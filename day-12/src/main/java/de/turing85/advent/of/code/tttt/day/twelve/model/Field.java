package de.turing85.advent.of.code.tttt.day.twelve.model;

/**
 * A Field.
 *
 * @param symbol symbol of the field
 * @param point the x/y-coordinate of the field
 * @param height the field's height
 */
public record Field (char symbol, Point point, int height){
}
