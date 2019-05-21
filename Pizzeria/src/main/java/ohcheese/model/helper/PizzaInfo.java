package ohcheese.model.helper;


import javafx.scene.control.Button;
import ohcheese.controller.GeneralWindowControl;

import java.io.IOException;

public class PizzaInfo extends GeneralWindowControl {

    private  int pizzaID;
    private String pizzaname;
    private String descriptioon;
    private String type;
    private Button btn;

    public static int temp_id;

    //CONSTRUCTOR
    public PizzaInfo(int pizzaID,String pizzaname) {
        this.pizzaID = pizzaID;
        this.pizzaname = pizzaname;
        this.btn = new Button("test");
        this.btn.setOnAction( e->{
            this.temp_id = this.pizzaID;
            try {
                openscene(e, "editpizza","GeneralWindowStyle", "Employee/controls","Global_Resources");
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        });
        this.btn.getStyleClass().add("edit_btn");
    }

    public static int getTemp_id() { return temp_id; }
    public static void setTemp_id(int temp_id) { PizzaInfo.temp_id = temp_id; }

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

    public Button getBtn() { return btn; }
    public void setBtn(Button btn) { this.btn = btn; }
}
