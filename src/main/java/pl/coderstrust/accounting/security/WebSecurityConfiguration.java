package pl.coderstrust.accounting.security;


@Configuration
class WebSecurityConfiguration extends GlobalAuthenticationConfigurerAdapter {

  @Autowired
  AccountRepositoryImpl accountRepository;

  @Override
  public void init(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService());
  }

  @Bean
  UserDetailsService userDetailsService() {
    return new UserDetailsService() {

      PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

      @Override
      public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username);
        if (account != null) {
          return User.withUsername(account.getUsername())
              .password(encoder.encode(account.getPassword())).roles("User").build();
        } else {
          throw new UsernameNotFoundException("could not find the user '"
              + username + "'");
        }
      }

    };
  }
}