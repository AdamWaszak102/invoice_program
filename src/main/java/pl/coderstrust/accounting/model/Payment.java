package pl.coderstrust.accounting.model;

/**
 * Created by Adam on 2018-04-17.
 */
public enum Payment {
  PAYMENT_cash(120),
  PAYMENT_creditCard(5),
  PAYMENT_onlnePayment(8),
  PAYMENT_blik(23);

  private double paymentValue;

  Payment(double paymentValue) {
    this.paymentValue = paymentValue;

  }
}
