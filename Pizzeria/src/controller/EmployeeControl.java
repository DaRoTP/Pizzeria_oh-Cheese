package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class EmployeeControl extends GeneralWindowControl {

    private int EmployeeID;

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
}
