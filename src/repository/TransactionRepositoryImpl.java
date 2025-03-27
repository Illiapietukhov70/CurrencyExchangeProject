package repository;

import model.Transaction;
import utils.MyArrayList;
import utils.MyList;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class TransactionRepositoryImpl implements TransactionRepository {

    private final static DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private final static String FILE_PATH = "src/model/files/transaction_lib.csv";
    private final AtomicInteger idTransaction;
    private final MyList<Transaction> transactions;

    public TransactionRepositoryImpl() throws IOException {
       this.transactions = new MyArrayList<>();
       initTransactionsRepository();
       this.idTransaction = new AtomicInteger(this.transactions.size());
    }

    private void initTransactionsRepository() throws IOException {
        File file = new File(FILE_PATH);
       try(BufferedReader br = new BufferedReader(new FileReader(file))) {
           String line;
           while ((line = br.readLine()) != null) {
               String[] data = line.split(";");
               LocalDateTime dateTime = LocalDateTime.parse(data[4], dateTimeFormat);
               Transaction transaction = new Transaction(Integer.parseInt(data[0]), Double.parseDouble(data[1]), Integer.parseInt(data[2]),Integer.parseInt(data[3]), dateTime);
               transactions.add(transaction);
           }
       }
    }

    @Override
    public Transaction saveTransaction(double amount, int accountCredit, int accountDebit) throws IOException {
        Transaction newTransaction = new Transaction(idTransaction.incrementAndGet(), amount, accountCredit, accountDebit, LocalDateTime.now());
        transactions.add(newTransaction);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            String line = newTransaction.toParsing();
            bufferedWriter.write(line);
        }
        return newTransaction;
    }

    @Override
    public Transaction getTransaction(int id) {
        for (Transaction transaction : transactions) {
            if (transaction.getId() == id) {
                return transaction;
            }
        }
        System.out.println("Transaction not found");
        return null;
    }

    @Override
    public MyList<Transaction> getAllTransactions() {
        return this.transactions;
    }

    @Override
    public MyList<Transaction> getTransactionsByAccountCredit(int accountCredit) {
        MyList<Transaction> transactions = new MyArrayList<>();
        for (Transaction transaction : transactions) {
            if (transaction.getAccountCredit() == accountCredit) {
                transactions.add(transaction);
            }
        }
        return transactions;
    }

    @Override
    public MyList<Transaction> getTransactionsByAccountDebit(int accountDebit) {
        MyList<Transaction> transactions = new MyArrayList<>();
        for (Transaction transaction : transactions) {
            if (transaction.getAccountDebit() == accountDebit) {
                transactions.add(transaction);
            }
        }
        return transactions;
    }
}
