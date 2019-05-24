package ohcheese.model.helper;

import javafx.scene.control.Button;
import ohcheese.controller.GeneralWindowControl;

import java.io.IOException;

public class Toppings_Info extends GeneralWindowControl {

    private int topping_id;
    private String topping_name;
    private Button edit_btn;

    public static boolean class_type;
    public static int temp_id;


    public Toppings_Info(int topping_id, String topping_name) {
        this.topping_id = topping_id;
        this.topping_name = topping_name;
        this.class_type = false;

        this.edit_btn = new Button("Edit");
        this.edit_btn.setOnAction( e->{
            this.temp_id = this.topping_id;
            this.class_type = true;
            try {
                openscene(e, "edittoppings","GeneralWindowStyle", "Employee/controls","Global_Resources");
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        });
        this.edit_btn.getStyleClass().add("edit_btn");
    }


    public static int getTemp_id() { return temp_id; }
    public static void setTemp_id(int temp_id) { Toppings_Info.temp_id = temp_id; }

    public static boolean getClass_type() { return class_type; }
    public static void setClass_type(boolean class_type) { Toppings_Info.class_type = class_type; }

    public int getTopping_id() { return topping_id; }
    public void setTopping_id(int topping_id) { this.topping_id = topping_id; }

    public String getTopping_name() { return topping_name; }
    public void setTopping_name(String topping_name) { this.topping_name = topping_name; }

    public Button getEdit_btn() { return edit_btn; }
    public void setEdit_btn(Button edit_btn) { this.edit_btn = edit_btn; }

}
