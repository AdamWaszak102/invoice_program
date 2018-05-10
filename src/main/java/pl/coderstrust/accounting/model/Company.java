package pl.coderstrust.accounting.model;

import java.util.Objects;

/**
 * Created by Adam on 2018-04-17.
 */
public class Company {

  private String companyName;
  private String address;
  private Long taxIdentificationNumber;

  public Company(String companyName, String address, Long taxIdentificationNumber) {
    this.companyName = companyName;
    this.address = address;
    this.taxIdentificationNumber = taxIdentificationNumber;
  }

  // needed for json serialization using jackson
  public Company() {
  }

  public String getCompanyName() {
    return companyName;
  }

  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public Long getTaxIdentificationNumber() {
    return taxIdentificationNumber;
  }

  public void setTaxIdentificationNumber(Long taxIdentificationNumber) {
    this.taxIdentificationNumber = taxIdentificationNumber;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof Company)) {
      return false;
    }
    Company company = (Company) obj;
    return Objects.equals(companyName, company.companyName)
        && Objects.equals(address, company.address)
        && Objects.equals(taxIdentificationNumber, company.taxIdentificationNumber);
  }

  @Override
  public int hashCode() {

    return Objects.hash(companyName, address, taxIdentificationNumber);
  }
}