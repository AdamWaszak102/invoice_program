package pl.coderstrust.accounting.database.impl.file;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static pl.coderstrust.accounting.model.TestInvoiceProvider.invoiceOne;
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
  Invoice invoiceTwo = invoiceTwo();
  List<Invoice> invoicesList = new ArrayList<>(Arrays.asList(invoiceOne, invoiceTwo, invoiceTwo));
  String anything = "anything";
  String something = "something";
  List<String> invoicesListInString = new ArrayList<>(
      Arrays.asList(anything, something, something));

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
    verify(fileHelper).appendLine(anything, dbFileName);
    assertEquals((Long) (previousId + 1L), result);
  }

  @Test
  public void shouldSaveListOfThreeInvoicesAndReturnId() {
    //given
    Long id = 135L;
    when(fileHelper.readNumberFromFile(idFileName)).thenReturn(id);
    List<Long> ids = new ArrayList<>(Arrays.asList(136L, 137L, 138L));
    when(jsonHelper.convertInvoiceToJsonString(invoiceOne)).thenReturn(anything);
    when(jsonHelper.convertInvoiceToJsonString(invoiceTwo)).thenReturn(something);

    //when
    List<Long> result = inFileDatabase.saveInvoices(invoicesList);

    //then
    assertEquals(ids.size(), result.size());
    verify(fileHelper).writeListToFile(invoicesListInString, dbFileName, true);
    verify(fileHelper, times(3)).readNumberFromFile(idFileName);
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
        .removeLineWithContentWhenReadingJsonFile(dbFileName,
            content + id + ",");
  }

  @Test
  public void shouldReadThreeInvoices() {
    //given
    when(fileHelper.readLines(dbFileName)).thenReturn(invoicesListInString);

    //when
    inFileDatabase.getInvoices();

    //then
    verify(fileHelper).readLines(dbFileName);
    verify(jsonHelper).convertJsonStringsListToListOfInvoices(invoicesListInString);
  }

  @Test
  public void shouldReadOneInvoice() {
    //given
    Long id = 32L;
    when(fileHelper.readJsonFileAndFindInvoiceLineById(dbFileName, "\"id\":" + id + ","))
        .thenReturn(anything);

    //when
    inFileDatabase.getInvoiceById(id);

    //then
    verify(fileHelper)
        .readJsonFileAndFindInvoiceLineById(dbFileName, "\"id\":" + id + ",");
    verify(jsonHelper).convertJsonStringToInvoice(anything);
  }

  @Test
  public void shouldUpdateInvoice() {
    //given
    Long id = 0L;
    when(jsonHelper.convertInvoiceToJsonString(invoiceOne)).thenReturn(something);

    //when
    inFileDatabase.updateInvoice(invoiceOne);

    //then
    verify(jsonHelper).convertInvoiceToJsonString(invoiceOne);
    verify(fileHelper).updateLineWithContentWhenReadingJsonFile(something, dbFileName,
        "\"id\":" + id + ",");
  }
}
