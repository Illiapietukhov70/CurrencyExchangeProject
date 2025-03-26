package service;

import repository.*;

import java.io.IOException;
import java.text.ParseException;

public class initClass {
    private static CurrencyWiki wiki;
    private static DayRateCurrencyInt dayRateCurrencyInt;

    private static TransactionRepository transactionRepository;
    private static AccountRepository accountRepository;
    private static UserRepository userRepository;

    private static TransactionService transactionService;
    private static AccountService accountService;
    private static UserService userService;
    private static RatesService ratesService;

    public static void init() throws IOException, ParseException {
        wiki = new CurrencyWiki();
        dayRateCurrencyInt = new DayRateCurrencyIntImpl();
        transactionRepository = new TransactionRepositoryImpl();
        accountRepository = new AccountRepositoryImpl(transactionRepository);
        userRepository = new UserRepositoryImpl(accountRepository);
        transactionService = new TransactionServiceImpl(transactionRepository, accountRepository, userRepository, dayRateCurrencyInt);
        accountService = new AccountServiceImpl(transactionRepository, userRepository, accountRepository);
        userService = new UserServiceImpl(userRepository, accountRepository);
        ratesService = new RatesServiceImpl(dayRateCurrencyInt);
    }
    public static TransactionRepository getTransactionRepository() {
        return transactionRepository;
    }

    public static AccountRepository getAccountRepository() {
        return accountRepository;
    }

    public static UserRepository getUserRepository() {
        return userRepository;
    }

    public static TransactionService getTransactionService() {
        return transactionService;
    }

    public static AccountService getAccountService() {
        return accountService;
    }

    public static UserService getUserService() {
        return userService;
    }

    public static RatesService getRatesService() {
        return ratesService;
    }
}

