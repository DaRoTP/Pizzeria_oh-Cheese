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
import ohcheese.model.helper.*;
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

    @FXML private TableView<PizzaInfo> pizzaTable = new TableView<>();
    @FXML private TableView<Promo_Code_Info> promocodeTable = new TableView<>();
    @FXML private TableView<Toppings_Info> toppingTable = new TableView<>();
    @FXML private TableView<Pizza_type_Info> typeTable = new TableView<>();
    @FXML private TableView<Size_Info> sizeTable = new TableView<>();
    @FXML private TableView<Shopping_Cart_Info> ShoppingCartTable = new TableView<>();

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
        create_toppings();
        create_pizzatype();
        create_size();
        create_promo_code();
        create_Shopping_Cart();


    }


    public void create_pizzaTable(){
        TableColumn<PizzaInfo, Integer> IdColumn = new TableColumn<>("ID");
        IdColumn.setCellValueFactory(new PropertyValueFactory<>("pizzaID"));
        IdColumn.setMinWidth(53);

        TableColumn<PizzaInfo, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("pizzaname"));
        nameColumn.setMinWidth(267);


        TableColumn<PizzaInfo, String> button = new TableColumn<>("Edit");
        button.setCellValueFactory(new PropertyValueFactory<>("edit_btn"));
        button.setMinWidth(69);




        pizzaTable.setItems(getPizzas());
        pizzaTable.getColumns().addAll(IdColumn,nameColumn,button);
    }

    public void create_promo_code(){
        TableColumn<Promo_Code_Info, Integer> IdColumn = new TableColumn<>("ID");
        IdColumn.setCellValueFactory(new PropertyValueFactory<>("promo_code_id"));
        IdColumn.setMinWidth(53);

        TableColumn<Promo_Code_Info, String> Promo_Code = new TableColumn<>("Promo Code");
        Promo_Code.setCellValueFactory(new PropertyValueFactory<>("promo_code_name"));
        Promo_Code.setMinWidth(132);

        TableColumn<Promo_Code_Info, Integer> Percent_Off = new TableColumn<>("Discount");
        Percent_Off.setCellValueFactory(new PropertyValueFactory<>("discount"));
        Percent_Off.setMinWidth(60);

        TableColumn<Promo_Code_Info, String> button = new TableColumn<>("Edit");
        button.setCellValueFactory(new PropertyValueFactory<>("edit_btn"));
        button.setMinWidth(69);

        promocodeTable.setItems(getPromoCodes());
        promocodeTable.getColumns().addAll(IdColumn,Promo_Code,Percent_Off,button);
    }

    public void create_toppings(){
        TableColumn<Toppings_Info, Integer> IdColumn = new TableColumn<>("ID");
        IdColumn.setCellValueFactory(new PropertyValueFactory<>("topping_id"));
        IdColumn.setMinWidth(60);

        TableColumn<Toppings_Info, String> topping_name = new TableColumn<>("Toppings");
        topping_name.setCellValueFactory(new PropertyValueFactory<>("topping_name"));
        topping_name.setMinWidth(126);

        TableColumn<Toppings_Info, String> edit = new TableColumn<>("Edit");
        edit.setCellValueFactory(new PropertyValueFactory<>("edit_btn"));
        edit.setMinWidth(72);


        toppingTable.setItems(getToppings());
        toppingTable.getColumns().addAll(IdColumn,topping_name,edit);
    }

    public void create_pizzatype(){
        TableColumn<Pizza_type_Info, Integer> IdColumn = new TableColumn<>("ID");
        IdColumn.setCellValueFactory(new PropertyValueFactory<>("pizza_type_ID"));
        IdColumn.setMinWidth(60);

        TableColumn<Pizza_type_Info, String> type_name = new TableColumn<>("Pizza Type");
        type_name.setCellValueFactory(new PropertyValueFactory<>("pizza_type"));
        type_name.setMinWidth(126);

        TableColumn<Pizza_type_Info, String> edit = new TableColumn<>("Edit");
        edit.setCellValueFactory(new PropertyValueFactory<>("edit_btn"));
        edit.setMinWidth(72);


        typeTable.setItems(getPizzaType());
        typeTable.getColumns().addAll(IdColumn,type_name,edit);
    }

    public void create_size(){
        TableColumn<Size_Info, Integer> IdColumn = new TableColumn<>("ID");
        IdColumn.setCellValueFactory(new PropertyValueFactory<>("size_ID"));
        IdColumn.setMinWidth(60);

        TableColumn<Size_Info, String> Size = new TableColumn<>("Size");
        Size.setCellValueFactory(new PropertyValueFactory<>("Size"));
        Size.setMinWidth(126);

        TableColumn<Size_Info, String> price = new TableColumn<>("Price");
        price.setCellValueFactory(new PropertyValueFactory<>("Price"));
        price.setMinWidth(126);

        TableColumn<Size_Info, String> edit = new TableColumn<>("Edit");
        edit.setCellValueFactory(new PropertyValueFactory<>("edit_btn"));
        edit.setMinWidth(72);


        sizeTable.setItems(getSize());
        sizeTable.getColumns().addAll(IdColumn,Size,price,edit);
    }

    public void create_Shopping_Cart(){
        TableColumn<Shopping_Cart_Info, Integer> IdColumn = new TableColumn<>("ID");
        IdColumn.setCellValueFactory(new PropertyValueFactory<>("SC_ID"));
        IdColumn.setMinWidth(60);

        TableColumn<Shopping_Cart_Info, String> username = new TableColumn<>("Customer");
        username.setCellValueFactory(new PropertyValueFactory<>("username"));
        username.setMinWidth(60);

        TableColumn<Shopping_Cart_Info, String> steet = new TableColumn<>("Street");
        steet.setCellValueFactory(new PropertyValueFactory<>("street"));
        steet.setMinWidth(60);

        TableColumn<Shopping_Cart_Info, String> city = new TableColumn<>("City");
        city.setCellValueFactory(new PropertyValueFactory<>("city"));
        city.setMinWidth(60);

        TableColumn<Shopping_Cart_Info, String> house_Nr = new TableColumn<>("House Number");
        house_Nr.setCellValueFactory(new PropertyValueFactory<>("house_Nr"));
        house_Nr.setMinWidth(60);

        TableColumn<Shopping_Cart_Info, String> apartment_Nr = new TableColumn<>("Apartment Number");
        apartment_Nr.setCellValueFactory(new PropertyValueFactory<>("apartment_Nr"));
        apartment_Nr.setMinWidth(60);

        TableColumn<Shopping_Cart_Info, String> status = new TableColumn<>("Status");
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
        status.setMinWidth(60);

        TableColumn<Shopping_Cart_Info, String> edit = new TableColumn<>("Edit");
        edit.setCellValueFactory(new PropertyValueFactory<>("edit_btn"));
        edit.setMinWidth(72);


        ShoppingCartTable.setItems(getCart());
        ShoppingCartTable.getColumns().addAll(IdColumn,username,city,steet,house_Nr,apartment_Nr,status,edit);
    }

    public ObservableList<Pizza_type_Info> getPizzaType(){
        ObservableList<Pizza_type_Info> pizza_type = FXCollections.observableArrayList();

        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        try {
            session.getTransaction().begin();

            Query query = session.createQuery("from Pizza_Type");
            List<Pizza_Type> pizzatype_list = query.list();

            for(int i = 0; i < pizzatype_list.size(); i++){
                pizza_type.add(new Pizza_type_Info(pizzatype_list.get(i).getId(),pizzatype_list.get(i).getPizza_Type()));
            }
            session.getTransaction().commit();
            return pizza_type;

        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();

        return null;
    }

    public ObservableList<Promo_Code_Info> getPromoCodes(){
        ObservableList<Promo_Code_Info> promocodes = FXCollections.observableArrayList();

        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        try {
            session.getTransaction().begin();

            Query query = session.createQuery("from Promo_Codes");
            List<Promo_Codes> promocode_list = query.list();

            for(int i = 0; i < promocode_list.size(); i++){
                promocodes.add(new Promo_Code_Info(promocode_list.get(i).getId(),promocode_list.get(i).getPromo_Code(),promocode_list.get(i).getPercent_Off()) );
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

    public ObservableList<Toppings_Info> getToppings(){
        ObservableList<Toppings_Info> topping = FXCollections.observableArrayList();

        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        try {
            session.getTransaction().begin();

            Query query = session.createQuery("from Toppings");
            List<Toppings> topping_list = query.list();

            for(int i = 0; i < topping_list.size(); i++){
                topping.add(new Toppings_Info(topping_list.get(i).getId(),topping_list.get(i).getTopping_Name()));
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

    public ObservableList<PizzaInfo> getPizzas(){
        ObservableList<PizzaInfo> pizzas = FXCollections.observableArrayList();

        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        try {
            session.getTransaction().begin();

            Query query = session.createQuery("from Pizza");
            List<Pizza> pizzas_list = query.list();

            for(int i = 0; i < pizzas_list.size(); i++){
                pizzas.add(new PizzaInfo(pizzas_list.get(i).getId(),pizzas_list.get(i).getPizza_Name()));
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

    public ObservableList<Size_Info> getSize(){
        ObservableList<Size_Info> size = FXCollections.observableArrayList();

        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        try {
            session.getTransaction().begin();

            Query query = session.createQuery("from Size");
            List<Size> size_list = query.list();

            for(int i = 0; i < size_list.size(); i++){
                size.add(new Size_Info(size_list.get(i).getId(),size_list.get(i).getSize(),size_list.get(i).getPrice()));
            }
            session.getTransaction().commit();
            return size;

        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();

        return null;
    }

    public ObservableList<Shopping_Cart_Info> getCart(){
        ObservableList<Shopping_Cart_Info> size = FXCollections.observableArrayList();

        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        try {
            session.getTransaction().begin();

            Query query = session.createQuery("from Shopping_Cart");
            List<Shopping_Cart> sc_list = query.list();

            for(int i = 0; i < sc_list.size(); i++){
                size.add(new Shopping_Cart_Info(sc_list.get(i).getId(),sc_list.get(i).getCustomer_ID(),sc_list.get(i).getAddress_ID(),
                        sc_list.get(i).getPromo_Code_ID(),sc_list.get(i).getOrder_status_ID()));
            }
            session.getTransaction().commit();
            return size;

        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();

        return null;
    }

    public void Open_Add_Pizza(ActionEvent event) throws IOException {
        openscene(event, "addpizza","GeneralWindowStyle", "Employee/controls","Global_Resources");
    }
    public void Open_Add_Toppings(ActionEvent event) throws IOException {
        openscene(event, "addpromocode","GeneralWindowStyle", "Employee/controls","Global_Resources");
    }
    public void Open_Add_Promo_Code(ActionEvent event) throws IOException {
        openscene(event, "addpromocode","GeneralWindowStyle", "Employee/controls","Global_Resources");
    }
    public void Open_Add_Type(ActionEvent event) throws IOException {
        openscene(event, "addtype","GeneralWindowStyle", "Employee/controls","Global_Resources");
    }
    public void Open_Add_Size(ActionEvent event) throws IOException {
        openscene(event, "addSize","GeneralWindowStyle", "Employee/controls","Global_Resources");
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




    public ObservableList<PizzaInfo> getPizzaInfo(){
        ObservableList<PizzaInfo> pizzas = FXCollections.observableArrayList();
        pizzas.add(new PizzaInfo(0,"1"));
        pizzas.add(new PizzaInfo(1,"2"));
        pizzas.add(new PizzaInfo(2,"3"));
        pizzas.add(new PizzaInfo(3,"4"));
        pizzas.add(new PizzaInfo(4,"5"));
        pizzas.add(new PizzaInfo(5,"6"));
        return pizzas;
    }
}
