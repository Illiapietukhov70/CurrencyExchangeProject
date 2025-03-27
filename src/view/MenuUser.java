package view;


import java.io.IOException;

public interface MenuUser {

    void updatePassword() throws IOException;
    void showMenuUserAccounts() throws IOException;
    void logoutUser() throws IOException;
    void returnLastMenu() throws IOException;

}
