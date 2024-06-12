package com.example.trading_project;

import INFO.Currency;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private ImageView imageIcon;

    @FXML
    private TableView<Currency> tab;

    @FXML
    private TableColumn<Currency, Float> changes;

    @FXML
    private TableColumn<Currency, String> currencyName;

    @FXML
    private TableColumn<Currency, Double> currencyValue;

    @FXML
    private TableColumn<Currency, Double> maxValue;

    @FXML
    private TableColumn<Currency, Double> minValue;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        currencyName.setCellValueFactory(new PropertyValueFactory<>("name"));
        currencyValue.setCellValueFactory(new PropertyValueFactory<>("currentPriceStr"));
        maxValue.setCellValueFactory(new PropertyValueFactory<>("maxPriceStr"));
        minValue.setCellValueFactory(new PropertyValueFactory<>("minPriceStr"));
        changes.setCellValueFactory(new PropertyValueFactory<>("changeStr"));
        mainCode.socketOut.println("home page");
        Thread myThread = new Thread(new MyThread());
        myThread.start();
        tab.setFixedCellSize(25);
        tab.prefHeightProperty().bind(tab.fixedCellSizeProperty().multiply(Bindings.size(tab.getItems()).add(1.3)));
        tab.minHeightProperty().bind(tab.prefHeightProperty());
        tab.maxHeightProperty().bind(tab.prefHeightProperty());
    }

    public class MyThread implements Runnable {

        public Currency usd, eur, toman, yen, gbp;

        @Override
        public void run() {
            while (true){
                try {
                    tab.refresh();
                    usd = (Currency) mainCode.objIn.readObject();
                    eur = (Currency) mainCode.objIn.readObject();
                    toman = (Currency) mainCode.objIn.readObject();
                    yen = (Currency) mainCode.objIn.readObject();
                    gbp = (Currency) mainCode.objIn.readObject();
                    tab.getItems().setAll(usd,eur,toman,yen,gbp);
                    tab.refresh();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}

