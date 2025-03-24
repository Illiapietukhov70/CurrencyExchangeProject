package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;

public class Transaction {
    private final static DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private int id;
    private final double amount;
    private final LocalDateTime dateTime;
    private final int accountCredit;
    private final int accountDebit;

    public Transaction(int id, double amount, int accountCredit, int accountDebit, LocalDateTime dateTime) {
        this.id = id;
        this.amount = amount;
        this.accountCredit = accountCredit;
        this.accountDebit = accountDebit;
        this.dateTime = dateTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public int getAccountCredit() {
        return accountCredit;
    }

    public int getAccountDebit() {
        return accountDebit;
    }
    public String toParsing () {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(id + ";");
        stringBuilder.append(amount + ";");
        stringBuilder.append(accountCredit + ";");
        stringBuilder.append(accountDebit + ";");
        stringBuilder.append(dateTime.format(dateTimeFormat));
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }
}
