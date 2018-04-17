package pl.coderstrust.accounting.logic;

import pl.coderstrust.accounting.database.Database;
import pl.coderstrust.accounting.model.Invoice;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by Adam on 2018-04-16.
 */
public class InvoiceBook {

  private Database database;
  //ciezko przetestowacwiec lepiej wygenerowac konstruktor, jest ponizej.podpowiedz tak jak w numbersFromFile.

  public InvoiceBook(Database database) {
    this.database = database;
  }

  // w przyszlosci invoiceService ---> kiedy bedzie spring
//najwazniejsza klasa na przyszlosc
  public void saveInvoice(Invoice invoice) {
    if (invoice.getId() != null) {
      database.saveInvoice(invoice);
      //uzywamy interfejsu by clasa invoiceBook mogla dzialac z kazda baza.
      //tu wolamy save
    }
  }

  public Collection<Invoice> getInvoice() {
    return Arrays.asList();
    //tu wolamy get
  }

  public void updateInvoice(Invoice invoice) {
// tu wolamy update
  }

  public void removeInvoice(int id) {
// tu wolamy remove
  }

}
