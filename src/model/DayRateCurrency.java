package model;

import model.MyException.NullCurrencyInput;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class DayRateCurrency {
    private LocalDate date;
    private String rootCurrency;
    Map<String, Double> rates;

    public DayRateCurrency(String rootCurrency) {
        this.rootCurrency = rootCurrency;
        this.rates = new HashMap<>();
        this.date = LocalDate.now();

    }

    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getRootCurrency() {
        return rootCurrency;
    }


    public Map<String, Double> getRates() {
        return rates;
    }
    public boolean addRate(String currency, double rate) {
        try {
            this.rates.put(currency, rate);
        } catch (NullCurrencyInput e) {
            return false;
        }
        return true;
    }
}
