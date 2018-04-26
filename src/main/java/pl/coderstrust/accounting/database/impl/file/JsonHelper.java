package pl.coderstrust.accounting.database.impl.file;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import pl.coderstrust.accounting.model.Invoice;

public class JsonHelper {

  public String convertInvoiceToJsonString(Invoice invoice){
    ObjectMapper mapper = new ObjectMapper();
    mapper.registerModule(new JavaTimeModule());
    mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    try {
      return mapper.writeValueAsString(invoice);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
    return "";
  }
}
