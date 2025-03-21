package service;

import model.Role;
import model.User;
import repository.UserRepository;
import utils.MyList;
import utils.PersonValidition;

import java.awt.print.Book;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class userServiceImpl implements userService {
    private final UserRepository userRepository;
    private User activeUser;

    public userServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.activeUser = null;
    }
    @Override
        public User registerUser(String email, String password) {
            if (!PersonValidition.isEmailValid(email)) {
                System.out.print("Email не прошел проверку!");
                return null;
            }

            if (!PersonValidition.isPasswordValid(password)) {
                System.out.println("Password не прошел проверку!");
                return null;
            }

            if (userRepository.isEmailExist(email)) {
                System.out.println("Email already exists!");
                return null;
            }
            User user = userRepository.addUser(email, password);
            if (this.setActiveUser(user)){
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
        if(activeUser.getRole() == Role.ADMIN || activeUser.getEmail().equals(email)) {
            return userRepository.updatePassword(email, newPassword);
        }
        return false;
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.getUserByEmail(email);
    }

    @Override
    public User getActiveUser() {
        return activeUser;
    }


    @Override
    public boolean blockedUser(String email) {
        if(activeUser.getRole() == Role.ADMIN) {
            User tempUser = userRepository.getUserByEmail(email);
            if(tempUser != null) {
                tempUser.setRole(Role.BLOCKED);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteUser(String email) {
        //Todo !!!!!!!!!!!!
        return false;
    }

    @Override
    public MyList<User> allAllUsers() {
        if(activeUser.getRole() == Role.ADMIN) {
            return userRepository.getAllUsers();
        }
        return null;
    }

    @Override
    public boolean setActiveUser(User user) {
        if(user instanceof User) {
            this.activeUser = user;
            return true;
        }
        return false;
    }

    @Override
    public boolean logout() throws IOException {
        BufferedWriter fileWriter = new BufferedWriter(new FileWriter("src/model/files/users.csv", false));
        for (User user : userRepository.getAllUsers()) {
            String [] newUserArray = new String[] {user.getEmail(),
                    user.getPassword(),
                    user.getRole().toString()
            };
            if (!user.getEmail().equals("SuperEmail")) {
                String newLine = String.join(";", newUserArray);
                newLine = newLine + "\n";
                fileWriter.write(newLine);
            }
        }
        fileWriter.close();
        return true;
    }
}
