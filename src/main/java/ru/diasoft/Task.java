package ru.diasoft;

import java.io.IOException;

public class Task implements Runnable {
    @Override
    public void run() {
        try {
            Excel excel = new Excel();
            excel.saveExcel();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}