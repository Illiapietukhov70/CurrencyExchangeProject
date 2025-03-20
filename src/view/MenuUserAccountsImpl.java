package view;

import model.Account;
import model.MenuMain;
import model.Transaction;
import utils.MyArrayList;
import utils.MyList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//    открывать счета в различных валютах (в приложении должна быть поддержка минимум 3 валют),
//    пополнять счета,
//    снимать средства со счетов,
//    закрывать счета,
//    осуществлять обмен валюты (например, перевод с EUR счета на USD счет с конвертацией по курсу),
//    а также просматривать историю своих операций.


public class MenuUserAccountsImpl extends MenuMain implements MenuUserAccounts {

    public MenuUserAccountsImpl() {

        addAllTitles();
    }

    private void addAllTitles() {
    menuTitle.put(1, "Открыть новый счет" );
    menuTitle.put(2, "Пополнить счет" );
    menuTitle.put(3, "Снять деньги со счета" );
    menuTitle.put(4, "Закрыть счет" );
    menuTitle.put(5, "Закрыть все счета");
    menuTitle.put(6, "Трансфер денежных средств");
    menuTitle.put(7, "Просмотреть историю операций");
    menuTitle.put(8, "Проcмотреть баланс счета");
    menuTitle.put(9, "Logout");
    menuTitle.put(10, "Вернуться в предыдущее меню");
}
public void startMenu() {
    printMenu();
    int result = scanMenu(10);
    switch (result) {
        case 1 -> openNewAccount();
        case 2 -> addMoneyOnAccount();
        case 3 -> drawMoneyMyAccount();
        case 4 -> closeAccount();
        case 5 -> closeAccounts();
        case 6 -> transferMoney();
        case 7-> borrowBookByID();
        case 8 -> returnBookByID();
        case 9 -> reserveBookByID();
        case 10 -> logout();
        case 11-> returnLastMenu();
    }
}
    @Override
    public void returnLastMenu() throws IOException {
    }
    @Override
    public Account openNewAccount() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите код валюты нового счета");
        String newAccountCurrency = scanner.nextLine();
        if (checkCurrency(newAccountCurrency)) {
            System.out.println("Открываем счет в сервисе"); // ToDo
        }
        scanner.close();
        return null;
    }

    private boolean checkCurrency(String newAccountCurrency) {
        //1. Проверяем есть ли валюта в SetKey Map dayRateCurrency -> соответственно ругаемся
        //2. Проверяем есть ли у Юзера счет в такой же валюте и просто напоминаем!
        return true;
    }
//    double amount, String currency, Account accountInterCredit
    @Override
    public Account addMoneyOnAccount() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Выберите счет пополнения: ");
        //sout -> service.getAllMyAccount-> sout All My Account
        //service.MyAccount.getCurrency
        double amount = scanner.nextDouble();
        scanner.nextLine();
        scanner.close();
        //service.THIS AMOUNT ON THIS ACCOUNT
        return null;
    }
//    double amount, String currency, Account accountInterDebit
    @Override
    public Account drawMoneyMyAccount() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Выберите счет: ");
        //sout -> service.getMyAllAccount -> sout choiceAccount.getBalance
        double amount = scanner.nextDouble();
        //service.checkAvailableAmount -> if true -> service.DrawChoiceAccount THIS AMOUNT
        // if false -> sout "Измените сумму!"
        scanner.nextLine();
        scanner.close();
        return null;
    }


    @Override
    public boolean closeAccount() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Выберите счет: ");
        //sout -> service.getAllMyAccount -> sout choiceAccount.getBalance
        // choiceAccount.getBalance > 0 -> sout "Make transferMoney(double choiceAccount.getBalance) Or drawMoneyMyAccount(double choiceAccount.getBalance)"
        // if choiceAccount.getBalance == 0 -> service.closeChoiceAccount
        return false;
    }

    @Override
    public boolean closeAccounts() {
//        MyList<Account> accounts = new MyArrayList<>(List.of(service.getAllMyAccounts)); // делаем стрим из БД с всеми Accouts User и в to

        return false;
    }






    // double amount, Account accountCredit, Account accountDebit
    @Override
    public Account transferMoney() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Выберите Дебит счет: ");
        //service.getAllMyAccount-> sout All My Account
        System.out.println("Выберите Кредит счет: ");
        //service.checkAccount - if true = creditAccount
        double amount = scanner.nextDouble();
        //service.checkBalanceMyAccount (debitAccount) -> if true -> service.makeTransfer
        scanner.nextLine();
        scanner.close();
        //return MyCreditAccount;

        return null;
    }





    @Override
    public ArrayList<Transaction> getAllTransactions() {
        return null;
    }
}
