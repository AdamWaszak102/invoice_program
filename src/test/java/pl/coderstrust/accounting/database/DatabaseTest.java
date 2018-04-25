package pl.coderstrust.accounting.database;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import pl.coderstrust.accounting.model.Invoice;
import pl.coderstrust.accounting.model.TestInvoiceProvider;

public abstract class DatabaseTest {

  protected abstract Database getDatabase();

//  @Test // ???
//  public void shouldReturn2InvoicesWhen2InvoicesWereAdded() {
//    Database db = getDatabase();
//
//    db.saveInvoice(new Invoice(null,null,null,null,null,null));
//  //  Assert.assertEquals(0, db.getInvoices().size());
//  }

  @Test   //?????? NULL!
  public void shouldSaveTwoInvoices() {
    //given
    Invoice invoiceProviderOne = new TestInvoiceProvider().InvoiceOne();
    Invoice invoiceProviderTwo = new TestInvoiceProvider().InvoiceTwo();
    Database db = getDatabase();

    //when
    db.saveInvoice(invoiceProviderOne);
    db.saveInvoice(invoiceProviderTwo);

    //then
    assertEquals(2, db.getInvoices().size());
    assertNull(db.getInvoices());
  }


  @Test
  public void shouldCheckIdNumbers() {
    //given
    Invoice invoiceProviderOne = new TestInvoiceProvider().InvoiceOne();
    Invoice invoiceProviderTwo = new TestInvoiceProvider().InvoiceTwo();
    Invoice invoiceProviderThree = new TestInvoiceProvider().InvoiceThree();
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

  @Test   //?????? NULL!
  public void shouldSaveAndRemoveOneInvoice() {
    //given
    Invoice invoiceProviderOne = new TestInvoiceProvider().InvoiceOne();
    Database db = getDatabase();

    //when
    db.saveInvoice(invoiceProviderOne);

    //then
  //  assertEquals(0, db.getInvoices().size());
    assertNull(db.getInvoices());
  }

}