package de.turing85.advent.of.code.tttt.day.three.supplier.impl;

import de.turing85.advent.of.code.tttt.day.three.supplier.RucksacksSupplier;
import de.turing85.advent.of.code.tttt.day.three.supplier.RucksacksSupplierTest;
import java.io.IOException;
import org.junit.jupiter.api.DisplayName;

@DisplayName("FromFileRucksackSupplier tests")
class FromFileRucksackSupplierTest extends RucksacksSupplierTest {

  @Override
  protected RucksacksSupplier rucksacksSupplierForIllegalInputOne() throws IOException {
    return new FromFileRucksackSupplier(ILLEGAL_INPUT_ONE_URI);
  }

  @Override
  protected RucksacksSupplier rucksacksSupplierForIllegalInputTwo() throws IOException {
    return new FromFileRucksackSupplier(ILLEGAL_INPUT_TWO_URI);
  }

  @Override
  protected RucksacksSupplier rucksacksSupplierForIllegalInputThree() throws IOException {
    return new FromFileRucksackSupplier(ILLEGAL_INPUT_THREE_URI);
  }

  @Override
  protected RucksacksSupplier rucksacksSupplierForCommonInput() throws IOException {
    return new FromFileRucksackSupplier(COMMON_INPUT_URI);
  }

  @Override
  protected RucksacksSupplier rucksacksSupplierForPersonalInput() throws IOException {
    return new FromFileRucksackSupplier(PERSONAL_INPUT_URI);
  }
}
