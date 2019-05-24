package ohcheese.model.helper;

import javafx.scene.control.Button;
import ohcheese.controller.GeneralWindowControl;

import java.io.IOException;

public class Promo_Code_Info extends GeneralWindowControl {

    private int promo_code_id;
    private String promo_code_name;
    private int discount;
    private Button edit_btn;

    public static int temp_id;
    public static boolean class_type;


    public Promo_Code_Info(int promo_code_id, String promo_code_name, int discount) {
        this.promo_code_id = promo_code_id;
        this.promo_code_name = promo_code_name;
        this.discount = discount;
        this.class_type = false;


        this.edit_btn = new Button("Edit");
        this.edit_btn.setOnAction( e->{
            this.temp_id = this.promo_code_id;
            this.class_type = true;
            try {
                openscene(e, "editpromocode","GeneralWindowStyle", "Employee/controls","Global_Resources");
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        });
        this.edit_btn.getStyleClass().add("edit_btn");
    }

    public static int getTemp_id() { return temp_id; }
    public static void setTemp_id(int temp_id) { Promo_Code_Info.temp_id = temp_id; }

    public static boolean isClass_type() { return class_type; }
    public static void setClass_type(boolean class_type) { Promo_Code_Info.class_type = class_type; }

    public int getPromo_code_id() { return promo_code_id; }
    public void setPromo_code_id(int promo_code_id) { this.promo_code_id = promo_code_id; }

    public String getPromo_code_name() { return promo_code_name; }
    public void setPromo_code_name(String promo_code_name) { this.promo_code_name = promo_code_name; }

    public int getDiscount() { return discount; }
    public void setDiscount(int discount) { this.discount = discount; }

    public Button getEdit_btn() { return edit_btn; }
    public void setEdit_btn(Button edit_btn) { this.edit_btn = edit_btn; }
}
