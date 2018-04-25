package pl.coderstrust.accounting.model;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class VatTest {

  @Test
  public void shouldCheckTheValueOfTheVatRate0() {
    //given
    BigDecimal expected = new BigDecimal(0);
    //when
    BigDecimal actual = Vat.VAT_0.forCheckValue(0);
    //then
    Assert.assertEquals(expected, actual);
  }

  @Test
  public void shouldCheckTheValueOfTheVatRateZERO() {
    //given
    BigDecimal expected = BigDecimal.ZERO;
    //when
    BigDecimal actual = Vat.VAT_0.forCheckValue(0);
    //then
    Assert.assertEquals(expected, actual);
  }

  //  @Test   *****
//  public void shouldCheckTheValueOfTheVatRate5() {
//    Assert.assertEquals(Vat.VAT_5.forCheckValue(),"5");
//}

//  @Test
//  public void shouldCheckTheValueOfTheVatRate5() {
//    Vat vat = new Vat();
//    BigDecimal expected = new BigDecimal("5.0");
//    Vat actual = vatValue.VAT_5(5);
//    Assert.assertEquals(expected, actual);
//  }

  @Test
  public void shouldCheckTheValueOfTheVatRate5() {
    //given
    BigDecimal expected = new BigDecimal(5);
    //when
    BigDecimal actual = Vat.VAT_5.forCheckValue(5);
    //then
    Assert.assertEquals(expected, actual);
  }
}

