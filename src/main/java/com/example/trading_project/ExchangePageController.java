package com.example.trading_project;

import INFO.*;
import INFO.Server;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;

import java.net.URL;
//import java.util.Currency;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Collections;
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
    private Text errorText;

    @FXML
    private Button backButton;

    @FXML
    private TableView<Request> buyTable;

    @FXML
    private TableColumn<Request, Double> buyPrice;

    @FXML
    private TableColumn<Request, Double> buyVolume;

    @FXML
    private TableColumn<Request, Double> totalBuy;

    @FXML
    private TableView<Request> sellTable;

    @FXML
    private TableColumn<Request, Double> sellPrice;

    @FXML
    private TableColumn<Request, Double> sellVolume;

    @FXML
    private TableColumn<Request, Double> totalSell;

    String decision = "buy";
    double price;
    private String[] currencies = {"USD", "EUR", "TOMAN", "YEN", "GBP"};
    String currencyStr = "USD";
    Currency usd, eur, toman, yen, gbp;
    Currency currency;
    account a;
    boolean stop = false;
    public ArrayList<Request> buyRequests = new ArrayList<>();
    public ArrayList<Request> sellRequests = new ArrayList<>();
    public ArrayList<Request> newBuyRequest = new ArrayList<>();
    public ArrayList<Request> newSellRequest = new ArrayList<>();

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
        double property = a.wallet.getProperty(currency.name);

        if (decision.equals("buy")) {
            Request request = new Request(a, currency, spinner.getValue(), price, 1);
            saveRequest(request);
            a.buyRequest.add(request);
            errorText.setText("");
            updateBuyTable();
        }
        else if (decision.equals("sell") && property >= (spinner.getValue()*price)) {
            Request request = new Request(a, currency, spinner.getValue(), price, 0);
            saveRequest(request);
            a.sellRequest.add(request);
            errorText.setText("");
            updateSellTable();
        }
        else {
            errorText.setText("you dont have enough money");
        }
    }

    private void saveRequest(Request request) {
        if (request.type == Request.Type.Buy) {
//            buyRequests.add(request);
            newBuyRequest.add(request);
            Collections.sort(buyRequests, Request.COMPARE_BY_PRICE);
            Collections.reverse(buyRequests);
            a.buyRequest.add(request);
        }
        else if (request.type == Request.Type.Sell) {
//            sellRequests.add(request);
            newSellRequest.add(request);
            Collections.sort(sellRequests, Request.COMPARE_BY_PRICE);
            a.sellRequest.add(request);
        }
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
        setTable();
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
                updateBuyTable();
                updateSellTable();
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

    private void setTable() {
        buyPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        buyVolume.setCellValueFactory(new PropertyValueFactory<>("volume"));
        totalBuy.setCellValueFactory(new PropertyValueFactory<>("totalAmount"));
        sellPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        sellVolume.setCellValueFactory(new PropertyValueFactory<>("volume"));
        totalSell.setCellValueFactory(new PropertyValueFactory<>("totalAmount"));
    }

    private synchronized void updateBuyTable() {
        buyTable.refresh();
        buyTable.getItems().setAll(filterBuyRequests());
        buyTable.refresh();
    }

    private synchronized void updateSellTable() {
        sellTable.refresh();
        sellTable.getItems().setAll(filterSellRequests());
        sellTable.refresh();
    }

    private ArrayList<Request> filterBuyRequests() {
        ArrayList<Request> copy = new ArrayList<>();
        for (Request r : buyRequests) {
            if (r.getCurrency().name.equals(currencyStr))
                copy.add(r);
        }
        for (Request r : newBuyRequest)
            if (r.getCurrency().name.equals(currencyStr))
                copy.add(r);
        Collections.sort(copy, Request.COMPARE_BY_PRICE);
        return copy;
    }

    private ArrayList<Request> filterSellRequests() {
        ArrayList<Request> copy = new ArrayList<>();
        for (Request r : sellRequests) {
            if (r.getCurrency().name.equals(currencyStr))
                copy.add(r);
        }
        for (Request r : newSellRequest)
            if (r.getCurrency().name.equals(currencyStr))
                copy.add(r);
        Collections.sort(copy, Request.COMPARE_BY_PRICE);
        return copy;
    }

    public class MyThread implements Runnable {

        @Override
        public void run() {
            mainCode.socketOut.println("exchange");
            while (true){
                try {
                    buyRequests = (ArrayList<Request>) mainCode.objIn.readObject();
                    sellRequests = (ArrayList<Request>) mainCode.objIn.readObject();
                    a = (account) mainCode.objIn.readObject();
                    usd = (Currency) mainCode.objIn.readObject();
                    eur = (Currency) mainCode.objIn.readObject();
                    toman = (Currency) mainCode.objIn.readObject();
                    yen = (Currency) mainCode.objIn.readObject();
                    gbp = (Currency) mainCode.objIn.readObject();
                    updateBuyTable();
                    updateSellTable();
                    mainCode.objOut.writeObject(newBuyRequest);
                    mainCode.objOut.writeObject(newSellRequest);
                    newBuyRequest = new ArrayList<>();
                    newSellRequest = new ArrayList<>();
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
