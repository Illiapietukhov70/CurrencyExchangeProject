package repository;


import model.DayRateCurrency;
import utils.JsonApiCurrency;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DayRateCurrencyIntImpl implements DayRateCurrencyInt {
    private static final String DATE_FORMAT = "yyyy_MM_dd";
    private static final String DATE_PATH = "src/model/files/day_rate_files";

    public DayRateCurrency activeDayRateCurrency;
    public final LocalDate date;

    public DayRateCurrencyIntImpl() throws IOException {
        this.date = LocalDate.now();
        this.activeDayRateCurrency = null;
        initDayRateCurrency();

    }
    private void initDayRateCurrency() throws IOException {
        /* Инициализация репозитария DRC ->
     Запрос есть ли файл в папке "day_rate_files" с текущей датой -> если нет = запуск утилиты связи с API биржи и запись
     1. Нового файла в папку
     2. Создание сущности Текущих курсов валют
         */
        String nameFileDate  = date.format(DateTimeFormatter.ofPattern(DATE_FORMAT)).concat(".txt");
        File fileCheck = new File(DATE_PATH, nameFileDate);
        if (!fileCheck.exists()) {
            JsonApiCurrency currency = new JsonApiCurrency();
            activeDayRateCurrency = currency.getDayRateCurrency();

            BufferedWriter writer = new BufferedWriter(new FileWriter(fileCheck, false));
            activeDayRateCurrency.getRates().forEach((key, value) -> {
                StringBuilder builder = new StringBuilder();
                builder.append(key).append(";").append(value).append("\n");
                try {
                    writer.write(builder.toString());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            writer.close();
        } else {
            DayRateCurrency lastDayRateCurrency = new DayRateCurrency("EUR");
            try (BufferedReader reader = new BufferedReader(new FileReader(fileCheck))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String [] split = line.split(";");
                    lastDayRateCurrency.addRate(split[0], Double.parseDouble(split[1]));
                }
            }
            activeDayRateCurrency = lastDayRateCurrency;
        }
    }

    @Override
    public DayRateCurrency getRate(LocalDate date) throws IOException {
        String nameFileDate  = date.format(DateTimeFormatter.ofPattern(DATE_FORMAT)).concat(".txt");
        File fileCheck = new File(DATE_PATH, nameFileDate);
        if (!fileCheck.exists()) {
            System.out.println("File with THIS DATE RATE not found");
            return null;
        } else {
            DayRateCurrency responDayRateCurrency = new DayRateCurrency("EUR");
            responDayRateCurrency.setDate(date);
            try (BufferedReader reader = new BufferedReader(new FileReader(fileCheck))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String [] split = line.split(";");
                    responDayRateCurrency.addRate(split[0], Double.parseDouble(split[1]));
                }
            }
            return responDayRateCurrency;
        }
    }

    @Override
    public DayRateCurrency getActiveDayRateCurrency() throws IOException {
        return activeDayRateCurrency;
    }
}


