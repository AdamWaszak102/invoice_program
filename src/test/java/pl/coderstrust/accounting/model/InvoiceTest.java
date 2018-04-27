package pl.coderstrust.accounting.model;

import static org.junit.Assert.assertEquals;
import static pl.coderstrust.accounting.model.TestInvoiceProvider.invoiceOne;
import static pl.coderstrust.accounting.model.TestInvoiceProvider.invoiceTwo;

import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class InvoiceTest {

  Invoice invoiceProviderOne = invoiceOne();
  Invoice invoiceProviderTwo = invoiceTwo();

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
  public void getEntries() {
    //given
    List<String> expected = new ArrayList<>();
    expected.add("microscope");
    expected.add(String.valueOf(BigDecimal.valueOf(1000)));
    expected.add("VAT_23");

    //when
//    List<String> actual = invoiceProviderOne.getEntries();
//    Assert.assertEquals(expected, actual);
  }

//  InvoiceEntry invoiceEntry = new InvoiceEntry("microscope", BigDecimal.valueOf(1000), VAT_23);
//  Invoice invoice = new Invoice(0L, "FV 1/2017", LocalDate.of(2017, 10, 2), buyer, seller,
//  Arrays.asList(invoiceEntry));

  @Test
  public void getId() {
  }
}