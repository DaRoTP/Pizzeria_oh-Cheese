package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

import java.io.IOException;

public class CheckoutControl {

    @FXML RadioButton card = new RadioButton();
    @FXML RadioButton cash = new RadioButton();
    @FXML Label warning = new Label();

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
