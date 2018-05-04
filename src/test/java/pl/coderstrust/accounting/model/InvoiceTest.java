package pl.coderstrust.accounting.model;

import static org.junit.Assert.assertEquals;
import static pl.coderstrust.accounting.model.TestInvoiceProvider.invoiceOne;
import static pl.coderstrust.accounting.model.TestInvoiceProvider.invoiceTwo;

import org.junit.Test;

import java.time.LocalDate;

public class InvoiceTest {

  private Invoice invoiceProviderOne = invoiceOne();
  private Invoice invoiceProviderTwo = invoiceTwo();

  @Test
  public void shouldGetIssueDate() {
    //given
    LocalDate expected = LocalDate.of(2018, 2, 2);

    //when
    LocalDate actual = invoiceProviderTwo.getIssueDate();

    //then
    assertEquals(expected, actual);
  }

  @Test
  public void shouldReturnId() {
    //given
    Long idExpected = 0L;

    //when
    Long idActual = invoiceProviderOne.getId();

    //then
    assertEquals(idExpected, idActual);
  }
}