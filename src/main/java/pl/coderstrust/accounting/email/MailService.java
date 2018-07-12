package pl.coderstrust.accounting.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.coderstrust.accounting.database.impl.file.JsonHelper;
import pl.coderstrust.accounting.logic.InvoiceBook;
import pl.coderstrust.accounting.model.Invoice;

import java.time.LocalDate;
import java.util.List;

@Service
public class MailService {

  @Autowired
  JsonHelper jsonHelper;
  @Autowired
  private MailSender mailSender;
  @Autowired
  InvoiceBook invoiceBook;


  public void sendEmail(Long id) {
    jsonHelper.convertInvoiceToJsonString(invoiceBook.getInvoiceById(id));
    String invoiceContent = jsonHelper.convertInvoiceToJsonString(invoiceBook.getInvoiceById(id));
    mailSender.sendMail("jola.coderstrust@gmail.com", new String[]{"coderstrust.1@gmail.com",
        "coderstrust.2@onet.pl"}, "Invoice", invoiceContent);
  }


  private String invoiceConverter(List<Invoice> invoicesFromCurrentDay) {
    StringBuilder stringBuilder = new StringBuilder();
    invoicesFromCurrentDay
        .forEach(invoice -> stringBuilder.append(jsonHelper.convertInvoiceToJsonString(invoice)));
    return stringBuilder.toString();

  }

  @Scheduled(cron = "0 50 19 * * ?", zone = "GMT")
  public void sendMailAtSpecifiedTime() {
    mailSender.sendMail("jola.coderstrust@gmail.com", new String[]{"coderstrust.1@gmail.com",
        "coderstrust.2@onet.pl"}, "Invoice", invoiceConverter(
        invoiceBook.getInvoicesByIssueDateRange(LocalDate.now().minusDays(1L), LocalDate.now().plusDays(1))));
  }
}

