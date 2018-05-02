package pl.coderstrust.accounting.model;

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

  public Company() {
    super();
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
}