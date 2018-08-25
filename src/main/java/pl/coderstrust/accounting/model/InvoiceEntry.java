package pl.coderstrust.accounting.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@ApiModel(value = "Invoice entries model", description = "Sample Invoice entries")
public class InvoiceEntry {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @ApiModelProperty(value = "Transaction's description", example = "Fruit and vegetable processing")
  private String description;

  @ApiModelProperty(value = "Transaction's price", example = "500")
  private BigDecimal price;
  private Vat vat;

  public InvoiceEntry(String description, BigDecimal price, Vat vat) {
    this.description = description;
    this.price = price;
    this.vat = vat;
  }

  // needed for json serialization using jackson
  public InvoiceEntry() {
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public Vat getVat() {
    return vat;
  }

  public void setVat(Vat vat) {
    this.vat = vat;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof InvoiceEntry)) {
      return false;
    }
    InvoiceEntry that = (InvoiceEntry) obj;
    return Objects.equals(description, that.description)
        && Objects.equals(price, that.price)
        && vat == that.vat;
  }

  @Override
  public int hashCode() {
    return Objects.hash(description, price, vat);
  }
}
