package pl.coderstrust.accounting.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CompanyTest {

  private Company company = new Company("Poczta kwiatowa", "Bluszczańska 12", 1234567890L);

  @Test
  public void shouldReturnCompanyName() {
    //given
    String expected = "Poczta kwiatowa";

    //when
    String actual = company.getCompanyName();

    //then
    assertEquals(expected, actual);
  }
}