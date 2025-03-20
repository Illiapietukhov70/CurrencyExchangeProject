package view;

import java.io.IOException;
import java.text.ParseException;

public interface MenuUser {

    void updatePassword() throws CloneNotSupportedException, IOException, ParseException;
    void deleteAccount() throws CloneNotSupportedException, IOException, ParseException;
    void logoutUser() throws IOException, CloneNotSupportedException;
    void returnLastMenu() throws CloneNotSupportedException, IOException, ParseException;
}
