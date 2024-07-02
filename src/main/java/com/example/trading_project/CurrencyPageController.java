package com.example.trading_project;

import INFO.Currency;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableView;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CurrencyPageController implements Initializable {

    @FXML
    private Text changeText;

    @FXML
    private Text changes;

    @FXML
    private Text currentPrice;

    @FXML
    private Text currentPriceText;

    @FXML
    private Text turnover;

    @FXML
    private Text turnoverText;

    @FXML
    private Text currencyName;

    @FXML
    private LineChart<?, ?> chart;

    @FXML
    private TableView<?> table1;

    @FXML
    private TableView<?> table2;

    @FXML
    private TableView<?> table3;

    @FXML
    private Button backButton;

    Currency currency;
    boolean stop = false;
    XYChart.Series series;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Thread thread = new Thread(new MyThread());
        makeChart();
        thread.start();
    }

    public class MyThread implements Runnable {

        @Override
        public void run() {
            int count = 1;
            while (true){
                try {
                    currency = (Currency) mainCode.objIn.readObject();
                    currencyName.setText(currency.getName());
                    currentPriceText.setText(currency.getCurrentPriceStr());
                    changeText.setText(currency.getChangeStr());
                    updateChart(count++, currency);
                    if (stop == false)
                        mainCode.socketOut.println("true");
                    else
                        break;
                    Thread.sleep(5000);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }

    public void makeChart() {
        series = new XYChart.Series();
        chart.getData().addAll(series);
//        chart.setAnimated(false);
        chart.setCreateSymbols(false);
        chart.setTitle("نمودار قیمت");
    }

    public void updateChart(int count, Currency currency) {
        if (count > 8)
            series.getData().remove(0, series.getData().size() - 8);
        series.getData().add(new XYChart.Data(String.valueOf(count), currency.currentPrice));
    }

    @FXML
    void onBackButton(ActionEvent event) {

    }

}

