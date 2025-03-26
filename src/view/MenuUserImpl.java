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
        menuTitle.put(2, "Меню счетов");
        menuTitle.put(3, "Logout");
        menuTitle.put(4, "Вернуться в предыдущее меню");
    }
    public void startMenu() {
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
    public void showMenuUserAccounts() {

    }

    @Override
    public void logoutUser() {

    }

    @Override
    public void returnLastMenu() {

    }
}
