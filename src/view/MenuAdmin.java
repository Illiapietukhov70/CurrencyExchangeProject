package view;

import java.io.IOException;
import java.text.ParseException;

public interface MenuAdmin {

    void setNewUserRoleByEmail () throws IOException ; // BLOCKED and ADMIN
    void deleteUserByEmail() throws IOException; // Удалением Пользователя
    void editUserByEmail();
    void findUserByEmail() throws IOException;
    void getActiveUser() throws IOException;
    void getAllUsers() throws IOException;
    void logout() throws IOException;
    void returnLastMenu() throws IOException;
}
