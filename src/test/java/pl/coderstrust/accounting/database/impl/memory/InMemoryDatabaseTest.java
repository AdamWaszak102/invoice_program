package pl.coderstrust.accounting.database.impl.memory;

import pl.coderstrust.accounting.database.Database;
import pl.coderstrust.accounting.database.DatabaseTest;

/**
 * Created by Adam on 2018-04-16.
 */
public class InMemoryDatabaseTest extends DatabaseTest {

  @Override
  protected Database getDatabase() {
    return new InMemoryDatabase();
  }
  //trzeba zrobic testy, abstrakcyjne,by moc testowac na kolejnych bazach!!
  //fileDatabese i multyFileDatabaze, zestaw testow generycznych. dziedziczenie.

}