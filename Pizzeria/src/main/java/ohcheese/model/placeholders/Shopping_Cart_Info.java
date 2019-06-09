package ohcheese.model.placeholders;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ohcheese.controller.GeneralWindowControl;
import ohcheese.model.*;

import java.io.IOException;

public class Shopping_Cart_Info extends GeneralWindowControl {

    private  int SC_ID;
    private Customer customer;
    private Address address;
    private Promo_Codes promo_Code;
    private Order_status order_status;
    private Button edit_btn;
    private Button view_btn;

    private String username;
    private String street;
    private String city;
    private String house_Nr;
    private String apartment_Nr;
    private String status;
    private float final_price;
//    private String promocodeName;

    private ImageView acceptedIV;
    private ImageView bakingIV;
    private ImageView deliveryIV;
    private ImageView finishedIV;

    private Image accpetedImg = new Image("view/Customer/view_status/checked.png");
    private Image bakingImg = new Image("view/Customer/view_status/oven.png");
    private Image deliveryImg = new Image("view/Customer/view_status/delivery.png");
    private Image finishedImg = new Image("view/Customer/view_status/flags-crossed.png");



    public static boolean class_type;
    public static int temp_id;

    public Shopping_Cart_Info(int SC_ID, Customer customer, Address address, Promo_Codes promo_Code, Order_status order_status, float final_price) {
        this.SC_ID = SC_ID;
        this.customer = customer;
        this.address = address;
        this.promo_Code = promo_Code;
        this.order_status = order_status;
        this.username = this.customer.getUsername();
        this.street = this.address.getStreet();
        this.city = this.address.getCity();
        this.house_Nr = this.address.getHouse_Number();
        this.apartment_Nr = this.address.getApartment_Number();
        this.status = this.order_status.getOrder_status();
        this.final_price = final_price;
        this.final_price = discount();

        this.acceptedIV = new ImageView();
        acceptedIV.setFitHeight(30);
        acceptedIV.setFitWidth(30);
        this.bakingIV = new ImageView();
        bakingIV.setFitHeight(30);
        bakingIV.setFitWidth(30);
        this.deliveryIV = new ImageView();
        deliveryIV.setFitHeight(30);
        deliveryIV.setFitWidth(30);
        this.finishedIV = new ImageView();
        finishedIV.setFitHeight(30);
        finishedIV.setFitWidth(30);

        if(order_status.getId() == 2 || order_status.getId() == 3 || order_status.getId() == 4 || order_status.getId() == 5)
            this.acceptedIV.setImage(accpetedImg);
        if(order_status.getId() == 3 || order_status.getId() == 4 || order_status.getId() == 5)
            this.bakingIV.setImage(bakingImg);
        if(order_status.getId() == 4 || order_status.getId() == 5)
            this.deliveryIV.setImage(deliveryImg);
        if(order_status.getId() == 5)
            this.finishedIV.setImage(finishedImg);




        this.class_type = false;
        this.edit_btn = new Button("Edit");
        this.edit_btn.setOnAction(e->{
            this.temp_id = this.SC_ID;
            this.class_type = true;
            try {
                openScene( "Order","GeneralWindowStyle", "Order","Global_Resources");
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        });
        this.edit_btn.getStyleClass().add("addbtn");

        this.view_btn = new Button("View");
        this.view_btn.setOnAction(e->{
            this.temp_id = this.SC_ID;
            this.class_type = true;
            try {
                openScene("viewOrder","GeneralWindowStyle", "Order","Global_Resources");
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        });
        this.view_btn.getStyleClass().add("addbtn");
    }

    public float discount(){
        if(this.promo_Code != null){
            final_price -= final_price * ((float)promo_Code.getPercent_Off() / 100);
        }
        return final_price;
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

    public float getFinal_price() { return final_price; }
    public void setFinal_price(float final_price) { this.final_price = final_price; }

    public static boolean isClass_type() { return class_type; }
    public static void setClass_type(boolean class_type) { Shopping_Cart_Info.class_type = class_type; }

    public ImageView getAcceptedIV() { return acceptedIV; }
    public void setAcceptedIV(ImageView acceptedIV) { this.acceptedIV = acceptedIV; }

    public ImageView getBakingIV() { return bakingIV; }
    public void setBakingIV(ImageView bakingIV) { this.bakingIV = bakingIV; }

    public ImageView getDeliveryIV() { return deliveryIV; }
    public void setDeliveryIV(ImageView deliveryIV) { this.deliveryIV = deliveryIV; }

    public ImageView getFinishedIV() { return finishedIV; }
    public void setFinishedIV(ImageView finishedIV) { this.finishedIV = finishedIV; }

    public Image getAccpetedImg() { return accpetedImg; }
    public void setAccpetedImg(Image accpetedImg) { this.accpetedImg = accpetedImg; }

    public Image getBakingImg() { return bakingImg; }
    public void setBakingImg(Image bakingImg) { this.bakingImg = bakingImg; }

    public Image getDeliveryImg() { return deliveryImg; }
    public void setDeliveryImg(Image deliveryImg) { this.deliveryImg = deliveryImg; }

    public Image getFinishedImg() { return finishedImg; }
    public void setFinishedImg(Image finishedImg) { this.finishedImg = finishedImg; }

    public Button getView_btn() { return view_btn; }
    public void setView_btn(Button view_btn) { this.view_btn = view_btn; }

}
