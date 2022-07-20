package peaksoft.dao;

import peaksoft.model.User;
import peaksoft.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJdbcImpl implements UserDao {

    private final Connection connection;

    public UserDaoJdbcImpl() throws SQLException{
        connection = new Util().getConnection();
    }

    public void createUsersTable() {

        String createTable =
                        "CREATE TABLE IF NOT EXISTS USERS" +
                        "(id SERIAL PRIMARY KEY," +
                        "name VARCHAR(255) NOT NULL," +
                        "last_name VARCHAR(255) NOT NULL," +
                        "age INTEGER NOT NULL)";

        try (Statement statement = connection.createStatement()) {
            statement.execute(createTable);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void dropUsersTable() {

        String dropTable ="DROP TABLE IF EXISTS USERS";

        try(Statement statement = connection.createStatement()) {
            statement.execute(dropTable);
            System.out.println("Table deleted in given database...");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {

        String saveUser = "INSERT INTO USERS (NAME, LAST_NAME, AGE) VALUES(?,?,?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(saveUser)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.execute();
            System.out.println("User " + name + " created successfully!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {

        String removeUser = "DELETE FROM USERS WHERE id = " + id;
        String getAll = "SELECT * FROM USERS";

        try(Statement statement = connection.createStatement()) {
            statement.executeUpdate(removeUser);
            ResultSet resultSet = statement.executeQuery(getAll);
            System.out.println("User signed as ID of: " + id + " is deleted in given database...");
            System.out.println();

            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setAge(resultSet.getByte("age"));

                System.out.println("User ID: " + user.getId());
                System.out.println("User name: " + user.getName());
                System.out.println("User last name: " + user.getLastName());
                System.out.println("User age: " + user.getAge());
                System.out.println();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {

        String getAll = "SELECT * FROM USERS";

        List<User> userList = new ArrayList<>();
        try(Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(getAll);

            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setAge(resultSet.getByte("age"));
                userList.add(user);
                System.out.println("User ID: " + user.getId());
                System.out.println("User name: " + user.getName());
                System.out.println("User last name: " + user.getLastName());
                System.out.println("User age: " + user.getAge());
                System.out.println();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userList;
    }

    public void cleanUsersTable() {
        try(Statement statement = connection.createStatement()) {
            String truncate = "TRUNCATE TABLE USERS";
            statement.executeUpdate(truncate);
            System.out.println("Table cleaned successfully!!!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}