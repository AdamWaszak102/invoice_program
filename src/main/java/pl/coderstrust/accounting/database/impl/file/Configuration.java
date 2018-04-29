package pl.coderstrust.accounting.database.impl.file;

public class Configuration {

  private String fileName;

  public Configuration(String fileName) {
    this.fileName = fileName;
  }

  public Configuration() {
    this("allInvoices.json");
  }

  public String getFileName() {
    return fileName;
  }
}
