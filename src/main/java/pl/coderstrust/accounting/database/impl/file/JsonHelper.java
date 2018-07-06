package pl.coderstrust.accounting.database.impl.file;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import pl.coderstrust.accounting.exceptions.ApplicationException;
import pl.coderstrust.accounting.model.Invoice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class JsonHelper {

  private ObjectMapper objectMapper;
  private static final Logger logger = LoggerFactory.getLogger(JsonHelper.class);



  public JsonHelper( ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  public String convertInvoiceToJsonString(Invoice invoice) {
    try {
      return objectMapper.writeValueAsString(invoice);
    } catch (JsonProcessingException exception) {
      logger.error("There was a problem with JSON serialization of invoice with id: {}",
          invoice.getId(), exception);
      throw new ApplicationException("There was a problem with JSON serialization", exception);
    }
  }

  public List<Invoice> convertJsonStringsListToListOfInvoices(List<String> allInvoicesInJson) {
    List<Invoice> allInvoices = new ArrayList<>();
    for (String invoiceInString : allInvoicesInJson) {
      try {
        Invoice invoice = objectMapper.readValue(invoiceInString, Invoice.class);
        allInvoices.add(invoice);
      } catch (IOException exception) {
        logger.error("There was a problem with JSON deserialization of invoice list string: {}",
            invoiceInString, exception);
        throw new ApplicationException("There was a problem with JSON deserialization", exception);
      }
    }
    return allInvoices;
  }

  public Invoice convertJsonStringToInvoice(String invoiceLine) {
    try {
      return objectMapper.readValue(invoiceLine, Invoice.class);
    } catch (IOException exception) {
      logger.error("Wrong file format: {}", invoiceLine, exception);
      throw new ApplicationException("Wrong file format.", exception);
    }
  }
}
