package pl.coderstrust.accounting.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.util.Objects;

@ApiModel(value = "CompanyModel",
    description = "Sample Company Buyer and Seller's information")
@Entity
public class Company {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ApiModelProperty(value = "Company buyer name",
      example = "Abracodeabra sp. z o.o.")
  private String companyName;

  @ApiModelProperty(value = "Company buyer address",
      example = "ul. Puławska 20/33, 02-222 Warszawa")
  private String address;

  @ApiModelProperty(value = "Company buyer taxIdentification number",
      example = "5555555555")
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