package pl.coderstrust.accounting.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.math.BigDecimal;

public class VatTest {

  @Test
  public void shouldCheckValueOfVatRate0() {
    //given
    BigDecimal expected = new BigDecimal(0);

    //when
    BigDecimal actual = Vat.VAT_0.getVatValue();

    //then
    assertEquals(expected, actual);
  }

  @Test
  public void shouldCheckValueOfVatRate5() {
    //given
    BigDecimal expected = new BigDecimal(5);

    //when
    BigDecimal actual = Vat.VAT_5.getVatValue();

    //then
    assertEquals(expected, actual);
  }

  @Test
  public void shouldCheckValueOfVatRate8() {
    //given
    BigDecimal expected = new BigDecimal(8);

    //when
    BigDecimal actual = Vat.VAT_8.getVatValue();

    //then
    assertEquals(expected, actual);
  }

  @Test
  public void shouldCheckValueOfVatRate23() {
    //given
    BigDecimal expected = new BigDecimal(23);

    //when
    BigDecimal actual = Vat.VAT_23.getVatValue();

    //then
    assertEquals(expected, actual);
  }
}

