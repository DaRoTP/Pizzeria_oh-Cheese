package ohcheese.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import ohcheese.Utilities.HibernateUtil;
import ohcheese.model.*;
import ohcheese.model.helper.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.net.URL;
import java.util.ResourceBundle;

public class Employee_add_edit_remove implements Initializable {

    //general
    @FXML private Label id_label;

    // toppings
    @FXML private TextField topping_TF;
    @FXML private Label warning;

    //promocode
    @FXML private TextField promo_code_name_TF;
    @FXML private TextField discount_TF;

    //Type
    @FXML private TextField type_TF;

    //Size
    @FXML private TextField size_TF;
    @FXML private TextField price_TF;

    //Pizza
    @FXML private TextField pizza_name_TF;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        if(Toppings_Info.getClass_type()){
            set_toppings();
            Toppings_Info.setClass_type(false);
        }
        else if(Pizza_type_Info.getClass_type()){
            set_type();
            Pizza_type_Info.setClass_type(false);
        }
        else if(Promo_Code_Info.isClass_type()){
            set_promo_code();
            Promo_Code_Info.setClass_type(false);
        }
        else if(Size_Info.isClass_type()){
            set_size();
            Size_Info.setClass_type(false);
        }
        else if(PizzaInfo.isClass_type()){
            set_pizza();
            PizzaInfo.setClass_type(false);
        }

    }

    public void set_toppings(){

        int id = Toppings_Info.getTemp_id();
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        session.getTransaction().begin();

        Toppings topping;
        topping = session.get(Toppings.class, id);

        id_label.setText("ID: "+topping.getId());
        topping_TF.setText(topping.getTopping_Name());

        session.getTransaction().commit();
        session.close();
    }

    public void set_type(){

        int id = Pizza_type_Info.getTemp_id();
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        session.getTransaction().begin();

        Pizza_Type type;
        type = session.get(Pizza_Type.class, id);

        id_label.setText("ID: "+type.getId());
        type_TF.setText(type.getPizza_Type());

        session.getTransaction().commit();
        session.close();
    }

    public void set_promo_code(){

        int id = Promo_Code_Info.getTemp_id();
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        session.getTransaction().begin();

        Promo_Codes promocode;
        promocode = session.get(Promo_Codes.class, id);

        id_label.setText("ID: "+promocode.getId());
        promo_code_name_TF.setText(promocode.getPromo_Code());
        discount_TF.setText(Integer.toString(promocode.getPercent_Off()));

        session.getTransaction().commit();
        session.close();
    }

    public void set_size(){

        int id = Size_Info.getTemp_id();
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        session.getTransaction().begin();

        Size size;
        size = session.get(Size.class, id);

        id_label.setText("ID: "+size.getId());
        size_TF.setText(size.getSize());
        price_TF.setText(size.getPrice());

        session.getTransaction().commit();
        session.close();
    }

    public void set_pizza(){

        int id = PizzaInfo.getTemp_id();
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        session.getTransaction().begin();

        Pizza pizza;
        pizza = session.get(Pizza.class, id);

        id_label.setText("ID: "+pizza.getId());
        pizza_name_TF.setText(pizza.getPizza_Name());
        type_TF.setText(pizza.getPizza_Type_ID().getPizza_Type());

        session.getTransaction().commit();
        session.close();
    }

    public void add_pizza(ActionEvent event){
        //pizza_name.getText();
        //CHECK IF PIZZA OF THIS NAME ALREADY EXISTS
        //IF YES warning.setText("Pizza already exists");
        // IF NO
        //pizza_toppings.getText();
        //pizza_type.getText();
        //add pizza to the database
    }


    public void remove_pizza(ActionEvent event){
        //select all pizzainfo of this.searchedID and display it on textfields
    }
    public void edit_pizza(ActionEvent event){
        //select all pizzainfo of this.searchedID and display it on textfields
        //pizza_name.getText();
        //CHECK IF PIZZA OF THIS NAME ALREADY EXISTS
        //IF YES warning.setText("Pizza already exists");
        // IF NO
        //pizza_toppings.getText();
        //pizza_type.getText();
        //alter pizza info of given ID
    }
    public void remove_promo_code(ActionEvent event){
        //seatch for id from this.searchedID
        //and remove from database
    }
    public void add_promo_code(ActionEvent event){
        //search IF promo_code.getText() exists in database
        //if yes warning.setText("promo code exists in database");
        //if no, add
    }
}

