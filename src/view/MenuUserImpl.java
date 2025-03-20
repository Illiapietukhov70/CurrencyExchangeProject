package view;


import model.MenuMain;
import model.User;
import java.io.IOException;
import java.util.Scanner;

public class MenuUserImpl extends MenuMain implements MenuUser {
    public MenuUserImpl() {
        super();

        addAllTitles();
        halloUsers();
    }
    private void halloUsers() {
        User user = userService.getActiveUser();
    }

    private void addAllTitles() {
        menuTitle.put(1, "Сменить пароль" );
        menuTitle.put(2, "Удалить аккаунт");
        menuTitle.put(3, "Logout");
        menuTitle.put(4, "Вернуться в предыдущее меню");
    }
    public void startMenu() throws IOException {
        printMenu();
        int result = scanMenu(5);
        switch (result) {
            case 1 -> updatePassword();
            case 2 -> deleteAccount();
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
    public void deleteAccount() throws IOException{
        if(userService.deleteUser(userService.getActiveUser().getEmail())) {
            System.out.println("Delete Account");
            WelcomeMenu welcomeMenu = new WelcomeMenu(userService, bookService, userRepository, bookRepository);
            welcomeMenu.startMenu();
        } else {
            System.out.println("Удаление не возможно!");
            startMenu();
        }
    }

    @Override
    public void logoutUser() throws IOException  {
        if(userService.logout() && bookService.logout()) {
            System.out.println("Logout! Массив Users обновлен!");
            System.exit(0);
        }
        System.out.println("Массив Users не был корректно обновлен!" );
    }

    @Override
    public void returnLastMenu() throws IOException {
        System.out.println("Return Last Menu");
        WelcomeMenu welcomeMenu = new WelcomeMenu(userService, bookService, userRepository, bookRepository);
        welcomeMenu.startMenu();
    }
}
