package pl.coderstrust.accounting.database.impl.memory;

import pl.coderstrust.accounting.database.Database;
import pl.coderstrust.accounting.model.Invoice;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Adam on 2018-04-16.
 */
public class InMemoryDatabase implements Database {

  private final Map<Integer, Invoice> invoices = new HashMap<>();

  @Override
  public void saveInvoice(Invoice invoice) {
    invoices.put(invoice.getId(), invoice);

  }

  @Override
  public Collection<Invoice> getInvoices() {
    return invoices.values();
  }

  @Override
  public Collection<Invoice> getInvoiceById() {
    return invoices.values();
  }

  @Override
  public void updateInvoice(Invoice invoice) {
    invoices.put(invoice.getId(), invoice);
    /* sprawdzic na testach czy dziala, wezmie nowa wartosc
    czy zostawi stara.*/
  }

  @Override
  public void removeInvoiceById(int id) {
    invoices.remove(id);

  }
}
