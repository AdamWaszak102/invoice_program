package pl.coderstrust.accounting.database.impl.file;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static pl.coderstrust.accounting.model.TestInvoiceProvider.emptyInvoice;
import static pl.coderstrust.accounting.model.TestInvoiceProvider.invoiceOne;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import pl.coderstrust.accounting.model.Invoice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class JsonHelperTest {

  @Mock
  ObjectMapper writingMapper;

  @Mock
  ObjectMapper readingMapper;

  Invoice invoiceOne = invoiceOne();
  Invoice invoiceThree = emptyInvoice();
  String invoiceLine = "{\"id\":1,\"identifier\":\"FV 2/2018\",\"issueDate\":\"2018-03-02"
      + "\",\"buyer\":{\"companyName\":\"Bravecto\",\"address\":\"Matuszewska 14, 25-022 Kielce"
      + "\",\"taxIdentificationNumber\":4443322000},\"seller\":{\"companyName\":"
      + "\"Proactive Investment Sp. z o.o.\",\"address\":\"Wiejska 17, 00-001 Mysiadło\","
      + "\"taxIdentificationNumber\":5218774000},\"entries\":[{\"description"
      + "\":\"Fruit and vegetable processing\",\"price\":2500,\"vat\":\"VAT_5\"}]},";
  List<String> invoicesInJsonList = new ArrayList<>(Arrays.asList(invoiceLine,
      "{\"id\":1,\"identifier\":\"FV 2/2018\",\"issueDate\":\"2018-03-02\",\"buyer"
          + "\":{\"companyName\":\"Bravecto\",\"address\":\"Matuszewska 14, 25-022 Kielce"
          + "\",\"taxIdentificationNumber\":4443322000},\"seller\":{\"companyName"
          + "\":\"Proactive Investment Sp. z o.o.\",\"address\":\"Wiejska 17, 00-001 Mysiadło"
          + "\",\"taxIdentificationNumber\":5218774000},\"entries\":[{\"description"
          + "\":\"Fruit and vegetable processing\",\"price\":2500,\"vat\":\"VAT_5\"}]},",
      "{\"id\":1,\"identifier\":\"FV 2/2018\",\"issueDate\":\"2018-03-02\",\"buyer"
          + "\":{\"companyName\":\"Bravecto\",\"address\":\"Matuszewska 14, 25-022 Kielce"
          + "\",\"taxIdentificationNumber\":4443322000},\"seller\":{\"companyName"
          + "\":\"Proactive Investment Sp. z o.o.\",\"address\":\"Wiejska 17, 00-001 Mysiadło"
          + "\",\"taxIdentificationNumber\":5218774000},\"entries\":[{\"description\":"
          + "\"Fruit and vegetable processing\",\"price\":2500,\"vat\":\"VAT_5\"}]},"));
  String anything = "anything";

  @InjectMocks
  JsonHelper jsonHelper;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void shouldCheckThatJsonHelperConvertsInvoiceToJsonStringWhenInvoiceProvided()
      throws Exception {
    //given

    //when
    jsonHelper.convertInvoiceToJsonString(invoiceOne);

    //then
    verify(writingMapper).writeValueAsString(invoiceOne);
  }

  @Test
  public void shouldCheckThatJsonHelperConvertsInvoiceToJsonStringWhenInvoiceIsNotProvided()
      throws Exception {
    //given
    String expected = "";
    when(writingMapper.writeValueAsString(invoiceThree)).thenReturn("");

    //when
    String actual = jsonHelper.convertInvoiceToJsonString(invoiceThree);

    //then
    verify(writingMapper).writeValueAsString(invoiceThree);
    assertEquals(expected, actual);
  }

  @Test
  public void shouldCheckThatJsonHelperConvertsJsonStringsListToListOfInvoices()
      throws Exception {
    //given
    List<Invoice> expected = new ArrayList<>(Arrays.asList(invoiceOne, invoiceOne, invoiceOne));
    for (String invoiceInString : invoicesInJsonList) {
      when(readingMapper.readValue(invoiceInString, Invoice.class))
          .thenReturn(invoiceOne);
    }

    //when
    List<Invoice> actual = jsonHelper.convertJsonStringsListToListOfInvoices(invoicesInJsonList);

    //then
    verify(readingMapper, times(3)).readValue(invoiceLine, Invoice.class);
    assertEquals(expected, actual);

  }

  @Test(expected = IOException.class)
  public void shouldCheckThatJsonHelperThrowsExceptionWhenWrongListOfInvoicesInJsonProvided()
      throws Exception {
    //given
    List<String> someThings = new ArrayList<>(Arrays.asList(""));
    when(readingMapper.readValue(anything, Invoice.class)).thenThrow(new IOException());

    //when
    jsonHelper.convertJsonStringsListToListOfInvoices(someThings);

    //then
    verify(readingMapper.readValue(anything, Invoice.class));
  }

  @Test
  public void shouldCheckThatJsonHelperConvertsInvoiceInJsonStringToInvoice()
      throws Exception {
    //given
    when(readingMapper.readValue(invoiceLine, Invoice.class))
        .thenReturn(invoiceOne);

    //when
    Invoice actual = jsonHelper.returnInvoiceById(invoiceLine);

    //then
    verify(readingMapper).readValue(invoiceLine, Invoice.class);
    assertEquals(invoiceOne, actual);
  }

  @Test(expected = IOException.class)
  public void shouldCheckThatJsonHelperThrowsExceptionWhenInvoiceInJsonNotProvided()
      throws Exception {
    //given
    String anything = "anything";
    when(readingMapper.readValue(anything, Invoice.class)).thenThrow(new IOException());

    //when
    jsonHelper.returnInvoiceById(anything);

    //then
    verify(readingMapper.readValue(anything, Invoice.class));
  }
}