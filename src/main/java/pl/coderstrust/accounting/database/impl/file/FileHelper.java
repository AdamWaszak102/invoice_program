package pl.coderstrust.accounting.database.impl.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import pl.coderstrust.accounting.exceptions.ApplicationException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
      throw new ApplicationException("There was a problem with the file");
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
      logger.error("There was a problem with the file: {}.", fileName, exception);
      throw new ApplicationException("There was a problem with the file.Cannot save data to file.");
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
      logger.error("There was a problem with the file: {}.", fileName, exception);
      throw new ApplicationException("There was a problem with the file, cannot read the data form file");
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
      logger.error("There was a problem with the file: {}.", fileName, exception);
      throw new ApplicationException("There was a problem with the file.");
    }
  }

  public void updateLineWithContentWhenReadingJsonFile(String invoiceAsJsonString, String fileName,
      String content) {
    List<String> allInvoicesInJsonAfterUpdate = new ArrayList<>();
    try (
        FileReader fileReader = new FileReader(fileName);
        BufferedReader bufferedReader = new BufferedReader(fileReader)
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
      logger.error("There was a problem with the file: {}.", fileName, exception);
      throw new ApplicationException("There was a problem with the file, cannot write data to file.");
    }
  }

  public void writeNumberToFile(Long id, String fileName) {
    try (FileWriter fileWriter = new FileWriter(fileName)) {
      fileWriter.write(id.toString());
    } catch (IOException exception) {
      exception.printStackTrace();
    }
  }

  public Long readNumberFromFile(String fileName) {
    File file = new File(fileName);
    if (file.exists()) {
      try (Scanner scanner = new Scanner(file)) {
        if (scanner.hasNextLong()) {
          return scanner.nextLong();
        }
      } catch (FileNotFoundException exception) {
        exception.printStackTrace();
      }
    }
    return 0L;
  }
}