package model;

import java.util.HashMap;
import java.util.Scanner;

public abstract class MenuMain {
    protected HashMap<Integer, String> menuTitle;

    public MenuMain() {
        this.menuTitle = new HashMap<>() {
        };
    }
    protected void printMenu() {
        for (HashMap.Entry<Integer, String> entry : menuTitle.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
    protected int scanMenu(int option) {
        int result = 0;
        boolean scanResult = false;
        while (scanResult == false) {
            Scanner scanner = new Scanner(System.in);
            try {
                result = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Введите цифру из меню!");
                scanMenu(option);
            }
            if (result > 0 && result <= option) {
                scanResult = true;
                return result;
            } else {
                System.out.println("Ввод должен быть от 0 до " + option);
            }


        }



        return result;
    }
}
