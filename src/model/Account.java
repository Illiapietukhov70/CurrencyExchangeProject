package model;

import model.MyException.NullTransactionInput;

import java.util.HashMap;
import java.util.Map;
//
public class Account {
    private int accountNumber;
    private String currency;
    private double balance;
    Map<Integer, Transaction> transactions;

    public Account(int accountNumber, String currency) {
        this.accountNumber = accountNumber;
        this.currency = currency;
        transactions = new HashMap<>();
        balance = 0;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getBalance() {
        return balance;
    }

    public Map<Integer, Transaction> getTransactions() {
        return transactions;
    }
    // Добавление транзакции User
    public boolean addTransaction(Transaction transaction) {
        try {
            this.transactions.put(transaction.getId(), transaction);
            return true;
        } catch (NullPointerException e) {
            return false;
        }
    }

}
