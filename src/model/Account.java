package model;

import model.MyException.NullTransactionInput;
import utils.MyArrayList;
import utils.MyList;

import java.util.HashMap;
import java.util.Map;

public class Account {
    private int accountNumber;
    private boolean status;
    private String currency;
    private double balance;
    private String emailOwner;
    Map<Integer, Transaction> transactions;

    public Account(int accountNumber,boolean status, String currency, double balance, String emailOwner ) {
        this.status = status;
        this.accountNumber = accountNumber;
        this.currency = currency;
        this.balance = balance;
        this.emailOwner = emailOwner;
        transactions = new HashMap<>();
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getAccountNumber() {
        return accountNumber;
    }


    public String getCurrency() {
        return currency;
    }


    public double getBalance() {
        return balance;
    }


    public String getEmailOwner() {
        return emailOwner;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Map<Integer, Transaction> getTransactions() {
        return transactions;
    }

    public boolean addTransaction(Transaction transaction) {
        transactions.put(transaction.getId(), transaction);
        return true;
    }

    // Добавление транзакции User
    public boolean initTransactions(MyList<Transaction> inputTransactions) {
        try {
            for (Transaction transaction : inputTransactions) {
                this.transactions.put(transaction.getId(), transaction);
            }
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }
    public String toParsing (){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(accountNumber + ";");
        stringBuilder.append(currency + ";");
        stringBuilder.append(balance + ";");
        stringBuilder.append(emailOwner + ";");
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }

}
