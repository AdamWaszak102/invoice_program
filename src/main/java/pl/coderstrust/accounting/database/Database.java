package pl.coderstrust.accounting.database;

import pl.coderstrust.accounting.model.Invoice;

import java.util.Collection;

/**
 * Created by Adam on 2018-04-16.
 */
public interface Database {

  void saveInvoice(Invoice invoice);

  Collection<Invoice> getInvoice();

  void updateInvoice(Invoice invoice);

  void removeInvoice(int id);

}
