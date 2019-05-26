package ohcheese.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ohcheese.Utilities.HibernateUtil;
import ohcheese.model.Address;
import ohcheese.model.Job_Position;
import ohcheese.model.Promo_Codes;
import ohcheese.model.helper.Address_Info;
import ohcheese.model.helper.Job_position_Info;
import ohcheese.model.helper.Promo_Code_Info;
import ohcheese.model.helper.Toppings_Info;
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


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(Address_Info.isClass_type()) {
            set_addresses();
            Address_Info.setClass_type(false);
        }
        else if(Job_position_Info.isClass_type()){
            set_jobposition();
            Job_position_Info.setClass_type(false);
        }
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


}
