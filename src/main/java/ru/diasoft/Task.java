package ru.diasoft;

import java.io.IOException;
import java.time.LocalDateTime;

public class Task implements Runnable {
    @Override
    public void run() {
        try {
            if (LocalDateTime.now().getHour() >= 2 && LocalDateTime.now().getHour() <= 22) {
                Excel excel = new Excel();
                excel.saveExcel();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}