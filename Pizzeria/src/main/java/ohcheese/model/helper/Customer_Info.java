package ohcheese.model.helper;

import javafx.scene.control.Button;
import ohcheese.controller.GeneralWindowControl;
import ohcheese.model.Address;

import java.io.IOException;

public class Customer_Info extends GeneralWindowControl {

    private  int customer_ID;
    private String name;
    private String surname;
    private String phone_number;
    private String e_mail;
    private String username;
    private String password;
    private Address address;
    private Button edit_btn;

    public static boolean class_type;
    public static int temp_id;

    public Customer_Info(int customer_ID, String name, String surname, String phone_number, String e_mail, String username, String password, Address address) {
        this.customer_ID = customer_ID;
        this.name = name;
        this.surname = surname;
        this.phone_number = phone_number;
        this.e_mail = e_mail;
        this.username = username;
        this.password = password;
        this.address = address;
        this.class_type = false;
        this.edit_btn = new Button("Edit");
        this.edit_btn.setOnAction(e->{
            this.temp_id = this.customer_ID;
            this.class_type = true;
            try {
                openscene(e, "edit_customer","GeneralWindowStyle", "Admin/tools","Global_Resources");
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        });
        this.edit_btn.getStyleClass().add("edit_btn");


    }

    public int getCustomer_ID() { return customer_ID; }
    public void setCustomer_ID(int customer_ID) { this.customer_ID = customer_ID; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSurname() { return surname; }
    public void setSurname(String surname) { this.surname = surname; }

    public String getPhone_number() { return phone_number; }
    public void setPhone_number(String phone_number) { this.phone_number = phone_number; }

    public String getE_mail() { return e_mail; }
    public void setE_mail(String e_mail) { this.e_mail = e_mail; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Address getAddress() { return address; }
    public void setAddress(Address address) { this.address = address; }

    public Button getEdit_btn() { return edit_btn; }
    public void setEdit_btn(Button edit_btn) { this.edit_btn = edit_btn; }

    public static boolean isClass_type() { return class_type; }
    public static void setClass_type(boolean class_type) { Customer_Info.class_type = class_type; }

    public static int getTemp_id() { return temp_id; }
    public static void setTemp_id(int temp_id) { Customer_Info.temp_id = temp_id; }
}
