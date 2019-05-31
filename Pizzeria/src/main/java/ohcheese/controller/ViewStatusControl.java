package ohcheese.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import ohcheese.Utilities.HibernateUtil;
import ohcheese.model.Shopping_Cart;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;



public class ViewStatusControl extends GeneralWindowControl implements Initializable {

    private SessionFactory factory = HibernateUtil.getSessionFactory();

    @FXML VBox acceptedVbox = new VBox();
    @FXML VBox bakingVbox = new VBox();
    @FXML VBox sendingVbox = new VBox();
    @FXML Line first = new Line();
    @FXML Line second = new Line();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateStatus();
    }
    public void refresh(ActionEvent e){
        updateStatus();
    }

    public void updateStatus(){

        Session session = factory.getCurrentSession();
        try {
            session.getTransaction().begin();


            Query query = session.createQuery("from Shopping_Cart where Shopping_Cart_ID = "+CheckoutControl.getCart().getId());
            List<Shopping_Cart> shopping_cart = query.list();

            int status = shopping_cart.get(0).getOrder_status_ID().getId();

            if(status == 2){
                acceptedVbox.getStyleClass().clear();
                acceptedVbox.getStyleClass().add("status_Active");
                first.getStyleClass().clear();
                first.getStyleClass().add("lineActive");
            }

            if(status == 3) {
                bakingVbox.getStyleClass().clear();
                bakingVbox.getStyleClass().add("status_Active");
                second.getStyleClass().clear();
                second.getStyleClass().add("lineActive");
            }

            if(status == 4) {
                sendingVbox.getStyleClass().clear();
                sendingVbox.getStyleClass().add("status_Active");
            }

            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();


    }
}
