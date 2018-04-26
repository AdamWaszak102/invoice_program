package pl.coderstrust.accounting.database;

import pl.coderstrust.accounting.model.Invoice;

import java.util.Collection;
import java.util.List;

/**
 * Created by Adam on 2018-04-16.
 */
public interface Database {

  void saveInvoice(Invoice invoice);

  void saveListOfInvoices (List<Invoice> invoicesListName);

  Collection<Invoice> getInvoices();

  Invoice getInvoiceById(Long id);

  void updateInvoice(Invoice invoice);

  void removeInvoiceById(Long id);
}
