package pl.coderstrust.accounting.database.impl.mongo;

import com.mongodb.client.MongoCollection;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.coderstrust.accounting.database.Database;
import pl.coderstrust.accounting.database.DatabaseTest;
import pl.coderstrust.accounting.model.Invoice;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MongoDatabaseIntegrationTest extends DatabaseTest{

  @Autowired
  private Database Mongodatabase;

  @Autowired
  MongoCollection<Invoice> collection;

  @Override
  protected Database getDatabase() {
    return Mongodatabase;
  }
}