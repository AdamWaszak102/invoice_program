package pl.coderstrust.accounting.database.impl.file;

import pl.coderstrust.accounting.database.Database;
import pl.coderstrust.accounting.model.Invoice;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class InFileDatabase implements Database {

  private FileHelper fileHelper;
  private JsonHelper jsonHelper;
  private Configuration configuration;
  private File idNumber = new File("idNumber.txt");
  private Long id;

  public InFileDatabase(FileHelper fileHelper, JsonHelper jsonHelper, Configuration configuration) {
    this.fileHelper = fileHelper;
    this.jsonHelper = jsonHelper;
    this.configuration = configuration;
  }

  @Override
  public void saveInvoices(List<Invoice> invoicesListName) {
    List<String> jsonArray = new ArrayList<>();
    for (Invoice invoice : invoicesListName) {
      id = checkId();
      ++id;
      saveNewId(id);
      invoice.setId(id);
      String jsonAsString = jsonHelper.convertInvoiceToJsonString(invoice);
      jsonArray.add(jsonAsString);
    }
    fileHelper.writeListToFile(jsonArray, configuration.getFileName(), true);
  }

  @Override
  public void saveInvoice(Invoice invoice) {
    id = checkId();
    ++id;
    saveNewId(id);
    invoice.setId(id);
    String jsonAsString = jsonHelper.convertInvoiceToJsonString(invoice);
    fileHelper.appendLine(jsonAsString, configuration.getFileName(), true);
  }

  @Override
  public List<Invoice> getInvoices() {
    List<String> allInvoices = fileHelper.readLines(configuration.getFileName());
    return jsonHelper.convertInvoicesToJsonStringsList(allInvoices);
  }

  @Override
  public Invoice getInvoiceById(Long id) {
    String invoiceLine = fileHelper
        .readJsonFileAndFindInvoiceLineById(configuration.getFileName(), getContent(id));
    return jsonHelper.returnInvoiceById(invoiceLine);
  }

  @Override
  public void updateInvoice(Invoice invoice) {
    String invoiceAsJsonString = jsonHelper.convertInvoiceToJsonString(invoice);
    Long currentId = Optional.ofNullable(invoice.getId())
        .orElse(0L);
    System.out.println(currentId);
    fileHelper
        .updateLineWithContentWhenReadingJsonFile(invoiceAsJsonString, configuration.getFileName(),
            getContent(currentId));
  }

  @Override
  public void removeInvoiceById(Long id) {
    fileHelper
        .removeLineWithContentWhenReadingJsonFile(configuration.getFileName(), getContent(id));
  }

  public String getContent(Long id) {
    return "\"id\":" + id + ",";
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
