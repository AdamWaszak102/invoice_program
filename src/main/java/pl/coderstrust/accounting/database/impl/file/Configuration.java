package pl.coderstrust.accounting.database.impl.file;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Configuration {

  @Value("${fileName}")
  private String fileName;

  @Value("${idNumberFileName}")
  private String idNumberFileName;

  public String getFileName() {
    return fileName;
  }

  public String getIdNumberFileName() {
    return idNumberFileName;
  }
}
