package pl.coderstrust.accounting.database.impl.file;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static pl.coderstrust.accounting.model.TestInvoiceProvider.invoiceOne;
import static pl.coderstrust.accounting.model.TestInvoiceProvider.invoiceTwo;

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
  Invoice invoiceTwo = invoiceTwo();
  List<Invoice> invoicesList = new ArrayList<>(Arrays.asList(invoiceOne, invoiceTwo));
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

  @Test(expected = Exception.class)
  public void shouldCheckThatJsonHelperConvertsInvoiceToJsonStringWhenInvoiceIsNotProvided()
      throws Exception {
    //given
    Invoice invoiceThree = mock(Invoice.class);

    //when
    given(jsonHelper.convertInvoiceToJsonString(any(Invoice.class)))
        .willThrow(new Exception("Error parsing the object to json string."));

    //then
    jsonHelper.convertInvoiceToJsonString(invoiceThree);

  }

  @Test
  public void shouldCheckThatJsonHelperConvertsJsonStringsListToListOfInvoices()
      throws Exception {
    //given

    //when
    when(jsonHelper.convertJsonStringsListToListOfInvoices(invoicesInJsonList))
        .thenReturn(invoicesList);

    //then
    for (String invoiceInString : invoicesInJsonList) {
      verify(readingMapper, times(2)).readValue(invoiceLine, Invoice.class);
    }
  }

  @Test
  public void shouldCheckThatJsonHelperConvertsInvoiceInJsonStringToInvoice()
      throws Exception {
    //given

    //when
    jsonHelper.returnInvoiceById(invoiceLine);

    //then
    verify(readingMapper).readValue(invoiceLine, Invoice.class);
  }

  @Test(expected = IOException.class)
  public void shouldCheckThatJsonHelperThrowsExceptionWhenInvoiceInJsonNotProvided()
      throws Exception {
    //given
    String anything = "anything";

    //when
    given(jsonHelper.returnInvoiceById(anything))
        .willThrow((new IOException()));

    //then
    jsonHelper.returnInvoiceById(anything);
  }
}