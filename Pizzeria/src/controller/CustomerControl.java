package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CustomerControl {
	
    public void ChangeScene(ActionEvent event) throws IOException {
        Parent extended_calculator = FXMLLoader.load(getClass().getResource("/View/Login/Login.fxml"));
        Scene scene = new Scene(extended_calculator);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
        scene.getStylesheets().clear();
        scene.getStylesheets().add("/View/Login/Login_style.css");
    }

}
