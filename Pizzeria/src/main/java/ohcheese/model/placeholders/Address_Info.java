package ohcheese.model.placeholders;


import javafx.scene.control.Button;
import ohcheese.controller.GeneralWindowControl;

import java.io.IOException;

public class Address_Info extends GeneralWindowControl {

    private  int address_ID;
    private String city;
    private String street;
    private String zip_code;
    private String house;
    private String apartment;

    private Button edit_btn;

    public static boolean class_type;
    public static int temp_id;


    public Address_Info(int address_ID, String city, String street, String zip_code, String house, String apartment) {
        this.address_ID = address_ID;
        this.city = city;
        this.street = street;
        this.zip_code = zip_code;
        this.house = house;
        this.apartment = apartment;
        this.class_type = false;
        this.edit_btn = new Button("Edit");
        this.edit_btn.setOnAction(e->{
            this.temp_id = this.address_ID;
            this.class_type = true;
            try {
                openScene( "edit_address","GeneralWindowStyle", "Admin/tools","Global_Resources");
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        });
        this.edit_btn.getStyleClass().add("edit_btn");
    }

    public int getAddress_ID() { return address_ID; }
    public void setAddress_ID(int address_ID) { this.address_ID = address_ID; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getStreet() { return street; }
    public void setStreet(String street) { this.street = street; }

    public String getZip_code() { return zip_code; }
    public void setZip_code(String zip_code) { this.zip_code = zip_code; }

    public String getHouse() { return house; }
    public void setHouse(String house) { this.house = house; }

    public String getApartment() { return apartment; }
    public void setApartment(String apartment) { this.apartment = apartment; }

    public Button getEdit_btn() { return edit_btn; }
    public void setEdit_btn(Button edit_btn) { this.edit_btn = edit_btn; }

    public static boolean isClass_type() { return class_type; }
    public static void setClass_type(boolean class_type) { Address_Info.class_type = class_type; }

    public static int getTemp_id() { return temp_id; }
    public static void setTemp_id(int temp_id) { Address_Info.temp_id = temp_id; }
}
