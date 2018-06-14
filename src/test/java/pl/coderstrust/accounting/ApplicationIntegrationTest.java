package pl.coderstrust.accounting;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.coderstrust.accounting.rest.InvoiceController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationIntegrationTest {

  @Autowired
  private InvoiceController controller;

  @Test
  public void checkThatContextAndControllerLoadProperly() throws Exception {
    assertThat(controller).isNotNull();
  }
}
