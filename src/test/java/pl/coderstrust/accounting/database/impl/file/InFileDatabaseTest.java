package pl.coderstrust.accounting.database.impl.file;

import org.junit.After;
import org.junit.Ignore;
import pl.coderstrust.accounting.database.Database;
import pl.coderstrust.accounting.database.DatabaseTest;

import java.io.File;

@Ignore
// FIXME: This should be Spring integration test
public class InFileDatabaseTest extends DatabaseTest {

  private static final String dbFileName = "test.json";
  private static final String idFileName = "idTest.txt";

  @Override
  protected Database getDatabase() {
//    FileHelper fileHelper = new FileHelper();
//    JsonHelper jsonHelper = new JsonHelper();
//    cleanUp();
//    Configuration configuration = new Configuration(/*dbFileName, idFileName*/);
//    return new InFileDatabase(fileHelper, jsonHelper, configuration);
    return null;
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