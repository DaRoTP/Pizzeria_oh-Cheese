package ohcheese.controller;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMessages_pt_BR;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ohcheese.Utilities.HibernateUtil;
import ohcheese.model.Address;
import ohcheese.model.Customer;
import ohcheese.model.Employee;
import ohcheese.model.Job_Position;
import ohcheese.model.helper.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Admin_tools implements Initializable {

    //GENERAL
    @FXML private Label warning;
    @FXML private Label id_label;

    //ADDRESS
    @FXML private TextField city;
    @FXML private TextField zip_code;
    @FXML private TextField house_number;
    @FXML private TextField apartment_number;
    @FXML private TextField street;

    //JOB POSITION
    @FXML private TextField job_position_TF;

    //EMPLOYEE
    @FXML private ChoiceBox<String> job_positons = new ChoiceBox<String>();
    @FXML private TextField salary;
    @FXML private TextField name;
    @FXML private TextField surname;
    @FXML private TextField e_mail;
    @FXML private TextField phone_number;
    @FXML private TextField pesel;
    @FXML private TextField username;
    @FXML private TextField password;
    @FXML private TextField birthday;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        if(AdminControl.isJob_pos()) {
            set_existing_job_pos();
            AdminControl.setJob_pos(false);
        }

        if(Address_Info.isClass_type()) {
            set_addresses();
            Address_Info.setClass_type(false);
        }
        else if(Job_position_Info.isClass_type()){
            set_jobposition();
            Job_position_Info.setClass_type(false);
        }
        else if(Employee_Info.isClass_type()){
            set_employees();
            Employee_Info.setClass_type(false);
        }
        else if(Customer_Info.isClass_type()){
            set_customer();
            Customer_Info.setClass_type(false);
        }
    }
    public void set_existing_job_pos(){
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        session.getTransaction().begin();
        Query query = session.createQuery("from Job_Position");
        List<Job_Position> jobs = query.list();

        for(int i = 0; i < jobs.size(); i++) {
            job_positons.getItems().add(jobs.get(i).getPosition_Name());
        }

        session.getTransaction().commit();
        session.close();
    }
    public void set_jobposition(){
        int id = Job_position_Info.getTemp_id();
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        session.getTransaction().begin();

        Job_Position job_position = session.get(Job_Position.class, id);

        id_label.setText("ID: "+job_position.getId());
        job_position_TF.setText(job_position.getPosition_Name());

        session.getTransaction().commit();
        session.close();
    }
    public void update_jobposition(ActionEvent event){
        SessionFactory factory = ohcheese.Utilities.HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();

        try {
            session.getTransaction().begin();

            Job_Position updated_jobpos = session.get(Job_Position.class, Job_position_Info.getTemp_id());
            updated_jobpos.setPosition_Name(job_position_TF.getText());
            session.update(updated_jobpos);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.close();

            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();
    }
    public void delete_jobposition(ActionEvent event){
        SessionFactory factory = ohcheese.Utilities.HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();

        try {
            session.getTransaction().begin();

            Job_Position delete_jobpos = session.get(Job_Position.class, Job_position_Info.getTemp_id());
            session.delete(delete_jobpos);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.close();

            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();
    }
    public void add_jobposition(ActionEvent event){
        SessionFactory factory = ohcheese.Utilities.HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        System.out.println("click");
        try {
            session.getTransaction().begin();

            Query query = session.createQuery("from Job_Position where Position_Name = '"+job_position_TF.getText()+"'");
            List<Job_Position> c_user = query.list();

            if(c_user.size() == 0) {
                Job_Position new_jobpos = new Job_Position(job_position_TF.getText());
                session.save(new_jobpos);

                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.close();
            }
            else {
                warning.setText("Job position already exists");
                System.out.println("no");
            }

            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();
    }

    public void set_addresses(){
        int id = Address_Info.getTemp_id();
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        session.getTransaction().begin();

        Address address = session.get(Address.class, id);

        id_label.setText("ID: "+address.getId());
        city.setText(address.getCity());
        zip_code.setText(address.getZIP_Code());
        house_number.setText(address.getHouse_Number());
        apartment_number.setText(address.getApartment_Number());
        street.setText(address.getStreet());

        session.getTransaction().commit();
        session.close();
    }
    public void update_address(ActionEvent event){
        SessionFactory factory = ohcheese.Utilities.HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();

        try {
            session.getTransaction().begin();

            Address updated_address = session.get(Address.class, Address_Info.getTemp_id());
            updated_address.setCity(city.getText());
            updated_address.setStreet(street.getText());
            updated_address.setHouse_Number(house_number.getText());
            updated_address.setApartment_Number(apartment_number.getText());
            updated_address.setZIP_Code(zip_code.getText());
            session.update(updated_address);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.close();

            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();
    }
    public void delete_address(ActionEvent event){
        SessionFactory factory = ohcheese.Utilities.HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();

        try {
            session.getTransaction().begin();

            Address delete_address = session.get(Address.class, Address_Info.getTemp_id());
            session.delete(delete_address);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.close();

            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();
    }
    public void add_address(ActionEvent event){
        SessionFactory factory = ohcheese.Utilities.HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        System.out.println("click");
        try {
            session.getTransaction().begin();

            Query query = session.createQuery("from Address where City = '"+city.getText()+"' and Street = '"+street.getText()+ "' and House_Number = "+
                    house_number.getText()+" and Apartment_Number = "+apartment_number.getText()+" and ZIP_Code = '"+zip_code.getText()+"'");
            List<Address> c_user = query.list();

            if(c_user.size() == 0) {
                Address new_address = new Address(city.getText(),street.getText(),house_number.getText(),
                        apartment_number.getText(),zip_code.getText());
                session.save(new_address);

                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.close();
            }
            else {
                warning.setText("Address already exists");
                System.out.println("no");
            }

            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();
    }

    public Address check_If__given_Address_Exists(){
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
    public void set_employees(){
        int id = Employee_Info.getTemp_id();
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        session.getTransaction().begin();

        Employee employee = session.get(Employee.class, id);

        id_label.setText("ID: "+employee.getId());

        Query query = session.createQuery("from Job_Position");
        List<Job_Position> jobs = query.list();

        for(int i = 0; i < jobs.size(); i++) {
            job_positons.getItems().add(jobs.get(i).getPosition_Name());
            if(employee.getPosition_ID() == jobs.get(i)){
                job_positons.getSelectionModel().select(i);
            }
        }

        salary.setText(Float.toString(employee.getSalary()));
        name.setText(employee.getName());
        surname.setText(employee.getSurname());
        e_mail.setText(employee.getEmail());
        phone_number.setText(employee.getPhone_Number());
        pesel.setText(employee.getPESEL());
        username.setText(employee.getUsername());
        password.setText(employee.getPassword());
        birthday.setText(employee.getDate_Of_Birth());

        city.setText(employee.getAddress_ID().getCity());
        zip_code.setText(employee.getAddress_ID().getZIP_Code());
        house_number.setText(employee.getAddress_ID().getHouse_Number());
        apartment_number.setText(employee.getAddress_ID().getApartment_Number());
        street.setText(employee.getAddress_ID().getStreet());

        session.getTransaction().commit();
        session.close();
    }
    public void update_employee(ActionEvent event){

        Address temp_address = check_If__given_Address_Exists();

        SessionFactory factory = ohcheese.Utilities.HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();

        try {
            session.getTransaction().begin();

            Employee updated_employee = session.get(Employee.class, Employee_Info.getTemp_id());
            updated_employee.setSalary(Float.parseFloat(salary.getText()));
            updated_employee.setName(name.getText());
            updated_employee.setSurname(surname.getText());
            updated_employee.setEmail(e_mail.getText());
            updated_employee.setPhone_Number(phone_number.getText());
            updated_employee.setPESEL(pesel.getText());
            updated_employee.setUsername(username.getText());
            updated_employee.setPassword(password.getText());
            updated_employee.setDate_Of_Birth(birthday.getText());

            Query query = session.createQuery("from Job_Position");
            List<Job_Position> jobs = query.list();
            for(int i = 0; i < jobs.size(); i++){
                if(jobs.get(i).getPosition_Name().equals(job_positons.getSelectionModel().getSelectedItem())){
                    updated_employee.setPosition_ID(jobs.get(i));
                    break;
                }
            }

            if(temp_address == null){
                Address new_address = new Address(city.getText(), street.getText(), house_number.getText(), apartment_number.getText(), zip_code.getText());
                session.save(new_address);

                updated_employee.setAddress_ID(new_address);
            }
            else{
                updated_employee.setAddress_ID(temp_address);
            }

            session.update(updated_employee);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.close();

            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();
    }
    public void delete_employee(ActionEvent event){
        SessionFactory factory = ohcheese.Utilities.HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();

        try {
            session.getTransaction().begin();

            Employee delete_employee = session.get(Employee.class, Employee_Info.getTemp_id());
            session.delete(delete_employee);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.close();

            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();
    }
    public void add_employee(ActionEvent event){
        Address temp_address = check_If__given_Address_Exists();
        SessionFactory factory = ohcheese.Utilities.HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        try {
            session.getTransaction().begin();

            Query query = session.createQuery("from Employee where Employee_Username='"+username.getText()+"'");
            List<Employee> employee = query.list();

            if(employee.size() == 0) {
                Employee new_employee = new Employee(name.getText(),surname.getText(),birthday.getText(),phone_number.getText(),
                        e_mail.getText(),pesel.getText(),Float.parseFloat(salary.getText()),username.getText(),password.getText());

                //ADDRESS
                if(temp_address == null){
                    Address new_address = new Address(city.getText(), street.getText(), house_number.getText(), apartment_number.getText(), zip_code.getText());
                    session.save(new_address);

                    new_employee.setAddress_ID(new_address);
                }
                else{
                    new_employee.setAddress_ID(temp_address);
                }
                //JOB POSITION

                query = session.createQuery("from Job_Position");
                List<Job_Position> jobs = query.list();


                for(int i = 0; i < jobs.size(); i++){
                    if(jobs.get(i).getPosition_Name().equals(job_positons.getSelectionModel().getSelectedItem())){
                        new_employee.setPosition_ID(jobs.get(i));
                        break;
                    }
                }

                session.save(new_employee);

                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.close();
            }
            else {
                warning.setText("Employee already exists");
            }

            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();
    }

    public void set_customer(){
        int id = Customer_Info.getTemp_id();
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        session.getTransaction().begin();

        Customer customer = session.get(Customer.class, id);

        id_label.setText("ID: "+customer.getId());


        name.setText(customer.getName());
        surname.setText(customer.getSurname());
        e_mail.setText(customer.getEmail());
        phone_number.setText(customer.getPhone_Number());
        username.setText(customer.getUsername());
        password.setText(customer.getPassword());

        city.setText(customer.getAddress_ID().getCity());
        zip_code.setText(customer.getAddress_ID().getZIP_Code());
        house_number.setText(customer.getAddress_ID().getHouse_Number());
        apartment_number.setText(customer.getAddress_ID().getApartment_Number());
        street.setText(customer.getAddress_ID().getStreet());

        session.getTransaction().commit();
        session.close();
    }
    public void update_customer(ActionEvent event){

        Address temp_address = check_If__given_Address_Exists();

        SessionFactory factory = ohcheese.Utilities.HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();

        try {
            session.getTransaction().begin();

            Customer updated_customer = session.get(Customer.class, Customer_Info.getTemp_id());
            updated_customer.setName(name.getText());
            updated_customer.setSurname(surname.getText());
            updated_customer.setEmail(e_mail.getText());
            updated_customer.setPhone_Number(phone_number.getText());
            updated_customer.setUsername(username.getText());
            updated_customer.setPassword(password.getText());


            if(temp_address == null){
                Address new_address = new Address(city.getText(), street.getText(), house_number.getText(), apartment_number.getText(), zip_code.getText());
                session.save(new_address);

                updated_customer.setAddress_ID(new_address);
            }
            else{
                updated_customer.setAddress_ID(temp_address);
            }

            session.update(updated_customer);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.close();

            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();
    }
    public void delete_customer(ActionEvent event){
        SessionFactory factory = ohcheese.Utilities.HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();

        try {
            session.getTransaction().begin();

            Customer delete_customer = session.get(Customer.class, Customer_Info.getTemp_id());
            session.delete(delete_customer);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.close();

            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();
    }


}
