package repository;

import model.DayRateCurrency;
import java.io.IOException;
import java.time.LocalDate;

public interface DayRateCurrencyInt {
//CRUD

DayRateCurrency getRate(LocalDate date) throws IOException;
DayRateCurrency getActiveDayRateCurrency() throws IOException;






}
