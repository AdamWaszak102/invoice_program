package pl.coderstrust.accounting.database.impl.file;

import pl.coderstrust.accounting.database.Database;
import pl.coderstrust.accounting.model.Invoice;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adam on 2018-04-16.
 */
public class InFileDatabase implements Database {
//  The tricky part is id of invoices - you need to generate unique id for each invoice -
// the easiest solution is to keep current largest id and update it each time new invoice is added.
// The simplest solution is to keep that id in separate file.
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
    fileHelper.printJsonInvoiceAsString(configuration.getFileName());
    return null;
  }

  @Override
  public Invoice getInvoiceById(Long id) {
//    for (Object item : mainMap2) {
//      Map<String, Object> mapItem = (Map<String, Object>) item;
//      id = (Integer) mapItem.get("id");
//      if (id != null) {
//        // We have found an Id so we print it and exit from the for loop
//        System.out.printf("Id=%d%n", id);
//        break;
//      }
//    }

    return null;
  }

  @Override
  public void updateInvoice(Invoice invoice) {

  }

  @Override
  public void removeInvoiceById(Long id) {
  }
}
