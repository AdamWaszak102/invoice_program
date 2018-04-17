package pl.coderstrust.accounting.model;

/**
 * Created by Adam on 2018-04-17.
 */
public enum Vat {
  VAT_0(0),
  VAT_5(5),
  VAT_8(8),
  VAT_23(23);

  private double vatValue;


  Vat(double vatValue) {
    this.vatValue = vatValue;
  }

  public double getPrice() {
    return vatValue;
  }
}
