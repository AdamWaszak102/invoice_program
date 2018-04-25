package pl.coderstrust.accounting.model;

import static pl.coderstrust.accounting.model.Vat.VAT_5;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class InvoiceEntryTest {

  InvoiceEntry invoiceEntry = new InvoiceEntry("Report", new BigDecimal("30.00"), VAT_5);

  @Test
  public void shouldReturnTheDescription() {
    //given
    String expected = "Report";
    //when
    String actual = invoiceEntry.getDescription();
    //then
    Assert.assertEquals(expected, actual);
  }

//  @Test  //
//  public void shouldReturnTheDescription2() {
//    String expected = "Report";
//    String actual = invoiceEntry.setDescription;
//    Assert.assertEquals(expected, actual);
//  }

  @Test
  public void shouldReturnThePrice() {
    //given
    BigDecimal expected = new BigDecimal("30.00");
    //when
    BigDecimal actual = invoiceEntry.getPrice();
    //then
    Assert.assertEquals(expected, actual);
  }

  @Test
  public void setPrice() {
  }

  @Test
  public void shouldReturnVatValue() {
    //given
    Vat expected = VAT_5;
    //when
    Vat actual = invoiceEntry.getVat();
    //then
    Assert.assertEquals(expected, actual);
  }

  @Test
  public void setVat() {
  }
}