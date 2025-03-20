package view;
import model.Book;
import model.MenuMain;
import model.Role;
import model.User;
import repository.BookRepository;
import repository.UserRepository;
import service.BookService;
import service.UserService;
import utils.MyArrayList;
import utils.MyList;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;
//userService, bookService, userRepository, bookRepository

public class MenuAdminImpl extends MenuMain implements MenuAdmin {


    public MenuAdminImpl() {
        super();
//        this.userService = userService;
//        this.bookService = bookService;
//        this.userRepository = userRepository;
//        this.bookRepository = bookRepository;

        addAllTitles();
        halloUsers();
    }
    private void addAllTitles() {
        menuTitle.put(1, "Сменить роль User by Email" );
        menuTitle.put(2, "Удалить аккаунт User by Email" );
        menuTitle.put(3, "Редактировать данные User by Email" );
        menuTitle.put(4, "Найти User by Email" );
        menuTitle.put(5, "HOW I AM?");
        menuTitle.put(6, "Распечатать всех Users");
        menuTitle.put(7, "Logout");
        menuTitle.put(8, "Вернуться в предыдущее меню");
    }
    public void startMenu() throws IOException {
        printMenu();
        int result = scanMenu(8);
        switch (result) {
            case 1 -> setNewUserRoleByEmail();
            case 2 -> deleteUserByEmail();
            case 3 -> editUserByEmail();
            case 4 -> findUserByEmail();
            case 5 -> getActiveUser();
            case 6 -> getAllUsers();
            case 7 -> logout();
            case 8 -> returnLastMenu();
        }
    }
    private User returnUserByEmail() {
        System.out.println("Введи Email требуемого User");
        Scanner scanner = new Scanner(System.in);
        String email = scanner.nextLine();
        return userService.getUserByEmail(email);
    }
    private void halloUsers() {
        User user = userService.getActiveUser();

    }

    @Override
    public void setNewUserRoleByEmail() throws IOException {
        User tempUser = returnUserByEmail();
        System.out.println(tempUser);
        System.out.println("Введите новую роль User");
        System.out.println("1, ADMIN");
        System.out.println("2, USER");
        System.out.println("3, BLOCKED");
        System.out.println("4, Вернуться в предыдущее меню");
        Scanner scanner = new Scanner(System.in);
        int newUserRole = scanner.nextInt();
        scanner.nextLine();
        switch (newUserRole) {
            case 1 -> tempUser.setRole(Role.ADMIN);
            case 2 -> tempUser.setRole(Role.USER);
            case 3 -> tempUser.setRole(Role.BLOCKED);
            case 4 -> this.startMenu();
        }
        startMenu();
    }
    @Override
    public void deleteUserByEmail() throws IOException {
        User tempUser = returnUserByEmail();
        if (userService.deleteUser(tempUser.getEmail())) {
            System.out.println(" User Deleted Successfully");
        } else {
            System.out.println(" User Delete Failed");
        }
        startMenu();
    }

    @Override
    public void editUserByEmail() {
        // Menu edit User

    }

    @Override
    public void findUserByEmail() throws IOException {
        User tempUser = returnUserByEmail();
        System.out.println(tempUser);
        startMenu();
    }

    @Override
    public void getActiveUser() throws IOException {
        System.out.println(userService.getActiveUser());
        startMenu();

    }

    @Override
    public void getAllUsers() throws IOException {
//        MyList<User> users = userRepository.getAllUsers();
//        if (users != null && users.size() > 0) {
//            for (User user : users) {
//                System.out.println(user);
//            }
//        } else {
//            System.out.println("No User Found");
//        }
//        startMenu();
    }



    @Override
    public void logout() throws IOException {
        if(userService.logout() && bookService.logout()) {
            System.out.println("Logout! Массив Users обновлен!");
            System.exit(0);
        }
        System.out.println("Массив Users не был корректно обновлен!" );

    }


    @Override
    public void returnLastMenu() throws IOException {
        WelcomeMenu welcomeMenu = new WelcomeMenu(userService, bookService, userRepository, bookRepository);
        welcomeMenu.startMenu();
    }
}
