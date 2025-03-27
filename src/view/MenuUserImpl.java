package view;

import model.Account;
import model.MenuMain;
import model.User;
import service.AccountService;
import service.UserService;
import utils.MyList;

import java.util.Scanner;

public class MenuUserImpl extends MenuMain implements MenuUser {
    UserService userService;
    AccountService accountService;


    public MenuUserImpl(UserService userService, AccountService accountService) {
        super();
        this.userService = userService;
        this.accountService = accountService;

    }

    private void addAllTitles() {
        menuTitle.put(1, "Сменить пароль" );
        menuTitle.put(2, "Удалить аккаунт");
        menuTitle.put(3, "Меню счетов");
        menuTitle.put(4, "Logout");
        menuTitle.put(5, "Вернуться в предыдущее меню");
    }
    public void startMenu() {
        printMenu();
        int result = scanMenu(5);
        switch (result) {
            case 1 -> updatePassword();
            case 2 -> deleteAccount();
            case 3 -> showMenuUserAccounts();
            case 4 -> logoutUser();
            case 5 -> returnLastMenu();
        }
    }

    @Override
    public void updatePassword() {
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
    public void deleteAccount() {
        User activeUser = userService.getActiveUser();
        MyList<Account> userAccounts = accountService.getAccountsByEmailOwner(activeUser.getEmail());
        System.out.println("Выберите Счет для закрытия");
        System.out.println(userAccounts);
        Scanner scanner = new Scanner(System.in);
        int accountId = scanner.nextInt();
        scanner.nextLine();
        Account accountForDelete = null;
        try {
            Account account = userAccounts.get(accountId);
            accountForDelete = account;
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(accountForDelete.getBalance() > 0) {
            System.out.println("Перед удалением обнулите счет на сумму: "
                    + accountForDelete.getBalance() + " " + accountForDelete.getCurrency());
        } else {
            Account deleteAccount = accountService.deleteAccount(accountId);
            if(deleteAccount != null) {
            System.out.println("Счет: " + deleteAccount.getAccountNumber() + " " + deleteAccount.getCurrency() +
                    " успешно удален!");
            } else {
                System.out.println("404");
            }
        }

    }

    @Override
    public void showMenuUserAccounts() {

    }

    @Override
    public void logoutUser() {

    }

    @Override
    public void returnLastMenu() {

    }
}
