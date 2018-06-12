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
  public Long saveInvoice(@RequestBody Invoice invoice) {
    return invoiceBook.saveInvoice(invoice);
  }

  @DeleteMapping("/{id}")
  public void removeInvoiceById(@PathVariable("id") Long id) {
    invoiceBook.removeInvoiceById(id);
    logger.info("Invoice deleted", id);
  }

  @PutMapping
  public void updateInvoice(@RequestBody Invoice invoice) {
    invoiceBook.updateInvoice(invoice);
  }

  @PostMapping("/add_invoices")
  public List<Long> saveInvoices(@RequestBody List<Invoice> invoices) {
    return invoiceBook.saveInvoices(invoices);
  }
}
