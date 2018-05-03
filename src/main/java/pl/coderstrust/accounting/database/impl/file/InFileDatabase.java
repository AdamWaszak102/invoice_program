package pl.coderstrust.accounting.database.impl.file;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import pl.coderstrust.accounting.database.Database;
import pl.coderstrust.accounting.model.Invoice;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class InFileDatabase implements Database {

  private FileHelper fileHelper;
  private JsonHelper jsonHelper;
  private Configuration configuration;
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


  public InFileDatabase(FileHelper fileHelper, JsonHelper jsonHelper, Configuration configuration) {
    this.fileHelper = fileHelper;
    this.jsonHelper = jsonHelper;
    this.configuration = configuration;
  }

  @Override
  public void saveInvoices(List<Invoice> invoicesListName) {
    List<String> jsonArray = new ArrayList<>();
    for (Invoice invoice : invoicesListName) {
      String jsonAsString = convertInvoiceToJsonString(invoice);
      jsonArray.add(jsonAsString);
    }

      fileHelper.writeListToTheFile(jsonArray, configuration.getFileName());
  }

  @Override
  public void saveInvoice(Invoice invoice) {
    String jsonAsString = convertInvoiceToJsonString(invoice);
    fileHelper.appendLine(jsonAsString, configuration.getFileName());
  }

  @Override
  public List<Invoice> getInvoices() {
    List<String> allInvoices = fileHelper.readLines(configuration.getFileName());
    return returnAllInvoices(allInvoices);
  }

  public void readInvoices() {
    fileHelper.readLines(configuration.getFileName());
  }

  @Override
  public Invoice getInvoiceById(Long id) {
    String invoiceLine = fileHelper
        .readJsonFileAndFindInvoiceLineById(configuration.getFileName(), id);
    return returnInvoiceById(invoiceLine);
  }

  @Override
  public void updateInvoice(Invoice invoice) {
    String invoiceAsJsonString = convertInvoiceToJsonString(invoice);
    Long currentId = Optional.ofNullable(invoice.getId())
        .orElse(0L);//do decyzji grupy, co zwrocic jesli id = null.
    fileHelper
        .updateInvoiceByIdWhenReadingJsonFile(invoiceAsJsonString, configuration.getFileName(),
            currentId);
  }

  @Override
  public void removeInvoiceById(Long id) {
    fileHelper.removeInvoiceByIdWhenReadingJsonFile(configuration.getFileName(), id);
  }
}
