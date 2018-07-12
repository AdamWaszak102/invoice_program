package pl.coderstrust.accounting;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import pl.coderstrust.accounting.model.Invoice;

@Configuration
public class SpringConfiguration {

  @Primary
  @Bean
  public ObjectMapper objectMapper() {
    ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule())
        .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
        .configure(DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS, true);
    return objectMapper;
  }

  @Primary
  @Bean
  public MongoDatabase database() {
    CodecRegistry pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
        fromProviders(PojoCodecProvider.builder().automatic(true).build()));
    MongoClient mongo = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
    MongoDatabase database = mongo.getDatabase("invoice")
        .withCodecRegistry(pojoCodecRegistry);
    return database;
  }

  @Primary
  @Bean
  public MongoCollection<Invoice> collection() {
    MongoDatabase database = database();
    MongoCollection<Invoice> collection = database
        .getCollection("MongoInvoices", Invoice.class);
    return collection;
  }
}
