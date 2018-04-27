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
    BigDecimal actual = Vat.VAT_0.getVatValue(0);

    //then
    assertEquals(expected, actual);
  }

  @Test
  public void shouldCheckValueOfVatRateZERO() {
    //given
    BigDecimal expected = BigDecimal.ZERO;

    //when
    BigDecimal actual = Vat.VAT_0.getVatValue(0);

    //then
    assertEquals(expected, actual);
  }

  @Test
  public void shouldCheckValueOfVatRate5() {
    //given
    BigDecimal expected = new BigDecimal(5);

    //when
    BigDecimal actual = Vat.VAT_5.getVatValue(5);

    //then
    assertEquals(expected, actual);
  }
}

