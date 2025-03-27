package test;

import model.Account;
import model.Role;
import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.*;
import service.*;
import utils.MyList;

import java.awt.print.Book;

import static org.junit.jupiter.api.Assertions.*;

class AccountServiceTest {
    DayRateCurrencyInt dayRateCurrencyInt;
    TransactionRepository transactionRepository;
    AccountRepository accountRepository;
    UserRepository userRepository;
    TransactionService transactionService;
    AccountService accountService;
    UserService userService;

    @BeforeEach
    void setUp() throws Exception {
        dayRateCurrencyInt = new DayRateCurrencyIntImpl();
        transactionRepository = new TransactionRepositoryImpl();
        accountRepository = new AccountRepositoryImpl(transactionRepository);
        userRepository = new UserRepositoryImpl(accountRepository);
        transactionService = new TransactionServiceImpl(transactionRepository, accountRepository, userRepository, dayRateCurrencyInt);
        accountService = new AccountServiceImpl(transactionRepository, userRepository, accountRepository);
        userService = new UserServiceImpl(userRepository, accountRepository);
        User testUser = userService.registerUser("super@pupperrr.com", "Super12345+");
        testUser.setRole(Role.ADMIN);
        userService.setActiveUser(testUser);

    }
    @Test
    void getAccountsByEmailOwner() {
        MyList<Account> accounts = accountRepository.getAccountsByEmailOwner("super@pupperrr.com");
        assertNotNull(accounts);


    }
    @Test
    void addAccount() throws Exception {
        int test1 = accountRepository.getAccountsByEmailOwner("super@pupperrr.com").size();
        accountService.addAccount(true, "USD", 1000, "super@pupperrr.com");
        assertEquals(test1 + 1, accountService.getAccountsByEmailOwner("super@pupperrr.com").size());
        assertFalse(test1 == accountService.getAccountsByEmailOwner("super@pupperrr.com").size());
        accountService.addAccount(true, "EUR", 2000, "super@pupperrr.com");
        assertEquals(test1 + 2, accountService.getAccountsByEmailOwner("super@pupperrr.com").size());
        assertNotNull(test1 != accountService.getAccountsByEmailOwner("super@pupperrr.com").size());
    }
    @Test
    void deleteAccount() throws Exception {
        Account account1 = accountRepository.deleteAccount(1);
        Account account2 = accountRepository.deleteAccount(1);
        assertEquals(account1, account2);
        Account account3 = accountRepository.deleteAccount(2);
        assertNotEquals(account1, account3);

    }
    @Test
    void getAccount() throws Exception {
        Account account1 = accountService.getAccount(1);
        Account account2 = accountService.getAccount(1);
        assertEquals(account1, account2);
        Account account3 = accountService.getAccount(2);
        assertNotEquals(account1, account3);
    }


    }