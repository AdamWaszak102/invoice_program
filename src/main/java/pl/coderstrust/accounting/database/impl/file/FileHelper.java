package pl.coderstrust.accounting.database.impl.file;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileHelper {

  private File tempFile = new File("temporaryFile.json");

  public void writeJsonInvoiceToFile(String jsonAsString, String fileName) {
    try {
      FileWriter fileWriter = new FileWriter(fileName, true);
      try (BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
        bufferedWriter.append(jsonAsString);
        bufferedWriter.append(",");
        bufferedWriter.newLine();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public List<String> printJsonInvoiceAsString(String fileName) {
    List<String> jsonArray = new ArrayList<>();
    try {
      FileReader fileReader = new FileReader(fileName);
      try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {
        String currentLine;
        while ((currentLine = bufferedReader.readLine()) != null) {
          System.out.println(currentLine);
          jsonArray.add(currentLine);
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    return jsonArray;
  }

  public List<String> readAllJsonFiles(String fileName) {
    List<String> allInvoicesInJson = new ArrayList<>();
    try {
      FileReader fileReader = new FileReader(fileName);
      try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {
        String currentLine;
        while ((currentLine = bufferedReader.readLine()) != null) {
          allInvoicesInJson.add(currentLine);
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    return allInvoicesInJson;
  }

  public String readJsonFileAndFindInvoiceLineById(String fileName, Long id) {
    try {
      FileReader fileReader = new FileReader(fileName);
      try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {
        String currentLine;
        while ((currentLine = bufferedReader.readLine()) != null) {
          if (currentLine.contains("\"id\":" + id.toString() + ",")) {
            return currentLine;
          }
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    return null;
  }

  public void removeInvoiceByIdWhenReadingJsonFile(String fileName, Long id) {
    try {
      FileReader fileReader = new FileReader(fileName);
      try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(tempFile))) {
          String currentLine;
          String emptyLine = "";
          while ((currentLine = bufferedReader.readLine()) != null) {
            if (currentLine.contains("\"id\":" + id.toString() + ",")) {
              bufferedWriter.append((emptyLine));
              continue;
            }
            bufferedWriter.append(currentLine);
            bufferedWriter.newLine();
          }
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void updateInvoiceByIdWhenReadingJsonFile(String invoiceAsJsonString, String fileName,
      Long id) {
    try {
      FileReader fileReader = new FileReader(fileName);
      try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(tempFile))) {
          String currentLine;
          while ((currentLine = bufferedReader.readLine()) != null) {
            if (currentLine.contains("\"id\":" + id.toString() + ",")) {
              bufferedWriter.append((invoiceAsJsonString));
              bufferedWriter.append(",");
              bufferedWriter.newLine();
              continue;
            }
            bufferedWriter.write(currentLine);
            bufferedWriter.newLine();
          }
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public boolean fileExists(String fileName) {
    File file = new File(fileName);
    return file.exists();
  }
}