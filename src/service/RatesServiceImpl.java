package service;

import model.DayRateCurrency;
import repository.CurrencyWiki;
import repository.DayRateCurrencyInt;

import java.io.IOException;
import java.time.LocalDate;

public class RatesServiceImpl implements RatesService {
    private final DayRateCurrencyInt dayRateCurrencyInt;

    public RatesServiceImpl(DayRateCurrencyInt dayRateCurrencyInt) {
        this.dayRateCurrencyInt = dayRateCurrencyInt;
    }

    @Override
    public DayRateCurrency getRate(LocalDate date) throws IOException {
        DayRateCurrency rateCurrency = dayRateCurrencyInt.getRate(date);
        if (rateCurrency != null) {
            return rateCurrency;
        }
        return null;
    }

    @Override
    public DayRateCurrency getActiveDayRateCurrency() throws IOException {
        DayRateCurrency rateCurrency = dayRateCurrencyInt.getActiveDayRateCurrency();
        if (rateCurrency != null) {
            return rateCurrency;
        }
        return null;
    }

    @Override
    public CurrencyWiki getCurrencyWiki() throws IOException {
           return new CurrencyWiki();
    }
}
