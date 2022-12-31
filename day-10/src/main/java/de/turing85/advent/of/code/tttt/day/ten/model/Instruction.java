package de.turing85.advent.of.code.tttt.day.ten.model;

import java.util.function.BiFunction;
import lombok.Getter;

/** A (prototype) instruction. */
@Getter
public enum Instruction {
  /** ADD_X instruction adds some value (parameter) to register X. */
  ADD_X(2, Register::addToX),

  /** NO_OP instruction */
  NO_OP(1, ((register, unused) -> register));

  private final int cycles;
  private final BiFunction<Register, Integer, Register> onFinishCallback;

  Instruction(int cycles, BiFunction<Register, Integer, Register> onFinishCallback) {
    this.cycles = cycles;
    this.onFinishCallback = onFinishCallback;
  }
}
