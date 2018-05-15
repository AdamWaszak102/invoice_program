package pl.coderstrust.accounting.logic;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static pl.coderstrust.accounting.model.TestInvoiceProvider.invoiceOne;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pl.coderstrust.accounting.database.Database;
import pl.coderstrust.accounting.model.Invoice;

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

    //when
    invoiceBook.saveInvoice(invoice);

    //then
    verify(database).saveInvoice(invoice);
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

}