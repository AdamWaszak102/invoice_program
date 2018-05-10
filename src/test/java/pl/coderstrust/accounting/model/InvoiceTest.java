package pl.coderstrust.accounting.model;

import static org.junit.Assert.assertEquals;
import static pl.coderstrust.accounting.model.TestInvoiceProvider.invoiceOne;
import static pl.coderstrust.accounting.model.TestInvoiceProvider.invoiceTwo;

import org.junit.Test;

import java.time.LocalDate;

public class InvoiceTest {

  @Test
  public void shouldGetIssueDate() {
    //given
    Invoice invoiceTwo = invoiceTwo();
    LocalDate expected = LocalDate.of(2018, 2, 2);

    //when
    LocalDate actual = invoiceTwo.getIssueDate();

    //then
    assertEquals(expected, actual);
  }

  @Test
  public void shouldReturnId() {
    //given
    Invoice invoiceOne = invoiceOne();
    Long idExpected = 1L;
    invoiceOne.setId(idExpected);

    //when
    Long idActual = invoiceOne.getId();

    //then
    assertEquals(idExpected, idActual);
  }
}