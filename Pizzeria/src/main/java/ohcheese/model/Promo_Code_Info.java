package ohcheese.model;

import javafx.scene.control.Button;
import ohcheese.controller.GeneralWindowControl;

import java.io.IOException;

public class Promo_Code_Info extends GeneralWindowControl {

    private int promo_code_id;
    private String promo_code_name;
    private int discount;
    private Button edit_btn;



    public Promo_Code_Info(int promo_code_id, String promo_code_name, int discount) {
        this.promo_code_id = promo_code_id;
        this.promo_code_name = promo_code_name;
        this.discount = discount;
        this.edit_btn = new Button("Edit");
        this.edit_btn.setOnAction( e->{
            try {
                openscene(e, "editpromocode","GeneralWindowStyle", "Employee","Global_Resources");
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        });
    }

    public int getPromo_code_id() { return promo_code_id; }
    public void setPromo_code_id(int promo_code_id) { this.promo_code_id = promo_code_id; }

    public String getPromo_code_name() { return promo_code_name; }
    public void setPromo_code_name(String promo_code_name) { this.promo_code_name = promo_code_name; }

    public int getDiscount() { return discount; }
    public void setDiscount(int discount) { this.discount = discount; }

    public Button getEdit_btn() { return edit_btn; }
    public void setEdit_btn(Button edit_btn) { this.edit_btn = edit_btn; }
}
