package com.example.trading_project;

import INFO.CurrencyHandler;
import INFO.Server;
import INFO.account;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.Color;

import java.net.URL;
//import java.util.Currency;
import INFO.Currency;
import javafx.scene.text.Text;

import java.util.ResourceBundle;

public class ExchangePageController  implements Initializable {

    @FXML
    private ToggleButton buyButton;

    @FXML
    private ToggleButton sellButton;

    @FXML
    private ToggleGroup toggleButtons;

    @FXML
    private ChoiceBox<String> choiceBox;

    @FXML
    private Button decisionButton;

    @FXML
    private Spinner<Double> spinner;

    @FXML
    private Slider slide;

    @FXML
    private Text currenyText;

    @FXML
    private Button backButton;

    @FXML
    private TableView<?> buyTable;

    @FXML
    private TableView<?> sellTable;


    String decision;
    double price;
    private String[] currencies = {"USD", "EUR", "TOMAN", "YEN", "GBP"};
    String currencyStr = "USD";
    Currency usd, eur, toman, yen, gbp;
    Currency currency;
    account a;
    boolean stop = false;

    @FXML
    void onToggleButton(ActionEvent event) {
        if (event.getSource() == buyButton) {
            sellButton.setStyle("-fx-background-color:  White");
            sellButton.setTextFill(Color.valueOf("#398348"));
            buyButton.setTextFill(Color.WHITE);
            buyButton.setStyle("-fx-background-color:  #398348");
            decision = "buy";
        }
        else if (event.getSource() == sellButton) {
            sellButton.setStyle("-fx-background-color:  #398348");
            sellButton.setTextFill(Color.WHITE);
            buyButton.setTextFill(Color.valueOf("#398348"));
            buyButton.setStyle("-fx-background-color:  White");
            decision = "sell";
        }
    }

    @FXML
    void onDecisionClick(ActionEvent event) {

    }

    @FXML
    void onBackButton(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Thread myThread = new Thread(new MyThread());
        myThread.start();
        findCurrency();
        currenyText.setText("0.0");
        choiceBox.getItems().addAll(currencies);
        choiceBox.setOnAction(this::getCurrency);
        SpinnerValueFactory<Double> valueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 1000);
        valueFactory.setValue(1.0);
        spinner.setValueFactory(valueFactory);
        setSlider();
    }

    public void getCurrency(ActionEvent event) {
        currencyStr = choiceBox.getValue();
        findCurrency();
        changeSlide();
    }

    private void setSlider() {
        slide.setMin(0);
        slide.setMax(100);
        slide.setValue(50);
        slide.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                setPrice(slide.getValue());
                currenyText.setText(String.valueOf(price));
            }
        });
    }

    private void changeSlide() {
        slide.setMin(currency.currentPrice*0.8);
        slide.setMax(currency.currentPrice*1.2);
        slide.setValue(currency.currentPrice);
    }

    private void findCurrency() {
        switch (currencyStr) {
            case "USD":
                currency = usd;
                break;
            case "EUR":
                currency = eur;
                break;
            case "TOMAN":
                currency = toman;
                break;
            case "YEN":
                currency = yen;
                break;
            case "GBP":
                currency = gbp;
                break;
        }
    }

    private void setPrice(double price) {
        this.price = (double) Math.round(price*1000)/1000.0;
    }

    public class MyThread implements Runnable {

        @Override
        public void run() {
            mainCode.socketOut.println("exchange");
            while (true){
                try {
                    a = (account) mainCode.objIn.readObject();
                    usd = (Currency) mainCode.objIn.readObject();
                    eur = (Currency) mainCode.objIn.readObject();
                    toman = (Currency) mainCode.objIn.readObject();
                    yen = (Currency) mainCode.objIn.readObject();
                    gbp = (Currency) mainCode.objIn.readObject();
                    if (!stop)
                        mainCode.socketOut.println("true");
                    else
                        break;
                    Thread.sleep(5000);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }






}
