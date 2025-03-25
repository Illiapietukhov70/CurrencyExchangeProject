package service;

import model.DayRateCurrency;
import repository.CurrencyWiki;

import java.io.IOException;
import java.time.LocalDate;

public interface RatesService {
    DayRateCurrency getRate(LocalDate date) throws IOException;
    DayRateCurrency getActiveDayRateCurrency() throws IOException;
    CurrencyWiki getCurrencyWiki() throws IOException;

}
