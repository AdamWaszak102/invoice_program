package pl.coderstrust.accounting.database.impl.file;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import pl.coderstrust.accounting.model.Invoice;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class JsonHelper {
  private ObjectMapper writingMapper = new ObjectMapper().registerModule(new JavaTimeModule())
      .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
  private File idNumber = new File("idNumber.txt");
  private Long id;
//  private Long id;
//  private ObjectMapper readingMapper;


//  public void setReadingMapper(ObjectMapper readingMapper) {
//    this.readingMapper = new ObjectMapper().registerModule(new JavaTimeModule())
//        .configure(DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS, true);
//  }

  public String convertInvoiceToJsonString(Invoice invoice) {
    Long id = checkId();
    System.out.println(id);
//    Long lastId = id;
    ++id;
    System.out.println(id);
    try (FileWriter fileWriter = new FileWriter("idNumber.txt")){
      fileWriter.write(id.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
//    Path path = Paths.get("idNumber.txt");
//    Charset charset = StandardCharsets.UTF_8;

//    try {
//      Long newId = new Long(Files.readAllBytes(path), charset);
//    } catch (IOException e) {
//      e.printStackTrace();
//    }
//    content = content.replaceAll("foo", "bar");
//    Files.write(path, content.getBytes(charset));
//    try (Scanner scanner = new Scanner(idNumber)) {
//      while (scanner.hasNextLong()) {
//        id.   ( "textFiles/a.txt", "something/bob.txt" );;
//      }
//    } catch (FileNotFoundException e) {
//      e.printStackTrace();
//    } catch (FileNotFoundException e) {
//      e.printStackTrace();
//    }
    try {
      invoice.setId(id);
      return writingMapper.writeValueAsString(invoice);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
    return "";
  }
  public Long checkId(){
    try (Scanner scanner = new Scanner(idNumber)) {
      while (scanner.hasNextLong()) {
        id = scanner.nextLong();
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    return id;
  }
}
