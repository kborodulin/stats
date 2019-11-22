package ru.diasoft;

import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledStart extends HttpServlet {
    @Override
    public void init() {
        ConfigFileOperation configFileOperation = new ConfigFileOperation();
        try {
            configFileOperation.launch();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.setProperty("sun.net.client.defaultConnectTimeout", "600000");
        System.setProperty("sun.net.client.defaultReadTimeout", "600000");
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(new Task(), 0, configFileOperation.getLaunchFrequency(), TimeUnit.MINUTES);
    }
}