package pl.coderstrust.accounting.database.impl.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import pl.coderstrust.accounting.exceptions.ApplicationException;

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
    } catch (IOException exception) {
      logger.error("There was a problem with the file: {}.", fileName, exception);
      throw new ApplicationException("There was a problem with the file", exception);
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
    } catch (IOException exception) {
      logger.error("There was a problem with the file: {}.Cannot write list to the file.", fileName,
          exception);
      throw new ApplicationException("There was a problem with the file.", exception);
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
      logger
          .error("There was a problem with the file: {}. Cannot read data from the file", fileName,
              exception);
      throw new ApplicationException("There was a problem with the file", exception);
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
    } catch (IOException exception) {
      logger.error(
          "There was a problem with the file: {}. Cannot remove line containing the following data from the file: {}",
          fileName, content, exception);
      throw new ApplicationException("There was a problem with the file.", exception);
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
    } catch (IOException exception) {
      logger.error(
          "There was a problem with the file: {}.Cannot update line containing the following data from the file: {}",
          fileName, content, exception);
      throw new ApplicationException("There was a problem with the file.", exception);
    }
  }
}