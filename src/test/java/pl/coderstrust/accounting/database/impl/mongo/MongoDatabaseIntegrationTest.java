package pl.coderstrust.accounting.database.impl.mongo;

import static org.junit.Assert.assertEquals;
import static pl.coderstrust.accounting.model.TestInvoiceProvider.invoiceOne;
import static pl.coderstrust.accounting.model.TestInvoiceProvider.invoiceTwo;

import com.mongodb.client.MongoCollection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.coderstrust.accounting.model.Invoice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MongoDatabaseIntegrationTest {

  @Autowired
  private MongoDatabase db;

  @Autowired
  MongoCollection<Invoice> collection;

  protected MongoCollection<Invoice> getCollection() {
    return collection;
  }

  @Test
  public void shouldSaveTwoInvoicesSeparately() {
    //given
    Invoice invoiceOne = invoiceOne();
    Invoice invoiceTwo = invoiceTwo();
    MongoCollection<Invoice> collection = getCollection();
    int initialInvoicesNumber = db.getInvoices().size();

    //when
    db.saveInvoice(invoiceOne);
    db.saveInvoice(invoiceTwo);

    //then
    assertEquals(initialInvoicesNumber + 2, db.getInvoices().size());
  }

  @Test
  public void shouldSaveListOfTwoInvoices() {
    //given
    Invoice invoiceOne = invoiceOne();
    Invoice invoiceTwo = invoiceTwo();
    List<Invoice> invoicesList = new ArrayList<>(Arrays.asList(invoiceOne, invoiceTwo));
    MongoCollection<Invoice> collection = getCollection();
    int initialInvoicesNumber = db.getInvoices().size();

    //when
    db.saveInvoices(invoicesList);

    //then
    assertEquals(initialInvoicesNumber + 2, db.getInvoices().size());
  }

  @Test
  public void shouldSaveAndRemoveOneInvoice() {
    //given
    Invoice invoiceOne = invoiceOne();
    MongoCollection<Invoice> collection = getCollection();
    int initialInvoicesNumber = db.getInvoices().size();

    //when
    Long id = db.saveInvoice(invoiceOne);
    db.removeInvoiceById(id);

    //then
    assertEquals(initialInvoicesNumber, db.getInvoices().size());
  }

  @Test
  public void shouldGetInvoiceById() {
    //given
    Invoice expected = invoiceTwo();
    MongoCollection<Invoice> collection = getCollection();
    Long id = db.saveInvoice(expected);

    //when
    Invoice actual = db.getInvoiceById(id);

    //then
    assertEquals(actual, expected);
  }

  @Test
  public void shouldSaveAndUpdateInvoice() {
    //given
    Invoice beforeUpdate = invoiceOne();
    Invoice afterUpdate = invoiceOne();
    MongoCollection<Invoice> collection = getCollection();
    afterUpdate.setIdentifier("FV 5/2018");

    //when
    Long id = db.saveInvoice(beforeUpdate);
    afterUpdate.setId(id);
    db.updateInvoice(afterUpdate);
    Invoice actual = db.getInvoiceById(beforeUpdate.getId());

    //then
    assertEquals(afterUpdate, actual);
  }
}