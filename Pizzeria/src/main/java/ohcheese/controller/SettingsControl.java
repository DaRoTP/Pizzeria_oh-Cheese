package ohcheese.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ohcheese.model.Address;
import ohcheese.model.Customer;
import ohcheese.model.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SettingsControl implements Initializable {

    private  Customer logedInCustomer;
    private Employee logedInEmployee;

    @FXML public TextField name = new TextField();
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

    @FXML public TextField job_position = new TextField();
    @FXML public TextField salary = new TextField();
    @FXML public TextField pesel= new TextField();

    @FXML public Label warning = new Label();



    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        logedInCustomer = LoginControl.get_loggedinCustomer();
        logedInEmployee = LoginControl.get_loggedinEmployee();

        if(logedInCustomer != null) {
            name.setText(logedInCustomer.getName());
            surname.setText(logedInCustomer.getSurname());
            e_mail.setText(logedInCustomer.getEmail());
            phone_number.setText(logedInCustomer.getPhone_Number());
            username.setText(logedInCustomer.getUsername());
            password.setText(logedInCustomer.getPassword());
            city.setText(logedInCustomer.getAddress_ID().getCity());
            zip_code.setText(logedInCustomer.getAddress_ID().getZIP_Code());
            house_number.setText(logedInCustomer.getAddress_ID().getHouse_Number());
            apartment_number.setText(logedInCustomer.getAddress_ID().getApartment_Number());
            street.setText(logedInCustomer.getAddress_ID().getStreet());
        }
        else{
            name.setText(logedInEmployee.getName());
            surname.setText(logedInEmployee.getSurname());
            e_mail.setText(logedInEmployee.getEmail());
            phone_number.setText(logedInEmployee.getPhone_Number());
            username.setText(logedInEmployee.getUsername());
            password.setText(logedInEmployee.getPassword());
            city.setText(logedInEmployee.getAddress_ID().getCity());
            zip_code.setText(logedInEmployee.getAddress_ID().getZIP_Code());
            house_number.setText(logedInEmployee.getAddress_ID().getHouse_Number());
            street.setText(logedInEmployee.getAddress_ID().getStreet());
            apartment_number.setText(logedInEmployee.getAddress_ID().getApartment_Number());
            job_position.setText(logedInEmployee.getPosition_ID().getPosition_Name());
            salary.setText(Float.toString(logedInEmployee.getSalary()));
            pesel.setText(logedInEmployee.getPESEL());
        }

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

            if (!phone_number.getText().matches("\\d{9}")){
                phone_number.getStyleClass().add("warning");
                warning.setText("phone number must have 9 digits");
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

    public Address checkIfGivenAddressExists(){
        SessionFactory factory = ohcheese.Utilities.HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();

        try {
            session.getTransaction().begin();

            Query query = session.createQuery("from Address where City = '"+city.getText()+"' and Street = '"+street.getText()+ "' and House_Number = "+
                    house_number.getText()+" and Apartment_Number = "+apartment_number.getText()+" and ZIP_Code = '"+zip_code.getText()+"'");
            List c_user = query.list();

            session.getTransaction().commit();

            if(c_user.size() > 0)
                return (Address)c_user.get(0);
            else
                return null;

        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();
        return null;
    }

    public void setNewValueForCustomer(){
        if(!logedInCustomer.getName().equals(name.getText()))
            logedInCustomer.setName(name.getText());

        if(!logedInCustomer.getSurname().equals(surname.getText()))
            logedInCustomer.setSurname(surname.getText());

        if(!logedInCustomer.getEmail().equals(e_mail.getText()))
            logedInCustomer.setEmail(e_mail.getText());

        if(!logedInCustomer.getPhone_Number().equals(phone_number.getText()))
            logedInCustomer.setPhone_Number(phone_number.getText());

        if(!logedInCustomer.getPassword().equals(password.getText()))
            logedInCustomer.setPassword(password.getText());

    }

    public void setNewValueForEmployee(){
        if(!logedInEmployee.getName().equals(name.getText()))
            logedInEmployee.setName(name.getText());

        if(!logedInEmployee.getSurname().equals(surname.getText()))
            logedInEmployee.setSurname(surname.getText());

        if(!logedInEmployee.getEmail().equals(e_mail.getText()))
            logedInEmployee.setEmail(e_mail.getText());

        if(!logedInEmployee.getPhone_Number().equals(phone_number.getText()))
            logedInEmployee.setPhone_Number(phone_number.getText());

        if(!logedInEmployee.getPassword().equals(password.getText()))
            logedInEmployee.setPassword(password.getText());

        if(!logedInEmployee.getPESEL().equals(pesel.getText()))
            logedInEmployee.setPESEL(pesel.getText());

    }


    public void updateProfile(ActionEvent event){

        Address result_Address = checkIfGivenAddressExists();

        SessionFactory factory = ohcheese.Utilities.HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();

        try {
            session.getTransaction().begin();

            if(logedInCustomer != null) {
                setNewValueForCustomer();
                if (result_Address == null) {
                    Address new_address = new Address(city.getText(), street.getText(), house_number.getText(), apartment_number.getText(), zip_code.getText());
                    session.save(new_address);

                    logedInCustomer.setAddress_ID(new_address);
                    session.update(logedInCustomer);

                    session.getTransaction().commit();
                } else {
                    logedInCustomer.setAddress_ID(result_Address);
                    session.update(logedInCustomer);

                    session.getTransaction().commit();
                }
            }
            else{
                setNewValueForEmployee();
                if (result_Address == null) {
                    Address new_address = new Address(city.getText(), street.getText(), house_number.getText(), apartment_number.getText(), zip_code.getText());
                    session.save(new_address);

                    logedInEmployee.setAddress_ID(new_address);
                    session.update(logedInEmployee);

                    session.getTransaction().commit();
                } else {
                    logedInEmployee.setAddress_ID(result_Address);
                    session.update(logedInEmployee);

                    session.getTransaction().commit();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();
        Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        stage.close();

    }

    public void submit(ActionEvent event){
        if(checkInputLogic()){
            updateProfile(event);
        }
    }
}
