package repository;

import model.Role;
import model.User;
import utils.MyArrayList;
import utils.MyList;
import utils.PersonValidition;

import java.io.*;
import java.text.ParseException;

public class UserRepositoryImpl implements UserRepository {
    private final MyList<User> users;
    private static final String FILE_PATH = "src/model/files/users.csv";
    private AccountRepository accountRepository;

    public UserRepositoryImpl(AccountRepository accountRepository) throws IOException, ParseException {
        this.accountRepository = accountRepository;
        users = new MyArrayList<>();
        addSuperAdmin();
        initUsers();

    }
    private void addSuperAdmin() {
        User superUser = new User("SuperEmail", "SuperPassword");
        superUser.setRole(Role.ADMIN);
        users.add(superUser);
    }
    private void initUsers() throws IOException, ParseException {
        String row;
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            while ((row = reader.readLine()) != null) {
                String[] fields = row.split(";");
                String email = fields[0];
                String password = fields[1];
                User user = new User(email, password);
                user.setTrueUser(Boolean.getBoolean(fields[1]));
                Role role = Role.valueOf(fields[2]);
                user.setRole(role);
                accountRepository.getAccountsByEmailOwner(user.getEmail()).forEach(user::addAccount);
                users.add(user);
            }
            reader.close();
        }
    }


    @Override
    public User addUser(String email, String password) throws IOException {
        User user = new User(email, password);
        user.setRole(Role.USER);
        users.add(user);
        return user;
    }



    @Override
    public User getUserByEmail(String email) {
        for (User user : users) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public boolean updatePassword(String email, String newPassword) {
        User user = getUserByEmail(email);
        if (user != null && PersonValidition.isPasswordValid(newPassword)) {
            user.setPassword(newPassword);
            return true;
        }
        return false;
    }

    @Override
    public MyList<User> getAllUsers() {

        return this.users;
    }

    @Override
    public User deleteUser(String email) throws IOException {
        return null;
    }

    @Override
    public boolean logoutUserRepository() throws IOException {
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(FILE_PATH, false))){
            users.toList().forEach(user -> {
                try {
                    if(user.getEmail()!= "SuperEmail"){
                        bufferedWriter.write(user.toParsing());
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        return true;
    }
}
