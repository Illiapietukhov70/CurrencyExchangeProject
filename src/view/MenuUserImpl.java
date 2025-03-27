package view;

import model.Account;
import model.MenuMain;
import model.User;
import service.AccountService;
import service.RatesService;
import service.TransactionService;
import service.UserService;
import utils.MyList;

import java.io.IOException;
import java.util.Scanner;

public class MenuUserImpl extends MenuMain implements MenuUser {
    UserService userService;
    AccountService accountService;
    TransactionService transactionService;
    RatesService ratesService;


    public MenuUserImpl(UserService userService, AccountService accountService, TransactionService transactionService, RatesService ratesService) throws IOException {
        super();
        this.userService = userService;
        this.accountService = accountService;
        this.transactionService = transactionService;
        this.ratesService = ratesService;
        addAllTitles();
        startMenu();

    }

    private void addAllTitles() {
        menuTitle.put(1, "Сменить пароль" );
        menuTitle.put(2, "Меню счетов");
        menuTitle.put(3, "Logout");
        menuTitle.put(4, "Вернуться в предыдущее меню");
    }
    public void startMenu() throws IOException {
        printMenu();
        int result = scanMenu(4);
        switch (result) {
            case 1 -> updatePassword();
            case 2 -> showMenuUserAccounts();
            case 3 -> logoutUser();
            case 4 -> returnLastMenu();
        }
    }

    @Override
    public void updatePassword() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите новый пароль: ");
        String newPassword = scanner.nextLine();
        if(userService.updatePassword(userService.getActiveUser().getEmail(), newPassword)) {
            System.out.println("newPassword: " + newPassword);
            startMenu();
        } else {
            System.out.println("Пароль не изменен");
            startMenu();
        }

    }


    @Override
    public void showMenuUserAccounts() throws IOException {
        MenuUserAccountsImpl menuUserAccounts = new MenuUserAccountsImpl(userService,transactionService, accountService, ratesService);
        menuUserAccounts.startMenu();

    }

    @Override
    public void logoutUser() throws IOException {
        boolean userLogout = userService.logout();
        if(userLogout) {
            System.out.println("Logout successful");
            System.exit(0);
        }
        else {
            System.out.println("Logout failed");
            System.exit(1);
        }
    }

    @Override
    public void returnLastMenu() throws IOException {
        WelcomeMenu welcomeMenu = new WelcomeMenu(userService , accountService, transactionService, ratesService);
        welcomeMenu.startMenu();
    }
}
