package peaksoft.service;

import peaksoft.dao.UserDaoHibernateImpl;
import peaksoft.dao.UserDaoJdbcImpl;
import peaksoft.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {

    UserDaoHibernateImpl hibernate = new UserDaoHibernateImpl();

    public UserServiceImpl() throws SQLException {
    }

    public void createUsersTable() {
        hibernate.createUsersTable();
    }

    public void dropUsersTable() {
        hibernate.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        hibernate.saveUser(name,lastName,age);
    }

    public void removeUserById(long id) {
        hibernate.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return hibernate.getAllUsers();
    }

    public void cleanUsersTable() {
        hibernate.cleanUsersTable();
    }
}
