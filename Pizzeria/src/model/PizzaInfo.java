package model;


import java.awt.*;

public class PizzaInfo {

    private  int pizzaID;
    private String pizzaname;
    private String descriptioon;
    private String type;

    //CONSTRUCTOR
    public PizzaInfo(int pizzaID,String pizzaname, String descriptioon) {
        this.pizzaID = pizzaID;
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
        //pizza ID
    public int getPizzaID() { return pizzaID; }
    public void setPizzaID(int pizzaID) { this.pizzaID = pizzaID; }

}
