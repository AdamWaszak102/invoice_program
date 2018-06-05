package pl.coderstrust.accounting.database.impl.file;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static pl.coderstrust.accounting.model.TestInvoiceProvider.invoiceOne;
import static pl.coderstrust.accounting.model.TestInvoiceProvider.invoiceTwo;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pl.coderstrust.accounting.database.Database;
import pl.coderstrust.accounting.model.Invoice;

import java.io.File;
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
  List<Invoice> invoicesList = new ArrayList<>(Arrays.asList(invoiceOne, invoiceTwo));
  String anything = "anything";
  String something = "something";

  @InjectMocks
  InFileDatabase inFileDatabase;

  protected Database getInFileDatabase() {
    cleanUp();
    when(configuration.getFileName()).thenReturn(dbFileName);
    when(configuration.getIdNumberFileName()).thenReturn(idFileName);
    return inFileDatabase;
  }

  @After
  public void cleanUp() {
    File dbFile = new File(dbFileName);
    ifDbFileExists(dbFile);
    File idFile = new File(idFileName);
    ifDbFileExists(idFile);
  }

  private void ifDbFileExists(File dbFile) {
    if (dbFile.exists()) {
      dbFile.delete();
    }
  }

  @Test
  public void shouldSaveAndRemoveOneInvoice() {
    //given
//    Invoice invoiceOne = invoiceOne();
    Database db = getInFileDatabase();
    int initialInvoicesNumber = db.getInvoices().size();

    //when
    Long id = db.saveInvoice(invoiceOne);
    db.removeInvoiceById(id);

    //then
    assertEquals(initialInvoicesNumber, db.getInvoices().size());
  }

  @Test
  public void shouldCheckIdNumbers() {
    //given
    Database db = getInFileDatabase();

    //when
    long idOne = db.saveInvoice(invoiceOne);
    long idTwo = db.saveInvoice(invoiceOne);
    long idThree = db.saveInvoice(invoiceOne);

    //then
    assertEquals(idOne + 1L, idTwo);
    assertEquals(idTwo + 1L, idThree);
  }

  @Test
  public void shouldSaveListOfTwoInvoices() {
    //given
    Database db = getInFileDatabase();
    List<String> invoicesListInStrings = new ArrayList<>();

    //when
    db.saveInvoices(invoicesList);

    //then
    for (Invoice invoice : invoicesList) {
      invoicesListInStrings.add(jsonHelper.convertInvoiceToJsonString(invoice));
      verify(jsonHelper, times(2)).convertInvoiceToJsonString(invoice);
    }
    verify(fileHelper)
        .writeListToFile(invoicesListInStrings, dbFileName, true);
  }

  @Test
  public void shouldSaveInvoice() {
    //given
    Database db = getInFileDatabase();

    //when
    db.saveInvoice(invoiceOne);

    //then
    verify(fileHelper)
        .appendLine(jsonHelper.convertInvoiceToJsonString(invoiceOne), configuration.getFileName());
  }

  @Test
  public void shouldGetInvoiceById() {
    //given
    Database db = getInFileDatabase();
    Long id = db.saveInvoice(invoiceOne);

    //when
    db.getInvoiceById(id);

    //then
    verify(fileHelper)
        .readJsonFileAndFindInvoiceLineById(configuration.getFileName(), "\"id\":" + id + ",");
  }

  @Test
  public void shouldSaveAndUpdateInvoice() {
    //given
    Invoice beforeUpdate = invoiceOne();
    Invoice afterUpdate = invoiceOne();
    Database db = getInFileDatabase();
    afterUpdate.setIdentifier("FV 5/2018");
    Long id = db.saveInvoice(beforeUpdate);
    afterUpdate.setId(id);

    //when
    db.updateInvoice(afterUpdate);

    //then
    verify(fileHelper, times(1))
        .updateLineWithContentWhenReadingJsonFile(jsonHelper
                .convertInvoiceToJsonString(afterUpdate), configuration.getFileName(),
            "\"id\":" + id + ",");
  }
}
