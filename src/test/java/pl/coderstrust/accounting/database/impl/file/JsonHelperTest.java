package pl.coderstrust.accounting.database.impl.file;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class JsonHelperTest {

  @Mock
  ObjectMapper writingMapper;

  @Mock
  ObjectMapper readingMapper;

  Invoice invoice = invoiceOne();
  List<String> invoicesInJsonList = new ArrayList<>(Arrays.asList("{\"id\":1,\"identifier\":\"FV 2/2018\",\"issueDate\":\"2018-03-02\",\"buyer\":{\"companyName\":\"Bravecto\",\"address\":\"Matuszewska 14, 25-022 Kielce\",\"taxIdentificationNumber\":4443322000},\"seller\":{\"companyName\":\"Proactive Investment Sp. z o.o.\",\"address\":\"Wiejska 17, 00-001 Mysiadło\",\"taxIdentificationNumber\":5218774000},\"entries\":[{\"description\":\"Fruit and vegetable processing\",\"price\":2500,\"vat\":\"VAT_5\"}]},","{\"id\":1,\"identifier\":\"FV 2/2018\",\"issueDate\":\"2018-03-02\",\"buyer\":{\"companyName\":\"Bravecto\",\"address\":\"Matuszewska 14, 25-022 Kielce\",\"taxIdentificationNumber\":4443322000},\"seller\":{\"companyName\":\"Proactive Investment Sp. z o.o.\",\"address\":\"Wiejska 17, 00-001 Mysiadło\",\"taxIdentificationNumber\":5218774000},\"entries\":[{\"description\":\"Fruit and vegetable processing\",\"price\":2500,\"vat\":\"VAT_5\"}]},"));
  String invoiceLine = "{\"id\":1,\"identifier\":\"FV 2/2018\",\"issueDate\":\"2018-03-02\",\"buyer\":{\"companyName\":\"Bravecto\",\"address\":\"Matuszewska 14, 25-022 Kielce\",\"taxIdentificationNumber\":4443322000},\"seller\":{\"companyName\":\"Proactive Investment Sp. z o.o.\",\"address\":\"Wiejska 17, 00-001 Mysiadło\",\"taxIdentificationNumber\":5218774000},\"entries\":[{\"description\":\"Fruit and vegetable processing\",\"price\":2500,\"vat\":\"VAT_5\"}]},";

  @InjectMocks
  JsonHelper jsonHelper;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void shouldCheckThatJsonHelperConvertsInvoiceToJsonStringWhenInvoiceProvided() throws Exception {
    //given

    //when
    jsonHelper.convertInvoiceToJsonString(invoice);

    //then
    verify(writingMapper).writeValueAsString(invoice);
  }

  @Test(expected = Exception.class)
  public void shouldCheckThatJsonHelperConvertsInvoiceToJsonStringWhenInvoiceIsNotProvided() throws Exception {
    given(jsonHelper.convertInvoiceToJsonString(invoice))
        .willThrow(new Exception("Error parsing the object to json string. "));
//

  }
//  @Test(expected = JsonProcessingException.class)
//  public void shouldCheckThatJsonHelperConvertsInvoiceToJsonStringWhenInvoiceIsNotProvided() {
//    JsonHelper jsonHelperMock = mock(JsonHelper.class);
//      when(jsonHelperMock.convertInvoiceToJsonString(invoice))
//          .thenThrow(JsonProcessingException.class);
//
//      jsonHelperMock.convertInvoiceToJsonString(invoice);
//  }
//
//
  @Test
  public void shouldCheckThatJsonHelperConvertsJsonStringsListToListOfInvoices() throws Exception{
    //given

    //when
      jsonHelper.convertJsonStringsListToListOfInvoices(invoicesInJsonList);

    //then
      verify(readingMapper.readValue(invoiceLine, Invoice.class));
//    verify(readingMapper.readValue(invoiceLine, Invoice.class));
  }
//
//  @Test
//  public void shouldCheckThatJsonHelperFailsToConvertToListOfInvoicesWhenJsonStringsListNotProvided() {
//      when(jsonHelper.convertJsonStringsListToListOfInvoices(invoicesInJsonList));
//
//    try {
//      readingMapper.readValue(invoiceLine, Invoice.class);
//
//      fail("expected IOException");
//
//    } catch (IOException e) {
//      //ignore, this exception is expected.
//    }
//
//  }
}