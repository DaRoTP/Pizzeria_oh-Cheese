package ohcheese.controller;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import ohcheese.Utilities.HibernateUtil;
import ohcheese.model.Promo_Codes;
import ohcheese.model.Toppings;
import ohcheese.model.helper.Promo_Code_Info;
import ohcheese.model.helper.Toppings_Info;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.net.URL;
import java.util.ResourceBundle;

public class Employee_add_edit_remove implements Initializable {

    // toppings
    @FXML private TextField topping_TF;
    @FXML private Label id_label;
    @FXML private Label warning;

    //promocode
    @FXML private TextField promo_code_name_TF;
    @FXML private TextField discount_TF;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        set_toppings();
//        set_promo_code();
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

    public void set_promo_code(){

        int id = Promo_Code_Info.getTemp_id();
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        session.getTransaction().begin();

        Promo_Codes promocode;
        promocode = session.get(Promo_Codes.class, id);

        id_label.setText("ID: "+promocode.getId());
        promo_code_name_TF.setText(promocode.getPromo_Code());
        discount_TF.setText(Float.toString(promocode.getPercent_Off()));

        session.getTransaction().commit();
        session.close();
    }



}
