package repository;

import model.Transaction;
import utils.MyList;

import java.io.IOException;

public interface TransactionRepository {
    Transaction saveTransaction(double amount, int accountCredit, int accountDebit) throws IOException;
    Transaction getTransaction(int id);
    MyList<Transaction> getAllTransactions();
    MyList<Transaction> getTransactionsByAccountCredit(int accountCredit);
    MyList<Transaction> getTransactionsByAccountDebit(int accountDebit);
}
