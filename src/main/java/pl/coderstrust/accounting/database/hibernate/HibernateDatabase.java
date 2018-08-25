package pl.coderstrust.accounting.database.hibernate;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;
import pl.coderstrust.accounting.database.Database;
import pl.coderstrust.accounting.model.Invoice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@ConditionalOnProperty(value = "inHibernateDatabase.enabled", havingValue = "true")
@Repository
public class HibernateDatabase implements Database {

  private InvoiceRepository repository;

  public HibernateDatabase(InvoiceRepository repository) {
    this.repository = repository;
  }

  @Override
  public Long saveInvoice(Invoice invoice) {
    return repository.save(invoice).getId();
  }

  @Override
  public List<Long> saveInvoices(List<Invoice> invoices) {
    List<Long> resultList = new ArrayList<>();
    for (Invoice invoice : invoices) {
      resultList.add(repository.save(invoice).getId());
    }
    return resultList;
  }

  @Override
  public Collection<Invoice> getInvoices() {
    Iterable<Invoice> iterable = repository.findAll();

    return StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
  }

  @Override
  public Invoice getInvoiceById(Long id) {
    return repository.findById(id).orElse(null);
  }
//@Override
//public Invoice getInvoiceById(Long id) {
//    ArrayList<Long> id = new ArrayList<>();
//  return Arrays.asList(repository.findById(id).orElse(null));
//}

  @Override
  public void updateInvoice(Invoice invoice) {
    Invoice toUpdate = repository.findById(invoice.getId()).orElse(null);
    if (toUpdate != null) {
      repository.save(invoice);
    }
  }

  @Override
  public void removeInvoiceById(Long id) {
    repository.deleteById(id);
  }
//@Override
//public void removeInvoiceById(Long id) {
//    ArrayList<Long> id = new ArrayList<>();
//  Arrays.asList(repository.deleteById(id));
//}
}
