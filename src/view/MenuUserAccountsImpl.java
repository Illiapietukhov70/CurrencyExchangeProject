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

public class MenuUserAccountsImpl extends MenuMain implements MenuUserAccounts {
    private UserService userService;
    private TransactionService transactionService;
    private AccountService accountService;
    private RatesService ratesService;


    public MenuUserAccountsImpl(UserService userService,TransactionService transactionService, AccountService accountService, RatesService ratesService) throws IOException {
        super();
        this.userService = userService;
        this.transactionService = transactionService;
        this.accountService = accountService;
        this.ratesService = ratesService;
        addAllTitles();
        startMenu();
    }

    private void addAllTitles() {
        menuTitle.put(1, "Мои счета" );
        menuTitle.put(2, "Открыть счет");
        menuTitle.put(3, "Операции со счетом");
        menuTitle.put(4, "Logout");
        menuTitle.put(5, "Вернуться в предыдущее меню");
    }
    public void startMenu() throws IOException {
        printMenu();
        int result = scanMenu(5);
        switch (result) {
            case 1 -> showMyAccounts();
            case 2 -> openAccount();
            case 3 -> jumpToAccount();
            case 4 -> logoutUser();
            case 5 -> returnLastMenu();
        }
    }

    @Override
    public void showMyAccounts() {
        MyList<Account> accountMyList = accountService.getAccountsByEmailOwner(userService.getActiveUser().getEmail());
        accountMyList.toList().stream().forEach(account -> {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Номер счета: " + account.getAccountNumber() + " | ");
            stringBuilder.append("Валюта: " + account.getCurrency() + " | ");
            stringBuilder.append("Balance: " + account.getBalance() + " | ");
            System.out.println(stringBuilder.toString());
        });
    }



    @Override
    public void openAccount() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите код Валюты: ");
        String openCurrency = scanner.nextLine();
        boolean status = true;
        double openBalance = 0;
        Account account = accountService.addAccount(status, openCurrency, openBalance, userService.getActiveUser().getEmail());
        if (account == null) {
            System.out.println("Open account failed");
        }
        else {
            System.out.println("Open account successful" + account.toParsing());
        }


    }

    @Override
    public void jumpToAccount() throws IOException {
        User activeUser = userService.getActiveUser();
        MyList<Account> userAccounts = accountService.getAccountsByEmailOwner(activeUser.getEmail());
        Scanner scanner = new Scanner(System.in);
        System.out.println("Выберите счет для работы!");
        userAccounts.toList().stream().forEach(a -> System.out.println(a.getAccountNumber() + " " + a.getBalance() + " " + a.getCurrency()));
        int accountId = scanner.nextInt();
        Account account = accountService.getAccount(accountId);// Todo Если нет Счетов!!!
        System.out.println(account.getAccountNumber() + " " + account.getBalance() + " " + account.getCurrency());
        scanner.nextLine();
        MenuJumpAccountImpl menuJumpAccount = new MenuJumpAccountImpl(accountService, transactionService, userService, ratesService, account);
        menuJumpAccount.startMenu();

    }
    public void logoutUser() throws IOException {
        boolean logoutUser = userService.logout();
        if (logoutUser) {
            System.out.println("logout successful");
        }else {
            System.out.println("logout failed");
        }

    }
    public void returnLastMenu() throws IOException {
        MenuUserImpl menuUser = new MenuUserImpl(userService, accountService, transactionService, ratesService);
        menuUser.startMenu();


    }
}
