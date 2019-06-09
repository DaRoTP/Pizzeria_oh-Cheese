package ohcheese.model.placeholders;

import javafx.scene.control.Button;
import ohcheese.controller.GeneralWindowControl;

import java.io.IOException;

public class Pizza_type_Info extends GeneralWindowControl {

    private  int pizza_type_ID;
    private String pizza_type;
    private Button edit_btn;

    public static boolean class_type;
    public static int temp_id;

    public Pizza_type_Info(int pizza_type_ID, String pizza_type) {
        this.pizza_type_ID = pizza_type_ID;
        this.pizza_type = pizza_type;
        this.edit_btn = new Button("Edit");
        this.class_type = false;

        this.edit_btn.setOnAction(e->{
            this.temp_id = this.pizza_type_ID;
            this.class_type = true;
            try {
                openScene( "edittype","GeneralWindowStyle", "Employee/controls","Global_Resources");
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        });
        this.edit_btn.getStyleClass().add("edit_btn");
    }

    public int getPizza_type_ID() { return pizza_type_ID; }
    public void setPizza_type_ID(int pizza_type_ID) { this.pizza_type_ID = pizza_type_ID; }

    public String getPizza_type() { return pizza_type; }
    public void setPizza_type(String pizza_type) { this.pizza_type = pizza_type; }

    public Button getEdit_btn() { return edit_btn; }
    public void setEdit_btn(Button edit_btn) { this.edit_btn = edit_btn; }

    public static int getTemp_id() { return temp_id; }
    public static void setTemp_id(int temp_id) { Pizza_type_Info.temp_id = temp_id; }

    public static boolean getClass_type() { return class_type; }
    public static void setClass_type(boolean class_type) { Pizza_type_Info.class_type = class_type; }
}
