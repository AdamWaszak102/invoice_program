package pl.coderstrust.accounting.database.impl.file;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import pl.coderstrust.accounting.model.Invoice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonHelper {

  private ObjectMapper writingMapper = new ObjectMapper().registerModule(new JavaTimeModule())
      .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
  private ObjectMapper readingMapper = new ObjectMapper().registerModule(new JavaTimeModule())
      .configure(DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS, true);

  public String convertInvoiceToJsonString(Invoice invoice) {
    try {
      return writingMapper.writeValueAsString(invoice);
    } catch (JsonProcessingException ex) {
      ex.printStackTrace();
    }
    return "";
  }

  public List<Invoice> convertInvoicesToJsonStringsList(List<String> allInvoicesInJson) {
    List<Invoice> allInvoices = new ArrayList<>();
    for (String invoiceInString : allInvoicesInJson) {
      try {
        Invoice invoice = readingMapper.readValue(invoiceInString, Invoice.class);
        allInvoices.add(invoice);
      } catch (IOException ex) {
        ex.printStackTrace();
      }
    }
    return allInvoices;
  }

  public Invoice returnInvoiceById(String invoiceLine) {
    try {
      return readingMapper.readValue(invoiceLine, Invoice.class);
    } catch (IOException ex) {
      ex.printStackTrace();
    }
    return null;
  }
}
