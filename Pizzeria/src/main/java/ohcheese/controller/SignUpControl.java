package ohcheese.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ohcheese.model.Address;
import ohcheese.model.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class SignUpControl {


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

    @FXML public Label warning = new Label();


    public boolean checkInputLogic() {
        if (name.getText() == null || name.getText().trim().isEmpty()){
            name.getStyleClass().add("warning");
            warning.setText("name field can not be empty");
            return false;
        }
        else
        {
            name.getStyleClass().remove("warning");

            if (!name.getText().trim().matches("[a-zA-Z]+")){
                name.getStyleClass().add("warning");
                warning.setText("name must contain characters A-Z");
                return false;
            }
            else
            {
                name.getStyleClass().remove("warning");
            }
        }
        if (surname.getText() == null || surname.getText().trim().isEmpty()){
            surname.getStyleClass().add("warning");
            warning.setText("surname field can not be empty");
            return false;
        }
        else
        {
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
        if (e_mail.getText() == null || e_mail.getText().trim().isEmpty()){
            e_mail.getStyleClass().add("warning");
            warning.setText("e-mail field can not be empty");
            return false;
        }
        else
        {
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
        if (phone_number.getText() == null || phone_number.getText().trim().isEmpty()){
            phone_number.getStyleClass().add("warning");
            warning.setText("phone number field can not be empty");
            return false;
        }
        else
        {
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

        if (username.getText() == null || username.getText().trim().isEmpty()){
            username.getStyleClass().add("warning");
            warning.setText("username field can not be empty");
            return false;
        }
        else
        {
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
        if (password.getText() == null || password.getText().trim().isEmpty()){
            password.getStyleClass().add("warning");
            warning.setText("password field can not be empty");
            return false;
        }
        else
        {
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
                warning.setText("ZIP CODE must like: 87-345");
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

    public int check_If_Customer_Of_Given_Username_Exists(){
        SessionFactory factory = OhCheese.Utilities.HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();

        try {
            session.getTransaction().begin();

            Query query = session.createQuery("from Customer where Customer_Username='"+username.getText()+"'");
            List c_user = query.list();

            session.getTransaction().commit();

            return c_user.size();

        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();
        return 0;
    }

    public Address check_If__given_Address_Exists(){
        SessionFactory factory = OhCheese.Utilities.HibernateUtil.getSessionFactory();
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

    public void Insert_Values(){

        if (check_If_Customer_Of_Given_Username_Exists() == 0) {
            Address result_Address = check_If__given_Address_Exists();

            SessionFactory factory = OhCheese.Utilities.HibernateUtil.getSessionFactory();
            Session session = factory.getCurrentSession();

            Customer new_customer = new Customer(name.getText(),surname.getText(),phone_number.getText(),e_mail.getText(),username.getText(),password.getText());
                try {
                    session.getTransaction().begin();
                    if(result_Address == null) {
                        Address new_address = new Address(city.getText(), street.getText(), house_number.getText(), apartment_number.getText(), zip_code.getText());
                        session.save(new_address);

                        new_customer.setAddress_ID(new_address);
                        session.save(new_customer);

                        session.getTransaction().commit();
                    }
                    else
                    {
                        new_customer.setAddress_ID(result_Address);
                        session.save(new_customer);

                        session.getTransaction().commit();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    session.getTransaction().rollback();
                }
                session.close();


        }
        else
            warning.setText("User exists");
    }

    public void test() {

        if (check_If_Customer_Of_Given_Username_Exists() == 0) {
            SessionFactory factory = OhCheese.Utilities.HibernateUtil.getSessionFactory();
            Session session = factory.getCurrentSession();
            try {
                session.getTransaction().begin();

                Address new_address;
                if(apartment_number.getText() == null || apartment_number.getText().trim().isEmpty())
                    new_address = new Address(city.getText(),street.getText(),house_number.getText(),"-",zip_code.getText());
                else
                    new_address = new Address(city.getText(),street.getText(),house_number.getText(),apartment_number.getText(),zip_code.getText());
                session.save(new_address);

                session.getTransaction().commit();

            } catch (Exception e) {
                e.printStackTrace();
                session.getTransaction().rollback();
            }
            session.close();
        }
        else
            System.out.println("exists");
    }


    public void submit(ActionEvent event){
        if(checkInputLogic()){
            Insert_Values();

        }


    }

    }