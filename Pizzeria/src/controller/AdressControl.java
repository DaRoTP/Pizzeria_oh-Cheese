package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AdressControl {

    @FXML public TextField city = new TextField();
    @FXML public TextField street = new TextField();
    @FXML public TextField house_number = new TextField();
    @FXML public TextField flat_number = new TextField();
    @FXML public TextField zip_code= new TextField();

    @FXML public Label warning = new Label();

    public boolean checkInputLogic() {


        if (city.getText() == null || city.getText().trim().isEmpty()){
            city.getStyleClass().add("warning");
            warning.setText("city field can not be empty");
            return false;
        }
        else
        {
            city.getStyleClass().remove("warning");

            if (!city.getText().matches("[a-zA-Z]+")){
                city.getStyleClass().add("warning");
                warning.setText("city must contain characters A-Z");
                return false;
            }
            else
            {
                city.getStyleClass().remove("warning");
            }
        }
        if (zip_code.getText() == null || zip_code.getText().trim().isEmpty()){
            zip_code.getStyleClass().add("warning");
            warning.setText("ZIP CODE field can not be empty");
            return false;
        }
        else
        {
            zip_code.getStyleClass().remove("warning");

            if (!zip_code.getText().matches("\\d{2}-\\d{3}")){
                zip_code.getStyleClass().add("warning");
                warning.setText("ZIP COde must like: 87-345");
                return false;
            }
            else
            {
                zip_code.getStyleClass().remove("warning");
            }

        }
        if (house_number.getText() == null || house_number.getText().trim().isEmpty()){
            house_number.getStyleClass().add("warning");
            warning.setText("house number field can not be empty");
            return false;
        }
        else
        {
            house_number.getStyleClass().remove("warning");

            if (!house_number.getText().matches("\\d+")){
                house_number.getStyleClass().add("warning");
                warning.setText("house number must contain characters 0-9");
                return false;
            }
            else
            {
                house_number.getStyleClass().remove("warning");
            }
        }
        if (street.getText() == null || street.getText().trim().isEmpty()){
            street.getStyleClass().add("warning");
            warning.setText("street field can not be empty");
            return false;
        }
        else
        {
            street.getStyleClass().remove("warning");
        }
        return true;
    }

    public void submit(ActionEvent event) {
        if (checkInputLogic()) {
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.close();
        }
    }
}
