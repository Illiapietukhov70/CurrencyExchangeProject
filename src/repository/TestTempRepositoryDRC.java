package repository;

import model.DayRateCurrency;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;

public class TestTempRepositoryDRC {
    public static void main(String[] args) throws IOException, ParseException {
        DayRateCurrencyInt dayRateCurrencyInt = new DayRateCurrencyIntImpl();
        DayRateCurrency testDayRates = dayRateCurrencyInt.getActiveDayRateCurrency();
        System.out.println(testDayRates.getRates());
        CurrencyWiki currencyWiki = new CurrencyWiki();
        TransactionRepository transactionRepository = new TransactionRepositoryImpl();
        AccountRepository accountRepository = new AccountRepositoryImpl(transactionRepository);
        UserRepository userRepository = new UserRepositoryImpl(accountRepository);
        System.out.println(currencyWiki.getWorldCurrencyMap());

    }

}
