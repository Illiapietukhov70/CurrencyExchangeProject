package repository;

import model.User;
import utils.MyList;

public interface UserRepository {
    User addUser(String email, String password);

    boolean isEmailExist(String email);

    User getUserByEmail(String email);

    boolean updatePassword(String email, String newPassword);
    MyList<User> getAllUsers();
}