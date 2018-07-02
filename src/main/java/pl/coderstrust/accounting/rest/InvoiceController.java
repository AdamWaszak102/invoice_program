package pl.coderstrust.accounting.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
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

@Api(value = "/invoices", description = "Operations on invoices")
@RestController
@RequestMapping("/invoices")
public class InvoiceController {

  private static final Logger logger = LoggerFactory.getLogger(InvoiceController.class);

  private InvoiceBook invoiceBook;

  public InvoiceController(InvoiceBook invoiceBook) {
    this.invoiceBook = invoiceBook;
  }

  @ApiOperation(value = "Gets all Invoices",
      notes = "Gets all invoices that were saved",
      response = Invoice.class,
      responseContainer = "List")
  @GetMapping
  public ResponseEntity<Collection<Invoice>> getInvoices() {
    Collection<Invoice> invoicesToReturn = invoiceBook.getInvoices();

    if (invoicesToReturn == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(invoicesToReturn);
  }

  @ApiOperation(value = "Posts one Invoice",
      notes = "One invoice is added to the list and is provided with a new id number value.")
  @PostMapping("/add_invoice")
  public ResponseEntity<Long> saveInvoice(@RequestBody Invoice invoice) {
    Long id = invoiceBook.saveInvoice(invoice);

    if (id == 0) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(id);
  }

  @ApiOperation(value = "Deletes one Invoice by id",
      notes = "One invoice is deleted from the list by its id.")
  @DeleteMapping("/{id}")
  public ResponseEntity removeInvoiceById(
      @ApiParam(value = "id number of the invoice to be deleted",
          required = true) @PathVariable("id") Long id) {
    if (invoiceBook.getInvoiceById(id) == null) {
      return ResponseEntity.notFound().build();
    }
    invoiceBook.removeInvoiceById(id);
    logger.info("Invoice deleted, invoice id:{}", id);
    return ResponseEntity.ok().build();
  }

  @ApiOperation(value = "Updates one invoice",
      notes = "Information contained in one invoice is updated"
          + " using its id and information provided")
  @PutMapping
  public ResponseEntity<Long> updateInvoice(@RequestBody Invoice invoice) {
    Long id = invoiceBook.updateInvoice(invoice);
    if (id == 0) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(id);
  }

  @ApiOperation(value = "Posts a list of invoices",
      notes = "Adds a list of invoices, each one provided with unique id number value.")
  @PostMapping("/add_invoices")
  public ResponseEntity<List<Long>> saveInvoices(@RequestBody List<Invoice> invoices) {
    List<Long> savedInvoices = invoiceBook.saveInvoices(invoices);
    if (savedInvoices == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok().body(savedInvoices);
  }

  @ApiOperation(value = "Gets one invoice by id",
      notes = "Gets one invoice by its id value.",
      response = Invoice.class,
      responseContainer = "String")
  @GetMapping("/{id}")
  public ResponseEntity<Invoice> getInvoiceById(
      @ApiParam(value = "id number of the invoice to be read",
          required = true) @PathVariable Long id) {
    Invoice invoiceToReturn = invoiceBook.getInvoiceById(id);
    if (invoiceToReturn == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(invoiceToReturn);
  }
}
