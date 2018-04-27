package pl.coderstrust.accounting.database.impl.memory;

import pl.coderstrust.accounting.database.Database;
import pl.coderstrust.accounting.database.DatabaseTest;

public class InMemoryDatabaseTest extends DatabaseTest {

  @Override
  protected Database getDatabase() {
    return new InMemoryDatabase();
  }
}
