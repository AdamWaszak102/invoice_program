package pl.coderstrust.accounting.model;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Created by Adam on 2018-04-17.
 */
public class InvoiceEntry {

  private String description;
  private BigDecimal price;
  private Vat vat;

  public InvoiceEntry(String description, BigDecimal price, Vat vat) {
    this.description = description;
    this.price = price;
    this.vat = vat;
  }

  // needed for json serialization using jackson
  public InvoiceEntry() {
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public Vat getVat() {
    return vat;
  }

  public void setVat(Vat vat) {
    this.vat = vat;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof InvoiceEntry)) {
      return false;
    }
    InvoiceEntry that = (InvoiceEntry) o;
    return Objects.equals(description, that.description) &&
        Objects.equals(price, that.price) &&
        vat == that.vat;
  }

  @Override
  public int hashCode() {
    return Objects.hash(description, price, vat);
  }
}
