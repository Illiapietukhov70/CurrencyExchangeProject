package model;

import java.time.LocalDateTime;

public class Transaction {
    private int id;
    private int amount;
    private LocalDateTime dateTime;
    private Account accountCredit;
    private Account accountDebit;

    public Transaction(int amount, Account accountCredit, Account accountDebit) {
        this.id = id;
        this.amount = amount;
        this.accountCredit = accountCredit;
        this.accountDebit = accountDebit;
        this.dateTime = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }


    public Account getAccountCredit() {
        return accountCredit;
    }


    public Account getAccountDebit() {
        return accountDebit;
    }
}
