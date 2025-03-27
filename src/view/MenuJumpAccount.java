package view;

import java.io.IOException;

public interface MenuJumpAccount {
    void creditBalance();
    void debitBalance();
    void deleteAccount();
    void makeTransaction() throws IOException;
    void showMyTransactions();
    void showMayAllAccounts();
    void returnLastMenu() throws IOException;
    void logoutUser() throws IOException;
}
