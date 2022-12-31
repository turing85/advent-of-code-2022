package de.turing85.advent.of.code.tttt.day.ten.model;

import java.util.function.UnaryOperator;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * An instance of an {@link Instruction}, with a corresponding parameter.
 *
 * <p>For {@link Instruction}s that do not have a parameter (e.g. {@link Instruction#NO_OP}), the
 * parameter should be set to {@code null}.
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class InstructionWithParameter implements UnaryOperator<Register> {
  private final Instruction instruction;
  private final Integer parameter;

  /**
   * Factory method for {@link Instruction#NO_OP}.
   *
   * @return {@link Instruction#NO_OP} with {@code null} as parameter (since it is unused)
   */
  public static InstructionWithParameter noOp() {
    return new InstructionWithParameter(Instruction.NO_OP, null);
  }

  /**
   * Factory method for a {@link Instruction#ADD_X} instruction with {@code deltaX} parameter
   *
   * @param deltaX value
   * @return a {@link Instruction#ADD_X} with {@code deltaX} parameter
   */
  public static InstructionWithParameter addX(int deltaX) {
    return new InstructionWithParameter(Instruction.ADD_X, deltaX);
  }

  @Override
  public Register apply(Register register) {
    return instruction().onFinishCallback().apply(register, parameter());
  }
}
