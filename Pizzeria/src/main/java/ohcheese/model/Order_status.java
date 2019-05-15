package ohcheese.model;

import javax.persistence.*;

@Entity
@Table(name = "Order_status")
public class Order_status {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "Order_status")
    private String order_status;


    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getOrder_status() { return order_status; }
    public void setOrder_status(String order_status) { this.order_status = order_status; }

    public Order_status() { }
}
