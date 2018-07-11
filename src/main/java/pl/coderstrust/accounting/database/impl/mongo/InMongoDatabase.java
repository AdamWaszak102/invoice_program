package pl.coderstrust.accounting.database.impl.mongo;

import static com.mongodb.client.model.Filters.in;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;
import pl.coderstrust.accounting.database.Database;
import pl.coderstrust.accounting.model.Invoice;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@ConditionalOnProperty(value = "inMongoDatabase.enabled", havingValue = "true")
@Repository
public class InMongoDatabase implements Database {

  private CodecRegistry pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
      fromProviders(PojoCodecProvider.builder().automatic(true).build()));
  private MongoClient mongo = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
  private MongoDatabase database = mongo.getDatabase("invoice")
      .withCodecRegistry(pojoCodecRegistry);
  private MongoCollection<Invoice> collection = database
      .getCollection("MongoInvoices", Invoice.class);
  private static final String idFileName = "idMongo.txt";

  @Override
  public Long saveInvoice(Invoice invoice) {
    getIdFromFileAndSaveItBack(invoice);
    collection.insertOne(invoice);
    return invoice.getId();
  }

  @Override
  public List<Long> saveInvoices(List<Invoice> invoices) {
    List<Long> ids = new ArrayList<>();
    for (Invoice invoice : invoices) {
      getIdFromFileAndSaveItBack(invoice);
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

  private synchronized void getIdFromFileAndSaveItBack(Invoice invoice) {
    Long id = 0L;
    File file = new File(idFileName);
    if (file.exists()) {
      try (Scanner scanner = new Scanner(file)) {
        while (scanner.hasNextLong()) {
          id = scanner.nextLong();
        }
      } catch (FileNotFoundException exception) {
        exception.printStackTrace();
      }
    }
    invoice.setId(++id);
    try (FileWriter fileWriter = new FileWriter(file)) {
      fileWriter.write(id.toString());
    } catch (IOException exception) {
      exception.printStackTrace();
    }
  }
}
