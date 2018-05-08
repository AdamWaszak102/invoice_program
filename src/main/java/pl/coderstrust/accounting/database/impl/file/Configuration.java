package pl.coderstrust.accounting.database.impl.file;

import java.io.File;

public class Configuration {

  private String fileName;

  private File idNumber = new File("idNumber.txt");

  public Configuration(String fileName) {
    this.fileName = fileName;
  }

  public Configuration() {
    this("allInvoices.json");
  }

  public String getFileName() {
    return fileName;
  }

  public File getIdFileName() {
    return idNumber;
  }
}
