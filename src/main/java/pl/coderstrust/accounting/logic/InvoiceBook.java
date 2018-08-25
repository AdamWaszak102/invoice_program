package pl.coderstrust.accounting.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderstrust.accounting.database.Database;
import pl.coderstrust.accounting.email.MailService;
import pl.coderstrust.accounting.model.Invoice;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvoiceBook {

  @Autowired
  private MailService mailService;

  private Database database;

  private void sendEmail(Long id) {
    mailService.sendEmail(id);
  }

  public List<Invoice> getInvoicesByIssueDateRange(LocalDate dateFrom, LocalDate dateTo) {
    return database.getInvoices().stream()
        .filter(invoice -> invoice.getIssueDate().isAfter(dateFrom))
        .filter(invoice -> invoice.getIssueDate().isBefore(dateTo))
        .collect(Collectors.toList());
  }

  public InvoiceBook(Database database) {
    this.database = database;
  }

  public Long saveInvoice(Invoice invoice) {
    Long id = database.saveInvoice(invoice);
    sendEmail(id);
    return id;
  }

  public Collection<Invoice> getInvoices() {
    return database.getInvoices();
  }

  public Invoice getInvoiceById(Long id) {
    return database.getInvoiceById(id);
  }

  public void updateInvoice(Invoice invoice) {
    if (invoice.getId() != null) {
      database.updateInvoice(invoice);
    }
  }

  public void removeInvoiceById(Long id) {
    database.removeInvoiceById(id);
  }

  public List<Long> saveInvoices(List<Invoice> invoices) {
    List<Long> ids = database.saveInvoices(invoices);
    for (Long id : ids) {
      sendEmail(id);
    }
    return ids;
  }
}