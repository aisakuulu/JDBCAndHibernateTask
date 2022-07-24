package peaksoft.dao;

import org.hibernate.*;
import peaksoft.model.User;
import peaksoft.util.Util;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private final SessionFactory factory = Util.getSessionFactory();

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        try {
            String createTable = "CREATE TABLE IF NOT EXISTS USERS(" +
                    "id SERIAL PRIMARY KEY," +
                    "first_name VARCHAR NOT NULL," +
                    "last_name VARCHAR NOT NULL," +
                    "age INTEGER NOT NULL);";
            Session session = factory.openSession();
            session.beginTransaction();
            session.createSQLQuery(createTable).executeUpdate();
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void dropUsersTable() {
        try {

            Session session = factory.openSession();
            session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS USERS").executeUpdate();
            session.getTransaction().commit();
            session.close();
            System.out.println("Table Deleted!!!");
        } catch (HibernateException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try{
            Session session = factory.openSession();
            session.beginTransaction();
            session.save(new User(name, lastName, age));
            session.getTransaction().commit();
            session.close();
            System.out.println("User " + name + " created successfully!");
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void removeUserById(long id) {
        try {
            Session session = factory.openSession();
            session.beginTransaction();
            session.createSQLQuery("DELETE FROM USERS WHERE ID = " +id).executeUpdate();
            session.getTransaction().commit();
            session.close();
            System.out.println("User Deleted!!!");
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users =new ArrayList<>();
        try {
            Session session = factory.openSession();
            session.beginTransaction();
             users = session.createQuery("FROM User").list();
            session.getTransaction().commit();
            session.close();
            System.out.println("Found " + users.size() + " users");

        } catch (HibernateException e){
            System.out.println(e.getMessage());
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
      try {
        Session session = factory.openSession();
        session.beginTransaction();
        session.createSQLQuery("TRUNCATE TABLE USERS").executeUpdate();
        session.getTransaction().commit();
        session.close();
          System.out.println("Table Cleaned Successfully!!!");
      } catch (HibernateException e){
          System.out.println(e.getMessage());
      }
    }
}
