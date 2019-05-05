package model;


import java.awt.*;

public class PizzaInfo {

    private String pizzaname;
    private String descriptioon;

    //CONSTRUCTOR
    public PizzaInfo(String pizzaname, String descriptioon) {
        this.pizzaname = pizzaname;
        this.descriptioon = descriptioon;
    }

    //GETTERS & SETTERS
        //NAME
    public String getPizzaname() { return pizzaname; }
    public void setPizzaname(String pizzaname) { this.pizzaname = pizzaname; }
        //DESCRIPTION
    public String getDescriptioon() { return descriptioon; }
    public void setDescriptioon(String descriptioon) { this.descriptioon = descriptioon; }



}
