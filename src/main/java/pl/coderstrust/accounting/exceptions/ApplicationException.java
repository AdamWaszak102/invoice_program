package pl.coderstrust.accounting.exceptions;

public class ApplicationException extends RuntimeException {

  public ApplicationException(String message) {
    super(message);
  }
}