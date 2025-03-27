package view;

import model.*;
import repository.AccountRepository;
import repository.CurrencyWiki;
import repository.TransactionRepository;
import service.AccountService;
import service.RatesService;
import service.TransactionService;
import service.UserService;
import utils.MyList;

import java.io.IOException;
import java.util.Scanner;

public class MenuJumpAccountImpl extends MenuMain implements MenuJumpAccount {
    AccountService accountService;
    TransactionService transactionService;
    UserService userService;
    RatesService ratesService;
    Account userAccount;

    public MenuJumpAccountImpl(AccountService accountService, TransactionService transactionService,UserService userService, RatesService ratesService, Account userAccount) throws IOException {
        this.accountService = accountService;
        this.transactionService = transactionService;
        this.userService = userService;
        this.ratesService = ratesService;
        this.userAccount = userAccount;
        printMessage();
        addAllTitles();
        startMenu();

    }
    private void printMessage(){
        System.out.println(userAccount.toString());
    }
    private void addAllTitles() {
        menuTitle.put(1, "Пополнить счет" );
        menuTitle.put(2, "Cash Out from this Account");
        menuTitle.put(3, "Удалить аккаунт");
        menuTitle.put(4, "Перевести деньги с этого Счета");
        menuTitle.put(5, "Движение по счету");
        menuTitle.put(6, "Показать мои счета");
        menuTitle.put(7, "Logout");
        menuTitle.put(8, "Вернуться в предыдущее меню");
    }
    public void startMenu() throws IOException {
        printMenu();
        int result = scanMenu(8);
        switch (result) {
            case 1 -> creditBalance();
            case 2 -> debitBalance();
            case 3 -> deleteAccount();
            case 4 -> makeTransaction();
            case 5 -> showMyTransactions();
            case 6 -> showMyAllAccounts();
            case 7 -> logoutUser();
            case 8 -> returnLastMenu();
        }
    }


    @Override
    public void creditBalance() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Внесите сумму пополнения: " + userAccount.getCurrency());
        int amount = scanner.nextInt();
        scanner.nextLine();
        userAccount.setBalance(userAccount.getBalance() + amount);
        System.out.println("На Вашем счету: " + userAccount.getBalance() + " " + userAccount.getCurrency());
    }

    @Override
    public void debitBalance() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Укажите сумму снятия: " + userAccount.getCurrency());
        int amount = scanner.nextInt();
        scanner.nextLine();
        if (userAccount.getBalance() < amount) {
            System.out.println("Не достаточно средств на счету");
        }
        userAccount.setBalance(userAccount.getBalance() - amount);
        System.out.println("На Вашем счету: " + userAccount.getBalance() + " " + userAccount.getCurrency());

    }

    @Override
    public void deleteAccount() {
        int idForDelete = this.userAccount.getAccountNumber();
        if(userAccount.getBalance() > 0) {
            System.out.println("Перед удалением обнулите счет на сумму: "
                    + userAccount.getBalance() + " " + userAccount.getCurrency());
        } else {

            Account deleteAccount = accountService.deleteAccount(idForDelete);
            if(deleteAccount != null) {
                System.out.println("Счет: " + deleteAccount.getAccountNumber() + " " + deleteAccount.getCurrency() +
                        " успешно удален!");
            } else {
                System.out.println("404");
            }
        }
    }

    @Override
    public void makeTransaction() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите номер счета получателя");
        int account = scanner.nextInt();
        if (accountService.getAccount(account) == null) {
            System.out.println("Данного счета не существует!");
        }
        System.out.println("Введите сумму для перевода!");
        double amount = scanner.nextDouble();
        scanner.nextLine();
        if (userAccount.getBalance() < amount) {
            System.out.println("Не достаточно средств на счету");
        }
        Transaction transaction = transactionService.makeTransaction(amount, account, userAccount.getAccountNumber());
        if (transaction != null) {
            System.out.println("Transaction was successful!");
        } else {
            System.out.println("404");
        }

    }

    @Override
    public void showMyTransactions() {
        MyList<Transaction> transactions = transactionService.getTransactionsByEmailUser(userAccount.getEmailOwner());
        System.out.println(transactions.size() + " size Transaction");
        if (transactions != null) {
            transactions.toList().forEach(t -> {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Id :" + t.getId() + " | ");
                stringBuilder.append("Ammount :" + t.getAmount() + " | ");
                stringBuilder.append("Currency :" + accountService.getAccount(t.getAccountDebit()).getCurrency() + " | ");
                stringBuilder.append("Отправитель :" + accountService.getAccount(t.getAccountCredit()).getEmailOwner() + "| ");
                stringBuilder.append("Получатель :" + accountService.getAccount(t.getAccountDebit()).getEmailOwner() + " | ");
                stringBuilder.append("Дата время :" + t.getDateTime());
                System.out.println(stringBuilder.toString());

            });
        }
        else {
            System.out.println("404");
        }
    }

    @Override
    public void showMyAllAccounts() {
        String emailOwner = userAccount.getEmailOwner();
        MyList<Account> accounts = accountService.getAccountsByEmailOwner(emailOwner);
        accounts.toList().stream().forEach(e -> System.out.println(e.toParsing()));
    }

    @Override
    public void returnLastMenu() throws IOException {
        MenuUserAccountsImpl menuUserAccount = new MenuUserAccountsImpl(userService,transactionService,accountService,ratesService);
        menuUserAccount.startMenu();
    }

    @Override
    public void logoutUser() throws IOException {
        boolean logoutUser = userService.logout();
        if (logoutUser) {
            System.out.println("logout successful!");
            System.exit(0);
        }else {
            System.out.println("logout failed");
            System.exit(1);
        }

    }
}
