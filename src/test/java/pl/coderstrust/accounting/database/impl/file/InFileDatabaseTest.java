package pl.coderstrust.accounting.database.impl.file;

import org.junit.After;
import org.junit.Test;
import pl.coderstrust.accounting.database.Database;
import pl.coderstrust.accounting.database.DatabaseTest;

import java.io.File;

/**
 * Created by Adam on 2018-04-17.
 */
public class InFileDatabaseTest extends DatabaseTest {
  private static final String dbFileName = "test.json";
  private static final String idFileName = "idTest.txt";

  @Override
  protected Database getDatabase() {
    FileHelper fileHelper = new FileHelper();
    JsonHelper jsonHelper = new JsonHelper();
    cleanUp();
    Configuration configuration = new Configuration(dbFileName, idFileName);
    return new InFileDatabase(fileHelper, jsonHelper, configuration);
  }

  @After
  public void cleanUp() {
    File dbFile = new File(dbFileName);
    if(dbFile.exists()){
      dbFile.delete();
    }
    File idFile = new File(idFileName);
    if(idFile.exists()){
      idFile.delete();
    }
  }

  @Test
  public void shouldCreateFileWhenInvoiceAdded() {
    new InFileDatabaseTest();
  }
}