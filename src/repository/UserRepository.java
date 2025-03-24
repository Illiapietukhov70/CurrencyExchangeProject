package repository;

import model.User;
import utils.MyList;

import java.io.IOException;

public interface UserRepository {
    User addUser(String email, String password) throws IOException;
    User deleteUser(String email) throws IOException;
    User getUserByEmail(String email);
    boolean updatePassword(String email, String newPassword);
    MyList<User> getAllUsers();
    boolean logoutUserRepository() throws IOException;
}