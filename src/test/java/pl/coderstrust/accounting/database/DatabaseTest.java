package pl.coderstrust.accounting.database;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static pl.coderstrust.accounting.model.TestInvoiceProvider.invoiceOne;
import static pl.coderstrust.accounting.model.TestInvoiceProvider.invoiceThree;
import static pl.coderstrust.accounting.model.TestInvoiceProvider.invoiceTwo;

import org.junit.Test;
import pl.coderstrust.accounting.model.Invoice;

public abstract class DatabaseTest {

  protected abstract Database getDatabase();

  @Test
  public void shouldReturn2InvoicesWhen2InvoicesWereAdded() {
    Database db = getDatabase();

    db.saveInvoice(new Invoice(null, null, null, null, null, null));
    assertEquals(1, db.getInvoices().size());
  }

  @Test
  public void shouldSaveTwoInvoices() {
    //given
    Invoice invoiceProviderOne = invoiceOne();
    Invoice invoiceProviderTwo = invoiceTwo();
    Database db = getDatabase();

    //when
    db.saveInvoice(invoiceProviderOne);
    db.saveInvoice(invoiceProviderTwo);

    //then
    assertEquals(2, db.getInvoices().size());
    assertNotNull(db.getInvoices());
  }

  @Test
  public void shouldCheckIdNumbers() {
    //given
    Invoice invoiceProviderOne = invoiceOne();
    Invoice invoiceProviderTwo = invoiceTwo();
    Invoice invoiceProviderThree = invoiceThree();
    Database db = getDatabase();

    //when
    db.saveInvoice(invoiceProviderOne);
    db.saveInvoice(invoiceProviderTwo);
    db.saveInvoice(invoiceProviderThree);
    Object idOne = invoiceProviderOne.getId();
    Object idTwo = invoiceProviderTwo.getId();
    Object idThree = invoiceProviderThree.getId();

    //then
    assertEquals(0L, idOne);
    assertEquals(1L, idTwo);
    assertEquals(2L, idThree);
  }

  @Test
  public void shouldSaveAndRemoveOneInvoice() {
    //given
    Invoice invoiceProviderOne = invoiceOne();
    Database db = getDatabase();

    //when
    db.saveInvoice(invoiceProviderOne);
    db.removeInvoiceById(0L);

    //then
    assertEquals(0, db.getInvoices().size());
  }

  @Test
  public void shouldGetInvoiceById() {
    //given
    Invoice invoiceProviderOne = invoiceOne();
    Database db = getDatabase();
    Long id = 0L;
    db.saveInvoice(invoiceProviderOne);

    //when
    Object actual = db.getInvoiceById(id);

    //then
    assertEquals(invoiceProviderOne, actual);
  }

  @Test
  public void shouldSaveAndUpdateInvoice() {
    //given
    Invoice invoiceProviderOne = invoiceOne();
    Database db = getDatabase();

    //when
    db.saveInvoice(invoiceProviderOne);
    db.updateInvoice(invoiceProviderOne);

    //then
    assertEquals(1, db.getInvoices().size());
  }
}
