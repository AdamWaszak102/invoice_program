package pl.coderstrust.accounting;

import pl.coderstrust.accounting.database.Database;
import pl.coderstrust.accounting.database.impl.memory.InMemoryDatabase;
import pl.coderstrust.accounting.logic.InvoiceBook;
import pl.coderstrust.accounting.model.Invoice;
import pl.coderstrust.accounting.model.InvoiceProvider;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Adam on 2018-04-16.
 */
public class Main {

  public static void main(String[] args) {
    InvoiceBook invoiceBook = new InvoiceBook(new InMemoryDatabase());
    Database db = new InMemoryDatabase();
    Invoice obj00 = new InvoiceProvider().InvoiceOne();
    Invoice obj01 = new InvoiceProvider().InvoiceTwo();
    Invoice obj02 = new InvoiceProvider().InvoiceThree();
    List<Invoice> invoicesExamples = Arrays.asList(obj00,obj01,obj02);
    for (int j = 0; j < invoicesExamples.size(); j++) {
      db.saveInvoice(invoicesExamples.get(j));}
    for(Invoice invoice: invoicesExamples ){
      db.saveInvoice(invoice);}
    db.saveInvoice(new Invoice(null,"qwerty", LocalDate.of(2018,4,4),null,null,null));
    System.out.println(db.getInvoiceById(1L));
  }
}
