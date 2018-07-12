package pl.coderstrust.accounting.database.hibernate;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.coderstrust.accounting.database.Database;
import pl.coderstrust.accounting.database.DatabaseTest;


@RunWith(SpringRunner.class)
@SpringBootTest
public class HibernateDatabaseIntegrationTest extends DatabaseTest {
  @Autowired
  private Database hibernateDatabase;

  @Autowired
  private InvoiceRepository repository;

  @Override
  protected Database getDatabase() {
    return hibernateDatabase;
  }
}
