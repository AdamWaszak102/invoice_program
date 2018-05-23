package pl.coderstrust.accounting;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class SpringConfiguration {

  @Primary
  @Bean(value = "writingMapper")
  public ObjectMapper writingMapper() {
    ObjectMapper writingMapper = new ObjectMapper().registerModule(new JavaTimeModule())
        .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    return writingMapper;
  }

  @Bean(value = "readingMapper")
  public ObjectMapper readingMapper() {
    ObjectMapper readingMapper = new ObjectMapper().registerModule(new JavaTimeModule())
        .configure(DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS, true);
    return readingMapper;
  }

}
