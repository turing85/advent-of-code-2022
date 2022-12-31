package de.turing85.advent.of.code.tttt.day.ten.model;

import java.util.List;
import java.util.function.BiConsumer;

/** A CPU.. */
public class Cpu {
  private final List<InstructionWithParameter> instructions;
  private Register register;
  private int cycle;
  private int instructionCounter;
  private int cyclesLeftOnCurrentInstruction;

  /**
   * Loads instructions in a freshly set CPU.
   *
   * @param instructions to load
   */
  public Cpu(List<InstructionWithParameter> instructions) {
    this.instructions = instructions;
    this.register = new Register();
    this.cycle = 1;
    this.instructionCounter = 0;
    this.cyclesLeftOnCurrentInstruction = currentInstructionWithParameter().instruction().cycles();
  }

  private InstructionWithParameter currentInstructionWithParameter() {
    return this.instructions.get(instructionCounter);
  }

  /**
   * Executes one clock cycle.
   *
   * @param duringCycleXRegistryObserver an observer that is called during the cycle with the
   *     current cycle and the current value of register {@code X}.
   * @return {@code true} if and only if there are still instructions to execute after this tick.
   */
  public boolean tick(BiConsumer<Integer, Integer> duringCycleXRegistryObserver) {
    --cyclesLeftOnCurrentInstruction;
    duringCycleXRegistryObserver.accept(cycle, register.x());
    cycle++;
    if (cyclesLeftOnCurrentInstruction == 0) {
      register = currentInstructionWithParameter().apply(register);
      ++instructionCounter;
      if (instructionCounter >= instructions.size()) {
        return false;
      }
      cyclesLeftOnCurrentInstruction = currentInstructionWithParameter().instruction().cycles();
    }
    return true;
  }
}
