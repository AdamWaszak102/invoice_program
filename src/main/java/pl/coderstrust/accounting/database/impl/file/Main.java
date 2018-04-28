package pl.coderstrust.accounting.database.impl.file;

import pl.coderstrust.accounting.database.Database;
import pl.coderstrust.accounting.model.Invoice;
import pl.coderstrust.accounting.model.InvoiceProvider;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Main {

  public static void main(String[] args) throws IOException {
    FileHelper fileHelper = new FileHelper();
    JsonHelper jsonHelper = new JsonHelper();
    Configuration configuration = new Configuration("allInvoices1.json");
    Invoice obj00 = new InvoiceProvider().InvoiceOne();
    Invoice obj01 = new InvoiceProvider().InvoiceTwo();
    Invoice obj02 = new InvoiceProvider().InvoiceThree();
    Database db = new InFileDatabase(fileHelper, jsonHelper, configuration);
    db.getInvoices();
    db.saveInvoice(obj02);
    List<Invoice> invoicesExamples = Arrays.asList(obj00, obj01, obj02);
    db.saveListOfInvoices(invoicesExamples);
//    ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule())
//        .configure(DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS, true);
//    Invoice inv = mapper.readValue(new File("allInvoices1.json"), Invoice.class);
//    String invo = inv.toString();
//    System.out.println(obj00.toString());
//    System.out.println(inv);
//    String iii = ReflectionToStringBuilder.toString(inv);
//    System.out.println(iii);
//    RecursiveToStringStyle style = new RecursiveToStringStyle();
//    return new ReflectionToStringBuilder(inv, new RecursiveToStringStyle()).toString();
//    String iiii = RecursiveToStringStyle

  }
}
