package pl.coderstrust.accounting;

import pl.coderstrust.accounting.database.Database;
import pl.coderstrust.accounting.database.impl.memory.InMemoryDatabase;
import pl.coderstrust.accounting.logic.InvoiceBook;

/**
 * Created by Adam on 2018-04-16.
 */
public class Main {

  public static void main(String[] args) {
    InvoiceBook invoiceBook = new InvoiceBook(new InMemoryDatabase());
    Database db = new InMemoryDatabase();
//    db.saveInvoice(new Invoice(2L,"qwerty", LocalDate.of(2018,4,4),"Budimex","Arka",null));
  }
}
