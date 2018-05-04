package pl.coderstrust.accounting.model;

import java.math.BigDecimal;

public enum Vat {
  VAT_0(BigDecimal.valueOf(0)),
  VAT_5(BigDecimal.valueOf(5)),
  VAT_8(BigDecimal.valueOf(8)),
  VAT_23(BigDecimal.valueOf(23));

  private BigDecimal vatValue;

  Vat(BigDecimal vatValue) {
    this.vatValue = vatValue;
  }

  public BigDecimal getVatValue() {
    return vatValue;
  }
}