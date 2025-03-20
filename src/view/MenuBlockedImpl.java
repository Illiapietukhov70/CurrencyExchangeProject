package view;


import model.MenuMain;


import java.io.IOException;
import java.text.ParseException;

public class MenuBlockedImpl extends MenuMain implements MenuBlocked {

    public MenuBlockedImpl() {
        super();
//        this.userService = userService;
//        this.bookService = bookService;
//        this.userRepository = userRepository;
//        this.bookRepository = bookRepository;

        addAllTitles();

    }
    private void addAllTitles() {
        menuTitle.put(1, "Logout");
        menuTitle.put(2, "Вернуться в предыдущее меню");
    }
    public void startMenu() throws IOException {
        printMenu();
        int result = scanMenu(2);
        switch (result) {
            case 1 -> logoutUser();
            case 2 -> returnLastMenu();
        }
    }



    @Override
    public void logoutUser() {
        System.out.println("Logout");
        System.exit(0);

    }

    @Override
    public void returnLastMenu() throws IOException {
        WelcomeMenu welcomeMenu = new WelcomeMenu(userService, bookService, userRepository, bookRepository);
        welcomeMenu.startMenu();
    }
}
