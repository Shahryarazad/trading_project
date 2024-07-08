package INFO;

import java.io.Serializable;
import java.lang.Class.*;
import java.security.Timestamp;
import java.sql.*;

public class CurrencyHandler implements Runnable{

    //fields
    public  Currency USD, EUR, TOMAN, YEN, GBP;
    public String date;
    static ResultSet resultSet;
    static Connection connection;

    public CurrencyHandler() {
        USD = new Currency("USD");
        EUR = new Currency("EUR");
        TOMAN = new Currency("TOMAN");
        YEN = new Currency("YEN");
        GBP = new Currency("GBP");
        connectTOSQL();
    }

    public void connectTOSQL() {
        String url = "jdbc:mysql://localhost:3306/currency";
        String username = "root";
        String password = "Kk1384840626";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from table1");

//            while (resultSet.next()) {
//                System.out.println(resultSet.getTimestamp(1) + " " + resultSet.getFloat(2) + " " +
//                        resultSet.getFloat(3) + " " + resultSet.getFloat(4) + " " +
//                        resultSet.getFloat(5) + " " + resultSet.getFloat(6));
//            }
//
//            connection.close();

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void run() {
        try {

            while (resultSet.next()) {
                date = String.valueOf(resultSet.getTimestamp(1));
                USD.setCurrentPrice(resultSet.getFloat(2));
                EUR.setCurrentPrice(resultSet.getFloat(3));
                TOMAN.setCurrentPrice(resultSet.getFloat(4));
                YEN.setCurrentPrice(resultSet.getFloat(5));
                GBP.setCurrentPrice(resultSet.getFloat(6));
                Thread.sleep(5000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //getter and setter

    public synchronized  Currency getUSD() {
        return USD;
    }

    public synchronized  void setUSD(Currency USD) {
        this.USD = USD;
    }

    public synchronized  Currency getEUR() {
        return EUR;
    }

    public synchronized void setEUR(Currency EUR) {
        this.EUR = EUR;
    }

    public synchronized  Currency getTOMAN() {
        return TOMAN;
    }

    public synchronized  void setTOMAN(Currency TOMAN) {
        this.TOMAN = TOMAN;
    }

    public synchronized  Currency getYEN() {
        return YEN;
    }

    public synchronized  void setYEN(Currency YEN) {
        this.YEN = YEN;
    }

    public synchronized  Currency getGBP() {
        return GBP;
    }

    public synchronized  void setGBP(Currency GBP) {
        this.GBP = GBP;
    }
}
