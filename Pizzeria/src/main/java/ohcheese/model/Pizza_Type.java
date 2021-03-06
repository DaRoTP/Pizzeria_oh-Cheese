package ohcheese.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Pizza_Type")
public class Pizza_Type {
    @Id
    @Column(name = "Pizza_Type_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "Pizza_Type_Name")
    private String pizza_Type;

    public Pizza_Type() { }

    public Pizza_Type(String pizza_Type) {
        this.pizza_Type = pizza_Type;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getPizza_Type() { return pizza_Type; }
    public void setPizza_Type(String pizza_Type) { this.pizza_Type = pizza_Type; }



}
