package service;

import model.Account;
import repository.AccountRepository;
import repository.TransactionRepository;
import repository.UserRepository;
import utils.MyArrayList;

import java.io.IOException;

public class AccountServiceImpl implements AccountService {
    TransactionRepository transactionRepository;
    UserRepository userRepository;
    AccountRepository accountRepository;

    public AccountServiceImpl(TransactionRepository transactionRepository, UserRepository userRepository, AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public Account addAccount(boolean status, String currency, double balance, String emailOwner) throws IOException {
        Account tempAccount = accountRepository.saveAccount(status , currency, balance, emailOwner);
        if (tempAccount != null) {
            return tempAccount;
        }
        return null;
    }

    @Override
    public Account getAccount(int id) {
        Account tempAccount = accountRepository.getAccount(id);
        if (tempAccount != null) {
            return tempAccount;
        }
        return null;
    }

    @Override
    public Account deleteAccount(int id) {
        Account tempAccount = accountRepository.deleteAccount(id);
        if (tempAccount != null) {
            return tempAccount;
        }
        return null;
    }


    @Override
    public MyArrayList<Account> getAccountsByEmailOwner(String emailOwner) {
        MyArrayList<Account> tempAccounts = accountRepository.getAccountsByEmailOwner(emailOwner);
        if (tempAccounts != null && !tempAccounts.isEmpty()) {
            return tempAccounts;
        }
        return null;
    }
}
