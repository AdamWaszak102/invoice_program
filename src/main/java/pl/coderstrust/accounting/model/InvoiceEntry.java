package pl.coderstrust.accounting.model;

import java.math.BigDecimal;

/**
 * Created by Adam on 2018-04-17.
 */
public class InvoiceEntry {

  private String description;

  private BigDecimal price = BigDecimal.valueOf(4);

  private Vat vat;

  public BigDecimal getGross() {
    return price.multiply(BigDecimal.valueOf(vat.getPrice()));
  }
}
