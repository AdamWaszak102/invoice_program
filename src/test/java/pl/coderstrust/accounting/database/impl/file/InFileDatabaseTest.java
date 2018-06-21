package pl.coderstrust.accounting.database.impl.file;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static pl.coderstrust.accounting.model.TestInvoiceProvider.invoiceOne;
import static pl.coderstrust.accounting.model.TestInvoiceProvider.invoiceOneModified;
import static pl.coderstrust.accounting.model.TestInvoiceProvider.invoiceTwo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pl.coderstrust.accounting.model.Invoice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class InFileDatabaseTest {

  private static final String dbFileName = "test.json";
  private static final String idFileName = "idTest.txt";

  @Mock
  private Configuration configuration;

  @Mock
  private JsonHelper jsonHelper;

  @Mock
  private FileHelper fileHelper;

  Invoice invoiceOne = invoiceOne();
  Invoice invoiceOneModified = invoiceOneModified();
  Invoice invoiceTwo = invoiceTwo();
  List<Invoice> invoicesList = new ArrayList<>(Arrays.asList(invoiceOne, invoiceTwo, invoiceTwo));
  String anything = "anything";
  String something = "something";

  @InjectMocks
  InFileDatabase inFileDatabase;

  @Before
  public void initialise() {
    when(configuration.getFileName()).thenReturn(dbFileName);
    when(configuration.getIdNumberFileName()).thenReturn(idFileName);
  }

  @Test
  public void shouldCheckIdNumber() {
    //given
    Long previousId = 153L;
    when(fileHelper.readNumberFromFile(idFileName)).thenReturn(previousId);

    //when
    inFileDatabase.saveInvoice(invoiceOne);

    //then
    verify(fileHelper).writeNumberToFile(previousId + 1L, idFileName);
  }

  @Test
  public void shouldSaveInvoiceAndReturnId() {
    //given
    Long previousId = 153L;
    when(fileHelper.readNumberFromFile(idFileName)).thenReturn(previousId);
    when(jsonHelper.convertInvoiceToJsonString(invoiceOne)).thenReturn(anything);

    //when
    Long result = inFileDatabase.saveInvoice(invoiceOne);

    //then
    verify(fileHelper)
        .appendLine(jsonHelper.convertInvoiceToJsonString(invoiceOne), configuration.getFileName());
    verify(fileHelper).appendLine(anything, dbFileName);
    assertEquals((Long) (previousId + 1L), result);
  }

  @Test
  public void shouldSaveListOfThreeInvoicesAndReturnId() {
    //given
    Long id = 135L;
    List<String> invoicesListInStrings = new ArrayList<>();
    List<Long> ids = new ArrayList<>();
    when(jsonHelper.convertInvoiceToJsonString(invoiceOne)).thenReturn(anything);
    when(jsonHelper.convertInvoiceToJsonString(invoiceTwo)).thenReturn(something);
    when(fileHelper.readNumberFromFile(idFileName)).thenReturn(id);
    for (Invoice invoice : invoicesList) {
      Long currentId = id + 1L;
      ids.add(currentId);
      invoicesListInStrings.add(jsonHelper.convertInvoiceToJsonString(invoice));
      id++;
    }

    //when
    List<Long> result = inFileDatabase.saveInvoices(invoicesList);

    //then
    assertEquals(ids.get(0), result.get(0));
    verify(jsonHelper, times(2)).convertInvoiceToJsonString(invoiceOne);
    verify(fileHelper).writeListToFile(invoicesListInStrings, configuration.getFileName(), true);
    verify(fileHelper, times(3)).readNumberFromFile(configuration.getIdNumberFileName());
  }

  @Test
  public void shouldSaveAndRemoveOneInvoice() {
    //given
    Long id = 100L;
    when(fileHelper.readNumberFromFile(idFileName)).thenReturn(id);
    inFileDatabase.saveInvoice(invoiceOne);
    id++;
    String content = "\"id\":";

    //when
    inFileDatabase.removeInvoiceById(id);

    //then
    verify(fileHelper)
        .removeLineWithContentWhenReadingJsonFile(configuration.getFileName(),
            content + id + ",");
  }

  @Test
  public void shouldSave3InvoicesAndReadThem() {
    //given
    inFileDatabase.saveInvoices(invoicesList);

    //when
    inFileDatabase.getInvoices();

    //then
    verify(fileHelper).readLines(configuration.getFileName());
  }

  @Test
  public void shouldSaveAndReadInvoiceById() {
    //given
    Long id = 32L;
    when(fileHelper.readNumberFromFile(configuration.getIdNumberFileName())).thenReturn(id);
    inFileDatabase.saveInvoice(invoiceOne);

    //when
    inFileDatabase.getInvoiceById(id);

    //then
    verify(fileHelper)
        .readJsonFileAndFindInvoiceLineById(configuration.getFileName(), "\"id\":" + id + ",");
    verify(jsonHelper)
        .convertJsonStringToInvoice(jsonHelper.convertInvoiceToJsonString(invoiceOne));
  }

  @Test
  public void shouldSaveAndUpdateInvoice() {
    //given
    Long id = 144L;
    when(fileHelper.readNumberFromFile(configuration.getIdNumberFileName())).thenReturn(id);
    inFileDatabase.saveInvoice(invoiceOne);
    invoiceOneModified.setId(id + 1L);
    id++;

    //when
    inFileDatabase.updateInvoice(invoiceOneModified);

    //then
    verify(jsonHelper).convertInvoiceToJsonString(invoiceOneModified);
    verify(fileHelper).updateLineWithContentWhenReadingJsonFile(jsonHelper
            .convertInvoiceToJsonString(invoiceOneModified), configuration.getFileName(),
        "\"id\":" + id + ",");
  }
}
