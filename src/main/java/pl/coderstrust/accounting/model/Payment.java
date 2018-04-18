package pl.coderstrust.accounting.model;

import java.math.BigDecimal;

/**
 * Created by Adam on 2018-04-17.
 */
public enum Payment {
  PAYMENT_cash(BigDecimal.valueOf(10)),
  PAYMENT_creditCard(BigDecimal.valueOf(120)),
  PAYMENT_onlnePayment(BigDecimal.valueOf(444)),
  PAYMENT_blik(BigDecimal.valueOf(4));

  private BigDecimal paymentValue;

  Payment(BigDecimal paymentValue) {
    this.paymentValue = paymentValue;
  }
}
