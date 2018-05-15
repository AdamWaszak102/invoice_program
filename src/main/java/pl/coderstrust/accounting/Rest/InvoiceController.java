package pl.coderstrust.accounting.Rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coderstrust.accounting.database.impl.memory.InMemoryDatabase;
import pl.coderstrust.accounting.logic.InvoiceBook;
import pl.coderstrust.accounting.model.Invoice;

import java.util.Collection;

/**
 * Created by Adam on 2018-05-08.
 */
@RestController
@RequestMapping("/invoices")
public class InvoiceController {

  private InvoiceBook invoiceBook = new InvoiceBook(new InMemoryDatabase());

//  public InvoiceController(InvoiceBook invoiceBook) {
//    this.invoiceBook = invoiceBook;
//  }

  @GetMapping
  public Collection<Invoice> getInvoices() {
    return invoiceBook.getInvoices();
  }

  @PostMapping
  public Long saveInvoice(@RequestBody Invoice invoice) {
    return invoiceBook.saveInvoice(invoice);
  }
//
//  @DeleteMapping
//  public void removeInvoiceById(Long id) {
//  invoiceBook.removeInvoiceById(id);
//  }

  @PutMapping
  public void updateInvoice(@RequestBody Invoice invoice) {
    invoiceBook.updateInvoice(invoice);
  }
}
