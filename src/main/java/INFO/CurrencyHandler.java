package INFO;

import java.io.Serializable;
import java.lang.Class.*;
import java.security.Timestamp;
import java.sql.*;

public class CurrencyHandler implements Runnable, Serializable {

    //fields
    Currency USD, EUR, TOMAN, YEN, GBP;
    ResultSet resultSet;
    Connection connection;

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
                USD.setCurrentPrice(resultSet.getFloat(2));
                EUR.setCurrentPrice(resultSet.getFloat(3));
                TOMAN.setCurrentPrice(resultSet.getFloat(4));
                YEN.setCurrentPrice(resultSet.getFloat(5));
                GBP.setCurrentPrice(resultSet.getFloat(6));
                Thread.sleep(2000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
