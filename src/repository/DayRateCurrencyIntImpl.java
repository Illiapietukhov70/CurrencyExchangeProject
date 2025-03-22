package repository;

import model.DayRateCurrency;
import utils.MyArrayList;
import utils.MyList;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class DayRateCurrencyIntImpl implements DayRateCurrencyInt {
    @Override
    public boolean addRate(DayRateCurrency dayRateCurrency) throws IOException {
        //Создаем Строку с курсами валют для записи в файл
        String formattedDate = dayRateCurrency.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        StringBuilder newDayRate = new StringBuilder();
        newDayRate.append(formattedDate + "|");
        newDayRate.append(dayRateCurrency.getRootCurrency() + "|");
        dayRateCurrency
                .getRates()
                .entrySet()
                .stream()
                .forEach(entry -> {
                    newDayRate.append(entry.getKey() + "=" + entry.getValue() + "$");
                });
        newDayRate.append("\n");
        File pathFile = new File("src/day_rate_currency.txt");
        BufferedWriter br = new BufferedWriter(new FileWriter(pathFile, true));
        br.write(newDayRate.toString());
        br.close();

        return false;
    }

    @Override
    public DayRateCurrency getRate(LocalDate date) throws IOException {
        File pathFile = new File("src/day_rate_currency.txt");
        BufferedReader br = new BufferedReader(new FileReader(pathFile));
        String line;
        DayRateCurrency dayRateCurrency = new DayRateCurrency("EUR");

        while ((line = br.readLine()) != null) {
            String[] splitLine = line.split("|");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            LocalDate tempDate = LocalDate.parse(splitLine[0], formatter);
            try {
                if (tempDate.equals(date)) {
                    String[] splitRate = splitLine[2].split("$");
                    Arrays.stream(splitRate).forEach(elem -> {
                        String[] tempElement = elem.split("=");
                        dayRateCurrency.addRate(tempElement[0], Double.parseDouble(tempElement[1]));
                    });
                }
            } catch (Exception e) {
                System.out.println(" Произошла ошибка!!!");
                e.printStackTrace();
            }

        }
        br.close();
        return dayRateCurrency;
    }
}

