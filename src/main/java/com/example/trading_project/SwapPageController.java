package com.example.trading_project;

import INFO.Currency;
import INFO.Request;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SwapPageController implements Initializable {

    Currency usd, eur, toman, yen, gbp;
    Currency buyCurrency, sellCurrency;
    boolean stop = false;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Thread thread = new Thread(new MyThread());
        thread.start();
    }

    public class MyThread implements Runnable {


        @Override
        public void run() {
            try {
                while (true) {
                    usd = mainCode.usd;
                    eur = mainCode.eur;
                    toman = mainCode.toman;
                    yen = mainCode.yen;
                    gbp = mainCode.gbp;
                    Thread.sleep(5000);

                    if (stop)
                        break;
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }


}
