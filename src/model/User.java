package model;

import java.util.HashMap;
import java.util.Map;

public class User {
    private String email;
    private String password;
    Map<String, Account> accounts;
    private Role role;


    public User() {
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
        accounts = new HashMap<>();
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Map<String, Account> getAccounts() {
        return accounts;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
