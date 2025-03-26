
import repository.*;
import service.*;
import view.WelcomeMenu;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;

public class StartApp {
    public static void main(String[] args) throws IOException, ParseException {
        initClass.init();
        TransactionService transactionService = initClass.getTransactionService();
        AccountService accountService = initClass.getAccountService();
        UserService userService = initClass.getUserService();
        RatesService ratesService = initClass.getRatesService();
        TransactionRepository transactionRepository = initClass.getTransactionRepository();
        AccountRepository accountRepository = initClass.getAccountRepository();
        UserRepository userRepository = initClass.getUserRepository();


        WelcomeMenu welcomeMenu = new WelcomeMenu(userService);
        welcomeMenu.startMenu();



    }
}
