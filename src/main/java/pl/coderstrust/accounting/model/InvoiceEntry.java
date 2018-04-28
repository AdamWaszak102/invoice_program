package pl.coderstrust.accounting.model;

import java.math.BigDecimal;

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
  public InvoiceEntry(){
    super();
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
}
