package ohcheese.controller.adminUntilities;

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
import ohcheese.model.placeholders.*;
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
            setExistingJobPos();
            AdminControl.setJob_pos(false);
        }

        if(Address_Info.isClass_type()) {
            setAddresses();
            Address_Info.setClass_type(false);
        }
        else if(Job_position_Info.isClass_type()){
            setJobposition();
            Job_position_Info.setClass_type(false);
        }
        else if(Employee_Info.isClass_type()){
            setEmployees();
            Employee_Info.setClass_type(false);
        }
        else if(Customer_Info.isClass_type()){
            setCustomer();
            Customer_Info.setClass_type(false);
        }
    }
    public void setExistingJobPos(){
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
    public void setJobposition(){
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
    public void updateJobposition(ActionEvent event){
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
    public void deleteJobposition(ActionEvent event){
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
    public void addJobposition(ActionEvent event){
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


    public void setAddresses(){
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
    public void updateAddress(ActionEvent event){
        if(checkAddressInputLogic()) {
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
    }
    public void deleteAddress(ActionEvent event){
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
    public void addAddress(ActionEvent event){
        if(checkAddressInputLogic()) {
            SessionFactory factory = ohcheese.Utilities.HibernateUtil.getSessionFactory();
            Session session = factory.getCurrentSession();
            System.out.println("click");
            try {
                session.getTransaction().begin();

                Query query = session.createQuery("from Address where City = '" + city.getText() + "' and Street = '" + street.getText() + "' and House_Number = " +
                        house_number.getText() + " and Apartment_Number = " + apartment_number.getText() + " and ZIP_Code = '" + zip_code.getText() + "'");
                List<Address> c_user = query.list();

                if (c_user.size() == 0) {
                    Address new_address = new Address(city.getText(), street.getText(), house_number.getText(),
                            apartment_number.getText(), zip_code.getText());
                    session.save(new_address);

                    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    window.close();
                } else {
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
    public void setEmployees(){
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
    public void updateEmployee(ActionEvent event){
        if(checkAddressInputLogic() && checkUserInputLogic() && checkEmployeeInputLogic()) {
            Address temp_address = checkIfGivenAddressExists();

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
                for (int i = 0; i < jobs.size(); i++) {
                    if (jobs.get(i).getPosition_Name().equals(job_positons.getSelectionModel().getSelectedItem())) {
                        updated_employee.setPosition_ID(jobs.get(i));
                        break;
                    }
                }

                if (temp_address == null) {
                    Address new_address = new Address(city.getText(), street.getText(), house_number.getText(), apartment_number.getText(), zip_code.getText());
                    session.save(new_address);

                    updated_employee.setAddress_ID(new_address);
                } else {
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
    }
    public void deleteEmployee(ActionEvent event){
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
    public void addEmployee(ActionEvent event){
        if(checkAddressInputLogic() && checkUserInputLogic() && checkEmployeeInputLogic()) {
            Address temp_address = checkIfGivenAddressExists();
            SessionFactory factory = ohcheese.Utilities.HibernateUtil.getSessionFactory();
            Session session = factory.getCurrentSession();
            try {
                session.getTransaction().begin();

                Query query = session.createQuery("from Employee where Employee_Username='" + username.getText() + "'");
                List<Employee> employee = query.list();

                if (employee.size() == 0) {
                    Employee new_employee = new Employee(name.getText(), surname.getText(), birthday.getText(), phone_number.getText(),
                            e_mail.getText(), pesel.getText(), Float.parseFloat(salary.getText()), username.getText(), password.getText());

                    //ADDRESS
                    if (temp_address == null) {
                        Address new_address = new Address(city.getText(), street.getText(), house_number.getText(), apartment_number.getText(), zip_code.getText());
                        session.save(new_address);

                        new_employee.setAddress_ID(new_address);
                    } else {
                        new_employee.setAddress_ID(temp_address);
                    }
                    //JOB POSITION

                    query = session.createQuery("from Job_Position");
                    List<Job_Position> jobs = query.list();


                    for (int i = 0; i < jobs.size(); i++) {
                        if (jobs.get(i).getPosition_Name().equals(job_positons.getSelectionModel().getSelectedItem())) {
                            new_employee.setPosition_ID(jobs.get(i));
                            break;
                        }
                    }

                    session.save(new_employee);

                    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    window.close();
                } else {
                    warning.setText("Employee already exists");
                }

                session.getTransaction().commit();

            } catch (Exception e) {
                e.printStackTrace();
                session.getTransaction().rollback();
            }
            session.close();
        }
    }

    public void setCustomer(){
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
    public void updateCustomer(ActionEvent event){
        if(checkUserInputLogic() && checkAddressInputLogic()){
            Address temp_address = checkIfGivenAddressExists();

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


                if (temp_address == null) {
                    Address new_address = new Address(city.getText(), street.getText(), house_number.getText(), apartment_number.getText(), zip_code.getText());
                    session.save(new_address);

                    updated_customer.setAddress_ID(new_address);
                } else {
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
    }
    public void deleteCustomer(ActionEvent event){
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
    public boolean checkUserInputLogic() {
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

        return true;
    }
    public boolean checkEmployeeInputLogic() {
        if (salary.getText() == null || salary.getText().trim().isEmpty()){
            salary.getStyleClass().add("warning");
            warning.setText("salary field can not be empty");
            return false;
        }
        else
        {
            salary.getStyleClass().remove("warning");

            if (!salary.getText().trim().matches("\\d+")){
                salary.getStyleClass().add("warning");
                warning.setText("salary must be of integer type");
                return false;
            }
            else
            {
                salary.getStyleClass().remove("warning");
            }
        }
        if (pesel.getText() == null || pesel.getText().trim().isEmpty()){
            pesel.getStyleClass().add("warning");
            warning.setText("pesel field can not be empty");
            return false;
        }
        else
        {
            pesel.getStyleClass().remove("warning");

            if (!pesel.getText().matches("\\d{11}")){
                pesel.getStyleClass().add("warning");
                warning.setText("PESEL input must be 11 digits");
                return false;
            }
            else
            {
                pesel.getStyleClass().remove("warning");

            }
        }
        if (birthday.getText() == null || birthday.getText().trim().isEmpty()){
            birthday.getStyleClass().add("warning");
            warning.setText("birthday field can not be empty");
            return false;
        }
        else
        {
            birthday.getStyleClass().remove("warning");

            if (!birthday.getText().matches("\\d{4}-\\d{2}-\\d{2}")){
                birthday.getStyleClass().add("warning");
                warning.setText("birthday format yyyy-mm-dd");
                return false;
            }
            else
            {
                birthday.getStyleClass().remove("warning");
            }
        }


        return true;
    }
    public boolean checkAddressInputLogic() {

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

}
