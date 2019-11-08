package ru.diasoft;

import javax.servlet.http.HttpServlet;
import java.io.IOException;

public class LoadStartup extends HttpServlet {
    public void init() {
        Start start = new Start();
        try {
            start.run();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
