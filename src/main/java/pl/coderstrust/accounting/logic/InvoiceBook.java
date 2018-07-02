package pl.coderstrust.accounting.logic;

import org.springframework.stereotype.Service;
import pl.coderstrust.accounting.database.Database;
import pl.coderstrust.accounting.model.Invoice;

import java.util.Collection;
import java.util.List;

@Service
public class InvoiceBook {

  private Database database;

  public InvoiceBook(Database database) {
    this.database = database;
  }

  public Long saveInvoice(Invoice invoice) {
    return database.saveInvoice(invoice);
  }

  public Collection<Invoice> getInvoices() {
    return database.getInvoices();
  }

  public Invoice getInvoiceById(Long id) {
    return database.getInvoiceById(id);
  }

  public Long updateInvoice(Invoice invoice) {
    if (invoice.getId() != null) {
      database.updateInvoice(invoice);
    }
    return null;
  }

  public void removeInvoiceById(Long id) {
    database.removeInvoiceById(id);
  }

  public List<Long> saveInvoices(List<Invoice> invoices) {
    return database.saveInvoices(invoices);
  }
}