package repository;

import model.DayRateCurrency;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;

public interface DayRateCurrencyInt {
//CRUD
boolean addRate(DayRateCurrency dayRateCurrency) throws IOException;

DayRateCurrency getRate(LocalDate date) throws IOException;






}
