package pl.coderstrust.accounting.logic;

import pl.coderstrust.accounting.database.Database;
import pl.coderstrust.accounting.model.Invoice;

import java.util.Collection;

/**
 * Created by Adam on 2018-04-16.
 */
public class InvoiceBook {

  private Database database;

  /*ciezko przetestowac wiec lepiej wygenerowac konstruktor, jest ponizej.podpowiedz tak jak
   w numbersFromFile.*/
  public InvoiceBook(Database database) {
    this.database = database;
  }

  public void saveInvoice(Invoice invoice) {
    if (invoice.getId() != null) {
      database.saveInvoice(invoice);
    }
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
}
