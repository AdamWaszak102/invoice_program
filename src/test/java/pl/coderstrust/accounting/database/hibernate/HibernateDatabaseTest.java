package pl.coderstrust.accounting.database.hibernate;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static pl.coderstrust.accounting.model.TestInvoiceProvider.invoiceOne;
import static pl.coderstrust.accounting.model.TestInvoiceProvider.invoiceThree;
import static pl.coderstrust.accounting.model.TestInvoiceProvider.invoiceTwo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pl.coderstrust.accounting.model.Invoice;

import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class HibernateDatabaseTest {

  @Mock
  InvoiceRepository repository;

  Invoice expectedInvoice;

  @InjectMocks
  HibernateDatabase hibernateDatabase;

  @Before
  public void setId() {
    expectedInvoice = invoiceOne();
    expectedInvoice.setId(12345L);
  }

  @Test
  public void shouldSaveInvoice() {
    //given
    when((repository).save(expectedInvoice)).thenReturn(expectedInvoice);

    //when
    Long result = hibernateDatabase.saveInvoice(expectedInvoice);

    //then
    verify(repository, times(1)).save(expectedInvoice);
    assertEquals(expectedInvoice.getId(), result);
  }

  @Test
  public void shouldUpdateInvoiceWhenInvoiceIdPresent() {
    //given
    expectedInvoice.setId(12345L);

    //when
    hibernateDatabase.updateInvoice(expectedInvoice);

    //then
    verify(repository).findById(expectedInvoice.getId());
  }

  @Test
  public void shouldNotCallUpdateInvoiceWhenInvoiceIdNotPresent() {
    //given
    expectedInvoice.setId(null);

    //when
    hibernateDatabase.updateInvoice(expectedInvoice);

    //then
    verify(repository, never()).save(expectedInvoice);
  }

  @Test
  public void shouldRemoveInvoiceById() {
    //given

    //when
    hibernateDatabase.removeInvoiceById(expectedInvoice.getId());

    //then
    verify(repository).deleteById(expectedInvoice.getId());
  }

  @Test
  public void shouldGetInvoiceById() {
    //given

    //when
    hibernateDatabase.getInvoiceById(expectedInvoice.getId());

    //then
    verify(repository).findById(expectedInvoice.getId());
  }

  @Test
  public void shouldGetInvoices() {
    //given

    //when
    hibernateDatabase.getInvoices();

    //then
    verify(repository).findAll();
  }
//  @Test
//  public void shouldSaveInvoices() {
//    //given
//    List<Invoice> invoices = Arrays.asList(invoiceOne(), invoiceTwo(), invoiceThree());
//    List<Long> ids = Arrays.asList(12345L, 345L, 678L);
//    when((repository).saveAll(invoices)).thenReturn(Arrays.asList());
//
//    //when
//    List<Long> result = hibernateDatabase.saveInvoices(invoices);
//
//    //then
//    verify(repository).saveAll(invoices);
//    assertEquals(ids, result);
//  }
}