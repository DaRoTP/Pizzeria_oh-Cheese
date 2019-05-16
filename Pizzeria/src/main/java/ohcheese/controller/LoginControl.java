package ohcheese.controller;


import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import ohcheese.model.Customer;
import ohcheese.model.Employee;
import ohcheese.model.Job_Position;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class LoginControl extends GeneralWindowControl implements Initializable{


    public static Customer loggedinCustomer;
    public static Employee loggedinEmployee;


    @FXML private ChoiceBox<String> Mode_choice = new ChoiceBox<String>();
	@FXML TextField usernameField = new TextField();
	@FXML PasswordField  passwordField = new PasswordField();
	@FXML Label Warning_label = new Label();

    public static Customer get_loggedinCustomer() { return loggedinCustomer; }

    public static Employee get_loggedinEmployee() { return loggedinEmployee; }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	Mode_choice.getItems().add("CUSTOMER");
        Mode_choice.getItems().add("ADMIN");
        Mode_choice.getItems().add("EMPLOYEE");
        setID(22);
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

	public List checkIF_Customer_Exists(){
        SessionFactory factory = ohcheese.Utilities.HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();

        try {
            session.getTransaction().begin();

            Query query = session.createQuery("from Customer where Customer_Username='"+usernameField.getText()+"' and Customer_Password='"+passwordField.getText()+"'");
            List c_user = query.list();

            session.getTransaction().commit();

            return c_user;

        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();
        return null;
    }

    public List checkIF_Employee_Exists(){
        SessionFactory factory = ohcheese.Utilities.HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();

        try {
            session.getTransaction().begin();

            Query query = session.createQuery("from Employee where Employee_Username='"+usernameField.getText()+"' and Employee_Password='"+passwordField.getText()+"'");
            List e_user = query.list();

            session.getTransaction().commit();


            return e_user;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();
        return null;
    }
    
    public void SignInOpen(ActionEvent event) throws IOException {
        openscene(event,"SignUp","GeneralWindowStyle","SignUp","Global_Resources");

    }



    public void open_window(ActionEvent event) throws IOException {
        List CustomerUser = checkIF_Customer_Exists();
        List EmployeeUser = checkIF_Employee_Exists();
        String value = Mode_choice.getValue();
        if(checkIfEmpty()) {
            if(CustomerUser.size() > 0){
                loggedinCustomer = (Customer)CustomerUser.get(0);
                try {
                    switch (value) {
                        case "ADMIN":
                            Warning_label.setText("Bad Login");
                            break;
                        case "CUSTOMER": {
                            changescene(event, "Customer", "Customer", "Customer", "Customer");
                        }
                        break;
                        case "EMPLOYEE":
                            Warning_label.setText("Bad Login");
                            break;
                        default:
                            System.out.println("default");
                    }
                } catch (NullPointerException e) {
                    Mode_choice.getStyleClass().add("warning_choice");
                    Warning_label.setText("User type not chosen");
                }
            }
            else if(checkIF_Employee_Exists().size() > 0){
                String position = ((Job_Position)((Employee) EmployeeUser.get(0)).getPosition_ID()).getPosition_Name();
                System.out.println(position);
                loggedinEmployee = (Employee)EmployeeUser.get(0);
                try {
                    switch (value) {
                        case "ADMIN": {
                            if(position.equals("Owner") || position.equals("Assistant Store General Managers ") || position.equals("Administrator"))
                                changescene(event, "Admin", "Admin", "Admin", "Admin");
                            else
                                Warning_label.setText("Bad Login");

                        }
                            break;
                        case "CUSTOMER": {
                            Warning_label.setText("Bad Login");                        }
                        break;
                        case "EMPLOYEE": {
                            if (position.equals("Owner") || position.equals("Assistant Store General Managers ") || position.equals("Administrator") || position.equals("Pizza Maker") || position.equals("Delivery Drivers"))
                                changescene(event, "Employee", "Employee", "Employee", "Employee");
                            else
                                Warning_label.setText("Bad Login");

                            break;
                        }
                        default:
                            System.out.println("default");
                    }
                } catch (NullPointerException e) {
                    Mode_choice.getStyleClass().add("warning_choice");
                    Warning_label.setText("User type not chosen");
                }
            }
            else{
                Warning_label.setText("Bad Login");
            }
        }
    }




    
	
}
