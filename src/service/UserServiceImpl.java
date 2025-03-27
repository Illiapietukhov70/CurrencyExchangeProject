package service;

import model.Role;
import model.User;
import repository.AccountRepository;
import repository.UserRepository;
import utils.MyList;
import utils.PersonValidition;

import java.io.IOException;

public class UserServiceImpl implements UserService {
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private User activeUser;

    public UserServiceImpl(UserRepository userRepository, AccountRepository accountRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        this.activeUser = null;
    }

    @Override
    public User registerUser(String email, String password) throws IOException {
        if (!PersonValidition.isEmailValid(email)) {
            System.out.print("Email не прошел проверку!");
            return null;
        }

        if (!PersonValidition.isPasswordValid(password)) {
            System.out.println("Password не прошел проверку!");
            return null;
        }

        if (userRepository.getUserByEmail(email) != null) {
            System.out.println("Email already exists!");
            return null;
        }
        User user = userRepository.addUser(email, password);
        if (this.setActiveUser(user)) {
            return user;
        }
        return null;
    }

    @Override
    public boolean loginUser(String email, String password) {
        User tempUser = userRepository.getUserByEmail(email);
        if (tempUser != null) {
            if (tempUser.getPassword().equals(password)) {
                this.activeUser = tempUser;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean updatePassword(String email, String newPassword) {
        if (activeUser.getRole() == Role.ADMIN || activeUser.getEmail().equals(email)) {
            return userRepository.updatePassword(email, newPassword);
        }
        return false;
    }

    @Override
    public User getUserByEmail(String email) {
        if (activeUser.getRole() == Role.ADMIN || activeUser.getEmail().equals(email)) {
        }
        return userRepository.getUserByEmail(email);
    }

    @Override
    public User getActiveUser() {
        return activeUser;
    }


    @Override
    public boolean deleteUser(String email) {
        if (activeUser.getRole() == Role.ADMIN) {
            User deleteUser = userRepository.getUserByEmail(email);
            if (deleteUser != null) {
                deleteUser.setTrueUser(false);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean blockedUser(String email) {
        if (activeUser.getRole() == Role.ADMIN) {
            User tempUser = userRepository.getUserByEmail(email);
            if (tempUser != null) {
                tempUser.setRole(Role.BLOCKED);
                return true;
            }
        }
        return false;
    }

    @Override
    public MyList<User> getAllUsers() {
        if (activeUser.getRole() == Role.ADMIN) {
            return userRepository.getAllUsers();
        }
        return null;
    }

    @Override
    public boolean setActiveUser(User user) {
        if (user instanceof User) {
            this.activeUser = user;
            return true;
        }
        return false;
    }

    @Override
    public boolean logout() throws IOException {
        if (userRepository.logoutUserRepository() && accountRepository.logoutAccountRepository()) {
            return true;
        }
        return false;
    }
}

