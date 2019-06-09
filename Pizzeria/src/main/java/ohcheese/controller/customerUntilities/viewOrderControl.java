package ohcheese.controller.customerUntilities;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import ohcheese.Utilities.HibernateUtil;
import ohcheese.model.*;
import ohcheese.model.placeholders.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class viewOrderControl implements Initializable {


    public SessionFactory factory = HibernateUtil.getSessionFactory();
    //general
    @FXML private ChoiceBox<String> delivery_choice = new ChoiceBox<String>();
    @FXML private ChoiceBox<String> pizzamaker_choice = new ChoiceBox<String>();
    @FXML private TextArea order_info_TA = new TextArea();


    @FXML private ChoiceBox<String> typeChoice = new ChoiceBox<String>();
    @FXML private ChoiceBox<String> toppingsChoice = new ChoiceBox<String>();
    @FXML private TableView<Toppings_Info> toppingTable = new TableView<>();
    public static ObservableList<Toppings_Info> topping = FXCollections.observableArrayList();
    public static ObservableList<Toppings_Info> getTopping() { return topping; }
    public static void setTopping(ObservableList<Toppings_Info> topping) { viewOrderControl.topping = topping; }

    //Shopping Cart

    @FXML private TextField city_TF;
    @FXML private TextField house_TF;
    @FXML private TextField apartment_TF;
    @FXML private TextField zipcode_TF;
    @FXML private TextField street_TF;

    @FXML private Button activeOrder = new Button();
    @FXML private Button bakingOrder = new Button();
    @FXML private Button deliveringOrder = new Button();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        if(Shopping_Cart_Info.isClass_type()){
            set_shopping_cart();
            get_Order_details();
            Shopping_Cart_Info.setClass_type(false);
        }

    }

    public void get_Order_details(){
        Session session = factory.getCurrentSession();
        session.getTransaction().begin();

        Query query = session.createQuery("from Pizza_Order where Shopping_Cart_ID='"+Shopping_Cart_Info.getTemp_id()+"'");
        List<Pizza_Order> Order_list = query.list();

        String temp_Info = "";
        for(int i = 0; i < Order_list.size(); i++){
            temp_Info += "#"+Order_list.get(i).getPizza_ID().getId()+" | ";
            temp_Info += Order_list.get(i).getPizza_ID().getPizza_Name()+" : ";
            temp_Info += Order_list.get(i).getSize_ID().getSize()+" | ";
            temp_Info += Order_list.get(i).getSize_ID().getPrice()+" zl";
            temp_Info += "\n";
        }
        order_info_TA.setText(temp_Info);


        session.getTransaction().commit();
        session.close();

    }







    public void set_shopping_cart(){

        int id = Shopping_Cart_Info.getTemp_id();
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        session.getTransaction().begin();

        Shopping_Cart scart;
        scart = session.get(Shopping_Cart.class, id);

        city_TF.setText(scart.getCustomer_ID().getAddress_ID().getCity());
        house_TF.setText(scart.getCustomer_ID().getAddress_ID().getHouse_Number());
        apartment_TF.setText(scart.getCustomer_ID().getAddress_ID().getApartment_Number());
        zipcode_TF.setText(scart.getCustomer_ID().getAddress_ID().getZIP_Code());
        street_TF.setText(scart.getCustomer_ID().getAddress_ID().getStreet());

        session.getTransaction().commit();
        session.close();
    }







}

