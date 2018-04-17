package pl.coderstrust.accounting.model;

/**
 * Created by Adam on 2018-04-17.
 */
public enum PaymentStatus {
  PAYMENT_STATUS_notPaid(0),
  PAYMENT_STATUS_paid(5),
  PAYMENT_STATUS_inProgress(8);

  private double paymentStatusValue;

  PaymentStatus(double paymentStatusValue) {
    this.paymentStatusValue = paymentStatusValue;

  }
}
