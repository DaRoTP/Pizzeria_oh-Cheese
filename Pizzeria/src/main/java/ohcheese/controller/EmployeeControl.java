package ohcheese.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import ohcheese.Utilities.HibernateUtil;
import ohcheese.model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class EmployeeControl extends GeneralWindowControl implements Initializable {

    private int EmployeeID;
    private int searchedID;

    @FXML private Label warning;
    @FXML Label WelcomeUser = new Label();

    @FXML private TableView<Pizza> pizzaTable = new TableView<>();
    @FXML private TableView<Promo_Codes> prmocodeTable = new TableView<>();
    @FXML private TableView<Toppings> toppingTable = new TableView<>();
    @FXML private TableView<PizzaInfo> OrderRequests = new TableView<>();

    @FXML private TextField pizza_name;
    @FXML private TextField promo_code;
    @FXML private TextField searchIDField;

    @FXML private Button activeOrder = new Button();
    @FXML private Button bakingOrder = new Button();
    @FXML private Button deliveringOrder = new Button();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        WelcomeUser.setText("Welcome "+LoginControl.get_loggedinEmployee().getName()+" !");
        create_pizzaTable();
        create_promo_code();
        create_toppings();


    }
    public void create_pizzaTable(){
        TableColumn<Pizza, Integer> IdColumn = new TableColumn<>("ID");
        IdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        IdColumn.setMinWidth(53);

        TableColumn<Pizza, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("pizza_Name"));
        nameColumn.setMinWidth(134);

//        TableColumn<Pizza, String> toppings = new TableColumn<>("toppings");
//        toppings.setCellValueFactory(new PropertyValueFactory<>("descriptioon"));
//        toppings.setMinWidth(214);

        pizzaTable.setItems(getPizzas());
        pizzaTable.getColumns().addAll(IdColumn,nameColumn);
    }

    public void create_promo_code(){
        TableColumn<Promo_Codes, Integer> IdColumn = new TableColumn<>("ID");
        IdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        IdColumn.setMinWidth(53);

        TableColumn<Promo_Codes, String> Promo_Code = new TableColumn<>("Promo Code");
        Promo_Code.setCellValueFactory(new PropertyValueFactory<>("promo_Code"));
        Promo_Code.setMinWidth(80);

        TableColumn<Promo_Codes, Integer> Percent_Off = new TableColumn<>("Discount");
        Percent_Off.setCellValueFactory(new PropertyValueFactory<>("Percent_Off"));
        Percent_Off.setMinWidth(50);

        prmocodeTable.setItems(getPromoCodes());
        prmocodeTable.getColumns().addAll(IdColumn,Promo_Code,Percent_Off);
    }

    public void create_toppings(){
        TableColumn<Toppings, Integer> IdColumn = new TableColumn<>("ID");
        IdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        IdColumn.setMinWidth(53);

        TableColumn<Toppings, String> topping_name = new TableColumn<>("Toppings");
        topping_name.setCellValueFactory(new PropertyValueFactory<>("topping_Name"));
        topping_name.setMinWidth(80);



        toppingTable.setItems(getToppings());
        toppingTable.getColumns().addAll(IdColumn,topping_name);
    }


    public void add_pizza_SceneOpen(ActionEvent event) throws IOException {
        openscene(event, "addpizza","GeneralWindowStyle", "Employee","Global_Resources");
    }
    public void remove_pizza_SceneOpen(ActionEvent event) throws IOException {
        openscene(event, "removepizza","GeneralWindowStyle", "Employee","Global_Resources");
    }
    public void edit_pizza_SceneOpen(ActionEvent event) throws IOException {
        openscene(event, "editpizza","GeneralWindowStyle", "Employee","Global_Resources");
    }
    public void accept_order_SceneOpen(ActionEvent event) throws IOException {
        openscene(event, "Order","GeneralWindowStyle", "Order","Global_Resources");
    }
    public void remove_promo_code_SceneOpen(ActionEvent event) throws IOException {
        openscene(event, "remove_promocode","GeneralWindowStyle", "Employee","Global_Resources");
    }
    public void add_promo_code_SceneOpen(ActionEvent event) throws IOException {
        openscene(event, "add_promocode","GeneralWindowStyle", "Employee","Global_Resources");
    }

    public void refresh_table_content(ActionEvent event){
        pizzaTable.getColumns().clear();
        create_pizzaTable();
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

    public void searchID(ActionEvent event){
        this.searchedID = Integer.parseInt(searchIDField.getText());
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

    public void accept_order(ActionEvent event){
        activeOrder.getStyleClass().clear();
        activeOrder.getStyleClass().add("price");
    }
    public void baking_order(ActionEvent event){
        bakingOrder.getStyleClass().clear();
        bakingOrder.getStyleClass().add("price");

    }    public void delivering_order(ActionEvent event){
        deliveringOrder.getStyleClass().clear();
        deliveringOrder.getStyleClass().add("price");
    }


    public ObservableList<Pizza> getPizzas(){
        ObservableList<Pizza> pizzas = FXCollections.observableArrayList();

        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        try {
            session.getTransaction().begin();

            Query query = session.createQuery("from Pizza");
            List<Pizza> pizzas_list = query.list();

            for(int i = 0; i < pizzas_list.size(); i++){
                pizzas.add(pizzas_list.get(i));
            }
            session.getTransaction().commit();
            return pizzas;

        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();

        return null;
    }

    public ObservableList<Promo_Codes> getPromoCodes(){
        ObservableList<Promo_Codes> promocodes = FXCollections.observableArrayList();

        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        try {
            session.getTransaction().begin();

            Query query = session.createQuery("from Promo_Codes");
            List<Promo_Codes> promocode_list = query.list();

            for(int i = 0; i < promocode_list.size(); i++){
                promocodes.add(promocode_list.get(i));
            }
            session.getTransaction().commit();
            return promocodes;

        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();

        return null;
    }

    public ObservableList<Toppings> getToppings(){
        ObservableList<Toppings> topping = FXCollections.observableArrayList();

        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        try {
            session.getTransaction().begin();

            Query query = session.createQuery("from Toppings");
            List<Toppings> topping_list = query.list();

            for(int i = 0; i < topping_list.size(); i++){
                topping.add(topping_list.get(i));
            }
            session.getTransaction().commit();
            return topping;

        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();

        return null;
    }

    public ObservableList<PizzaInfo> getPizzaInfo(){
        ObservableList<PizzaInfo> pizzas = FXCollections.observableArrayList();
        pizzas.add(new PizzaInfo(0,"1","dwddwd"));
        pizzas.add(new PizzaInfo(1,"2","dwfsfswd"));
        pizzas.add(new PizzaInfo(2,"3","dfsfsefd"));
        pizzas.add(new PizzaInfo(3,"4","dwgtggggwd"));
        pizzas.add(new PizzaInfo(4,"5","drrrr"));
        pizzas.add(new PizzaInfo(5,"6","dwrrd"));
        return pizzas;
    }
}
