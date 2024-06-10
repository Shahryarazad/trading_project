package com.example.trading_project;

import INFO.Currency;
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

    public Currency usd, eur, toman, yen, gbp;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        currencyName.setCellValueFactory(new PropertyValueFactory<>("name"));
        currencyValue.setCellValueFactory(new PropertyValueFactory<>("currentPrice"));
        maxValue.setCellValueFactory(new PropertyValueFactory<>("maxPrice"));
        minValue.setCellValueFactory(new PropertyValueFactory<>("minPrice"));
        changes.setCellValueFactory(new PropertyValueFactory<>("showChange"));
        fillTable();
    }

    public void fillTable() {
        try {
//            mainCode.socketOut.println("home page");
//            while (true) {
//                usd = (Currency) mainCode.objIn.readObject();
//                eur = (Currency) mainCode.objIn.readObject();
//                toman = (Currency) mainCode.objIn.readObject();
//                yen = (Currency) mainCode.objIn.readObject();
//                gbp = (Currency) mainCode.objIn.readObject();
//                ObservableList<Currency> currencies = FXCollections.observableArrayList();
//                currencies.add(usd);
//                currencies.add(eur);
//                currencies.add(toman);
//                currencies.add(yen);
//                currencies.add(gbp);
//                tab.setItems(currencies);
//                mainCode.socketOut.println("hello");
//            }

            usd = new Currency("usd");
            usd.setCurrentPrice(1.02);
            eur = new Currency("eur");
            eur.setCurrentPrice(2.03);
            toman = new Currency("toman");
            toman.setCurrentPrice(20.23);
            yen = new Currency("yen");
            yen.setCurrentPrice(12.023);
            gbp = new Currency("gbp");
            gbp.setCurrentPrice(0.2314);
            ObservableList<Currency> currencies = FXCollections.observableArrayList();
            currencies.add(usd);
            currencies.add(eur);
            currencies.add(toman);
            currencies.add(yen);
            currencies.add(gbp);
            tab.setItems(currencies);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
