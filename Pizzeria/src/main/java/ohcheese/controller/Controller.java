package ohcheese.controller;

import javafx.event.ActionEvent;
import ohcheese.model.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ohcheese.Utilities.HibernateUtil;
import ohcheese.model.client_table;


import java.util.List;

public class Controller {


    public void display_1(ActionEvent event){

        SessionFactory factory = HibernateUtil.getSessionFactory();

        Session session = factory.getCurrentSession();
        try {
            session.getTransaction().begin();


            Query query = session.createQuery("from Customer");
            List users = query.list();


            for(int i = 0; i < users.size(); i++)
                System.out.println("- "+((Customer) users.get(i)).getId()+" = "+((Customer) users.get(i)).getName());


            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();
    }

    public void display_2(ActionEvent event){
        SessionFactory factory = HibernateUtil.getSessionFactory();

        Session session = factory.getCurrentSession();
        try {
            session.getTransaction().begin();

            client_table u1 = new client_table();
            u1.setId(55);
            u1.setName("Tessst");

            session.save(u1);



            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();
    }
    public void display_3(ActionEvent event){
        SessionFactory factory = HibernateUtil.getSessionFactory();

        Session session = factory.getCurrentSession();
        try {
            session.getTransaction().begin();

            Query query = session.createQuery("from client_table where id='2'");
            client_table name = (client_table) query.uniqueResult();


            System.out.println("- "+name.getName());

            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();
    }

    public void display_4(ActionEvent event){
        SessionFactory factory = HibernateUtil.getSessionFactory();

        Session session = factory.getCurrentSession();
        try {
            session.getTransaction().begin();

//            Query query = session.createQuery("select name from client_table where id=2");

            Query query = session.createQuery("select c.id,c.name from client_table c where c.id=2");
            List<Object[]> rows = query.list();

            for (Object[] row: rows) {
                System.out.println(" ------------------- ");
                System.out.println("id: " + row[0]);
                System.out.println("name: " + row[1]);
            }
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();
    }
}
