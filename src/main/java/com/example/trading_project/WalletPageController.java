package com.example.trading_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class WalletPageController implements Initializable {

    @FXML
    private AnchorPane anchorPane1;

    @FXML
    private AnchorPane anchorPane2;

    @FXML
    private Button changeButton;

    @FXML
    private LineChart<?, ?> chart;

    @FXML
    private Text totalText;

    @FXML
    private Text eur;

    @FXML
    private Text gbp;

    @FXML
    private Text toman;

    @FXML
    private Text usd;

    @FXML
    private Text yen;

    @FXML
    private Text usdAmount;

    @FXML
    private Text eurAmount;

    @FXML
    private Text tomanAmount;

    @FXML
    private Text yenAmount;

    @FXML
    private Text gbpAmount;

    XYChart.Series series;
    boolean stop = false;
    double amountOfUSD;
    double amountOfEUR;
    double amountOfTOMAN;
    double amountOfYEN;
    double amountOfGBP;
    double totalAmount;
    double usdPrice;
    double eurPrice;
    double tomanPrice;
    double yenPrice;
    double gbpPrice;
    String situation;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        situation = "currencies";
        changeButton.setText("chart");
        anchorPane2.setVisible(false);
        makeChart();
        Thread thread = new Thread(new MyThread());
        thread.start();
    }

    public class MyThread implements Runnable {

        @Override
        public void run() {
            int count = 1;
            try {
                while (true) {
                    amountOfUSD = mainCode.account.wallet.getAmountOfUSD();
                    amountOfEUR = mainCode.account.wallet.getAmountOfEUR();
                    amountOfTOMAN = mainCode.account.wallet.getAmountOfTOMAN();
                    amountOfYEN = mainCode.account.wallet.getAmountOfYEN();
                    amountOfGBP = mainCode.account.wallet.getAmountOfGBP();
                    usdPrice = Double.parseDouble(mainCode.usd.currentPriceStr);
                    eurPrice = Double.parseDouble(mainCode.eur.currentPriceStr);
                    tomanPrice = Double.parseDouble(mainCode.toman.currentPriceStr);
                    yenPrice = Double.parseDouble(mainCode.yen.currentPriceStr);
                    gbpPrice = Double.parseDouble(mainCode.gbp.currentPriceStr);

                    updateCurrencies();
                    calculateTotalAmount();
                    updateChart(count++);
                    Thread.sleep(4000);
                    if (stop)
                        break;
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

    @FXML
    void onBackButton(ActionEvent event) {
        stop = true;
        Parent root = null;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("profile.fxml")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(root);
        mainCode.mainStage.setScene(scene);
        mainCode.mainStage.setWidth(705);
        mainCode.mainStage.setHeight(577);
        mainCode.mainStage.show();
    }

    @FXML
    void onChangeClick(ActionEvent event) {
        if (situation.equals("currencies")) {
            anchorPane1.setVisible(false);
            anchorPane2.setVisible(true);
            anchorPane2.setLayoutX(18);
            anchorPane2.setLayoutY(185);
            changeButton.setText("currencies");
            situation = "chart";
        }
        else if (situation.equals("chart")) {
            anchorPane1.setVisible(true);
            anchorPane2.setVisible(false);
            anchorPane2.setLayoutX(410);
            changeButton.setText("chart");
            situation = "currencies";
        }
    }

    private void makeChart() {
        series = new XYChart.Series();
        chart.getData().addAll(series);
        chart.setCreateSymbols(false);
    }

    private void updateChart(int count) {
        if (count > 8)
            series.getData().remove(0, series.getData().size() - 8);
        series.getData().add(new XYChart.Data(String.valueOf(count), totalAmount));
    }

    private void calculateTotalAmount() {
        totalAmount = (amountOfUSD) + (amountOfEUR/eurPrice) + (amountOfTOMAN/tomanPrice) +
                (amountOfYEN/yenPrice) + (amountOfGBP / gbpPrice);
        totalAmount = (Math.round(totalAmount * 100.0) / 100.0);
        totalText.setText(String.valueOf(totalAmount));
    }

    private void updateCurrencies() {
        usd.setText(String.valueOf(usdPrice));
        eur.setText(String.valueOf(eurPrice));
        toman.setText(String.valueOf(tomanPrice));
        yen.setText(String.valueOf(yenPrice));
        gbp.setText(String.valueOf(gbpPrice));
        usdAmount.setText(String.valueOf(amountOfUSD));
        eurAmount.setText(String.valueOf(amountOfEUR));
        tomanAmount.setText(String.valueOf(amountOfTOMAN));
        yenAmount.setText(String.valueOf(amountOfYEN));
        gbpAmount.setText(String.valueOf(amountOfGBP));
    }

}
