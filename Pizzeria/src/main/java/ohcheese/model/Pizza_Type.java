package ohcheese.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Pizza_Type")
public class Pizza_Type {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "Pizza_Type")
    private String pizza_Type;
    @ManyToMany
    private Set<Pizza> pizza = new HashSet<>();

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getPizza_Type() { return pizza_Type; }
    public void setPizza_Type(String pizza_Type) { this.pizza_Type = pizza_Type; }

    public Set<Pizza> getPizza() { return pizza; }
    public void setPizza(Set<Pizza> pizza) { this.pizza = pizza; }

    public Pizza_Type() { }
}
