package pl.coderstrust.accounting.database;

import org.junit.Test;
import pl.coderstrust.accounting.model.Invoice;

/**
 * Created by Adam on 2018-04-17.
 */
public abstract class DatabaseTest {

  protected abstract Database getDatabase();


  @Test
  public void shouldReturn2InvoicesWhen2InvoicesWereAdded() {
    Database db = getDatabase();

    db.saveInvoice(new Invoice(null,null,null,null,null,null));
    //dalej asercje
  }
}