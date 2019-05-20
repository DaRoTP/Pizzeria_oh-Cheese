package ohcheese.model;

import javafx.scene.control.Button;
import ohcheese.controller.GeneralWindowControl;

import java.io.IOException;

public class Toppings_Info extends GeneralWindowControl {

    private int topping_id;
    private String topping_name;
    private Button edit_btn;

    public Toppings_Info(int topping_id, String topping_name) {
        this.topping_id = topping_id;
        this.topping_name = topping_name;
        this.edit_btn = new Button("Edit");
        this.edit_btn.setOnAction( e->{
            try {
                openscene(e, "edittoppings","GeneralWindowStyle", "Employee","Global_Resources");
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        });
    }

    public int getTopping_id() { return topping_id; }
    public void setTopping_id(int topping_id) { this.topping_id = topping_id; }

    public String getTopping_name() { return topping_name; }
    public void setTopping_name(String topping_name) { this.topping_name = topping_name; }

    public Button getEdit_btn() { return edit_btn; }
    public void setEdit_btn(Button edit_btn) { this.edit_btn = edit_btn; }
}
