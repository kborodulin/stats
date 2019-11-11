package ru.diasoft;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class Json {
    private Map<String, CheckSessions> stringCheckSessionsMap = new LinkedHashMap<>();

    public Map<String, CheckSessions> getStringCheckSessionsMap() {
        return stringCheckSessionsMap;
    }

    /**
     * Опрос серверов из файла конфигурации по URL
     */
    public void jsonRead() throws IOException {
        ConfigFileOperation configFileOperation = new ConfigFileOperation();
        configFileOperation.server();
        ObjectMapper objectMapper = new ObjectMapper();
        CheckSessions checkSessions;

        Iterator<Map.Entry<String, String>> iterator = configFileOperation.getMapServer().entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> pair = iterator.next();
            try {
                try (BufferedReader readerURL = new BufferedReader(new InputStreamReader(new URL(pair.getValue()).openStream()))) {
                    checkSessions = objectMapper.readValue(readerURL, CheckSessions.class);
                    stringCheckSessionsMap.put(pair.getKey(), checkSessions);
                }
            } catch (Exception e) {
                // Заглушка если адрес недоступен
                stringCheckSessionsMap.put(pair.getKey(), new CheckSessions());
            }
        }
    }
}