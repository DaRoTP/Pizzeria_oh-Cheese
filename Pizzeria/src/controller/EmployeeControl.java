package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class EmployeeControl {

    public void changescene(ActionEvent event, String scenename)throws IOException {
        Parent extended_calculator = FXMLLoader.load(getClass().getResource("/View/"+scenename+"/"+scenename+".fxml"));
        Scene scene = new Scene(extended_calculator);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
        scene.getStylesheets().clear();
        scene.getStylesheets().add("/View/"+scenename+"/"+scenename+".css");

    }

    public void openscene(ActionEvent event, String scenename, String stylesheetname, String packagename) throws IOException {
        Parent nn = FXMLLoader.load(getClass().getResource("/View/"+packagename+"/"+scenename+".fxml"));
        Scene scene = new Scene(nn);
        Stage window = new Stage();
        window.setScene(scene);
        window.show();
        scene.getStylesheets().clear();
        scene.getStylesheets().add("/View/"+packagename+"/"+stylesheetname+".css");
        Image logo_icon = new Image("/View/Global_Resources/Logo.png");
        window.getIcons().add(logo_icon);
        window.setTitle("Pizzeria - oh Cheese!");
    }

    public void LogOut(ActionEvent event) throws IOException {
        changescene(event, "Login");
    }


    public void settings(ActionEvent event)throws IOException {
        openscene(event,"settings","settings","settings");


    }

    public void add_pizza(ActionEvent event) throws IOException {
        openscene(event, "addpizza","pizzainfo", "pizzainfo");
    }
    public void remove_pizza(ActionEvent event) throws IOException {
        openscene(event, "removepizza","pizzainfo", "pizzainfo");
    }
    public void edit_pizza(ActionEvent event) throws IOException {
        openscene(event, "editpizza","pizzainfo", "pizzainfo");
    }
    public void accept_order(ActionEvent event) throws IOException {
        openscene(event, "Order","order", "Order");
    }
}
