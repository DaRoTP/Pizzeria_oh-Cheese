package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class GeneralWindowControl {

    public void changescene(ActionEvent event, String scenename, String stylesheetname, String packagename)throws IOException {
        Parent extended_calculator = FXMLLoader.load(getClass().getResource("/View/"+packagename+"/"+scenename+".fxml"));
        Scene scene = new Scene(extended_calculator);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
        scene.getStylesheets().clear();
        scene.getStylesheets().add("/View/"+packagename+"/"+stylesheetname+".css");

    }

    public void openscene(ActionEvent event, String scenename, String stylesheetname, String scene_packagename,String stylesheet_packagename) throws IOException {
        Parent nn = FXMLLoader.load(getClass().getResource("/View/"+scene_packagename+"/"+scenename+".fxml"));
        Scene scene = new Scene(nn);
        Stage window = new Stage();
        window.setScene(scene);
        window.show();
        scene.getStylesheets().clear();
        scene.getStylesheets().add("/View/"+stylesheet_packagename+"/"+stylesheetname+".css");
        Image logo_icon = new Image("/View/Global_Resources/Logo.png");
        window.getIcons().add(logo_icon);
        window.setTitle("Pizzeria - oh Cheese!");
    }

    public void LogOut(ActionEvent event) throws IOException {
        changescene(event, "Login","Login","Login");
    }


    public void settings(ActionEvent event)throws IOException {
        openscene(event,"settings","GeneralWindowStyle","settings","Global_Resources");
    }
}
