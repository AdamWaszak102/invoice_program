package pl.coderstrust.accounting.database.impl.file;

import pl.coderstrust.accounting.database.Database;
import pl.coderstrust.accounting.model.Invoice;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adam on 2018-04-16.
 */
public class InFileDatabase implements Database {

  private FileHelper fileHelper;
  private JsonHelper jsonHelper;
  private Configuration configuration;

  public InFileDatabase(FileHelper fileHelper, JsonHelper jsonHelper, Configuration configuration) {
    this.fileHelper = fileHelper;
    this.jsonHelper = jsonHelper;
    this.configuration = configuration;
  }
  public void saveListOfInvoices (List<Invoice> invoicesListName){
    saveListOfInvoices(invoicesListName, configuration.getFileName(), jsonHelper, fileHelper);
  }

  public void saveListOfInvoices (List<Invoice> invoiceListName, String fileName, JsonHelper jsonHelper, FileHelper fileHelper){
    List<String> jsonArray = new ArrayList<>();
    for(Invoice invoice: invoiceListName ){
      String jsonAsString = jsonHelper.convertInvoiceToJsonString(invoice);
      jsonArray.add(jsonAsString);
    }
    for(String json: jsonArray ){
      fileHelper.writeJsonInvoiceToFile(json, fileName);
    }

  }
  public void saveInvoice(Invoice invoice, String fileName, FileHelper fileHelper, JsonHelper jsonHelper ){
    String jsonAsString = jsonHelper.convertInvoiceToJsonString(invoice);
    fileHelper.writeJsonInvoiceToFile(jsonAsString, fileName);
  }

  @Override
  public void saveInvoice(Invoice invoice){
    saveInvoice(invoice, configuration.getFileName(), fileHelper, jsonHelper);
    }

  @Override
  public List<Invoice> getInvoices() {
    return null;
  }

  @Override
  public Invoice getInvoiceById(Long id) {
    return null;
  }

  @Override
  public void updateInvoice(Invoice invoice) {

  }

  @Override
  public void removeInvoiceById(Long id) {
  }
}
