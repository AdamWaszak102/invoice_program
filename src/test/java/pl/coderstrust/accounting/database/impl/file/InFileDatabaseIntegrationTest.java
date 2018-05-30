package pl.coderstrust.accounting.database.impl.file;

import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import pl.coderstrust.accounting.database.Database;
import pl.coderstrust.accounting.database.DatabaseTest;

import java.io.File;

@RunWith(SpringRunner.class)
@SpringBootTest
@ComponentScan(basePackages = "pl.coderstrust.accounting")
public class InFileDatabaseIntegrationTest extends DatabaseTest {

  @Autowired
  private Database inFileDatabase;

  @Autowired
  private Configuration configuration;

  @Override
  protected Database getDatabase() {
    cleanUp();
    return inFileDatabase;
  }

  @After
  public void cleanUp() {
    File dbFile = new File(configuration.getFileName());
    if (dbFile.exists()) {
      dbFile.delete();
    }
    File idFile = new File(configuration.getIdNumberFileName());
    if (idFile.exists()) {
      idFile.delete();
    }
  }
}

