package de.turing85.advent.of.code.tttt.day.one.supplier.impl;

import de.turing85.advent.of.code.tttt.day.one.supplier.ExpeditionSupplier;
import de.turing85.advent.of.code.tttt.day.one.supplier.ExpeditionSupplierTest;
import java.io.IOException;
import org.junit.jupiter.api.DisplayName;

@DisplayName("FromFileExpeditionSupplier tests")
class FromFileExpeditionSupplierTest extends ExpeditionSupplierTest {
  @Override
  protected ExpeditionSupplier expeditionSupplierForIllegalInput() throws IOException {
    return new FromFileExpeditionSupplier(ILLEGAL_INPUT_URI);
  }

  @Override
  protected ExpeditionSupplier expeditionSupplierForCommonInput() throws IOException {
    return new FromFileExpeditionSupplier(COMMON_INPUT_URI);
  }

  @Override
  protected ExpeditionSupplier expeditionSupplierForPersonalInput() throws IOException {
    return new FromFileExpeditionSupplier(PERSONAL_INPUT_URI);
  }
}
