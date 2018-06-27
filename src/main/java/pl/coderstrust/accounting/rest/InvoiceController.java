package pl.coderstrust.accounting.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coderstrust.accounting.logic.InvoiceBook;
import pl.coderstrust.accounting.model.Invoice;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {

  private static final Logger logger = LoggerFactory.getLogger(InvoiceController.class);

  private InvoiceBook invoiceBook;

  public InvoiceController(InvoiceBook invoiceBook) {
    this.invoiceBook = invoiceBook;
  }

  @GetMapping
  public ResponseEntity<Collection<Invoice>> getInvoices() {
    Collection<Invoice> invoicesToReturn = invoiceBook.getInvoices();

    if (invoicesToReturn == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(invoicesToReturn);
  }

  @PostMapping("/add_invoice")
  public ResponseEntity<Long> saveInvoice(@RequestBody Invoice invoice) {
    Long id = invoiceBook.saveInvoice(invoice);

    if (id == 0) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(id);
  }

  @DeleteMapping("/{id}")
  public void removeInvoiceById(@PathVariable("id") Long id) {
    invoiceBook.removeInvoiceById(id);
    logger.info("Invoice deleted, invoice id:{}", id);
  }

  @PutMapping
  public ResponseEntity<Long> updateInvoice(@RequestBody Invoice invoice) {
    Long id = invoiceBook.updateInvoice(invoice);
    if (id == 0) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(id);
  }

  @PostMapping("/add_invoices")
  public ResponseEntity<List<Long>> saveInvoices(@RequestBody List<Invoice> invoices) {
    List<Long> savedInvoices = invoiceBook.saveInvoices(invoices);
    if (savedInvoices == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok().body(savedInvoices);
  }
}
