package de.turing85.advent.of.code.tttt.day.one.supplier.impl;

import de.turing85.advent.of.code.tttt.day.one.supplier.ExpeditionSupplier;
import de.turing85.advent.of.code.tttt.day.one.supplier.ExpeditionSupplierTest;
import org.junit.jupiter.api.DisplayName;

@DisplayName("FromStringExpeditionSupplier tests")
class FromStringExpeditionSupplierTest extends ExpeditionSupplierTest {

  @Override
  protected ExpeditionSupplier expeditionSupplierForIllegalInput() {
    return new FromStringExpeditionSupplier(ILLEGAL_INPUT);
  }

  @Override
  protected ExpeditionSupplier expeditionSupplierForCommonInput() {
    return new FromStringExpeditionSupplier(COMMON_INPUT);
  }

  @Override
  protected ExpeditionSupplier expeditionSupplierForPersonalInput() {
    return new FromStringExpeditionSupplier(PERSONAL_INPUT);
  }
}
