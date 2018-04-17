package pl.coderstrust.accounting.model;

/**
 * Created by Adam on 2018-04-17.
 */
public enum Vat {
  VAT_ZW(0),
  VAT_Jakis(7),
  VAT_23(23);

  private int price;

  private Vat(int price) {
    this.price = price;
  }

  public int getPrice() {
    return price;
  }
}
