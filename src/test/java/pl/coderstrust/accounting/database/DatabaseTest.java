package pl.coderstrust.accounting.database;

import static org.junit.Assert.assertEquals;
import static pl.coderstrust.accounting.model.TestInvoiceProvider.assertSameInvoice;
import static pl.coderstrust.accounting.model.TestInvoiceProvider.invoiceOne;
import static pl.coderstrust.accounting.model.TestInvoiceProvider.invoiceThree;
import static pl.coderstrust.accounting.model.TestInvoiceProvider.invoiceTwo;

import org.junit.Test;
import pl.coderstrust.accounting.model.Invoice;

public abstract class DatabaseTest {

  protected abstract Database getDatabase();

  @Test
  public void shouldSaveTwoInvoices() {
    //given
    Invoice invoiceOne = invoiceOne();
    Invoice invoiceTwo = invoiceTwo();
    Database db = getDatabase();

    //when
    db.saveInvoice(invoiceOne);
    db.saveInvoice(invoiceTwo);

    //then
    assertEquals(2, db.getInvoices().size());
   }

  @Test
  public void shouldCheckIdNumbers() {
    //given
    Invoice invoiceOne = invoiceOne();
    Invoice invoiceTwo = invoiceTwo();
    Invoice invoiceThree = invoiceThree();
    Database db = getDatabase();

    //when
    db.saveInvoice(invoiceOne);
    db.saveInvoice(invoiceTwo);
    db.saveInvoice(invoiceThree);
    long idOne = invoiceOne.getId();
    long idTwo = invoiceTwo.getId();
    long idThree = invoiceThree.getId();

    //then
    assertEquals(0, idOne);
    assertEquals(1, idTwo);
    assertEquals(2, idThree);
  }

  @Test
  public void shouldSaveAndRemoveOneInvoice() {
    //given
    Invoice invoiceOne = invoiceOne();
    Database db = getDatabase();

    //when
    db.saveInvoice(invoiceOne);
    db.removeInvoiceById(0L);

    //then
    assertEquals(0, db.getInvoices().size());
  }

  @Test
  public void shouldGetInvoiceById() {
    //given
    Invoice invoiceOne = invoiceOne();
    Database db = getDatabase();
    Long id = 0L;
    db.saveInvoice(invoiceOne);

    //when
    Invoice actual = db.getInvoiceById(id);

    //then
    assertSameInvoice(invoiceOne, actual);
  }

  @Test
  public void shouldSaveAndUpdateInvoice() {
    //given
    Invoice beforeUpdate = invoiceOne();
    Invoice afterUpdate = invoiceOne();
    Database db = getDatabase();

    afterUpdate.setIdentifier("FV 5/2018");

    //when
    db.saveInvoice(beforeUpdate);
    db.updateInvoice(afterUpdate);
    Invoice actual = db.getInvoiceById(beforeUpdate.getId());

    //then
    assertSameInvoice(afterUpdate, actual);
  }
}