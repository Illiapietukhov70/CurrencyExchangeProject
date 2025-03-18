package model.MyException;

import model.Transaction;

public class NullTransactionInput extends RuntimeException {
    public NullTransactionInput(String message) {
        super(message);
    }
    public void NullTransaction(Transaction transaction) {
        if (transaction == null) {
            throw new NullPointerException("Пришла пустая транзакция");
        }
    }
}
