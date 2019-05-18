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
    @ManyToMany
    private Set<Pizza_Type> pizza_type = new HashSet<>();
    @ManyToMany
    private Set<Toppings> toppings = new HashSet<>();

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getPizza_Name() { return pizza_Name; }
    public void setPizza_Name(String pizza_Name) { this.pizza_Name = pizza_Name; }

    public Set<Pizza_Type> getPizza_type() { return pizza_type; }
    public void setPizza_type(Set<Pizza_Type> pizza_type) { this.pizza_type = pizza_type; }

    public Set<Toppings> getToppings() { return toppings; }
    public void setToppings(Set<Toppings> toppings) { this.toppings = toppings; }

    public Pizza() { }
}
