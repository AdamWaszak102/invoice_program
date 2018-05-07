package pl.coderstrust.accounting.database.impl.file;

import org.junit.Test;
import pl.coderstrust.accounting.database.Database;
import pl.coderstrust.accounting.database.DatabaseTest;

/**
 * Created by Adam on 2018-04-17.
 */
public class InFileDatabaseTest extends DatabaseTest {

  @Override
  protected Database getDatabase() {
    FileHelper fileHelper = new FileHelper();
    JsonHelper jsonHelper = new JsonHelper();
    Configuration configuration = new Configuration("test.json");
    return new InFileDatabase(fileHelper, jsonHelper, configuration);
  }

  @Test
  public void shouldCreateFileWhenInvoiceAdded() {
    new InFileDatabaseTest();
  }
}