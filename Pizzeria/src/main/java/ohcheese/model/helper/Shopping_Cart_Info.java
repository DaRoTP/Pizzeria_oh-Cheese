package ohcheese.model.helper;

import javafx.scene.control.Button;
import ohcheese.model.Address;
import ohcheese.model.Customer;
import ohcheese.model.Order_status;
import ohcheese.model.Promo_Codes;

public class Shopping_Cart_Info {

    private  int SC_ID;
    private Customer customer;
    private Address address;
    private Promo_Codes promo_Code;
    private Order_status order_status;
    private Button edit_btn;

    private String username;
    private String street;
    private String city;
    private String house_Nr;
    private String apartment_Nr;
    private String status;

    public static int temp_id;

    public Shopping_Cart_Info(int SC_ID, Customer customer, Address address, Promo_Codes promo_Code, Order_status order_status) {
        this.SC_ID = SC_ID;
        this.customer = customer;
        this.address = address;
        this.promo_Code = promo_Code;
        this.order_status = order_status;
        this.edit_btn = new Button("View");
        this.edit_btn.getStyleClass().add("addbtn");

        this.username = this.customer.getUsername();
        this.street = this.address.getStreet();
        this.city = this.address.getHouse_Number();
        this.house_Nr = this.address.getHouse_Number();
        this.apartment_Nr = this.address.getApartment_Number();
        this.status = this.order_status.getOrder_status();
    }

    public int getSC_ID() { return SC_ID; }
    public void setSC_ID(int SC_ID) { this.SC_ID = SC_ID; }

    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }

    public Address getAddress() { return address; }
    public void setAddress(Address address) { this.address = address; }

    public Promo_Codes getPromo_Code() { return promo_Code; }
    public void setPromo_Code(Promo_Codes promo_Code) { this.promo_Code = promo_Code; }

    public Order_status getOrder_status() { return order_status; }
    public void setOrder_status(Order_status order_status) { this.order_status = order_status; }

    public Button getEdit_btn() { return edit_btn; }
    public void setEdit_btn(Button edit_btn) { this.edit_btn = edit_btn; }

    public static int getTemp_id() { return temp_id; }
    public static void setTemp_id(int temp_id) { Shopping_Cart_Info.temp_id = temp_id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getStreet() { return street; }
    public void setStreet(String street) { this.street = street; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getHouse_Nr() { return house_Nr; }
    public void setHouse_Nr(String house_Nr) { this.house_Nr = house_Nr; }

    public String getApartment_Nr() { return apartment_Nr; }
    public void setApartment_Nr(String apartment_Nr) { this.apartment_Nr = apartment_Nr; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
