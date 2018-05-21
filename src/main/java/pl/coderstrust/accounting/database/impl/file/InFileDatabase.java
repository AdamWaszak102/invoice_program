package pl.coderstrust.accounting.database.impl.file;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;
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

@ConditionalOnProperty(value = "myBean.enabled", havingValue = "false")
@Repository
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
  public List<Long> saveInvoices(List<Invoice> invoicesListName) {
    List<Long> ids = new ArrayList<>();
    List<String> jsonArray = new ArrayList<>();
    for (Invoice invoice : invoicesListName) {
      getIdFromFileAndSaveItBack(invoice);
      ids.add(invoice.getId());
      String jsonAsString = jsonHelper.convertInvoiceToJsonString(invoice);
      jsonArray.add(jsonAsString);
    }
    fileHelper.writeListToFile(jsonArray, configuration.getFileName(), true);
    return ids;
  }

  @Override
  public Long saveInvoice(Invoice invoice) {
    getIdFromFileAndSaveItBack(invoice);
    String jsonAsString = jsonHelper.convertInvoiceToJsonString(invoice);
    fileHelper.appendLine(jsonAsString, configuration.getFileName());
    return invoice.getId();
  }

  @Override
  public List<Invoice> getInvoices() {
    List<String> allInvoices = fileHelper.readLines(configuration.getFileName());
    return jsonHelper.convertJsonStringsListToListOfInvoices(allInvoices);
  }

  @Override
  public Invoice getInvoiceById(Long id) {
    String invoiceLine = fileHelper
        .readJsonFileAndFindInvoiceLineById(configuration.getFileName(), getJsonStringIdPart(id));
    return jsonHelper.returnInvoiceById(invoiceLine);
  }

  @Override
  public void updateInvoice(Invoice invoice) {
    String invoiceAsJsonString = jsonHelper.convertInvoiceToJsonString(invoice);
    Long currentId = Optional.ofNullable(invoice.getId())
        .orElse(0L);
    fileHelper
        .updateLineWithContentWhenReadingJsonFile(invoiceAsJsonString, configuration.getFileName(),
            getJsonStringIdPart(currentId));
  }

  @Override
  public void removeInvoiceById(Long id) {
    fileHelper
        .removeLineWithContentWhenReadingJsonFile(configuration.getFileName(),
            getJsonStringIdPart(id));
  }

  private String getJsonStringIdPart(Long id) {
    return "\"id\":" + id + ",";
  }

  private synchronized void getIdFromFileAndSaveItBack(Invoice invoice) {
    Long id = 0L;
    File file = new File(configuration.getIdNumberFileName());
    if (file.exists()) {
      try (Scanner scanner = new Scanner(file)) {
        while (scanner.hasNextLong()) {
          id = scanner.nextLong();
        }
      } catch (FileNotFoundException exception) {
        exception.printStackTrace();
      }
    }
    invoice.setId(++id);
    try (FileWriter fileWriter = new FileWriter(file)) {
      fileWriter.write(id.toString());
    } catch (IOException exception) {
      exception.printStackTrace();
    }
  }
}
