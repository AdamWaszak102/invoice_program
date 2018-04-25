package pl.coderstrust.accounting.model;

/**
 * Created by Adam on 2018-04-17.
 */
public class Company {

  private String companyName;
  private String address;
  private long taxIdentificationNumber;

  public String getcompanyName() {
    return companyName;
  }

  public void setcompanyName(String companyName) {
    this.companyName = companyName;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public long getTaxIdentificationNumber() {
    return taxIdentificationNumber;
  }

  public void setTaxIdentificationNumber(long taxIdentificationNumber) {
    this.taxIdentificationNumber = taxIdentificationNumber;
  }
}