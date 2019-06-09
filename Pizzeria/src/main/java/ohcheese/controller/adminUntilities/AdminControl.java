package ohcheese.controller.adminUntilities;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import ohcheese.Utilities.HibernateUtil;
import ohcheese.controller.GeneralWindowControl;
import ohcheese.model.*;
import ohcheese.model.placeholders.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AdminControl extends GeneralWindowControl implements Initializable {

    public static boolean job_pos;

    public static boolean isJob_pos() { return job_pos; }
    public static void setJob_pos(boolean job_pos) { AdminControl.job_pos = job_pos; }

    @FXML private TableView<Customer_Info> customer_table = new TableView<>();
    @FXML private TableView<Address_Info> address_table = new TableView<>();
    @FXML private TableView<Employee_Info> employee_table = new TableView<>();
    @FXML private TableView<Job_position_Info> jposition_table = new TableView<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        create_CustomerTable();
        create_AddressTable();
        create_EmployeeTable();
        create_JPositionTable();
        this.job_pos = false;
    }

    public void refresh(ActionEvent event){
        customer_table.getColumns().clear();
        address_table.getColumns().clear();
        employee_table.getColumns().clear();
        jposition_table.getColumns().clear();
        create_CustomerTable();
        create_AddressTable();
        create_EmployeeTable();
        create_JPositionTable();
    }

    public void switchToEmployee(ActionEvent event) throws IOException {
        changeScene(event, "Employee", "Employee", "Employee", "Employee");
    }

    public void create_CustomerTable(){
        TableColumn<Customer_Info, Integer> IdColumn = new TableColumn<>("ID");
        IdColumn.setCellValueFactory(new PropertyValueFactory<>("customer_ID"));
        IdColumn.setMinWidth(50);
        IdColumn.setMaxWidth(50);

        TableColumn<Customer_Info, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Customer_Info, String> surnameColumn = new TableColumn<>("Surname");
        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));

        TableColumn<Customer_Info, String> phoneColumn = new TableColumn<>("Phone Num.");
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone_number"));

        TableColumn<Customer_Info, String> emailColumn = new TableColumn<>("E-Mail");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("e_mail"));

        TableColumn<Customer_Info, String> usernameColumn = new TableColumn<>("Username");
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));

        TableColumn<Customer_Info, String> passwordColumn = new TableColumn<>("Password");
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));


        TableColumn<Customer_Info, String> button = new TableColumn<>("Edit");
        button.setCellValueFactory(new PropertyValueFactory<>("edit_btn"));
        button.setMinWidth(50);
        button.setMaxWidth(50);


        customer_table.setItems(getCustomers());
        customer_table.getColumns().addAll(IdColumn,nameColumn,surnameColumn,phoneColumn,emailColumn,usernameColumn,passwordColumn,button);
        customer_table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    public void create_EmployeeTable(){
        TableColumn<Employee_Info, Integer> IdColumn = new TableColumn<>("ID");
        IdColumn.setCellValueFactory(new PropertyValueFactory<>("employee_ID"));
        IdColumn.setMinWidth(50);
        IdColumn.setMaxWidth(50);

        TableColumn<Employee_Info, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Employee_Info, String> surnameColumn = new TableColumn<>("Surname");
        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));

        TableColumn<Employee_Info, String> phoneColumn = new TableColumn<>("Phone Num.");
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone_number"));

        TableColumn<Employee_Info, String> emailColumn = new TableColumn<>("E-Mail");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("e_mail"));

        TableColumn<Employee_Info, String> usernameColumn = new TableColumn<>("Username");
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));

        TableColumn<Employee_Info, String> passwordColumn = new TableColumn<>("Password");
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));

        TableColumn<Employee_Info, String> birthdayColumn = new TableColumn<>("Birth-Day");
        birthdayColumn.setCellValueFactory(new PropertyValueFactory<>("birthday"));

        TableColumn<Employee_Info, String> PESEL = new TableColumn<>("PESEL");
        PESEL.setCellValueFactory(new PropertyValueFactory<>("PESEL"));

        TableColumn<Employee_Info, String> salaryColumn = new TableColumn<>("Salary");
        salaryColumn.setCellValueFactory(new PropertyValueFactory<>("salary"));

        TableColumn<Employee_Info, String> Position_NameColumn = new TableColumn<>("Job Position");
        Position_NameColumn.setCellValueFactory(new PropertyValueFactory<>("Position_Name"));



        TableColumn<Employee_Info, String> button = new TableColumn<>("Edit");
        button.setCellValueFactory(new PropertyValueFactory<>("edit_btn"));
        button.setMinWidth(50);
        button.setMaxWidth(50);


        employee_table.setItems(getEmployees());
        employee_table.getColumns().addAll(IdColumn,nameColumn,surnameColumn,phoneColumn,emailColumn,birthdayColumn,Position_NameColumn,PESEL,usernameColumn,passwordColumn,button);
        employee_table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    public void create_AddressTable(){
        TableColumn<Address_Info, Integer> IdColumn = new TableColumn<>("ID");
        IdColumn.setCellValueFactory(new PropertyValueFactory<>("address_ID"));
        IdColumn.setMinWidth(50);
        IdColumn.setMaxWidth(50);

        TableColumn<Address_Info, String> cityColumn = new TableColumn<>("City");
        cityColumn.setCellValueFactory(new PropertyValueFactory<>("city"));

        TableColumn<Address_Info, String> streetColumn = new TableColumn<>("Street");
        streetColumn.setCellValueFactory(new PropertyValueFactory<>("street"));

        TableColumn<Address_Info, String> zip_codeColumn = new TableColumn<>("ZIP-CODE");
        zip_codeColumn.setCellValueFactory(new PropertyValueFactory<>("zip_code"));

        TableColumn<Address_Info, String> houseColumn = new TableColumn<>("House Num.");
        houseColumn.setCellValueFactory(new PropertyValueFactory<>("house"));

        TableColumn<Address_Info, String> apartmentColumn = new TableColumn<>("Apartment Num.");
        apartmentColumn.setCellValueFactory(new PropertyValueFactory<>("apartment"));


        TableColumn<Address_Info, String> button = new TableColumn<>("Edit");
        button.setCellValueFactory(new PropertyValueFactory<>("edit_btn"));
        button.setMinWidth(50);
        button.setMaxWidth(50);

        address_table.setItems(getAddress());
        address_table.getColumns().addAll(IdColumn,cityColumn,streetColumn,houseColumn,apartmentColumn,zip_codeColumn,button);
        address_table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    public void create_JPositionTable(){
        TableColumn<Job_position_Info, Integer> IdColumn = new TableColumn<>("ID");
        IdColumn.setCellValueFactory(new PropertyValueFactory<>("position_ID"));
        IdColumn.setMinWidth(50);
        IdColumn.setMaxWidth(50);

        TableColumn<Job_position_Info, String> positioncolumn = new TableColumn<>("Job Position");
        positioncolumn.setCellValueFactory(new PropertyValueFactory<>("job_position_name"));

        TableColumn<Job_position_Info, String> button = new TableColumn<>("Edit");
        button.setCellValueFactory(new PropertyValueFactory<>("edit_btn"));
        button.setMinWidth(50);
        button.setMaxWidth(50);

        jposition_table.setItems(getJobPositions());
        jposition_table.getColumns().addAll(IdColumn,positioncolumn,button);
        jposition_table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    public ObservableList<Employee_Info> getEmployees(){
        ObservableList<Employee_Info> employees = FXCollections.observableArrayList();

        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        try {
            session.getTransaction().begin();

            Query query = session.createQuery("from Employee");
            List<Employee> employee_list = query.list();

            for(int i = 0; i < employee_list.size(); i++){
                employees.add(new Employee_Info(employee_list.get(i).getId(),employee_list.get(i).getName(),employee_list.get(i).getSurname(),
                        employee_list.get(i).getPhone_Number(),employee_list.get(i).getEmail(),employee_list.get(i).getUsername(),employee_list.get(i).getPassword(),
                        employee_list.get(i).getAddress_ID(),employee_list.get(i).getDate_Of_Birth(),employee_list.get(i).getPESEL(),
                        employee_list.get(i).getSalary(),employee_list.get(i).getPosition_ID()));
            }
            session.getTransaction().commit();
            return employees;

        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();

        return null;
    }

    public ObservableList<Customer_Info> getCustomers(){
        ObservableList<Customer_Info> customers = FXCollections.observableArrayList();

        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        try {
            session.getTransaction().begin();

            Query query = session.createQuery("from Customer");
            List<Customer> customer_list = query.list();

            for(int i = 0; i < customer_list.size(); i++){
                customers.add(new Customer_Info(customer_list.get(i).getId(),customer_list.get(i).getName(),customer_list.get(i).getSurname(),
                        customer_list.get(i).getPhone_Number(),customer_list.get(i).getEmail(),customer_list.get(i).getUsername(),
                        customer_list.get(i).getPassword(),customer_list.get(i).getAddress_ID()));
            }
            session.getTransaction().commit();
            return customers;

        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();

        return null;
    }

    public ObservableList<Address_Info> getAddress(){
        ObservableList<Address_Info> address = FXCollections.observableArrayList();

        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        try {
            session.getTransaction().begin();

            Query query = session.createQuery("from Address");
            List<Address> address_list = query.list();

            for(int i = 0; i < address_list.size(); i++){
                address.add(new Address_Info(address_list.get(i).getId(),address_list.get(i).getCity(),address_list.get(i).getStreet(),
                        address_list.get(i).getZIP_Code(),address_list.get(i).getHouse_Number(),address_list.get(i).getApartment_Number()));
            }
            session.getTransaction().commit();
            return address;

        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();

        return null;
    }

    public ObservableList<Job_position_Info> getJobPositions(){
        ObservableList<Job_position_Info> job_positions = FXCollections.observableArrayList();

        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        try {
            session.getTransaction().begin();

            Query query = session.createQuery("from Job_Position");
            List<Job_Position> jpos_list = query.list();

            for(int i = 0; i < jpos_list.size(); i++){
                job_positions.add(new Job_position_Info(jpos_list.get(i).getId(),jpos_list.get(i).getPosition_Name()));
            }
            session.getTransaction().commit();
            return job_positions;

        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();

        return null;
    }

    public void Open_Add_Employee(ActionEvent event) throws IOException {
        this.job_pos = true;
        openScene( "add_employee","GeneralWindowStyle", "Admin/tools","Global_Resources");
    }
    public void Open_Add_Customer(ActionEvent event) throws IOException {
        openScene("SignUp","GeneralWindowStyle","SignUp","Global_Resources");
    }
    public void Open_Add_Address(ActionEvent event) throws IOException {
        openScene( "add_address","GeneralWindowStyle", "Admin/tools","Global_Resources");
    }
    public void Open_Job_position(ActionEvent event) throws IOException {
        openScene("add_jobposition","GeneralWindowStyle", "Admin/tools","Global_Resources");
    }


}

