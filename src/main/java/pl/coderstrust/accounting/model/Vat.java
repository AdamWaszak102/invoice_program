package pl.coderstrust.accounting.model;

import java.math.BigDecimal;

/**
 * Created by Adam on 2018-04-17.
 */
public enum Vat {
  VAT_0(BigDecimal.valueOf(0)),
  VAT_5(BigDecimal.valueOf(5)),
  VAT_8(BigDecimal.valueOf(8)),
  VAT_23(BigDecimal.valueOf(23));

  private BigDecimal vatValue;

  private Vat(BigDecimal vatValue) {
    this.vatValue = vatValue;
  }

  BigDecimal forCheckValue(int vatValue) { //only for tests
    return BigDecimal.valueOf(vatValue);
  }



}