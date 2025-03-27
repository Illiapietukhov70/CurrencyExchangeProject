package service;

import model.*;
import repository.AccountRepository;
import repository.DayRateCurrencyInt;
import repository.TransactionRepository;
import repository.UserRepository;
import utils.MyArrayList;
import utils.MyList;

import java.io.IOException;

public class TransactionServiceImpl implements TransactionService {
    TransactionRepository transactionRepository;
    AccountRepository accountRepository;
    UserRepository userRepository;
    DayRateCurrencyInt dayRateCurrencyInt;

    public TransactionServiceImpl(TransactionRepository transactionRepository, AccountRepository accountRepository, UserRepository userRepository, DayRateCurrencyInt dayRateCurrencyInt) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
        this.dayRateCurrencyInt = dayRateCurrencyInt;
    }

    @Override
    public Transaction getTransaction(int id) {
        //Проверка на User или принадлежность данной транзакции
        if (transactionRepository.getTransaction(id) != null) {
            return transactionRepository.getTransaction(id);
        }
        return null;
    }

    @Override
    public MyList<Transaction> getAllTransactions() {
        // Проверка на Роль Администратора
        if (transactionRepository.getAllTransactions() != null && !transactionRepository.getAllTransactions().isEmpty()) {
            return transactionRepository.getAllTransactions();
        }
        return null;
    }

    @Override
    public MyList<Transaction> getTransactionsByEmailUser(String emailUser) {
        //Проверка на Роль Администратора или на User принадлежность данной транзакции
        User tempUser = userRepository.getUserByEmail(emailUser);
        MyList<Transaction> outTransactions = new MyArrayList<>();
        tempUser.getAccounts()
                .entrySet()
                .forEach(entry -> {
                    outTransactions.add(transactionRepository.getTransaction(entry.getKey()));
                });
        if (!outTransactions.isEmpty() || dayRateCurrencyInt != null) {
            return outTransactions;
        }
        return null;
    }

    @Override
    public Transaction makeTransaction(double amount, int accountCredit, int accountDebit) throws IOException {
        Transaction transactionOut = null;
        Account accountCreditUser = accountRepository.getAccount(accountCredit);
        User userCredit = userRepository.getUserByEmail(accountCreditUser.getEmailOwner());

        System.out.println(userCredit);

        Account accountDebitUser = accountRepository.getAccount(accountDebit);
        User userDebit = userRepository.getUserByEmail(accountDebitUser.getEmailOwner());

        System.out.println(userDebit);

        if(userCredit.getRole()!=Role.BLOCKED && userDebit.getRole()!=Role.BLOCKED) {
            if(accountDebitUser.getBalance() >= amount) {
                Transaction transaction = transactionRepository.saveTransaction(amount, accountCredit, accountDebit);
                accountDebitUser.setBalance(accountDebitUser.getBalance() - amount);
                accountDebitUser.addTransaction(transaction);

                String currencyCredit = accountCreditUser.getCurrency();
                String currencyDebit = accountDebitUser.getCurrency();
                amount = amount * dayRateCurrencyInt.getActiveDayRateCurrency().getRates().get(currencyCredit) /
                dayRateCurrencyInt.getActiveDayRateCurrency().getRates().get(currencyDebit);

                accountCreditUser.setBalance(accountCreditUser.getBalance() + amount);
                accountCreditUser.addTransaction(transaction);
                transactionOut = transaction;
            }

        }

        return transactionOut;
    }
}
