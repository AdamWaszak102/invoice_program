package pl.coderstrust.accounting.database.impl.file;

public class Configuration {
  //file paths should be in Configuration class
  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  private String fileName;  //= "allInvoices.json";
}
