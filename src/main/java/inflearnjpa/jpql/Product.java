package inflearnjpa.jpql;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Product {

  @Id
  @GeneratedValue
  private Long id;

  private Long name;

  private int price;

  private int stockAmount;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getName() {
    return name;
  }

  public void setName(Long name) {
    this.name = name;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public int getStockAmount() {
    return stockAmount;
  }

  public void setStockAmount(int stockAmount) {
    this.stockAmount = stockAmount;
  }
}
