package ohcheese.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Pizza")
public class Pizza {
    @Id
    @Column(name = "Pizza_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "Pizza_Name")
    private String pizza_Name;
    @Column(name = "Pizza_image")
    private String pizza_image;
    @ManyToOne
    @JoinColumn(name = "Pizza_Type_ID")
    private Pizza_Type Pizza_Type_ID;

    @ManyToMany
    @JoinTable(
        name = "PizzaToppings",
        joinColumns = { @JoinColumn(name = "Pizza_ID") },
        inverseJoinColumns = { @JoinColumn(name = "Topping_ID") }
    )
    private Set<Toppings> toppings = new HashSet<>();

    public Pizza(String pizza_Name, String pizza_image, Pizza_Type pizza_Type_ID, Set<Toppings> toppings) {
        this.pizza_Name = pizza_Name;
        this.pizza_image = pizza_image;
        Pizza_Type_ID = pizza_Type_ID;
        this.toppings = toppings;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getPizza_Name() { return pizza_Name; }
    public void setPizza_Name(String pizza_Name) { this.pizza_Name = pizza_Name; }

    public Pizza_Type getPizza_Type_ID() { return Pizza_Type_ID; }
    public void setPizza_Type_ID(Pizza_Type pizza_Type_ID) { Pizza_Type_ID = pizza_Type_ID; }

    public String getPizza_image() { return pizza_image; }
    public void setPizza_image(String pizza_image) { this.pizza_image = pizza_image; }

    public Set<Toppings> getToppings() { return toppings; }
    public void setToppings(Set<Toppings> toppings) { this.toppings = toppings; }

    public Pizza() {

    }
}
