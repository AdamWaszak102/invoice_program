package pl.coderstrust.accounting.model;

import static org.junit.Assert.assertEquals;
import static pl.coderstrust.accounting.model.Vat.VAT_5;

import org.junit.Test;

import java.math.BigDecimal;

public class InvoiceEntryTest {

  private  InvoiceEntry invoiceEntry = new InvoiceEntry("Report", new BigDecimal("30.00"), VAT_5);

  @Test
  public void shouldReturnDescription() {
    //given
    String expected = "Report";

    //when
    String actual = invoiceEntry.getDescription();

    //then
    assertEquals(expected, actual);
  }

  @Test
  public void shouldReturnPrice() {
    //given
    BigDecimal expected = new BigDecimal("30.00");

    //when
    BigDecimal actual = invoiceEntry.getPrice();

    //then
    assertEquals(expected, actual);
  }

  @Test
  public void shouldReturnVatValue() {
    //given
    Vat expected = VAT_5;

    //when
    Vat actual = invoiceEntry.getVat();

    //then
    assertEquals(expected, actual);
  }
}