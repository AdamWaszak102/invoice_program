package pl.coderstrust.accounting.email;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.EnableScheduling;


@Configuration
@EnableScheduling
public class MailSenderConfig {

  @Bean
  public JavaMailSender JavaSender() {
    JavaMailSenderImpl sender = new JavaMailSenderImpl();
    sender.setHost("mail.smtp.ssl.trust");
    sender.setHost("smtp.gmail.com");
    sender.setUsername("jola.coderstrust@gmail.com");
    sender.setPassword("jola.coderstrust!");
    sender.getJavaMailProperties().setProperty("mail.smtp.auch", "true");
    sender.getJavaMailProperties().setProperty("mail.smpt.socketFactory.port", "465");
    sender.getJavaMailProperties().setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
    sender.getJavaMailProperties().setProperty("mail.smtp.port", "587");
    sender.getJavaMailProperties().setProperty("mail.smtp.starttls.enable", "true");
    sender.getJavaMailProperties().setProperty("mail.smtp.starttls.required", "true");
    return sender;
  }

}

