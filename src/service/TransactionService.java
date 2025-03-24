package service;

import model.Transaction;
import utils.MyList;

import java.io.IOException;

public interface TransactionService {
    Transaction getTransaction(int id);
    MyList<Transaction> getAllTransactions();
    MyList<Transaction> getTransactionsByEmailUser(String emailUser);
    Transaction makeTransaction(double amount, int accountCredit, int accountDebit) throws IOException;
}
