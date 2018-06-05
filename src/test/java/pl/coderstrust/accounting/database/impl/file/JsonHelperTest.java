package pl.coderstrust.accounting.database.impl.file;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static pl.coderstrust.accounting.model.TestInvoiceProvider.emptyInvoice;
import static pl.coderstrust.accounting.model.TestInvoiceProvider.invoiceOne;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pl.coderstrust.accounting.model.Invoice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class JsonHelperTest {

  @Mock
  ObjectMapper objectMapper;

  Invoice invoiceOne = invoiceOne();
  Invoice invoiceThree = emptyInvoice();
  String anything = "anything";
  String something = "something";
  List<String> stringsList = new ArrayList<>(Arrays.asList(anything, something));

  @InjectMocks
  JsonHelper jsonHelper;

  @Test
  public void shouldCheckThatJsonHelperConvertsInvoiceToJsonString()
      throws Exception {
    //given
    String expected = anything;
    when(objectMapper.writeValueAsString(invoiceThree)).thenReturn(anything);

    //when
    String actual = jsonHelper.convertInvoiceToJsonString(invoiceThree);

    //then
    verify(objectMapper).writeValueAsString(invoiceThree);
    assertEquals(expected, actual);
  }

  @Test
  public void shouldCheckThatJsonHelperConvertsJsonStringsListToListOfInvoices()
      throws Exception {
    //given
    List<Invoice> expected = new ArrayList<>(Arrays.asList(invoiceOne, invoiceOne));
    for (String invoiceInString : stringsList) {
      when(objectMapper.readValue(invoiceInString, Invoice.class))
          .thenReturn(invoiceOne);
    }

    //when
    List<Invoice> actual = jsonHelper.convertJsonStringsListToListOfInvoices(stringsList);

    //then
    for (String invoiceInString : stringsList) {
      verify(objectMapper).readValue(invoiceInString, Invoice.class);
    }
    assertEquals(expected, actual);
  }

  @Test(expected = IOException.class)
  public void shouldCheckThatObjectMapperThrowsIOExceptionWhenStringsListProvided()
      throws Exception {
    //given
    when(objectMapper.readValue(anything, Invoice.class)).thenThrow(new IOException());

    //when
    jsonHelper.convertJsonStringsListToListOfInvoices(stringsList);

    //then
    verify(objectMapper.readValue(anything, Invoice.class));
  }

  @Test
  public void shouldCheckThatJsonHelperConvertsInvoiceInJsonStringToInvoice()
      throws Exception {
    //given
    when(objectMapper.readValue(anything, Invoice.class))
        .thenReturn(invoiceOne);

    //when
    Invoice actual = jsonHelper.returnInvoiceById(anything);

    //then
    verify(objectMapper).readValue(anything, Invoice.class);
    assertEquals(invoiceOne, actual);
  }

  @Test(expected = IOException.class)
  public void shouldCheckThatObjectMapperThrowsIOException()
      throws Exception {
    //given
    when(objectMapper.readValue(anything, Invoice.class)).thenThrow(new IOException());

    //when
    jsonHelper.returnInvoiceById(anything);

    //then
    verify(objectMapper.readValue(anything, Invoice.class));
  }
}