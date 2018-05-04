package pl.coderstrust.accounting.model;

import static org.junit.Assert.assertEquals;
import static pl.coderstrust.accounting.model.Vat.VAT_0;
import static pl.coderstrust.accounting.model.Vat.VAT_23;
import static pl.coderstrust.accounting.model.Vat.VAT_5;
import static pl.coderstrust.accounting.model.Vat.VAT_8;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class TestInvoiceProvider {

  public static Invoice invoiceOne() {
    Company seller = new Company();
    seller.setcompanyName("Abracodeabra");
    seller.setTaxIdentificationNumber(5213344000L);
    seller.setAddress("Kolejowa 5/7, 01-217 Warszawa");

    Company buyer = new Company();
    buyer.setcompanyName("Bravecto");
    buyer.setTaxIdentificationNumber(4443322000L);
    buyer.setAddress("Matuszewska 14, 25-022 Kielce");

    InvoiceEntry invoiceEntry = new InvoiceEntry("microscope", BigDecimal.valueOf(1000), VAT_23);
    Invoice invoice = new Invoice(0L, "FV 1/2017", LocalDate.of(2017, 10, 2), buyer, seller,
        Arrays.asList(invoiceEntry));
    return invoice;
  }

  public static Invoice invoiceTwo() {
    Company seller = new Company();
    seller.setcompanyName("Manta");
    seller.setTaxIdentificationNumber(5213345000L);
    seller.setAddress("Lokalna 20, 00-217 Warszawa");

    Company buyer = new Company();
    buyer.setcompanyName("VetLab");
    buyer.setTaxIdentificationNumber(4443322000L);
    buyer.setAddress("Graniczna 14, 05-500 Piaseczno");

    InvoiceEntry one = new InvoiceEntry("services", BigDecimal.valueOf(567), VAT_0);
    InvoiceEntry two = new InvoiceEntry("treatment", BigDecimal.valueOf(567), VAT_8);
    Invoice invoice = new Invoice(1L, "FV 1/2018", LocalDate.of(2018, 2, 2), buyer, seller,
        Arrays.asList(one, two));
    return invoice;
  }

  public static Invoice invoiceThree() {
    Company seller = new Company();
    seller.setcompanyName("Proactive Investment Sp. z o.o.");
    seller.setTaxIdentificationNumber(5218774000L);
    seller.setAddress("Wiejska 17, 00-001 Mysiadło");

    Company buyer = new Company();
    buyer.setcompanyName("Bravecto");
    buyer.setTaxIdentificationNumber(4443322000L);
    buyer.setAddress("Matuszewska 14, 25-022 Kielce");

    InvoiceEntry invoiceEntry = new InvoiceEntry("Fruit and vegetable processing",
        BigDecimal.valueOf(2500), VAT_5);
    List<InvoiceEntry> entries = Arrays.asList(invoiceEntry);
    Invoice invoice = new Invoice(2L, "FV 2/2018", LocalDate.of(2018, 3, 2), buyer, seller,
        entries);
    return invoice;
  }

  public static void assertSameInvoice(Invoice expected, Invoice actual) {

    assertEquals(expected.getId(), actual.getId());
    assertEquals(expected.getIdentifier(), actual.getIdentifier());
    assertEquals(expected.getIssueDate(), actual.getIssueDate());
    assertEquals(expected.getBuyer(), actual.getBuyer());
    assertEquals(expected.getEntries(), actual.getEntries());
    assertEquals(expected.getSeller(), actual.getSeller());
  }

}