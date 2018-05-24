package pl.coderstrust.accounting.logic;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static pl.coderstrust.accounting.model.TestInvoiceProvider.invoiceOne;
import static pl.coderstrust.accounting.model.TestInvoiceProvider.invoiceThree;
import static pl.coderstrust.accounting.model.TestInvoiceProvider.invoiceTwo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pl.coderstrust.accounting.database.Database;
import pl.coderstrust.accounting.model.Invoice;

import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class InvoiceBookTest {

  @Mock
  Database database;

  Invoice invoice = invoiceOne();


  @InjectMocks
  InvoiceBook invoiceBook;

  @Test
  public void shouldSaveInvoice() {
    //given
    Long id = 1234L;
    when((database).saveInvoice(invoice)).thenReturn(id);

    //when
    Long result = invoiceBook.saveInvoice(invoice);

    //then
    verify(database).saveInvoice(invoice);
    assertEquals(id, result);
  }

  @Test
  public void shouldUpdateInvoiceWhenInvoiceIdPresent() {
    //given
    invoice.setId(1234L);

    //when
    invoiceBook.updateInvoice(invoice);

    //then
    verify(database).updateInvoice(invoice);
  }

  @Test
  public void shouldNotCallUpdateInvoiceWhenInvoiceIdNotPresent() {
    //given
    invoice.setId(null);

    //when
    invoiceBook.updateInvoice(invoice);

    //then
    verify(database, never()).updateInvoice(invoice);
  }

  @Test
  public void shouldRemoveInvoiceById() {
    //given
    Long id = 1L;

    //when
    invoiceBook.removeInvoiceById(id);

    //then
    verify(database).removeInvoiceById(id);
  }

  @Test
  public void shouldGetInvoiceById() {
    //given
    Long id = 1L;

    //when
    invoiceBook.getInvoiceById(id);

    //then
    verify(database).getInvoiceById(id);
  }

  @Test
  public void shouldGetInvoices() {
    //given

    //when
    invoiceBook.getInvoices();

    //then
    verify(database).getInvoices();
  }

  @Test
  public void shouldSaveInvoices() {
    //given
    List<Invoice> invoices = Arrays.asList(invoice, invoiceTwo(), invoiceThree());
    List<Long> ids = Arrays.asList(123L, 345L, 678L);
    when((database).saveInvoices(invoices)).thenReturn(ids);

    //when
    List<Long> result = invoiceBook.saveInvoices(invoices);

    //then
    verify(database).saveInvoices(invoices);
    assertEquals(ids, result);
  }

}