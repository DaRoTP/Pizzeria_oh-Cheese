package ohcheese.model.placeholders;

import javafx.scene.control.Button;
import ohcheese.controller.GeneralWindowControl;

import java.io.IOException;

public class Size_Info extends GeneralWindowControl {

    private  int size_ID;
    private String Size;
    private String Price;
    private Button edit_btn;

    public static boolean class_type;
    public static int temp_id;

    public Size_Info(int size_ID, String size, String price) {
        this.size_ID = size_ID;
        Size = size;
        Price = price;
        this.class_type = false;

        this.edit_btn = new Button("Edit");
        this.edit_btn.setOnAction(e->{
            this.temp_id = this.size_ID;
            this.class_type = true;
            try {
                openScene("editsize","GeneralWindowStyle", "Employee/controls","Global_Resources");
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        });
        this.edit_btn.getStyleClass().add("edit_btn");
    }

    public int getSize_ID() { return size_ID; }
    public void setSize_ID(int size_ID) { this.size_ID = size_ID; }

    public String getSize() { return Size; }
    public void setSize(String size) { Size = size; }

    public String getPrice() { return Price; }
    public void setPrice(String price) { Price = price; }

    public Button getEdit_btn() { return edit_btn; }
    public void setEdit_btn(Button edit_btn) { this.edit_btn = edit_btn; }

    public static int getTemp_id() { return temp_id; }
    public static void setTemp_id(int temp_id) { Size_Info.temp_id = temp_id; }

    public static boolean isClass_type() { return class_type; }
    public static void setClass_type(boolean class_type) { Size_Info.class_type = class_type; }
}
