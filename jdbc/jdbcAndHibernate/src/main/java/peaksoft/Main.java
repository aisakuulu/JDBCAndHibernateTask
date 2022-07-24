package peaksoft;

import peaksoft.service.UserServiceImpl;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        // реализуйте алгоритм здесь

        UserServiceImpl userService = new UserServiceImpl();

        userService.saveUser("Habib", "Abdukarim", (byte) 36);
        userService.saveUser("Musab", "Isakov", (byte) 8);
        userService.saveUser("Umar", "Isakov", (byte) 7);
        userService.saveUser("Kani", "Kurmanbekova", (byte) 32);

        userService.getAllUsers();

        userService.removeUserById(2);
        userService.cleanUsersTable();
    }
}
