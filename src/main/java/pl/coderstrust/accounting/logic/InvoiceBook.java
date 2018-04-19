package pl.coderstrust.accounting.logic;

import pl.coderstrust.accounting.database.Database;
import pl.coderstrust.accounting.model.Invoice;

import java.util.ArrayList;
import java.util.Arrays;
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
    if (getInvoices() != null) {
      database.getInvoices();
    }
    return Arrays.asList();
  }

  public Invoice getInvoiceById() {
    if (getInvoiceById() != null) {
      database.removeInvoiceById(null);
    }
    return null;
  }

  public void updateInvoice(Invoice invoice) {
    if (invoice.getId() != null) {
      database.updateInvoice(invoice);
    }
  }

  public void removeInvoiceById(Long id) {
    if (getInvoiceById().removeInvoiceById() != null) {
      database.removeInvoiceById(id);
    }
  }
}
