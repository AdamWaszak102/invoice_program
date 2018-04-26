package pl.coderstrust.accounting.database.impl.file;

import pl.coderstrust.accounting.database.Database;
import pl.coderstrust.accounting.model.Invoice;
import pl.coderstrust.accounting.model.InvoiceProvider;

import java.util.Arrays;
import java.util.List;

public class Main {

  public static void main(String[] args){
    FileHelper fileHelper = new FileHelper();
    JsonHelper jsonHelper = new JsonHelper();
    Configuration configuration = new Configuration();
    configuration.setFileName("allInvoices.json");
    Invoice obj00 = new InvoiceProvider().InvoiceOne();
    Invoice obj01 = new InvoiceProvider().InvoiceTwo();
    Invoice obj02 = new InvoiceProvider().InvoiceThree();
    Database db = new InFileDatabase(fileHelper, jsonHelper, configuration);
    db.saveInvoice(obj02);
    List<Invoice> invoicesExamples = Arrays.asList(obj00, obj01, obj02);
    db.saveListOfInvoices(invoicesExamples);
  }
}
