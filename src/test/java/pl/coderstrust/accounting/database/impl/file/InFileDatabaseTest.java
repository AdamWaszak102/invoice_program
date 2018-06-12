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

  @InjectMocks
  InFileDatabase inFileDatabase;

  @Before
  public void initialise() {
    when(configuration.getFileName()).thenReturn(dbFileName);
    when(configuration.getIdNumberFileName()).thenReturn(idFileName);
  }

  //  @Test
//  public void shouldSaveAndRemoveOneInvoice() {
//    //given
////    Invoice invoiceOne = invoiceOne();
//    Database db = getInFileDatabase();
//    int initialInvoicesNumber = db.getInvoices().size();
//
//    //when
//    Long id = db.saveInvoice(invoiceOne);
//    db.removeInvoiceById(id);
//
//    //then
//    assertEquals(initialInvoicesNumber, db.getInvoices().size());
//  }
//
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

  //
  @Test
  public void shouldSaveListOfThreeInvoicesAndReturnId() {
    //given
    Long previousId = 135L;
    List<String> invoicesListInStrings = new ArrayList<>();
    List<Long> ids = new ArrayList<>();
    when(jsonHelper.convertInvoiceToJsonString(invoiceOne)).thenReturn(anything);
    when(jsonHelper.convertInvoiceToJsonString(invoiceTwo)).thenReturn(something);
    for (Invoice invoice : invoicesList) {
      Long currentId = previousId + 1L;
      ids.add(currentId);
      invoicesListInStrings.add(jsonHelper.convertInvoiceToJsonString(invoice));
      when(fileHelper.readNumberFromFile(idFileName)).thenReturn(previousId);
      previousId++;
//      verify(jsonHelper, times(3)).convertInvoiceToJsonString(invoice);
    }
    //when
    List <Long> result = inFileDatabase.saveInvoices(invoicesList);

    //then
//    verify(fileHelper)
//        .writeListToFile(invoicesListInStrings, dbFileName, true);
    verify(fileHelper)
        .appendLine(jsonHelper.convertInvoiceToJsonString(invoiceOne), configuration.getFileName());
    verify(fileHelper, times(2))
        .appendLine(jsonHelper.convertInvoiceToJsonString(invoiceTwo), configuration.getFileName());
    verify(fileHelper).appendLine(anything, dbFileName);
    verify(fileHelper, times(2)).appendLine(anything, dbFileName);
    assertEquals(ids, result);
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
//
//  @Test
//  public void shouldGetInvoiceById() {
//    //given
//    Database db = getInFileDatabase();
//    Long id = db.saveInvoice(invoiceOne);
//
//    //when
//    db.getInvoiceById(id);
//
//    //then
//    verify(fileHelper)
//        .readJsonFileAndFindInvoiceLineById(configuration.getFileName(), "\"id\":" + id + ",");
//  }
//
//  @Test
//  public void shouldSaveAndUpdateInvoice() {
//    //given
//    Invoice beforeUpdate = invoiceOne();
//    Invoice afterUpdate = invoiceOne();
//    Database db = getInFileDatabase();
//    afterUpdate.setIdentifier("FV 5/2018");
//    Long id = db.saveInvoice(beforeUpdate);
//    afterUpdate.setId(id);
//
//    //when
//    db.updateInvoice(afterUpdate);
//
//    //then
//    verify(fileHelper, times(1))
//        .updateLineWithContentWhenReadingJsonFile(jsonHelper
//                .convertInvoiceToJsonString(afterUpdate), configuration.getFileName(),
//            "\"id\":" + id + ",");
//  }
}
