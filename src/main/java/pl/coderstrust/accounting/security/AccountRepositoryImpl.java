package pl.coderstrust.accounting.security;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AccountRepositoryImpl implements AccountRepository {

  private final Map<String, Account> accounts = new HashMap<>();

  public Account findByUsername(String username) {
    return accounts.get(username);
  }

  public void save(String username, String password) {
    accounts.put(username, new Account(username, password));
  }

  @Bean
  CommandLineRunner init(final AccountRepository accountRepository) {

    return new CommandLineRunner() {

      @Override
      public void run(String... arg0) throws Exception {
        accountRepository.save("adam", "password");
        accountRepository.save("ania", "password1");
        accountRepository.save("karina", "password2");

      }

    };

  }
}
