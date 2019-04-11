package controller;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import javax.swing.*;

public class LoginControl implements Initializable{

    @FXML private ChoiceBox<String> Mode_choice = new ChoiceBox<>();
	@FXML TextField usernameField = new TextField();
	@FXML PasswordField  passwordField = new PasswordField();
	@FXML Label Warning_label = new Label();
	
	

    @Override
    public void initialize(URL arg0, ResourceBundle arg1)
    {
    	Mode_choice.getItems().add("CUSTOMER");
        Mode_choice.getItems().add("ADMIN");
        Mode_choice.getItems().add("EMPLOYEE");
    }
	
    public void ChangeScene(ActionEvent event) throws IOException {
    	

            Parent extended_calculator = FXMLLoader.load(getClass().getResource("/View/Customer/Customer.fxml"));
            Scene scene = new Scene(extended_calculator);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
            scene.getStylesheets().clear();
            scene.getStylesheets().add("/View/Customer/Customer.css");
    }
    
    public boolean checkIfEmpty() {
		if (usernameField.getText() == null || usernameField.getText().trim().isEmpty()){
			usernameField.getStyleClass().add("warning");
			Warning_label.setText("Bad Username");
			return false;
		}
		else
		{
			usernameField.getStyleClass().remove("warning");
		}
		if (passwordField.getText() == null || passwordField.getText().trim().isEmpty()){
			passwordField.getStyleClass().add("warning");
			Warning_label.setText("Bad Password");
			return false;
		}
		else
		{
			passwordField.getStyleClass().remove("warning");
		}
		return true;
	}	
    
    public void SignInOpen(ActionEvent event) throws IOException {
        Parent nn = FXMLLoader.load(getClass().getResource("/View/SignIn/SignIn.fxml"));
        Scene scene = new Scene(nn);
        Stage window = new Stage();
        window.setScene(scene);
        window.show();
        scene.getStylesheets().clear();
        scene.getStylesheets().add("/View/SignIn/SignInStyle.css");
        Image logo_icon = new Image("/View/Global_Resources/Logo.png");
        window.getIcons().add(logo_icon);
        window.setTitle("Pizzeria - oh Cheese!");

    }

    public void open_window(ActionEvent event) throws IOException {
        String value = Mode_choice.getValue();
        if(checkIfEmpty()) {
            try {
                switch (value) {
                    case "ADMIN":
                        System.out.println("Opened Admin Window");
                        break;
                    case "CUSTOMER":
                        ChangeScene(event);
                        break;
                    case "EMPLOYEE":
                        System.out.println("Opened employee Window");
                        break;
                    default:
                        System.out.println("default");
                }
            } catch (NullPointerException e) {
                System.out.println("fak");
            }
        }
    }
    
    
	
}
