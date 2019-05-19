package ohcheese.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import ohcheese.Utilities.HibernateUtil;
import ohcheese.model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CheckoutControl implements Initializable {

    Order newOrder;

    @FXML RadioButton card = new RadioButton();
    @FXML RadioButton cash = new RadioButton();
    @FXML Label warning = new Label();
    @FXML Label Price = new Label();
    @FXML TextArea OrderInfo = new TextArea();


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

    }



    public String displayOrderInfo(){
        String message = "";
        message += "Price:  "+newOrder.getFinalPrice()+" zlt\n";
        message += "----------------\n";
        for (int i = 0; i < newOrder.getPizzasIDs().size(); i++) {
            message += "- ID: " + newOrder.getPizzasIDs().get(i) + " -size: " + newOrder.getSizeIDs().get(i)+"\n";
        }
        return message;
    }

    public void getOrder(Order newOrder){
        this.newOrder = newOrder;
        Price.setText(newOrder.getFinalPrice()+" zlt");
        OrderInfo.setText(displayOrderInfo());
    }

    public Pizza find_pizza(List<Pizza> find, int index){
        for(int i = 0; i < find.size(); i++){
            if(find.get(i).getId() == index){
                return find.get(i);
            }
        }

        return null;
    }

    public Size find_size(List<Size> find, int index){
        for(int i = 0; i < find.size(); i++){
            if(find.get(i).getId() == index + 1){
                return find.get(i);
            }
        }

        return null;
    }

    public void create_Order_DataBase(){
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        try {
            session.getTransaction().begin();

            Query query = session.createQuery("from Order_status");
            List<Order_status> status = query.list();
            query = session.createQuery("from Pizza");
            List<Pizza> pizza = query.list();
            query = session.createQuery("from Size");
            List<Size> size = query.list();

            Customer user = LoginControl.get_loggedinCustomer();
            Shopping_Cart cart = new Shopping_Cart(user,user.getAddress_ID(),status.get(0));


            session.save(cart);
//            session.getTransaction().commit();

            for (int i = 0; i < newOrder.getPizzasIDs().size(); i++) {
                Pizza_Order newPizza = new Pizza_Order(find_pizza(pizza, newOrder.getPizzasIDs().get(i)),find_size(size, newOrder.getSizeIDs().get(i)),cart);
                session.save(newPizza);
            }
            System.out.println(cart.getId());
            session.getTransaction().commit();


        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();
    }


    public void submit(ActionEvent event) throws IOException {
        if (cash.isSelected() || card.isSelected()) {

            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.close();


                if(cash.isSelected())
                    newOrder.setPaymentMethod(0);
                else
                    newOrder.setPaymentMethod(1);
            create_Order_DataBase();

        }
        else
        {
            warning.setText("Please check payment method");
        }
    }
}
