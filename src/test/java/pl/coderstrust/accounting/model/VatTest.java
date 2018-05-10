package pl.coderstrust.accounting.model;

import static org.junit.Assert.assertEquals;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.math.BigDecimal;

@RunWith(JUnitParamsRunner.class)
public class VatTest {

  @Test
  @Parameters({"VAT_0, 0", "VAT_5, 5", "VAT_8, 8", "VAT_23, 23"})
  public void shouldCheckValueOfVatRate(Vat vat, BigDecimal expected) {
    //given
    //when
    BigDecimal actual = vat.getVatValue();

    //then
    assertEquals(expected, actual);
  }
}