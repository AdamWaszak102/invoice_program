package pl.coderstrust.accounting.database;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import pl.coderstrust.accounting.model.Invoice;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Adam on 2018-04-16.
 */
public interface Database {

  void saveInvoice(Invoice invoice);

  void saveInvoices(List<Invoice> invoices);

  Collection<Invoice> getInvoices();

  Invoice getInvoiceById(Long id);

  void updateInvoice(Invoice invoice);

  void removeInvoiceById(Long id);
}

