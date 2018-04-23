package pl.coderstrust.accounting.database.impl.file;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

  public static void main(String[] args) throws IOException {
//    Invoice invoice = new Invoice(1L, "234",  LocalDate.of(2015, 1, 1),"Ah", "Wa","{\"name\":\"Antek\",\"age\":20}" );
    ObjectMapper mapper = new ObjectMapper();
    Inv obj = new Inv();
//    Invoice obj = new Invoice();

    mapper.writeValue(new FileWriter("file.json", true), obj);

//    System.lineSeparator();
//  String jsonInString = mapper.writeValueAsString(obj);System.getProperty("line.separator") +

    Inv obj2 = mapper.readValue(new File("file.json"), Inv.class);
    System.out.println(obj2.toString());
  }
}
