package pl.coderstrust.accounting.database.impl.file;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import pl.coderstrust.accounting.model.Invoice;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JsonHelper {

  private ObjectMapper writingMapper = new ObjectMapper().registerModule(new JavaTimeModule())
      .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
  private ObjectMapper readingMapper = new ObjectMapper().registerModule(new JavaTimeModule())
      .configure(DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS, true);
  private File idNumber = new File("idNumber.txt");
  private Long id;

  public String convertInvoiceToJsonString(Invoice invoice) {
    id = checkId();
    ++id;
    saveNewId(id);
    try {
      invoice.setId(id);
      return writingMapper.writeValueAsString(invoice);
    } catch (JsonProcessingException exception) {
      exception.printStackTrace();
    }
    return "";
  }

  public List<Invoice> returnAllInvoices(List<String> allInvoicesInJson) {
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

  public Long checkId() {
    try (Scanner scanner = new Scanner(idNumber)) {
      while (scanner.hasNextLong()) {
        id = scanner.nextLong();
      }
    } catch (FileNotFoundException exception) {
      exception.printStackTrace();
    }
    return id;
  }

  public void saveNewId(Long id) {
    try (FileWriter fileWriter = new FileWriter(idNumber)) {
      fileWriter.write(id.toString());
    } catch (IOException exception) {
      exception.printStackTrace();
    }
  }
}
