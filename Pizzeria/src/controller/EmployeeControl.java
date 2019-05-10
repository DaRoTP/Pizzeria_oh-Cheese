package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.PizzaInfo;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EmployeeControl extends GeneralWindowControl implements Initializable {

    private int EmployeeID;

    @FXML private TableView<PizzaInfo> pizzaTable = new TableView<>();
    @FXML private TableView<PizzaInfo> prmocodes = new TableView<>();
    @FXML private TableView<PizzaInfo> TopPizzas = new TableView<>();
    @FXML private TableView<PizzaInfo> OrderRequests = new TableView<>();
    @FXML private Button test;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        //name collumn
        TableColumn<PizzaInfo, Integer> IdColumn = new TableColumn<>("ID");
        IdColumn.setCellValueFactory(new PropertyValueFactory<>("pizzaID"));

        TableColumn<PizzaInfo, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("pizzaname"));

        TableColumn<PizzaInfo, String> descriptioonColumn = new TableColumn<>("Descriptioon");
        descriptioonColumn.setCellValueFactory(new PropertyValueFactory<>("descriptioon"));

        pizzaTable.setItems(getPizzaInfo());
        pizzaTable.getColumns().addAll(IdColumn,nameColumn,descriptioonColumn);

        prmocodes.setItems(getPizzaInfo());
        prmocodes.getColumns().addAll(IdColumn,nameColumn,descriptioonColumn);

        TopPizzas.setItems(getPizzaInfo());
        TopPizzas.getColumns().addAll(IdColumn,nameColumn,descriptioonColumn);

        OrderRequests.setItems(getPizzaInfo());
        OrderRequests.getColumns().addAll(IdColumn,nameColumn,descriptioonColumn);

        test = new Button("etets");
        test.setOnAction(
                 e->{
                     System.out.print("test");
                 });
    }


    public void getIDFirst(int ID){
        this.EmployeeID = ID;
        System.out.println("Got Emp");
    }

    public void add_pizza(ActionEvent event) throws IOException {
        openscene(event, "addpizza","GeneralWindowStyle", "pizzainfo","Global_Resources");
    }
    public void remove_pizza(ActionEvent event) throws IOException {
        openscene(event, "removepizza","GeneralWindowStyle", "pizzainfo","Global_Resources");
    }
    public void edit_pizza(ActionEvent event) throws IOException {
        openscene(event, "editpizza","GeneralWindowStyle", "pizzainfo","Global_Resources");
    }
    public void accept_order(ActionEvent event) throws IOException {
        openscene(event, "Order","GeneralWindowStyle", "Order","Global_Resources");
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
