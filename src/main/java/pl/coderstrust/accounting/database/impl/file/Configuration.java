package pl.coderstrust.accounting.database.impl.file;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Configuration {

  @Value("${fileName}")
  private String fileName;

  @Value("${idNumberFileName}")
  private String idNumberFileName;

  public Configuration(String fileName, String idNumberFileName) {
    this.fileName = fileName;
    this.idNumberFileName = idNumberFileName;
  }

  public Configuration() {}

  public String getFileName() {
    return fileName;
  }

  public String getIdNumberFileName() {
    return idNumberFileName;
  }
}
