package repository;

import model.Account;
import utils.MyArrayList;

import java.io.IOException;

public interface AccountRepository {
    Account saveAccount(boolean status, String currency, double balance, String emailOwner) throws IOException;
    Account getAccount(int id);
    Account deleteAccount(int id);
    MyArrayList<Account> getAccountsByEmailOwner(String emailOwner);
    boolean logoutAccountRepository() throws IOException;
}
