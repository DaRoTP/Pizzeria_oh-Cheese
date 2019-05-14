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
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class LoginControl extends GeneralWindowControl implements Initializable{


    public static int loggedinUserID;
    public static String loggedinUser_Username;


    @FXML private ChoiceBox<String> Mode_choice = new ChoiceBox<String>();
	@FXML TextField usernameField = new TextField();
	@FXML PasswordField  passwordField = new PasswordField();
	@FXML Label Warning_label = new Label();

    public static int getLoggedinUserID() { return loggedinUserID; }

    public static String getLoggedinUser_Username() { return loggedinUser_Username; }

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
        SessionFactory factory = OhCheese.Utilities.HibernateUtil.getSessionFactory();
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
        SessionFactory factory = OhCheese.Utilities.HibernateUtil.getSessionFactory();
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
        String value = Mode_choice.getValue();
        if(checkIfEmpty()) {
            if(checkIF_Customer_Exists().size() > 0){
                loggedinUserID = ((Customer)checkIF_Customer_Exists().get(0)).getId();
                loggedinUser_Username = ((Customer)checkIF_Customer_Exists().get(0)).getUsername();
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
                loggedinUserID = ((Employee)checkIF_Employee_Exists().get(0)).getId();
                loggedinUser_Username = ((Employee)checkIF_Employee_Exists().get(0)).getUsername();
                try {
                    switch (value) {
                        case "ADMIN":
                            changescene(event, "Admin", "Admin", "Admin", "Admin");
                            break;
                        case "CUSTOMER": {
                            Warning_label.setText("Bad Login");                        }
                        break;
                        case "EMPLOYEE":
                            changescene(event, "Employee", "Employee", "Employee", "Employee");
                            break;
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
