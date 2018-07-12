package pl.coderstrust.accounting.database.impl.mongo;

import static com.mongodb.client.model.Filters.in;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;
import pl.coderstrust.accounting.database.Database;
import pl.coderstrust.accounting.model.Invoice;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@ConditionalOnProperty(value = "Mongodatabase.enabled", havingValue = "true")
@Repository
public class Mongodatabase implements Database {

  private MongoDatabase database;
  private MongoCollection<Invoice> collection;
  private static final String idFileName = "idMongo.txt";

  public Mongodatabase(MongoDatabase database, MongoCollection<Invoice> collection) {
    this.database = database;
    this.collection = collection;
  }

  @Override
  public Long saveInvoice(Invoice invoice) {
    setNewId(invoice);
    collection.insertOne(invoice);
    return invoice.getId();
  }

  @Override
  public List<Long> saveInvoices(List<Invoice> invoices) {
    List<Long> ids = new ArrayList<>();
    for (Invoice invoice : invoices) {
      setNewId(invoice);
      ids.add(invoice.getId());
    }
    collection.insertMany(invoices);
    return ids;
  }

  @Override
  public Collection<Invoice> getInvoices() {
    List<Invoice> invoices = new ArrayList<>();
    FindIterable findIterable = collection.find();
    Iterator iterator = findIterable.iterator();
    while (iterator.hasNext()) {
      Invoice invoice = (Invoice) iterator.next();
      invoices.add(invoice);
    }
    return invoices;
  }

  @Override
  public Invoice getInvoiceById(Long id) {
    FindIterable findIterable = collection.find(in("_id", id));
    Iterator iterator = findIterable.iterator();
    while (iterator.hasNext()) {
      Invoice invoice = (Invoice) iterator.next();
      return invoice;
    }
    return null;
  }

  @Override
  public void updateInvoice(Invoice invoice) {
    Long currentId = Optional.ofNullable(invoice.getId())
        .orElse(0L);
    collection.replaceOne(new Document("_id", currentId), invoice);
  }

  @Override
  public void removeInvoiceById(Long id) {
    collection.deleteOne((in("_id", id)));
  }

  public void setNewId(Invoice invoice) {
    invoice.setId(Long.valueOf(ThreadLocalRandom.current().nextLong()));
  }
}
