package com.example.trading_project;

import INFO.Currency;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.ResourceBundle;

public class HomePage2Controller implements Initializable {

    Thread myThread;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private ImageView imageIcon;

    @FXML
    private GridPane tab;

    @FXML
    private Text text11;

    @FXML
    private Text text12;

    @FXML
    private Text text13;

    @FXML
    private Text text14;

    @FXML
    private Text text15;

    @FXML
    private Text text21;

    @FXML
    private Text text22;

    @FXML
    private Text text23;

    @FXML
    private Text text24;

    @FXML
    private Text text25;

    @FXML
    private Text text31;

    @FXML
    private Text text32;

    @FXML
    private Text text33;

    @FXML
    private Text text34;

    @FXML
    private Text text35;

    @FXML
    private Text text41;

    @FXML
    private Text text42;

    @FXML
    private Text text43;

    @FXML
    private Text text44;

    @FXML
    private Text text45;

    @FXML
    private Text text51;

    @FXML
    private Text text52;

    @FXML
    private Text text53;

    @FXML
    private Text text54;

    @FXML
    private Text text55;

    String priority = "name";
    boolean swPrice = true, swChange = true, swMaxPrice = true, swMinPrice = true;
    boolean currentSw = true;
    boolean stop = false;

    public Currency usd, eur, toman, yen, gbp;
    public Currency first, second, third, forth, fifth;
    public Currency[] currencies = new Currency[5];

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mainCode.socketOut.println("home page");
        myThread = new Thread(new MyThread());
        myThread.start();
    }

    public class MyThread implements Runnable {


        @Override
        public void run() {
            while (true){
                try {
                    usd = (Currency) mainCode.objIn.readObject();
                    eur = (Currency) mainCode.objIn.readObject();
                    toman = (Currency) mainCode.objIn.readObject();
                    yen = (Currency) mainCode.objIn.readObject();
                    gbp = (Currency) mainCode.objIn.readObject();
                    setPriority(priority, currentSw);
                    Thread.sleep(5000);
                    if (!stop)
                        mainCode.socketOut.println("true");
                    else
                        break;
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }

    @FXML
    void onChangeClick(MouseEvent event) {
        priority = "change";
        setPriority(priority, swChange);
        currentSw = swChange;
        swChange = !swChange;
    }

    @FXML
    void onMaxPriceClick(MouseEvent event) {
        priority = "maxPrice";
        setPriority(priority, swMaxPrice);
        currentSw = swMaxPrice;
        swMaxPrice = !swMaxPrice;
    }

    @FXML
    void onMinPriceClick(MouseEvent event) {
        priority = "minPrice";
        setPriority(priority, swMinPrice);
        currentSw = swMinPrice;
        swMinPrice = !swMinPrice;
    }

    @FXML
    void onPriceClick(MouseEvent event) {
        priority = "price";
        setPriority(priority, swPrice);
        currentSw = swPrice;
        swPrice = !swPrice;
    }

    @FXML
    void onFirstClick(MouseEvent event) {
        //go to currency page
        mainCode.socketOut.println("false");
        stop = true;
    }

    @FXML
    void onSecondClick(MouseEvent event) {
        //go to currency page
        mainCode.socketOut.println("false");
        stop = true;
    }

    @FXML
    void onThirdClick(MouseEvent event) {
        //go to currency page
        mainCode.socketOut.println("false");
        stop = true;
    }

    @FXML
    void onFourthClick(MouseEvent event) {
        //go to currency page
        mainCode.socketOut.println("false");
        stop = true;
    }

    @FXML
    void onFifthClick(MouseEvent event) {
        //go to currency page
        mainCode.socketOut.println("false");
        stop = true;
    }

    public void setPriority(String s, boolean sw) {
        currencies = new Currency[]{usd, eur, toman, yen, gbp};
        if (s.equals("price")) {
            for (int i = 0; i < 5; i++) {
                for (int j = i; j < 5; j++) {
                    if (currencies[i].currentPrice > currencies[j].currentPrice)
                        swap(i, j);
                }
            }
        }
        else if (s.equals("change")) {
            for (int i = 0; i < 5; i++) {
                for (int j = i; j < 5; j++) {
                    if (currencies[i].change > currencies[j].change)
                        swap(i, j);
                }
            }
        }
        else if (s.equals("maxPrice")) {
            for (int i = 0; i < 5; i++) {
                for (int j = i; j < 5; j++) {
                    if (currencies[i].maxPrice > currencies[j].maxPrice)
                        swap(i, j);
                }
            }
        }
        else if (s.equals("minPrice")) {
            for (int i = 0; i < 5; i++) {
                for (int j = i; j < 5; j++) {
                    if (currencies[i].minPrice > currencies[j].minPrice)
                        swap(i, j);
                }
            }
        }

        if (sw) {
            first = currencies[0];
            second = currencies[1];
            third = currencies[2];
            forth = currencies[3];
            fifth = currencies[4];
        }
        else {
            first = currencies[4];
            second = currencies[3];
            third = currencies[2];
            forth = currencies[1];
            fifth = currencies[0];
        }

        fillTable();

    }

    public void fillTable() {
        text11.setText(first.name);
        text12.setText(first.currentPriceStr);
        text13.setText(first.changeStr);
        text14.setText(first.maxPriceStr);
        text15.setText(first.minPriceStr);
        text21.setText(second.name);
        text22.setText(second.currentPriceStr);
        text23.setText(second.changeStr);
        text24.setText(second.maxPriceStr);
        text25.setText(second.minPriceStr);
        text31.setText(third.name);
        text32.setText(third.currentPriceStr);
        text33.setText(third.changeStr);
        text34.setText(third.maxPriceStr);
        text35.setText(third.minPriceStr);
        text41.setText(forth.name);
        text42.setText(forth.currentPriceStr);
        text43.setText(forth.changeStr);
        text44.setText(forth.maxPriceStr);
        text45.setText(forth.minPriceStr);
        text51.setText(fifth.name);
        text52.setText(fifth.currentPriceStr);
        text53.setText(fifth.changeStr);
        text54.setText(fifth.maxPriceStr);
        text55.setText(fifth.minPriceStr);
    }

    private void swap(int i, int j) {
        Currency temp = currencies[i];
        currencies[i] = currencies[j];
        currencies[j] = temp;
    }
}
