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

public class CustomerControl extends GeneralWindowControl {

    @FXML CheckBox newadress = new CheckBox();


    public void Checkout(ActionEvent event) throws IOException {
        openscene(event,"checkout","GeneralWindowStyle","checkout","Global_Resources");
        if(newadress.isSelected()){
            openscene(event, "Adress","GeneralWindowStyle","Adress","Global_Resources");
        }

    }

}
