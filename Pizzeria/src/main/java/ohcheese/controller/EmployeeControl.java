package ohcheese.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import ohcheese.model.PizzaInfo;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EmployeeControl extends GeneralWindowControl implements Initializable {

    private int EmployeeID;
    private int searchedID;

    @FXML private Label warning;

    @FXML private TableView<PizzaInfo> pizzaTable = new TableView<>();
    @FXML private TableView<PizzaInfo> prmocodes = new TableView<>();
    @FXML private TableView<PizzaInfo> TopPizzas = new TableView<>();
    @FXML private TableView<PizzaInfo> OrderRequests = new TableView<>();

    @FXML private TextField pizza_name;
    @FXML private TextField promo_code;
    @FXML private TextField searchIDField;

    @FXML private Button activeOrder = new Button();
    @FXML private Button bakingOrder = new Button();
    @FXML private Button deliveringOrder = new Button();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        create_pizzaTable();


    }
    public void create_pizzaTable(){
        TableColumn<PizzaInfo, Integer> IdColumn = new TableColumn<>("ID");
        IdColumn.setCellValueFactory(new PropertyValueFactory<>("pizzaID"));
        IdColumn.setMinWidth(53);

        TableColumn<PizzaInfo, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("pizzaname"));
        nameColumn.setMinWidth(134);

        TableColumn<PizzaInfo, String> descriptioonColumn = new TableColumn<>("Descriptioon");
        descriptioonColumn.setCellValueFactory(new PropertyValueFactory<>("descriptioon"));
        descriptioonColumn.setMinWidth(214);

        pizzaTable.setItems(getPizzaInfo());
        pizzaTable.getColumns().addAll(IdColumn,nameColumn,descriptioonColumn);
    }

    public void create_promo_code(){

    }

    public void getIDFirst(int ID){
        this.EmployeeID = ID;
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
