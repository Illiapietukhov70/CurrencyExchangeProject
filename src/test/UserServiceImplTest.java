package test;

import model.Role;
import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.*;
import service.*;
import utils.MyList;

import static org.junit.jupiter.api.Assertions.*;

class  UserServiceImplTest {
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
    void registerUser() throws Exception {
        User user = userService.registerUser("super@pupper.com", "Super12345+");
        assertEquals(null, user);
        String testEmail = "santa@barbara.com";
        User user1 = userService.registerUser(testEmail, "Super12345+");
        assertEquals(user1.getEmail(),testEmail);

    }

    @Test
    void loginUser() {
        User loggedInUser = userService.getUserByEmail("super@pupper.com");
        assertNotNull(loggedInUser);
        assertEquals("Super@pupper.com", loggedInUser.getEmail());
        // Проверяем, что вход с неверным паролем НЕ проходит
        assertFalse(userService.loginUser("super@pupperr.com", "WrongPassword"));

        // Проверяем, что вход с правильным паролем работает
        String newEmail = "super@pupperrr.com";
        boolean loggedInSuccessfully = userService.loginUser(newEmail, "Super12345+");
        assertNotNull(loggedInSuccessfully);
        assertEquals(newEmail, userService.getActiveUser().getEmail());


    }
    @Test
    void updatePassword() {
        assertTrue(userService.updatePassword("super@pupper.com", "NewPass12345+"));
        assertFalse(userService.loginUser("super@pupper.com", "Super12345+"));
        assertNotNull(userService.loginUser("super@pupper.com", "NewPass12345+"));
    }
    @Test
    void getUserByEmail() {
        User user = userService.getUserByEmail("Super@pupper.com");
        assertNotNull(user);
        assertEquals("Super@pupper.com", user.getEmail());
        assertNull(userService.getUserByEmail("nonexistent@email.com"));
    }
    @Test
    void getActiveUser() {
        assertEquals("super@pupperrr.com", userService.getActiveUser().getEmail());
    }
    @Test
    void deleteUser() {
        assertTrue(userService.deleteUser("super@pupper.com"));
//        assertNull(userService.getUserByEmail("super@pupper.com"));
        assertFalse(userService.deleteUser("nonexistent@email.com"));

    }
    @Test
    void blockedUser() {
        assertTrue(userService.blockedUser("super@pupper.com"));
        assertTrue(userService.loginUser("super@pupperrr.com", "Super12345+"));
        assertEquals(userService.getUserByEmail("super@pupper.com").getRole(), Role.BLOCKED);

    }
    @Test
    void getAllUsers() throws Exception {
        MyList<User> users = userService.getAllUsers();
        int test1 = userService.getAllUsers().size();
        assertEquals(test1, users.size());
        User user = userService.registerUser("super@papperrr.com", "Super12345+");
        assertEquals(test1 + 1, users.size());

    }
    @Test
    void setActiveUser() throws Exception {
        User testUserNext = userService.registerUser("super@pupperpupper.com", "Super12345+");
        testUserNext.setRole(Role.ADMIN);
        userService.setActiveUser(testUserNext);
        String testEmail = userService.getUserByEmail("super@pupper.com").getEmail();
        assertNotEquals(userService.getActiveUser().getEmail(), testEmail);
    }



}

