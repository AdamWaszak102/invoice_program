package pl.coderstrust.accounting.database.impl.file;

import pl.coderstrust.accounting.database.Database;
import pl.coderstrust.accounting.model.Invoice;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

/**
 * Created by Adam on 2018-04-16.
 */
public class InFileDatabase implements Database {
// otwiera plik pisze cos w pliku, wyszukuje cos w pliku
  private FileHelper fileHelper;
  private Configuration configuration;
  // przesyłanie invoice id, description itp. trzeba stworzyc pojosy zeby te dane byly tam autom wpychane i zeby sie do tego dostac

  @Override
  public void saveInvoice(Invoice invoice) {
    File file = new File("invoices.json");
    if(!file.exists()) {
      try {
        file.createNewFile();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  @Override
  public Collection<Invoice> getInvoices() {
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
