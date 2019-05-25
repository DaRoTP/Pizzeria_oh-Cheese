package ohcheese.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import ohcheese.Utilities.HibernateUtil;
import ohcheese.model.*;
import ohcheese.model.helper.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

public class Employee_add_edit_remove implements Initializable {

    //general
    @FXML private Label id_label;
    @FXML private ChoiceBox<String> delivery_choice = new ChoiceBox<String>();
    @FXML private TextArea order_info_TA = new TextArea();

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

    //Shopping Cart
    @FXML private TextField name_TF;
    @FXML private TextField surname_TF;
    @FXML private TextField e_mail_TF;
    @FXML private TextField phone_number_TF;
    @FXML private TextField username_TF;
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
        set_delivery_drivers();

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
        else if(Shopping_Cart_Info.isClass_type()){
            set_shopping_cart();
            Shopping_Cart_Info.setClass_type(false);
            get_Order_details();
        }

    }

    public void get_Order_details(){
        SessionFactory factory = ohcheese.Utilities.HibernateUtil.getSessionFactory();
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

    public void select_driver(){
        System.out.println("test --------");
        String temp = delivery_choice.getValue();
        String number = "";
        for (int i = 1; i < temp.length(); i++){
            char c = temp.charAt(i);
            if(c != '|')
                number += c;
            else
                break;
        }
        SessionFactory factory = ohcheese.Utilities.HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        session.getTransaction().begin();

        System.out.println("selects--------");
        Shopping_Cart shopping_cart = session.get(Shopping_Cart.class, Shopping_Cart_Info.getTemp_id());
        Employee employee_driver = session.get(Employee.class, 2);
        System.out.println("add--------");
        Set<Employee> employeesss = new HashSet<>();
        employeesss.add(employee_driver);
        shopping_cart.setEmployee(employeesss);
        System.out.println("update--------");
        session.update(shopping_cart);

        session.getTransaction().commit();
        session.close();

    }

    //ORDER STATUS
    public void change_order_status(int status){
        SessionFactory factory = ohcheese.Utilities.HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        session.getTransaction().begin();

        Shopping_Cart shopping_cart = session.get(Shopping_Cart.class, Shopping_Cart_Info.getTemp_id());
        Order_status order_status = session.get(Order_status.class, status);
        shopping_cart.setOrder_status_ID(order_status);
        session.update(shopping_cart);

        session.getTransaction().commit();
        session.close();
    }

    public void accepted_order(ActionEvent event){
        activeOrder.getStyleClass().clear();
        activeOrder.getStyleClass().add("price");
//        change_order_status(2);
        select_driver();
    }
    public void baking_order(ActionEvent event){
        bakingOrder.getStyleClass().clear();
        bakingOrder.getStyleClass().add("price");
        change_order_status(3);

    }
    public void delivering_order(ActionEvent event){
        deliveringOrder.getStyleClass().clear();
        deliveringOrder.getStyleClass().add("price");
        change_order_status(4);
    }

    public void set_delivery_drivers(){
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        try {
            session.getTransaction().begin();

            Query query = session.createQuery("from Employee where Job_Position_ID=5");
            List<Employee> employee_list = query.list();
            for(int i = 0; i < employee_list.size(); i++)
                delivery_choice.getItems().add("#"+employee_list.get(i).getId()+"|"+employee_list.get(i).getName()+" "+
                        employee_list.get(i).getSurname());


            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();
    }

    //ON EDIT BUTTON PRESS
    public void set_toppings(){

        int id = Toppings_Info.getTemp_id();
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        session.getTransaction().begin();

        Toppings topping = session.get(Toppings.class, id);

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

    public void set_shopping_cart(){

        int id = Shopping_Cart_Info.getTemp_id();
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        session.getTransaction().begin();

        Shopping_Cart scart;
        scart = session.get(Shopping_Cart.class, id);

//        id_label.setText("ID: "+scart.getId());
        name_TF.setText(scart.getCustomer_ID().getName());
        surname_TF.setText(scart.getCustomer_ID().getSurname());
        e_mail_TF.setText(scart.getCustomer_ID().getEmail());
        phone_number_TF.setText(scart.getCustomer_ID().getPhone_Number());
        username_TF.setText(scart.getCustomer_ID().getUsername());
        city_TF.setText(scart.getCustomer_ID().getAddress_ID().getCity());
        house_TF.setText(scart.getCustomer_ID().getAddress_ID().getHouse_Number());
        apartment_TF.setText(scart.getCustomer_ID().getAddress_ID().getApartment_Number());
        zipcode_TF.setText(scart.getCustomer_ID().getAddress_ID().getZIP_Code());
        street_TF.setText(scart.getCustomer_ID().getAddress_ID().getStreet());

        session.getTransaction().commit();
        session.close();
    }



    //PIZZA
    public void add_pizza(ActionEvent event){
        //pizza_name.getText();
        //CHECK IF PIZZA OF THIS NAME ALREADY EXISTS
        //IF YES warning.setText("Pizza already exists");
        // IF NO
        //pizza_toppings.getText();
        //pizza_type.getText();
        //add pizza to the database
    }

    //PROMO CODES
    public void update_promo_code(ActionEvent event){
        SessionFactory factory = ohcheese.Utilities.HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();

        try {
            session.getTransaction().begin();

                Promo_Codes updated_promocode = session.get(Promo_Codes.class, Promo_Code_Info.getTemp_id());
                updated_promocode.setPercent_Off(Integer.parseInt(discount_TF.getText()));
                session.update(updated_promocode);

                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.close();

            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();
    }
    public void delete_promo_code(ActionEvent event){
        SessionFactory factory = ohcheese.Utilities.HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();

        try {
            session.getTransaction().begin();

            Promo_Codes delete_promocode = session.get(Promo_Codes.class, Promo_Code_Info.getTemp_id());
            session.delete(delete_promocode);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.close();

            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();
    }
    public void add_promo_code(ActionEvent event){
        SessionFactory factory = ohcheese.Utilities.HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();

        try {
            session.getTransaction().begin();

            Query query = session.createQuery("from Promo_Codes where Promo_Code_Name='"+promo_code_name_TF.getText()+"'");
            List<Promo_Codes> promo_codes_list = query.list();

            if(promo_codes_list.size() == 0) {
                Promo_Codes new_promocode = new Promo_Codes(promo_code_name_TF.getText(),Integer.parseInt(discount_TF.getText()));
                session.save(new_promocode);

                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.close();
            }
            else
                warning.setText("Promo Code already exists");

            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();
    }

    //Toppings
    public void update_topping(ActionEvent event){
        SessionFactory factory = ohcheese.Utilities.HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();

        try {
            session.getTransaction().begin();

            Toppings updated_topping = session.get(Toppings.class, Toppings_Info.getTemp_id());
            updated_topping.setTopping_Name(topping_TF.getText());
            session.update(updated_topping);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.close();

            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();
    }
    public void delete_topping(ActionEvent event){
        SessionFactory factory = ohcheese.Utilities.HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();

        try {
            session.getTransaction().begin();

            Toppings delete_topping = session.get(Toppings.class, Toppings_Info.getTemp_id());
            session.delete(delete_topping);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.close();

            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();
    }
    public void add_topping(ActionEvent event){
        SessionFactory factory = ohcheese.Utilities.HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();

        try {
            session.getTransaction().begin();

            Query query = session.createQuery("from Toppings where Topping_Name='"+topping_TF.getText()+"'");
            List toppings = query.list();

            if(toppings.size() == 0) {
                Toppings new_topping = new Toppings(topping_TF.getText());
                session.save(new_topping);

                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.close();
            }
            else
                warning.setText("Topping already exists");

            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();
    }

    //SIZE
    public void update_size(ActionEvent event){
        SessionFactory factory = ohcheese.Utilities.HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();

        try {
            session.getTransaction().begin();

            Query query = session.createQuery("from Size where Size_Name='"+size_TF.getText()+"' and Price='"+price_TF.getText()+"'");
            List size_list = query.list();

            if(size_list.size() == 0) {
                Size updated_size = session.get(Size.class, Size_Info.getTemp_id());
                updated_size.setSize(size_TF.getText());
                updated_size.setPrice(price_TF.getText());
                session.update(updated_size);

                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.close();
            }
            else
                warning.setText("Size already exists");

            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();
    }
    public void delete_size(ActionEvent event){
        SessionFactory factory = ohcheese.Utilities.HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();

        try {
            session.getTransaction().begin();

            Size delete_size = session.get(Size.class, Size_Info.getTemp_id());
            session.delete(delete_size);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.close();

            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();
    }
    public void add_size(ActionEvent event){
        SessionFactory factory = ohcheese.Utilities.HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();

        try {
            session.getTransaction().begin();

            Query query = session.createQuery("from Size where Size_Name='"+size_TF.getText()+"'");
            List size_list = query.list();

            if(size_list.size() == 0) {
                Size new_size = new Size(size_TF.getText(),price_TF.getText());
                session.save(new_size);

                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.close();
            }
            else
                warning.setText("Size already exists");

            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();
    }

    //PIZZA TYPE
    public void update_type(ActionEvent event){
        SessionFactory factory = ohcheese.Utilities.HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();

        try {
            session.getTransaction().begin();

            Query query = session.createQuery("from Pizza_Type where Pizza_Type_Name='"+type_TF.getText()+"'");
            List type_list = query.list();

            if(type_list.size() == 0) {
                Pizza_Type updated_type = session.get(Pizza_Type.class, Pizza_type_Info.getTemp_id());
                updated_type.setPizza_Type(type_TF.getText());
                session.update(updated_type);

                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.close();
            }
            else
                warning.setText("Pizza Type already exists");

            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();
    }
    public void delete_typ(ActionEvent event){
        SessionFactory factory = ohcheese.Utilities.HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();

        try {
            session.getTransaction().begin();

            Pizza_Type delete_type = session.get(Pizza_Type.class, Pizza_type_Info.getTemp_id());
            session.delete(delete_type);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.close();

            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();
    }
    public void add_type(ActionEvent event){

        SessionFactory factory = ohcheese.Utilities.HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();

        try {
            session.getTransaction().begin();

            Query query = session.createQuery("from Pizza_Type where Pizza_Type_Name='"+type_TF.getText()+"'");
            List<Pizza_Type> type = query.list();

            if(type.size() == 0) {

                Pizza_Type new_type = new Pizza_Type(type_TF.getText());
                session.save(new_type);

                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.close();
            }
            else
                warning.setText("Type already exists");

            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();
    }




}

