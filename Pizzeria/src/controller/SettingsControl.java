package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class SettingsControl implements Initializable {

    @FXML
    public TextField name = new TextField();
    @FXML public TextField surname = new TextField();
    @FXML public TextField e_mail = new TextField();
    @FXML public TextField phone_number = new TextField();
    @FXML public TextField username = new TextField();
    @FXML public TextField password = new TextField();

    @FXML public TextField city = new TextField();
    @FXML public TextField street = new TextField();
    @FXML public TextField house_number = new TextField();
    @FXML public TextField apartment_number = new TextField();
    @FXML public TextField zip_code= new TextField();

    @FXML public Label warning = new Label();


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        name.setText("Name");
        surname.setText("Surname");
        e_mail.setText("example@host.pl");
        phone_number.setText("+4812345678");
        username.setText("User");
        password.setText("Admin111");
        city.setText("Gdansk");
        zip_code.setText("34-567");
        house_number.setText("45");
        apartment_number.setText("2");
        street.setText("majowka");
    }

    public boolean checkInputLogic() {
        if (name.getText() != null || name.getText().trim().isEmpty() == false) {
            name.getStyleClass().remove("warning");

            if (!name.getText().trim().matches("[a-zA-Z]+")){
                name.getStyleClass().add("warning");
                name.setText("cos");
                warning.setText("name must contain characters A-Z");
                return false;
            }
            else
            {
                name.getStyleClass().remove("warning");
            }
        }
        if (surname.getText() != null || surname.getText().trim().isEmpty() == false){
            surname.getStyleClass().remove("warning");

            if (!surname.getText().matches("[a-zA-Z]+")){
                surname.getStyleClass().add("warning");
                warning.setText("surname must contain characters A-Z");
                return false;
            }
            else
            {
                surname.getStyleClass().remove("warning");

            }
        }
        if (e_mail.getText() != null || e_mail.getText().trim().isEmpty() == false){
            e_mail.getStyleClass().remove("warning");

            if (!e_mail.getText().matches("^[a-zA-Z0-9._]+@[a-zA-Z0-9._]+\\.[a-z]+")){
                e_mail.getStyleClass().add("warning");
                warning.setText("e_mail must be like: Example@host.com");
                return false;
            }
            else
            {
                e_mail.getStyleClass().remove("warning");
            }
        }
        if (phone_number.getText() != null || phone_number.getText().trim().isEmpty() == false){
            phone_number.getStyleClass().remove("warning");

            if (!phone_number.getText().matches("\\+48\\d{8}")){
                phone_number.getStyleClass().add("warning");
                warning.setText("phone number must be like: +48*******");
                return false;
            }
            else
            {
                phone_number.getStyleClass().remove("warning");
            }
        }

        if (username.getText() != null || username.getText().trim().isEmpty() == false){
            username.getStyleClass().remove("warning");

            if (!username.getText().matches("[a-zA-Z0-9_*@$#]+")){
                username.getStyleClass().add("warning");
                warning.setText("allowed characters a-z_*@$#");
                return false;
            }
            else
            {
                username.getStyleClass().remove("warning");
            }
        }
        if (password.getText() != null || password.getText().trim().isEmpty() == false){
            password.getStyleClass().remove("warning");

            if (!password.getText().matches("[a-zA-Z0-9_*@$#]+")){
                password.getStyleClass().add("warning");
                warning.setText("allowed characters a-z_*@$#");
                return false;
            }
            else
            {
                password.getStyleClass().remove("warning");
            }
        }

        if (city.getText() != null || city.getText().trim().isEmpty() == false){
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
        if (zip_code.getText() != null || zip_code.getText().trim().isEmpty() == false){
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
        if (house_number.getText() != null || house_number.getText().trim().isEmpty() == false){
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
        return true;
    }

    public void submit(ActionEvent event){
        if(checkInputLogic()){
            Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            stage.close();
        }


    }
}
