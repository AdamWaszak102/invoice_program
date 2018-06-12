package pl.coderstrust.accounting.database.impl.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class FileHelper {

  private static final Logger logger = LoggerFactory.getLogger(FileHelper.class);

  public void appendLine(String line, String fileName) {
    try (
        FileWriter fileWriter = new FileWriter(fileName, true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
      appendLine(line, bufferedWriter);
    } catch (IOException applicationException) {
      logger.error("File does not exist", applicationException);  // or "change the path"?
      throw new RuntimeException(applicationException);
    }
  }

  private void appendLine(String line, BufferedWriter bufferedWriter) throws IOException {
    bufferedWriter.append(line);
    bufferedWriter.append(",");
    bufferedWriter.newLine();
  }

  public void writeListToFile(List<String> stringList, String fileName, boolean append) {
    try (
        FileWriter fileWriter = new FileWriter(fileName, append);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)
    ) {
      for (String line : stringList) {
        appendLine(line, bufferedWriter);
      }
    } catch (IOException applicationException) {
      logger.error("File does not exist", applicationException);
      throw new RuntimeException(applicationException);
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
    } catch (IOException readingError) {
      logger.error("File is empty or does not exist", readingError);
    }
    return lines;
  }

  public String readJsonFileAndFindInvoiceLineById(String fileName, String content) {
    List<String> allInvoicesInJson = readLines(fileName);
    return allInvoicesInJson
        .stream()
        .filter(x -> x.contains(content))
        .findFirst()
        .orElse(null);
  }

  public void removeLineWithContentWhenReadingJsonFile(String fileName, String content) {
    List<String> allInvoicesInJsonAfterRemoval = new ArrayList<>();
    try (
        FileReader fileReader = new FileReader(fileName);
        BufferedReader bufferedReader = new BufferedReader(fileReader)) {
      String currentLine;
      while ((currentLine = bufferedReader.readLine()) != null) {
        if (currentLine.contains(content)) {
          continue;
        }
        allInvoicesInJsonAfterRemoval.add(currentLine);
      }
      writeListToFile(allInvoicesInJsonAfterRemoval, fileName, false);
    } catch (IOException readingError) {
      logger.info("File is empty or does not exist", readingError);
    }
  }

  public void updateLineWithContentWhenReadingJsonFile(String invoiceAsJsonString, String fileName,
      String content) {
    List<String> allInvoicesInJsonAfterUpdate = new ArrayList<>();
    try (
        FileReader fileReader = new FileReader(fileName);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
    ) {
      String currentLine;
      while ((currentLine = bufferedReader.readLine()) != null) {
        if (currentLine.contains(content)) {
          allInvoicesInJsonAfterUpdate.add(invoiceAsJsonString);
          continue;
        }
        allInvoicesInJsonAfterUpdate.add(currentLine);
      }
      writeListToFile(allInvoicesInJsonAfterUpdate, fileName, false);
    } catch (IOException readingError) {
      logger.info("File is empty or does not exist", readingError);
    }
  }
}