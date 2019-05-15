package ohcheese.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Shopping_Cart")
public class Shopping_Cart {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne
    @JoinColumn(name = "Customer_ID")
    private Customer customer_ID;
    @OneToOne
    @JoinColumn(name = "Address_ID")
    private Address address_ID;
    @OneToOne
    @JoinColumn(name = "Promo_Code_ID")
    private Promo_Codes promo_Code_ID;
    @OneToOne
    @JoinColumn(name = "Order_status_ID")
    private Order_status order_status_ID;
    @ManyToMany
    private Set<Employee> employee = new HashSet<>();

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Customer getCustomer_ID() { return customer_ID; }
    public void setCustomer_ID(Customer customer_ID) { this.customer_ID = customer_ID; }

    public Address getAddress_ID() { return address_ID; }
    public void setAddress_ID(Address address_ID) { this.address_ID = address_ID; }

    public Promo_Codes getPromo_Code_ID() { return promo_Code_ID; }
    public void setPromo_Code_ID(Promo_Codes promo_Code_ID) { this.promo_Code_ID = promo_Code_ID; }

    public Order_status getOrder_status_ID() { return order_status_ID; }
    public void setOrder_status_ID(Order_status order_status_ID) { this.order_status_ID = order_status_ID; }

    public Set<Employee> getEmployee() { return employee; }
    public void setEmployee(Set<Employee> employee) { this.employee = employee; }

    public Shopping_Cart() { }
}
