package pl.coderstrust.accounting.model;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Adam on 2018-04-16.
 */
public class Invoice {

  private Long id;
  private String identifier;
  private LocalDate issueDate;
  private Company buyer;
  private Company seller;
  private List<InvoiceEntry> entries;

  public Invoice(Long id, String identifier, LocalDate issueDate,
      Company buyer, Company seller,
      List<InvoiceEntry> entries) {
    this.id = id;
    this.identifier = identifier;
    this.issueDate = issueDate;
    this.buyer = buyer;
    this.seller = seller;
    this.entries = entries;
  }

  public String getIdentifier() {
    return identifier;
  }

  public LocalDate getIssueDate() {
    return issueDate;
  }

  public Company getBuyer() {
    return buyer;
  }

  public Company getSeller() {
    return seller;
  }

  public List<InvoiceEntry> getEntries() {
    return entries;
  }

  public Long getId() {
    return id;
  }
}
