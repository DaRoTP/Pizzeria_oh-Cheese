package ohcheese.model;

import javax.persistence.*;

@Entity
@Table(name = "Pizza_Order")
public class Pizza_Order {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne
    @JoinColumn(name = "Pizza_ID")
    private Pizza pizza_ID;
    @OneToOne
    @JoinColumn(name = "Size_ID")
    private Size size_ID;
    @OneToOne
    @JoinColumn(name = "Shopping_Cart_ID")
    private Shopping_Cart shopping_Cart_ID;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Pizza getPizza_ID() { return pizza_ID; }
    public void setPizza_ID(Pizza pizza_ID) { this.pizza_ID = pizza_ID; }

    public Size getSize_ID() { return size_ID; }
    public void setSize_ID(Size size_ID) { this.size_ID = size_ID; }

    public Shopping_Cart getShopping_Cart_ID() { return shopping_Cart_ID; }
    public void setShopping_Cart_ID(Shopping_Cart shopping_Cart_ID) { this.shopping_Cart_ID = shopping_Cart_ID; }

    public Pizza_Order() { }
}
