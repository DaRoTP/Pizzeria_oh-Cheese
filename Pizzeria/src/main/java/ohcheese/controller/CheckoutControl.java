package ohcheese.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
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

public class CheckoutControl{

    Order newOrder;
    public static Shopping_Cart cart;

    public static Shopping_Cart getCart() { return cart; }
    public static void setCart(Shopping_Cart cart) { CheckoutControl.cart = cart; }

    @FXML Label warning = new Label();
    @FXML Label Price = new Label();
    @FXML TextArea OrderInfo = new TextArea();
    @FXML TextField promoCode_Field = new TextField();
    @FXML Button redeem = new Button();

    private List<Order_status> status;
    private List<Pizza> pizza;
    private List<Size> size;
    private List<Promo_Codes> promoCodes;

    private Promo_Codes used_Promo_Code;



    public void getOrder(Order newOrder){
        this.newOrder = newOrder;
        Price.setText(newOrder.getFinalPrice()+" zlt");
        display_message();
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

    public Promo_Codes find_promoc_Code(List<Promo_Codes> find, String code){
        for(int i = 0; i < find.size(); i++){
            if(find.get(i).getPromo_Code() == code){
                return find.get(i);
            }
        }
        return null;
    }

    public void display_message(){
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        try {
            session.getTransaction().begin();

            Query query = session.createQuery("from Order_status");
            status = query.list();
            query = session.createQuery("from Pizza");
            pizza = query.list();
            query = session.createQuery("from Size");
            size = query.list();
            query = session.createQuery("from Promo_Codes");
            promoCodes = query.list();

            String message = new String();
            message = "";
            for (int i = 0; i < newOrder.getPizzasIDs().size(); i++) {
                Pizza found_pizza = find_pizza(pizza, newOrder.getPizzasIDs().get(i));
                Size found_size = find_size(size, newOrder.getSizeIDs().get(i));

                message += found_pizza.getPizza_Name() +" - ";
                message += found_size.getSize() + " - ";
                message += found_size.getPrice() +" zł \n";

            }
            session.getTransaction().commit();
            OrderInfo.setText(message);


        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();
    }

    public void create_Order_DataBase(){
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        try {
            session.getTransaction().begin();

            Customer user = LoginControl.get_loggedinCustomer();
            cart = new Shopping_Cart(user,user.getAddress_ID(),status.get(0));

            if(used_Promo_Code != null){
                cart.setPromo_Code_ID(used_Promo_Code);
            }

            session.save(cart);

            for (int i = 0; i < newOrder.getPizzasIDs().size(); i++) {
                Pizza found_pizza = find_pizza(pizza, newOrder.getPizzasIDs().get(i));
                Size found_size = find_size(size, newOrder.getSizeIDs().get(i));

                Pizza_Order newPizza = new Pizza_Order(found_pizza,found_size,cart);
                session.save(newPizza);
            }

            session.getTransaction().commit();


        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();
    }

    public void redeem_promo_code(){
        for(int i = 0; i < promoCodes.size(); i++) {
            if (promoCode_Field.getText().equals(promoCodes.get(i).getPromo_Code())){
                newOrder.setFinalPrice(newOrder.getFinalPrice() * (1 - (((float)promoCodes.get(i).getPercent_Off())/100)));
                Price.setText(newOrder.getFinalPrice()+" zlt");
                used_Promo_Code = promoCodes.get(i);
                redeem.setDisable(true);
                warning.setText("REDEEMED!");
                break;
            }
            else{
                warning.setText("Invalid Promo Code");
            }

        }
    }


    public void submit(ActionEvent event) throws IOException {

        create_Order_DataBase();
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.close();

    }
}
