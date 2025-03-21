package service;

import model.User;
import utils.MyList;

import java.awt.print.Book;
import java.io.IOException;

public interface userService {
    User registerUser(String email, String password);
    boolean loginUser(String email, String password);
    boolean updatePassword(String email, String newPassword);
    User getUserByEmail(String email);
    User getActiveUser();
    boolean blockedUser(String email);
    boolean deleteUser(String email);
    MyList<User> allAllUsers();
    boolean setActiveUser(User user);
    boolean logout() throws IOException;
}
