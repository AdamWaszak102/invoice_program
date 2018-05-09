package pl.coderstrust.accounting.database.impl.file;

public class Configuration {

  private String fileName;

  private String idNumberFileName;

  public Configuration(String fileName, String idNumberFileName) {
    this.fileName = fileName;
    this.idNumberFileName = idNumberFileName;
  }

  public Configuration() {
    this("allInvoices.json", "idNumber.txt");
  }

  public String getFileName() {
    return fileName;
  }

  public String getIdNumberFileName() {
    return idNumberFileName;
  }
}
