package view;


import model.MenuMain;
import model.Role;
import model.User;
import service.UserService;


import java.io.IOException;
import java.util.Scanner;

public class WelcomeMenu extends MenuMain {
    private final UserService userService;


    public WelcomeMenu(UserService userService) {
        super();
        this.userService = userService;

        addAllTitles();


    }

    private void addAllTitles() {
        menuTitle.put(1, "Sing In");
        menuTitle.put(2, "Registration");
        menuTitle.put(3, "Logout");
    }

    public void startMenu() throws IOException {
        printMenu();
        int result = scanMenu(3);
        switch (result) {
            case 1 -> loginUser();
            case 2 -> registrationUser();
            case 3 -> logoutUser();

        }

    }

    private void loginUser() throws IOException {
        System.out.println("Для входа в систему введите Ваш Email: ");
        Scanner scanner = new Scanner(System.in);
        String email = scanner.nextLine();
        System.out.println("Введите Ваш пароль: ");
        String password = scanner.nextLine();
        if (userService.loginUser(email, password)) {
            User tempUser = userService.getActiveUser();
            if (tempUser != null) {
                Role role = tempUser.getRole();
                System.out.println(role);
//                switch (role) {
//                    case ADMIN -> {
//                        MenuAdminImpl menuAdmin = new MenuAdminImpl(userService, bookService, userRepository, bookRepository);
//                        menuAdmin.startMenu();
//                    }
//                    case USER -> {
//                        MenuUserImpl menuUser = new MenuUserImpl(userService, bookService, userRepository, bookRepository);
//                        menuUser.startMenu();
//                    }
//                    case BLOCKED -> {
//                        MenuBlockedImpl menuBlocked = new MenuBlockedImpl(userService, bookService, userRepository, bookRepository);
//                        menuBlocked.startMenu();
//                    }
//                }
            }
        } else {
            System.out.println("Email или Пароль не верны!");
            this.startMenu();
        }
    }

    protected void registrationUser() throws IOException {
        System.out.println("Для регистрации в системе введите Ваш Email: ");
        Scanner scanner = new Scanner(System.in);
        String email = scanner.nextLine();
        System.out.println("Введите Ваш пароль: ");
        String password = scanner.nextLine();
        System.out.println(email + " " + password);
        User user = userService.registerUser(email, password);
        System.out.println(user);
        userService.logout();
//        if (user != null) {
//            MenuUserImpl menuUser = new MenuUserImpl(userService, bookService, userRepository, bookRepository);
//            menuUser.startMenu();
//        }
        scanner.close();


    }

    private void logoutUser() {
        System.out.println("Logout");
        System.exit(0);
    }
}
