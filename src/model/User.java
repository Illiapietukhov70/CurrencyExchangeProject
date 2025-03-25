package model;

import java.util.HashMap;
import java.util.Map;

public class User {
    private String email;
    private boolean trueUser; // что он есть и не удален!
    private String password;
    Map<Integer, Account> accounts;
    private Role role;


    public User() {
    }

    public User(String email, String password) {

        this.email = email;
        this.trueUser = true;
        this.password = password;
        accounts = new HashMap<>();
    }

    public String getEmail() {
        return email;
    }

    public boolean isTrueUser() {
        return trueUser;
    }

    public void setTrueUser(boolean trueUser) {
        this.trueUser = trueUser;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Map<Integer, Account> getAccounts() {
        return accounts;
    }

    public Account addAccount(Account account) {
        accounts.put(account.getAccountNumber(), account );
        return account;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String toParsing () {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(email + ";");
        stringBuilder.append(isTrueUser() + ";");
        stringBuilder.append(password + ";");
        stringBuilder.append(role);
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }
}
