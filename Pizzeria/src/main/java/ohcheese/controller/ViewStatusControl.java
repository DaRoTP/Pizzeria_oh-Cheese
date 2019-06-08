package ohcheese.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import ohcheese.Utilities.HibernateUtil;
import ohcheese.model.Pizza_Order;
import ohcheese.model.Shopping_Cart;
import ohcheese.model.helper.Shopping_Cart_Info;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.io.IOException;
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

    @FXML private TableView<Shopping_Cart_Info> ShoppingCartTable = new TableView<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        create_Shopping_Cart();
    }

    public void refresh(ActionEvent event){
        ShoppingCartTable.getColumns().clear();
        create_Shopping_Cart();
    }

    public void create_Shopping_Cart(){


        TableColumn<Shopping_Cart_Info, String> id = new TableColumn<>("ID");
        id.setCellValueFactory(new PropertyValueFactory<>("SC_ID"));

        TableColumn<Shopping_Cart_Info, String> finalprice = new TableColumn<>("Price");
        finalprice.setCellValueFactory(new PropertyValueFactory<>("final_price"));

//        TableColumn<Shopping_Cart_Info, String> promocode = new TableColumn<>("Promocode");
//        promocode.setCellValueFactory(new PropertyValueFactory<>("promocodeName"));


        TableColumn<Shopping_Cart_Info, String> image1 = new TableColumn<>("Accepted");
        image1.setCellValueFactory(new PropertyValueFactory<>("acceptedIV"));
        image1.setMaxWidth(900);
        image1.setMinWidth(70);

        TableColumn<Shopping_Cart_Info, String> image2 = new TableColumn<>("Baking");
        image2.setCellValueFactory(new PropertyValueFactory<>("bakingIV"));
        image2.setMaxWidth(900);
        image2.setMinWidth(70);

        TableColumn<Shopping_Cart_Info, String> image3 = new TableColumn<>("Delivery");
        image3.setCellValueFactory(new PropertyValueFactory<>("deliveryIV"));
        image3.setMaxWidth(900);
        image3.setMinWidth(70);

        TableColumn<Shopping_Cart_Info, String> image4 = new TableColumn<>("Finished");
        image4.setCellValueFactory(new PropertyValueFactory<>("finishedIV"));
        image4.setMaxWidth(900);
        image4.setMinWidth(70);

        TableColumn<Shopping_Cart_Info, String> view = new TableColumn<>("View");
        view.setCellValueFactory(new PropertyValueFactory<>("view_btn"));
        view.setMinWidth(70);
        view.setMaxWidth(70);


        ShoppingCartTable.setItems(getCart());
        ShoppingCartTable.getColumns().addAll(id,finalprice,image1,image2,image3,image4,view);
        ShoppingCartTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    public ObservableList<Shopping_Cart_Info> getCart(){
        ObservableList<Shopping_Cart_Info> shoppingcart = FXCollections.observableArrayList();

        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        float final_price_temp = 0;
        try {
            session.getTransaction().begin();

            Query query = session.createQuery("from Shopping_Cart where Customer_ID="+LoginControl.get_loggedinCustomer().getId());
            List<Shopping_Cart> sc_list = query.list();
            System.out.println(sc_list.get(0).getCustomer_ID().getName());

            for(int i = 0; i < sc_list.size(); i++){

                query = session.createQuery("from Pizza_Order where Shopping_Cart_ID='" + sc_list.get(i).getId() + "'");
                List<Pizza_Order> Order_list = query.list();
//
                for (int j = 0; j < Order_list.size(); j++) {
                    final_price_temp += Float.parseFloat(Order_list.get(j).getSize_ID().getPrice());
                }
                shoppingcart.add(new Shopping_Cart_Info(sc_list.get(i).getId(),sc_list.get(i).getCustomer_ID(),sc_list.get(i).getAddress_ID(),
                        sc_list.get(i).getPromo_Code_ID(),sc_list.get(i).getOrder_status_ID(),final_price_temp));
                final_price_temp = 0;

            }
            session.getTransaction().commit();
            return shoppingcart;

        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();

        return null;
    }

    public void exitToMenu(ActionEvent event) throws IOException {
        changescene(event, "Customer", "Customer", "Customer", "Customer");
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
