package pl.coderstrust.accounting.Additional;

import static pl.coderstrust.accounting.model.Vat.VAT_0;
import static pl.coderstrust.accounting.model.Vat.VAT_23;
import static pl.coderstrust.accounting.model.Vat.VAT_5;
import static pl.coderstrust.accounting.model.Vat.VAT_8;

import pl.coderstrust.accounting.model.Company;
import pl.coderstrust.accounting.model.Invoice;
import pl.coderstrust.accounting.model.InvoiceEntry;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class InvoiceProvider {
  public Invoice InvoiceOne(){
    Company seller = new Company();
    seller.setcompanyName("Abracodeabra");
    seller.setTaxIdentificationNumber(5213344);
    seller.setAddress("Kolejowa 5/7, 01-217 Warszawa");

    Company buyer = new Company();
    buyer.setcompanyName("Bravecto");
    buyer.setTaxIdentificationNumber(4443322);
    buyer.setAddress("Matuszewska 14, 25-022 Kielce");

    InvoiceEntry invoiceEntry= new InvoiceEntry("microscope", BigDecimal.valueOf(1000), VAT_23);
    Invoice invoice = new Invoice(0L, "FV 1/2017", LocalDate.of(2017,10,2), buyer, seller,null);
    return invoice;
  }
  public Invoice InvoiceTwo(){
    Company seller = new Company();
    seller.setcompanyName("Manta");
    seller.setTaxIdentificationNumber(5213345);
    seller.setAddress("Lokalna 20, 00-217 Warszawa");

    Company buyer = new Company();
    buyer.setcompanyName("VetLab");
    buyer.setTaxIdentificationNumber(4443322);
    buyer.setAddress("Graniczna 14, 05-500 Piaseczno");

    InvoiceEntry one= new InvoiceEntry("services", BigDecimal.valueOf(567), VAT_0);
    InvoiceEntry two= new InvoiceEntry("treatment", BigDecimal.valueOf(567), VAT_8);
    List<InvoiceEntry> entries = entries.add(one, two)
    Invoice invoice = new Invoice(1L, "FV 1/2018", LocalDate.of(2018,2,2), buyer, seller,(one, two));
    return invoice;
  }
  public Invoice InvoiceThree(){
    Company seller = new Company();
    seller.setcompanyName("Proactive Investment Sp. z o.o.");
    seller.setTaxIdentificationNumber(5218774);
    seller.setAddress("Wiejska 17, 00-001 Mysiadło");

    Company buyer = new Company();
    buyer.setcompanyName("Bravecto");
    buyer.setTaxIdentificationNumber(4443322);
    buyer.setAddress("Matuszewska 14, 25-022 Kielce");


    InvoiceEntry invoiceEntry= new InvoiceEntry("Fruit and vegetable processing", BigDecimal.valueOf(2500), VAT_5);
    List<InvoiceEntry> entries = entries.add("")
    Invoice invoice = new Invoice(0L, "FV 2-10-2017", LocalDate.of(2017,10,2), buyer, seller,null);
    invoice.setEntries();
    return invoice;
  }

}
