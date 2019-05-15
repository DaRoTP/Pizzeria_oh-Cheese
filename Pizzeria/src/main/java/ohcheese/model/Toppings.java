package ohcheese.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Toppings")
public class Toppings {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "Topping_Name")
    private String topping_Name;
    @ManyToMany
    private Set<Pizza> pizza = new HashSet<>();

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getTopping_Name() { return topping_Name; }
    public void setTopping_Name(String topping_Name) {   this.topping_Name = topping_Name; }

    public Set<Pizza> getPizza() { return pizza; }
    public void setPizza(Set<Pizza> pizza) { this.pizza = pizza; }

    public Toppings() { }
}