package pl.coderstrust.accounting.database;

import static org.junit.Assert.assertEquals;
import static pl.coderstrust.accounting.model.TestInvoiceProvider.invoiceOne;
import static pl.coderstrust.accounting.model.TestInvoiceProvider.invoiceTwo;

import org.junit.Test;
import pl.coderstrust.accounting.model.Invoice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class DatabaseTest {

  protected abstract Database getDatabase();

  @Test
  public void shouldSaveTwoInvoicesSeparately() {
    //given
    Invoice invoiceOne = invoiceOne();
    Invoice invoiceTwo = invoiceTwo();
    Database db = getDatabase();
    int invoicesNumberBeforeInsert = db.getInvoices().size();

    //when
    db.saveInvoice(invoiceOne);
    db.saveInvoice(invoiceTwo);

    //then
    assertEquals(invoicesNumberBeforeInsert + 2, db.getInvoices().size());
  }

  @Test
  public void shouldSaveListOfTwoInvoices() {
    //given
    Invoice invoiceOne = invoiceOne();
    Invoice invoiceTwo = invoiceTwo();
    List<Invoice> invoicesList = new ArrayList<>(Arrays.asList(invoiceOne, invoiceTwo));
    Database db = getDatabase();
    int initialInvoicesNumber = db.getInvoices().size();

    //when
    db.saveInvoices(invoicesList);

    //then
    assertEquals(initialInvoicesNumber + 2, db.getInvoices().size());
  }

  @Test
  public void shouldCheckIdNumbers() {
    //given
    Invoice invoiceOne = invoiceOne();
    Database db = getDatabase();

    //when
    long idOne = db.saveInvoice(invoiceOne);
    long idTwo = db.saveInvoice(invoiceOne);
    long idThree = db.saveInvoice(invoiceOne);

    //then
    assertEquals(idOne + 1L, idTwo);
    assertEquals(idTwo + 1L, idThree);
  }

  @Test
  public void shouldSaveAndRemoveOneInvoice() {
    //given
    Invoice invoiceOne = invoiceOne();
    Database db = getDatabase();
    int initialInvoicesNumber = db.getInvoices().size();

    //when
    Long id = db.saveInvoice(invoiceOne);
    db.removeInvoiceById(id);

    //then
    assertEquals(initialInvoicesNumber, db.getInvoices().size());
  }

  @Test
  public void shouldGetInvoiceById() {
    //given
    Invoice expected = invoiceTwo();
    Database db = getDatabase();
    Long id = db.saveInvoice(expected);

    //when
    Invoice actual = db.getInvoiceById(id);

    //then
    assertEquals(actual, expected);
  }

  @Test
  public void shouldSaveAndUpdateInvoice() {
    //given
    Invoice beforeUpdate = invoiceOne();
    Invoice afterUpdate = invoiceOne();
    Database db = getDatabase();
    afterUpdate.setIdentifier("FV 5/2018");

    //when
    Long id = db.saveInvoice(beforeUpdate);
    afterUpdate.setId(id);
    db.updateInvoice(afterUpdate);
    Invoice actual = db.getInvoiceById(beforeUpdate.getId());

    //then
    assertEquals(afterUpdate, actual);
  }
}