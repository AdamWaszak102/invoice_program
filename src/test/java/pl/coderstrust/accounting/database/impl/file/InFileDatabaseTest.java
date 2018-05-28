package pl.coderstrust.accounting.database.impl.file;

import org.junit.After;
import pl.coderstrust.accounting.SpringConfiguration;
import pl.coderstrust.accounting.database.Database;
import pl.coderstrust.accounting.database.DatabaseTest;

import java.io.File;

public class InFileDatabaseTest extends DatabaseTest {

  private static final String dbFileName = "test.json";
  private static final String idFileName = "idTest.txt";

  @Override
  protected Database getDatabase() {

    SpringConfiguration springConfiguration = new SpringConfiguration();
    FileHelper fileHelper = new FileHelper();
    JsonHelper jsonHelper = new JsonHelper(springConfiguration.writingMapper(),
        springConfiguration.readingMapper());
    cleanUp();
    Configuration configuration = new Configuration(dbFileName, idFileName);
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