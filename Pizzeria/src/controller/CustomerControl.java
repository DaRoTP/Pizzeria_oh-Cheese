package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class CustomerControl {

    @FXML CheckBox newadress = new CheckBox();


    public void changescene(ActionEvent event, String scenename)throws IOException{
        Parent extended_calculator = FXMLLoader.load(getClass().getResource("/View/"+scenename+"/"+scenename+".fxml"));
        Scene scene = new Scene(extended_calculator);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
        scene.getStylesheets().clear();
        scene.getStylesheets().add("/View/"+scenename+"/"+scenename+".css");

    }

    public void openscene(ActionEvent event, String scenename) throws IOException {
        Parent nn = FXMLLoader.load(getClass().getResource("/View/"+scenename+"/"+scenename+".fxml"));
        Scene scene = new Scene(nn);
        Stage window = new Stage();
        window.setScene(scene);
        window.show();
        scene.getStylesheets().clear();
        scene.getStylesheets().add("/View/"+scenename+"/"+scenename+".css");
        Image logo_icon = new Image("/View/Global_Resources/Logo.png");
        window.getIcons().add(logo_icon);
        window.setTitle("Pizzeria - oh Cheese!");
    }
	
    public void LogOut(ActionEvent event) throws IOException {
        changescene(event, "Login");
    }

    public void Checkout(ActionEvent event) throws IOException {
        openscene(event,"checkout");
        if(newadress.isSelected()){
            openscene(event, "Adress");
        }

    }



}
