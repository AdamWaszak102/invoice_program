package pl.coderstrust.accounting.database.impl.file;

import static org.mockito.Mockito.mock;

import org.junit.After;
import pl.coderstrust.accounting.database.Database;
import pl.coderstrust.accounting.database.DatabaseTest;

import java.io.File;

public class InFileDatabaseTest extends DatabaseTest {

  private static final String dbFileName = "test.json";
  private static final String idFileName = "idTest.txt";

  @Override
  protected Database getDatabase() {
    FileHelper fileHelper = mock(FileHelper.class);
    JsonHelper jsonHelper = mock(JsonHelper.class);
//    cleanUp();
    Configuration configuration = mock(Configuration.class);
    return new InFileDatabase(fileHelper, jsonHelper, configuration);
  }

  @After
  public void cleanUp() {
    File dbFile = new File(dbFileName);
    if (dbFile.exists()) {
      dbFile.delete();
    }
    File idFile = new File(idFileName);
    if (idFile.exists()) {
      idFile.delete();
    }
  }
}