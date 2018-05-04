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
      String jsonAsString = jsonHelper.convertInvoiceToJsonString(invoice);
      jsonArray.add(jsonAsString);
    }

    fileHelper.writeListToTheFile(jsonArray, configuration.getFileName());
  }

  @Override
  public void saveInvoice(Invoice invoice) {
    String jsonAsString = jsonHelper.convertInvoiceToJsonString(invoice);
    fileHelper.appendLine(jsonAsString, configuration.getFileName());
  }

  @Override
  public List<Invoice> getInvoices() {
    List<String> allInvoices = fileHelper.readLines(configuration.getFileName());
    return jsonHelper.returnAllInvoices(allInvoices);
  }

  public void readInvoices() {
    fileHelper.readLines(configuration.getFileName());
  }

  @Override
  public Invoice getInvoiceById(Long id) {
    String invoiceLine = fileHelper
        .readJsonFileAndFindInvoiceLineById(configuration.getFileName(), id);
    return jsonHelper.returnInvoiceById(invoiceLine);
  }

  @Override
  public void updateInvoice(Invoice invoice) {
    String invoiceAsJsonString = jsonHelper.convertInvoiceToJsonString(invoice);
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
