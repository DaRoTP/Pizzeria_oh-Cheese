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

public class LoginControl extends GeneralWindowControl implements Initializable{

    @FXML private ChoiceBox<String> Mode_choice = new ChoiceBox<>();
	@FXML TextField usernameField = new TextField();
	@FXML PasswordField  passwordField = new PasswordField();
	@FXML Label Warning_label = new Label();
	
	

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	Mode_choice.getItems().add("CUSTOMER");
        Mode_choice.getItems().add("ADMIN");
        Mode_choice.getItems().add("EMPLOYEE");
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
        openscene(event,"SignUp","GeneralWindowStyle","SignUp","Global_Resources");

    }

    public void open_window(ActionEvent event) throws IOException {
        String value = Mode_choice.getValue();
        if(checkIfEmpty()) {
            try {
                switch (value) {
                    case "ADMIN":
                        changescene(event,"Admin","Admin","Admin");
                        break;
                    case "CUSTOMER":
                        changescene(event,"Customer","Customer","Customer");
                        break;
                    case "EMPLOYEE":
                        changescene(event,"Employee","Employee","Employee");
                        break;
                    default:
                        System.out.println("default");
                }
            } catch (NullPointerException e) {
                Mode_choice.getStyleClass().add("warning_choice");
                Warning_label.setText("User type not chosen");
            }
        }
    }
    
    
	
}
