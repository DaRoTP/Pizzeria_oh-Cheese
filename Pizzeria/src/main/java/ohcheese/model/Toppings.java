package ohcheese.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Toppings")
public class Toppings {
    @Id
    @Column(name = "Toppings_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "Topping_Name")
    private String topping_Name;
    @ManyToMany(mappedBy = "toppings", fetch = FetchType.EAGER)
    private Set<Pizza> pizzas = new HashSet<>();

    public Toppings() { }

    public Toppings(String topping_Name) {
        this.topping_Name = topping_Name;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getTopping_Name() { return topping_Name; }
    public void setTopping_Name(String topping_Name) {   this.topping_Name = topping_Name; }

    public Set<Pizza> getPizzas() { return pizzas; }
    public void setPizzas(Set<Pizza> pizzas) { this.pizzas = pizzas; }


}
