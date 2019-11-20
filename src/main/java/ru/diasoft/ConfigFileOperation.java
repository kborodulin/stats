package ru.diasoft;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ConfigFileOperation {
    private final String fullPaths = "C:\\apache-tomcat-8.5.29\\conf\\menext.txt";
    private List<String> listConfigFile = new ArrayList<>();
    private Map<String, String> mapServer = new LinkedHashMap<>();
    private int launchFrequency;

    public Map<String, String> getMapServer() {
        return mapServer;
    }

    public int getLaunchFrequency() {
        return launchFrequency;
    }

    /**
     * Читаем файл конфигурации
     */
    public void readConfig() throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(fullPaths))) {
            while (reader.ready()) {
                listConfigFile.add(reader.readLine());
            }
        }
    }

    /**
     * Список серверов из файла конфигурации
     */
    public void server() throws IOException {
        readConfig();
        for (String str : listConfigFile) {
            if ("".equals(str)) break;
            if ("#".equals(str.substring(0, 1))) continue;
            mapServer.put(str.substring(0, str.indexOf(" ")), str.substring(str.indexOf(" ") + 1));
        }
    }

    /**
     * Периодичность запуска в минутах из файла конфигурации
     */
    public void launch() throws IOException {
        readConfig();
        int counter = 0;
        for (String str : listConfigFile) {
            if ("".equals(str)) continue;
            if ("#".equals(str.substring(0, 1))) counter++;
            if (counter == 2 && !"#".equals(str.substring(0, 1)))
                launchFrequency = Integer.parseInt(str);
            if (counter == 3) break;
        }
    }
}