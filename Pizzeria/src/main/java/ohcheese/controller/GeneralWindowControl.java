package ohcheese.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class GeneralWindowControl {

    public void changeScene(ActionEvent event, String sceneName, String stylesheetName, String scenePackageName, String stylesheetPackageName)throws IOException {
        Parent newScene = FXMLLoader.load(getClass().getResource("/view/" +scenePackageName+"/"+sceneName+".fxml"));
        Scene scene = new Scene(newScene);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
        scene.getStylesheets().clear();
        if(stylesheetName != "GeneralWindowStyle")
            scene.getStylesheets().add("/view/Global_Resources/GeneralWindowStyle.css");
        scene.getStylesheets().add("/view/" +stylesheetPackageName+"/"+stylesheetName+".css");
    }

    public void openScene(String sceneName, String stylesheetName, String scenePackageName, String stylesheetPackageName) throws IOException {
        Parent nn = FXMLLoader.load(getClass().getResource("/view/" +scenePackageName+"/"+sceneName+".fxml"));
        Scene scene = new Scene(nn);
        Stage window = new Stage();
        window.setScene(scene);
        window.show();
        scene.getStylesheets().clear();
        if(stylesheetName != "GeneralWindowStyle")
            scene.getStylesheets().add("/view/Global_Resources/GeneralWindowStyle.css");
        scene.getStylesheets().add("/view/" +stylesheetPackageName+"/"+stylesheetName+".css");
        Image logo_icon = new Image("/view/Global_Resources/Logo.png");
        window.getIcons().add(logo_icon);
        window.setTitle("Pizzeria - oh Cheese!");
    }

    public void LogOut(ActionEvent event) throws IOException {
        changeScene(event, "Login","Login","Login","Login");
        LoginControl.setLoggedinEmployee(null);
        LoginControl.setLoggedinCustomer(null);
    }


    public void settings()throws IOException {
        if(LoginControl.get_loggedinCustomer() != null)
            openScene("settings","GeneralWindowStyle","settings","Global_Resources");
        else
            openScene("settings_Employee","GeneralWindowStyle","settings","Global_Resources");
    }
}
