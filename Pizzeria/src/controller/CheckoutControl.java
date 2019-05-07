package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;
import model.Order;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CheckoutControl implements Initializable {


    @FXML RadioButton card = new RadioButton();
    @FXML RadioButton cash = new RadioButton();
    @FXML Label warning = new Label();
    @FXML Label Price = new Label();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

    }

    public void getPrice(int price){
        Price.setText(price+" zlt");
    }

    public void submit(ActionEvent event) throws IOException {
        if (cash.isSelected() || card.isSelected()) {
                Parent extended_calculator = FXMLLoader.load(getClass().getResource("/view/checkout/thankyou.fxml"));
                Scene scene = new Scene(extended_calculator);
                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();
                scene.getStylesheets().clear();
                scene.getStylesheets().add("/view/Global_Resources/GeneralWindowStyle.css");
        }
        else
        {
            warning.setText("Please check payment method");
        }
    }
}
