package model.MyException;

public class NullCurrencyInput extends RuntimeException {
  public NullCurrencyInput(String message) {
    super(message);
  }
  public void addRate(String currency, double rate) {
    if (currency == null || currency.isEmpty() || rate <= 0) {
      throw new NullCurrencyInput("Currency or Rate cannot be null or empty");
    }
  }
}
