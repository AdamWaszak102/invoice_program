package pl.coderstrust.accounting.exceptions;

public class ApplicationException extends RuntimeException {

  public ApplicationException(String message, Exception exception) {
    super(message, exception);
  }
}