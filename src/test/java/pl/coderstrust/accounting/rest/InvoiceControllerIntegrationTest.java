package pl.coderstrust.accounting.rest;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pl.coderstrust.accounting.model.TestInvoiceProvider.invoiceOne;
import static pl.coderstrust.accounting.model.TestInvoiceProvider.invoiceOneModified;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import pl.coderstrust.accounting.SpringConfiguration;
import pl.coderstrust.accounting.model.Invoice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class InvoiceControllerIntegrationTest {

  Invoice invoiceOne = invoiceOne();
  Invoice invoiceModified = invoiceOneModified();
  List<Invoice> invoicesList = new ArrayList<>(Arrays.asList(invoiceOne, invoiceModified));

  @Autowired
  private MockMvc mockMvc;

  private SpringConfiguration springConfiguration = new SpringConfiguration();

  @Autowired
  private ObjectMapper objectMapper = springConfiguration.objectMapper();


  @Test
  public void shouldCheckThatInvoiceControllerSavesInvoiceAndReturnsId() throws Exception {
    MvcResult response = mockMvc
        .perform(post("/invoices/add_invoice").content(invoiceToJson(invoiceOne))
            .contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk())
        .andReturn();

    assertTrue(StringUtils.isNumeric(response.getResponse().getContentAsString()));
  }

  @Test
  public void shouldCheckThatInvoiceControllerSavesAListOfInvoicesAndReturnsId() throws Exception {
    MvcResult response = mockMvc
        .perform(post("/invoices/add_invoices").content(invoicesToJson(invoicesList))
            .contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk())
        .andReturn();

    String[] ids = response.getResponse().getContentAsString()
        .replace("]", "").split(",");
    Long idsNumberExpected = 2L;

    Long lastId = Long.valueOf(ids[ids.length - 1]);
    assertEquals(lastId, idsNumberExpected);
  }

  @Test
  public void shouldCheckThatInvoiceControllerReadsInvoices() throws Exception {
    mockMvc.perform(post("/invoices/add_invoice").content(invoiceToJson(invoiceOne))
        .contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk());

    mockMvc.perform(get("/invoices"))
        .andDo(print())
        .andExpect(jsonPath("$", hasSize(1)))
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("FV 1/2017")));
  }

  @Test
  public void shouldCheckThatInvoiceControllerReadsInvoiceById() throws Exception {
    MvcResult result = mockMvc
        .perform(post("/invoices/add_invoices").content(invoicesToJson(invoicesList))
            .contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk())
        .andReturn();

    String[] ids = result.getResponse().getContentAsString().replace("]", "").split(",");
    Long lastId = Long.valueOf(ids[ids.length - 1]);

    mockMvc.perform(get("/invoices/{id}", lastId))
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("FV 6/2018")));
  }

  @Test
  public void shouldCheckThatStatusIsNotFoundWhenReadingInvoiceWithWrongId() throws Exception {
    mockMvc.perform(get("/invoices/{id}", 333L))
        .andExpect(status().isNotFound());
  }

  @Test
  public void shouldCheckThatInvoiceControllerUpdatesInvoice() throws Exception {
    mockMvc.perform(post("/invoices/add_invoice").content(invoiceToJson(invoiceOne))
        .contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk());

    mockMvc.perform(put("/invoices").content(invoiceToJson(invoiceModified))
        .contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk());

    mockMvc.perform(get("/invoices"))
        .andExpect(jsonPath("$", hasSize(1)))
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("FV 6/2018")));
  }

  @Test
  public void shouldCheckThatInvoiceControllerRemovesInvoice() throws Exception {
    MvcResult response = mockMvc
        .perform(post("/invoices/add_invoice").content(invoiceToJson(invoiceOne))
            .contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk())
        .andReturn();
    int id = Integer.parseInt(response.getResponse().getContentAsString());

    mockMvc.perform(delete("/invoices/" + id))
        .andExpect(status().isOk());

    mockMvc.perform(get("/invoices"))
        .andDo(print())
        .andExpect(jsonPath("$", hasSize(0)))
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("[]")));
  }

  @Test
  public void shouldCheckThatStatusIsNotFoundWhenRemovingInvoiceWithWrongId() throws Exception {
    mockMvc.perform(delete("/invoices/{id}", 333L))
        .andExpect(status().isNotFound());
  }

  private String invoiceToJson(Invoice invoice) throws JsonProcessingException {
    return objectMapper.writeValueAsString(invoice);
  }

  private String invoicesToJson(List<Invoice> invoicesList) throws JsonProcessingException {
    StringBuilder stringBuilder = new StringBuilder("[");
    for (Invoice invoice : invoicesList) {
      stringBuilder.append(invoiceToJson(invoice));
      stringBuilder.append(",");
    }
    stringBuilder.deleteCharAt((stringBuilder.length() - 1));
    return stringBuilder.append("]").toString();
  }
}
