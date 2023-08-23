package inflearnjpa.jpql;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ORDERS")
public class Order {

  @Id
  @GeneratedValue
  private Long id;

  private int orderAmount;

  @Embedded
  private Address address;

  // Order -> Product 단방향 매핑
  @ManyToOne
  @JoinColumn(name = "PRODUCT_ID")
  private Product product;
}