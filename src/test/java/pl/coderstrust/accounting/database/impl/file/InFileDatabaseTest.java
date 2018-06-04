package pl.coderstrust.accounting.database.impl.file;

import static org.mockito.Mockito.when;
import static pl.coderstrust.accounting.model.TestInvoiceProvider.invoiceOne;
import static pl.coderstrust.accounting.model.TestInvoiceProvider.invoiceTwo;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import pl.coderstrust.accounting.database.Database;
import pl.coderstrust.accounting.database.DatabaseTest;
import pl.coderstrust.accounting.model.Invoice;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class InFileDatabaseTest extends DatabaseTest {

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
  String something = "something";
  List<String> stringsList = new ArrayList<>(Arrays.asList(anything, something));

  @InjectMocks
  InFileDatabase inFileDatabase;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Override
  protected Database getDatabase() {
    cleanUp();
    when(configuration.getFileName()).thenReturn(dbFileName);
    when(configuration.getIdNumberFileName()).thenReturn(idFileName);
    //    when(fileHelper.appendLine(anything, dbFileName)).thenReturn(invoiceOne);
    return inFileDatabase;
  }

  @After
  public void cleanUp() {
    File dbFile = new File(dbFileName);
    if (dbFile.exists()) {
      dbFile.delete();
    }
    File idFile = new File(idFileName);
    if (idFile.exists()) {
      idFile.delete();
    }
  }
}
