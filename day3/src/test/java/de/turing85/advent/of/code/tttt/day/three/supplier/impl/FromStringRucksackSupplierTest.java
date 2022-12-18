package de.turing85.advent.of.code.tttt.day.three.supplier.impl;


import de.turing85.advent.of.code.tttt.day.three.supplier.RucksacksSupplier;
import de.turing85.advent.of.code.tttt.day.three.supplier.RucksacksSupplierTest;
import org.junit.jupiter.api.DisplayName;

@DisplayName("FromStringRucksackSupplier tests")
class FromStringRucksackSupplierTest extends RucksacksSupplierTest {

  @Override
  protected RucksacksSupplier rucksacksSupplierForIllegalInputOne() {
    return new FromStringRucksackSupplier(ILLEGAL_INPUT_ONE);
  }

  @Override
  protected RucksacksSupplier rucksacksSupplierForIllegalInputTwo() {
    return new FromStringRucksackSupplier(ILLEGAL_INPUT_TWO);
  }

  @Override
  protected RucksacksSupplier rucksacksSupplierForIllegalInputThree() {
    return new FromStringRucksackSupplier(ILLEGAL_INPUT_THREE);
  }

  @Override
  protected RucksacksSupplier rucksacksSupplierForCommonInput() {
    return new FromStringRucksackSupplier(COMMON_INPUT);
  }

  @Override
  protected RucksacksSupplier rucksacksSupplierForPersonalInput() {
    return new FromStringRucksackSupplier(PERSONAL_INPUT);
  }
}
