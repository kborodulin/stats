package ru.diasoft;

import java.io.IOException;

public class Start {
    public void run() throws InterruptedException, IOException {
        while (true) {
            ConfigFileOperation configFileOperation = new ConfigFileOperation();
            configFileOperation.launch();
            Excel excel = new Excel();
            excel.saveExcel();
            Thread.sleep(configFileOperation.getLaunchFrequency() * 60000);
        }
    }
}