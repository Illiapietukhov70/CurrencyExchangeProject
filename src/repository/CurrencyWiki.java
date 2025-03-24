package repository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class CurrencyWiki {
    Map<String, String> worldCurrencyMap;

    public CurrencyWiki() throws IOException {
        this.worldCurrencyMap = new HashMap<>();
        initWorldCurrencyMap();
    }
    private void initWorldCurrencyMap() throws IOException {
        File wikiFile = new File("src/model/files/currency_lib.csv");
        try (BufferedReader br = new BufferedReader(new FileReader(wikiFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] split = line.split("\\s\\d{3}\\s");
                worldCurrencyMap.put(split[0], split[1]);
            }
        }
    }

    public Map<String, String> getWorldCurrencyMap() {
        return worldCurrencyMap;
    }
}
