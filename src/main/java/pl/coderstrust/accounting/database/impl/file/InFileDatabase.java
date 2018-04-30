package pl.coderstrust.accounting.database.impl.file;

import pl.coderstrust.accounting.database.Database;
import pl.coderstrust.accounting.model.Invoice;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

public class InFileDatabase implements Database {

  private FileHelper fileHelper;
  private JsonHelper jsonHelper;
  private Configuration configuration;


  public InFileDatabase(FileHelper fileHelper, JsonHelper jsonHelper, Configuration configuration) {
    this.fileHelper = fileHelper;
    this.jsonHelper = jsonHelper;
    this.configuration = configuration;
  }

  @Override
  public void saveListOfInvoices(List<Invoice> invoicesListName) {
    List<String> jsonArray = new ArrayList<>();
    for (Invoice invoice : invoicesListName) {
      String jsonAsString = jsonHelper.convertInvoiceToJsonString(invoice);
      jsonArray.add(jsonAsString);
    }
    for (String json : jsonArray) {
      fileHelper.writeJsonInvoiceToFile(json, configuration.getFileName());
    }
  }

  @Override
  public void saveInvoice(Invoice invoice) {
    String jsonAsString = jsonHelper.convertInvoiceToJsonString(invoice);
    fileHelper.writeJsonInvoiceToFile(jsonAsString, configuration.getFileName());
  }

  @Override
  public List<Invoice> getInvoices() {
    List<String> allInvoices = fileHelper.readAllJsonFiles(configuration.getFileName());
    return jsonHelper.returnAllInvoices(allInvoices);
  }

  public void readInvoices() {
    fileHelper.printJsonInvoiceAsString(configuration.getFileName());
  }

  @Override
  public Invoice getInvoiceById(Long id) {
    String invoiceLine = fileHelper
        .readJsonFileAndFindInvoiceLineById(configuration.getFileName(), id);
    return jsonHelper.returnInvoiceById(invoiceLine);
  }

  @Override
  public void updateInvoice(Invoice invoice, Long id) {
    String invoiceAsJsonString = jsonHelper.convertInvoiceToJsonString(invoice);
    fileHelper
        .updateInvoiceByIdWhenReadingJsonFile(invoiceAsJsonString, configuration.getFileName(), id);
    replaceOldInvoiceFileByNew();
  }

  @Override
  public void removeInvoiceById(Long id) {
    fileHelper.removeInvoiceByIdWhenReadingJsonFile(configuration.getFileName(), id);
    replaceOldInvoiceFileByNew();
  }

  private void replaceOldInvoiceFileByNew() {
    File newFileName = new File("temporaryFile.json");
    File fileName = new File(configuration.getFileName());
    boolean successful;
    try {
      Files.move(newFileName.toPath(), fileName.toPath(), StandardCopyOption.REPLACE_EXISTING);
      successful = true;
    } catch (IOException e) {
      successful = false;
    }
  }
}
