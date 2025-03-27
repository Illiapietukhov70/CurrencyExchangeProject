package repository;

import model.Account;
import model.Transaction;
import utils.MyArrayList;
import utils.MyList;

import java.io.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class AccountRepositoryImpl implements AccountRepository {
    private final TransactionRepository transactionRepository;
    private static final String FILE_PATH = "src/model/files/accouts_lib.csv";
    private final MyList<Account> accounts;
    private final AtomicInteger idAccount;

    public AccountRepositoryImpl(TransactionRepository transactionRepository) throws IOException {
        this.transactionRepository = transactionRepository;
        this.accounts = new MyArrayList<>();
        initAccountRepository();
        inputTransactionToAccount();
        this.idAccount = new AtomicInteger(this.accounts.size());
    }

    private void initAccountRepository() throws IOException {
        File file = new File(FILE_PATH);
        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(";");
                Account newAccount = new Account(Integer.parseInt(data[0]), Boolean.parseBoolean(data[1]), data[2], Double.parseDouble(data[3]), data[4]);
                this.accounts.add(newAccount);
            }
        }
    }
    private void inputTransactionToAccount() {
        accounts.forEach(account -> {
            MyList<Transaction> creditTransactions = transactionRepository.getTransactionsByAccountCredit(account.getAccountNumber());
            MyList<Transaction> debitTransactions = transactionRepository.getTransactionsByAccountDebit(account.getAccountNumber());
            account.initTransactions(creditTransactions);
            account.initTransactions(debitTransactions);
        });
    }

    @Override
    public Account saveAccount(boolean status, String currency, double balance, String emailOwner) throws IOException {
        Account newAccount = new Account(idAccount.incrementAndGet(),status, currency, balance, emailOwner);
        this.accounts.add(newAccount);
        return newAccount;
    }

    @Override
    public Account getAccount(int id) {
        for (Account account : this.accounts) {
            if (account.getAccountNumber() == id) {
                return account;
            }
        }
        return null;
    }

    @Override
    public Account deleteAccount(int id) {
        for (Account account : this.accounts) {
            if (account.getAccountNumber() == id) {
                account.setStatus(false);
                return account;
            }
        }
        return null;
    }

    @Override
    public MyArrayList<Account> getAccountsByEmailOwner(String emailOwner) {
        MyArrayList<Account> newAccounts = new MyArrayList<>();
        accounts.toList().stream().filter(account -> account.getEmailOwner().equals(emailOwner)).forEach(newAccounts::add);
        return newAccounts;
    }

    @Override
    public boolean logoutAccountRepository() throws IOException {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH, false))) {
            accounts.toList().forEach(account -> {
                try {
                    bw.write(account.toParsing());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        return true;
    }
}
