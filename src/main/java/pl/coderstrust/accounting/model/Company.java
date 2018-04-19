package pl.coderstrust.accounting.model;

/**
 * Created by Adam on 2018-04-17.
 */
public class Company {

  private String companyName;
  private String address;
  private int taxIdentificationNumber;

  public String getcompanyName() {
    return companyName;
  }

  public void setcompanyName(String nameCompany) {
    this.companyName = nameCompany;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public int getTaxIdentificationNumber() {
    return taxIdentificationNumber;
  }

  public void setTaxIdentificationNumber(int taxIdentificationNumber) {
    this.taxIdentificationNumber = taxIdentificationNumber;
  }
}