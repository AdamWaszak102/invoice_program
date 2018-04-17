package pl.coderstrust.accounting;

import pl.coderstrust.accounting.database.impl.memory.InMemoryDatabase;
import pl.coderstrust.accounting.logic.InvoiceBook;

/**
 * Created by Adam on 2018-04-16.
 */
public class Main {

  public static void main(String[] args) {
    InvoiceBook invoiceBook = new InvoiceBook(new InMemoryDatabase());
  }
}
