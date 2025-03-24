package utils;

import model.DayRateCurrency;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;


public class JsonApiCurrency {
    private final DayRateCurrency dayRateCurrency;

    public JsonApiCurrency() throws IOException {
        this.dayRateCurrency = new DayRateCurrency("EUR");
        makeRateCurrency();
    }

    private void makeRateCurrency () throws IOException {

        URL url = new URL("https://api.currencylayer.com/latest?access_key=ca6d204f711c20872f2abed6a320e67d");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder responseContent = new StringBuilder();
        String inputLine;

        while ((inputLine = reader.readLine()) != null) {
            responseContent.append(inputLine);
        }
        reader.close();
//        System.out.println(responseContent);

        String[] result = responseContent.toString().split("rates\":");
        List<String> resulList = List.of(result[1].replaceAll("[{}\"]", "").split(","));
        resulList.forEach(s -> {
            String[] str = s.split(":");
            dayRateCurrency.addRate(str[0], Double.valueOf(str[1]));
        });
    }

    public DayRateCurrency getDayRateCurrency() {
        return dayRateCurrency;
    }
}
