package pl.coderstrust.accounting.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by Adam on 2018-04-16.
 */
public class Invoice {

  private int id;
  private String identifier;

  private LocalDate issueDate;

  private Company buyer;
  private Company seller;

  private List<InvoiceEntry> entries;


  public Integer getId() {
    return id;
  }

  public BigDecimal getNetValue() {
    for (InvoiceEntry entry : entries) {

    }
    return BigDecimal.valueOf(4);
  }
  //"podzo" classa bez logiki
}
