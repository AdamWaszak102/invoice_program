package pl.coderstrust.accounting.database.impl.file;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import pl.coderstrust.accounting.model.Invoice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class JsonHelper {

  private ObjectMapper writingMapper;
  private ObjectMapper readingMapper;

  public JsonHelper(@Qualifier("Ala") ObjectMapper writingMapper,
      @Qualifier("Kot")ObjectMapper readingMapper) {
    this.writingMapper = writingMapper;
    this.readingMapper = readingMapper;
  }

  public String convertInvoiceToJsonString(Invoice invoice) {
    try {
      return writingMapper.writeValueAsString(invoice);
    } catch (JsonProcessingException exception) {
      exception.printStackTrace();
    }
    return "";
  }

  public List<Invoice> convertJsonStringsListToListOfInvoices(List<String> allInvoicesInJson) {
    List<Invoice> allInvoices = new ArrayList<>();
    for (String invoiceInString : allInvoicesInJson) {
      try {
        Invoice invoice = readingMapper.readValue(invoiceInString, Invoice.class);
        allInvoices.add(invoice);
      } catch (IOException exception) {
        exception.printStackTrace();
      }
    }
    return allInvoices;
  }

  public Invoice returnInvoiceById(String invoiceLine) {
    try {
      return readingMapper.readValue(invoiceLine, Invoice.class);
    } catch (IOException exception) {
      exception.printStackTrace();
    }
    return null;
  }
}
