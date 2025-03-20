package view;

import model.Account;
import model.Transaction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public interface MenuUserAccounts {

//    регистрировать нового пользователя (аккаунт), -> Сделали в WelcomeMenu
//    открывать счета в различных валютах (в приложении должна быть поддержка минимум 3 валют),
//    пополнять счета,
//    снимать средства со счетов,
//    закрывать счета,
//    осуществлять обмен валюты (например, перевод с EUR счета на USD счет с конвертацией по курсу),
//    а также просматривать историю своих операций.

    void returnLastMenu() throws IOException;
    Account openNewAccount(); // хз Возможно boolean
    Account addMoneyOnAccount();
    Account transferMoney ();
    Account drawMoneyMyAccount ();
    boolean closeAccount();
    boolean closeAccounts();
    ArrayList<Transaction> getAllTransactions(); // хз не факт возможно, что-то другое...
}
