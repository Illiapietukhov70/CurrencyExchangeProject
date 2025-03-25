
import repository.*;
import service.*;
import view.WelcomeMenu;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;

public class StartApp {
    public static void main(String[] args) throws IOException, ParseException {
        CurrencyWiki wiki = new CurrencyWiki();
        DayRateCurrencyInt dayRateCurrencyInt = new DayRateCurrencyIntImpl();
        TransactionRepository transactionRepository = new TransactionRepositoryImpl();
        AccountRepository accountRepository = new AccountRepositoryImpl(transactionRepository);
        UserRepository userRepository = new UserRepositoryImpl(accountRepository);
        TransactionService transactionService = new TransactionServiceImpl(transactionRepository, accountRepository, userRepository, dayRateCurrencyInt);
        AccountService accountService = new AccountServiceImpl(transactionRepository, userRepository, accountRepository);
        UserService userService = new UserServiceImpl(userRepository, accountRepository);
        RatesService ratesService = new RatesServiceImpl(dayRateCurrencyInt);


        WelcomeMenu welcomeMenu = new WelcomeMenu(userService);
        welcomeMenu.startMenu();



    }
}
