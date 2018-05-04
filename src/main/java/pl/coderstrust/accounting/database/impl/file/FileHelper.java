package pl.coderstrust.accounting.database.impl.file;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileHelper {

  private File tempFile = new File("temporaryFile.json");

  public void appendLine(String line, String fileName) {
    try (
        FileWriter fileWriter = new FileWriter(fileName, true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
      bufferedWriter.append(line);
      bufferedWriter.append(",");
      bufferedWriter.newLine();
    } catch (
        IOException error) {
      error.printStackTrace();
    }
  }

  public void writeListToTheFile(List<String> stringList, String fileName) {
    try (
        FileWriter fileWriter = new FileWriter(fileName, false);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)
    ) {
      for (String line : stringList) {
        bufferedWriter.append(line);
        bufferedWriter.append(",");
        bufferedWriter.newLine();
      }

    } catch (IOException error) {
      error.printStackTrace();
    }
  }

  public List<String> readLines(String fileName) {
    List<String> lines = new ArrayList<>();
    try (
        FileReader fileReader = new FileReader(fileName);
        BufferedReader bufferedReader = new BufferedReader(fileReader)
    ) {
      String currentLine;
      while ((currentLine = bufferedReader.readLine()) != null) {
        lines.add(currentLine);
      }
    } catch (IOException exception) {
      exception.printStackTrace();
    }
    return lines;
  }

  public String readJsonFileAndFindInvoiceLineById(String fileName, Long id) {
    List<String> allInvoicesInJson = readLines(fileName);
    return allInvoicesInJson
        .stream()
        .filter(x -> x.contains("\"id\":" + id.toString() + ","))
        .findFirst()
        .orElse(null);
  }

  public void removeInvoiceByIdWhenReadingJsonFile(String fileName, Long id) {
    try (
        FileReader fileReader = new FileReader(fileName);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(tempFile))) {
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
    } catch (IOException exception) {
      exception.printStackTrace();
    }
  }

  public void updateLineWithContentWhenReadingJsonFile(String invoiceAsJsonString, String fileName,
      Long id) {
    try (
        FileReader fileReader = new FileReader(fileName);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(tempFile))) {
      String currentLine;
      while ((currentLine = bufferedReader.readLine()) != null) {
        if (currentLine.contains("\"id\":" + id + ",")) {
          bufferedWriter.append((invoiceAsJsonString));
          bufferedWriter.append(",");
          bufferedWriter.newLine();
          continue;
        }
        bufferedWriter.write(currentLine);
        bufferedWriter.newLine();
      }
    } catch (IOException exception) {
      exception.printStackTrace();
    }
  }
}