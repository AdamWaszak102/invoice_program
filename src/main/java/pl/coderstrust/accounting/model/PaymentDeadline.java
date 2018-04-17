package pl.coderstrust.accounting.model;

/**
 * Created by Adam on 2018-04-17.
 */
public enum PaymentDeadline {
  PAYMENT_DEADLINE_3Days(3),
  PAYMENT_DEADLINE_7Days(7),
  PAYMENT_DEADLINE_21Days(21);


  private double paymentDedlineValue;

  PaymentDeadline(double paymentDedlineValue) {
    this.paymentDedlineValue = paymentDedlineValue;

  }
}
