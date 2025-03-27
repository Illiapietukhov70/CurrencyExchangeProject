package service;

import model.Role;
import model.User;
import repository.AccountRepository;
import repository.UserRepository;
import utils.MyList;
import utils.PersonValidition;

import java.io.IOException;

public interface UserService {
    User registerUser(String email, String password) throws IOException;
    boolean loginUser(String email, String password);
    boolean updatePassword(String email, String newPassword);
    User getUserByEmail(String email);
    User getActiveUser();
    boolean blockedUser(String email);
    boolean deleteUser(String email);
    MyList<User> getAllUsers();
    boolean setActiveUser(User user);
    boolean logout() throws IOException;
}
