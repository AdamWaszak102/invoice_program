package pl.coderstrust.accounting.model;

/**
 * Created by Adam on 2018-04-17.
 */
public class Company {

  private String nameCompany;
  private String adress;
  private int taxIdentificationNumber;
  private String nip;

  public String getNameCompany() {
    return nameCompany;
  }

  public void setNameCompany(String nameCompany) {
    this.nameCompany = nameCompany;
  }

  public String getAdress() {
    return adress;
  }

  public void setAdress(String adress) {
    this.adress = adress;
  }

  public int getTaxIdentificationNumber() {
    return taxIdentificationNumber;
  }

  public void setTaxIdentificationNumber(int taxIdentificationNumber) {
    this.taxIdentificationNumber = taxIdentificationNumber;
  }

  public String getNip() {
    return nip;
  }

  public void setNip(String nip) {
    this.nip = nip;
  }
}
