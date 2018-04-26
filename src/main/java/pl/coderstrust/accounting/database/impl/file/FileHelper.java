package pl.coderstrust.accounting.database.impl.file;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileHelper {

  public void writeJsonInvoiceToFile(String jsonAsString, String fileName) {
    try {
      FileWriter fileWriter = new FileWriter(fileName, true);
      BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
      bufferedWriter.append(jsonAsString);
      bufferedWriter.append(",");
      bufferedWriter.newLine();
      bufferedWriter.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  public static boolean fileExists (String fileName) {
    File file = new File(fileName);
    return file.exists();
  }
}