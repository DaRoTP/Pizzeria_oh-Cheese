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

public class LoginControl extends GeneralWindowControl{


    public static Customer loggedinCustomer;
    public static Employee loggedinEmployee;

    public SessionFactory factory = ohcheese.Utilities.HibernateUtil.getSessionFactory();


	@FXML TextField usernameField = new TextField();
	@FXML PasswordField  passwordField = new PasswordField();
	@FXML Label Warning_label = new Label();

    public static Customer get_loggedinCustomer() { return loggedinCustomer; }
    public static void setLoggedinCustomer(Customer loggedinCustomer) { LoginControl.loggedinCustomer = loggedinCustomer; }

    public static void setLoggedinEmployee(Employee loggedinEmployee) { LoginControl.loggedinEmployee = loggedinEmployee; }
    public static Employee get_loggedinEmployee() { return loggedinEmployee; }


    
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
        List<Customer> CustomerUser = checkIF_Customer_Exists();
        List<Employee> EmployeeUser = checkIF_Employee_Exists();

        if(checkIfEmpty()) {
            if(CustomerUser.size() > 0){
                loggedinCustomer = CustomerUser.get(0);
                changescene(event, "Customer", "Customer", "Customer", "Customer");
            }
            else if(EmployeeUser.size() > 0){
                int positionID =  EmployeeUser.get(0).getPosition_ID().getId();
                loggedinEmployee = EmployeeUser.get(0);

                if(positionID == 2 || positionID == 3 || positionID == 4)
                    changescene(event, "Admin", "Admin", "Admin", "Admin");
                else
                    changescene(event, "Employee", "Employee", "Employee", "Employee");
            }
            else{
                Warning_label.setText("Bad Login");
            }
        }
    }




    
	
}
