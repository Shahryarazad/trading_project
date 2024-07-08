package com.example.trading_project;

import INFO.Request;
import INFO.Trade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class HistoryPageController implements Initializable {

    @FXML
    private Button backButton;

    @FXML
    private TableColumn<Request, String> buyCurrency;

    @FXML
    private TableColumn<Request, String> buyPrice;

    @FXML
    private TableView<Request> buyTable;

    @FXML
    private TableColumn<Request, String> buyVolume;

    @FXML
    private TableColumn<Request, String> sellCurrency;

    @FXML
    private TableColumn<Request, String> sellPrice;

    @FXML
    private TableView<Request> sellTable;

    @FXML
    private TableColumn<Request, String> sellVolume;

    @FXML
    private TableColumn<Request, String> totalBuy;

    @FXML
    private TableColumn<Request, String> totalSell;

    @FXML
    private TableColumn<Trade, String> totalTrade;

    @FXML
    private TableColumn<Trade, String> tradeCurrency;

    @FXML
    private TableColumn<Trade, String> tradeDate;

    @FXML
    private TableView<Trade> tradeTable;

    @FXML
    private TableColumn<Trade, String> tradeVolume;

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
    void onExportClick(ActionEvent event) {

    }

    ArrayList<Request> buyRequests = new ArrayList<>();
    ArrayList<Request> sellRequests = new ArrayList<>();
    ArrayList<Trade> trades = new ArrayList<>();
    boolean stop = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Thread thread = new Thread(new MyThread());
        setTable();
        thread.start();
    }

    public class MyThread implements Runnable {

        @Override
        public void run() {
            try {
                while (true) {
                    buyRequests = mainCode.account.buyRequest;
                    sellRequests = mainCode.account.sellRequest;
                    trades = mainCode.trades;
                    updateTable();
                    Thread.sleep(5000);
                    if (stop)
                        break;
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public void setTable() {
        buyCurrency.setCellValueFactory(new PropertyValueFactory<>("currencyName"));
        buyPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        buyVolume.setCellValueFactory(new PropertyValueFactory<>("volume"));
        totalBuy.setCellValueFactory(new PropertyValueFactory<>("totalAmount"));
        sellCurrency.setCellValueFactory(new PropertyValueFactory<>("currencyName"));
        sellPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        sellVolume.setCellValueFactory(new PropertyValueFactory<>("volume"));
        totalSell.setCellValueFactory(new PropertyValueFactory<>("totalAmount"));
        tradeDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tradeVolume.setCellValueFactory(new PropertyValueFactory<>("volume"));
        totalTrade.setCellValueFactory(new PropertyValueFactory<>("totalAmount"));
        tradeCurrency.setCellValueFactory(new PropertyValueFactory<>("currencyName"));
    }

    public void updateTable() {
        buyTable.refresh();
        sellTable.refresh();
        tradeTable.refresh();
        buyTable.getItems().setAll(buyRequests);
        sellTable.getItems().setAll(sellRequests);
        tradeTable.getItems().setAll(filterTradeTable());
        buyTable.refresh();
        sellTable.refresh();
        tradeTable.refresh();
    }

    private ArrayList<Trade> filterTradeTable() {
        ArrayList<Trade> copy = new ArrayList<>();
        for (Trade t : trades)
            if (t.getAccount1().equals(mainCode.account) || t.getAccount2().equals(mainCode.account))
                copy.add(t);
        return copy;
    }
}
