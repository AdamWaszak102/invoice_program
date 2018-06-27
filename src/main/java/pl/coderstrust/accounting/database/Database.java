package pl.coderstrust.accounting.database;

import pl.coderstrust.accounting.model.Invoice;

import java.util.Collection;
import java.util.List;

/**
 * Created by Adam on 2018-04-16.
 */
public interface Database {

  Long saveInvoice(Invoice invoice);

  List<Long> saveInvoices(List<Invoice> invoices);

  Collection<Invoice> getInvoices();

  Invoice getInvoiceById(Long id);

  void updateInvoiceById(Invoice invoice, Long id);

  void removeInvoiceById(Long id);
}

