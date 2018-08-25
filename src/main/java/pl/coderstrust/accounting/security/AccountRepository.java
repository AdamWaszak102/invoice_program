package pl.coderstrust.accounting.security;

public interface AccountRepository {

  public Account findByUsername(String username);

  public void save(String username, String password);


}