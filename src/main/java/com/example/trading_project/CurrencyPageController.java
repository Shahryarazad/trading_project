package com.example.trading_project;

import INFO.Currency;
import INFO.Request;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
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
    private TableView<Request> buyTable;

    @FXML
    private TableColumn<Request, String> buyPrice;

    @FXML
    private TableColumn<Request, String> buyVolume;

    @FXML
    private TableColumn<Request, String> totalBuy;

    @FXML
    private TableView<Request> sellTable;

    @FXML
    private TableColumn<Request, String> sellPrice;

    @FXML
    private TableColumn<Request, String> sellVolume;

    @FXML
    private TableColumn<Request, String> totalSell;

    @FXML
    private TableView<?> table3;

    @FXML
    private Button backButton;

    Currency currency;
    String currencyStr;
    ArrayList<Request> buyRequest = new ArrayList<>();
    ArrayList<Request> sellRequest = new ArrayList<>();
    boolean stop = false;
    XYChart.Series series;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        currencyStr = mainCode.currentCurrency;
        Thread thread = new Thread(new MyThread());
        makeChart();
        setTable();
        thread.start();
    }

    public class MyThread implements Runnable {

        @Override
        public void run() {
            int count = 1;
            try {
                while (true) {
                    currency = findCurrency();
                    buyRequest = mainCode.buyRequests;
                    sellRequest = mainCode.sellRequests;
                    currencyName.setText(currency.getName());
                    currentPriceText.setText(currency.getCurrentPriceStr());
                    changeText.setText(currency.getChangeStr());
                    updateChart(count++, currency);
                    updateTable();
                    Thread.sleep(3000);
                    if (stop)
                        break;
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
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

    private void setTable() {
        buyPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        buyVolume.setCellValueFactory(new PropertyValueFactory<>("volume"));
        totalBuy.setCellValueFactory(new PropertyValueFactory<>("totalAmount"));
        sellPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        sellVolume.setCellValueFactory(new PropertyValueFactory<>("volume"));
        totalSell.setCellValueFactory(new PropertyValueFactory<>("totalAmount"));
    }

    public void updateTable() {
        buyTable.refresh();
        sellTable.refresh();
        buyTable.getItems().setAll(filterBuyTable());
        sellTable.getItems().setAll(filterSellTable());
        buyTable.refresh();
        sellTable.refresh();
    }

    private ArrayList<Request> filterBuyTable() {
        ArrayList<Request> copy = new ArrayList<>();
        for (Request r : buyRequest)
            if (r.getCurrency().name.equals(currencyStr))
                copy.add(r);
        return copy;
    }

    private ArrayList<Request> filterSellTable() {
        ArrayList<Request> copy = new ArrayList<>();
        for (Request r : sellRequest)
            if (r.getCurrency().name.equals(currencyStr))
                copy.add(r);
        return copy;
    }

    private Currency findCurrency() {
        switch (currencyStr) {
            case "USD":
                return mainCode.usd;
            case "EUR":
                return mainCode.eur;
            case "TOMAN":
                return mainCode.toman;
            case "YEN":
                return mainCode.yen;
            case "GBP":
                return mainCode.gbp;
        }
        return null;
    }

    @FXML
    void onBackButton(ActionEvent event) {
        stop = true;
        Parent root = null;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("home_page2.fxml")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(root);
        mainCode.mainStage.setScene(scene);
        mainCode.mainStage.setWidth(950);
        mainCode.mainStage.setHeight(651);
        mainCode.mainStage.show();
    }

}

