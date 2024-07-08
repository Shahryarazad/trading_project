package INFO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Trade implements Serializable {

    public static ArrayList<Trade> trades = new ArrayList<>();

    protected account account1;
    protected account account2;
    public String currencyName;
    public double volume;
    public double price;
    public double totalAmount;
    public String date;


    public Trade(account account1, account account2, String currencyName, double volume, double price) {
        this.account1 = account1;
        this.account2 = account2;
        this.currencyName = currencyName;
        this.volume = volume;
        this.price = price;
        totalAmount = price*volume;
        date = Server.getCurrencyHandler().date;
//        account1.trades.add(this);
//        account2.trades.add(this);
        Server.trades.add(this);
        Server.setTrade(account1, account2, this);
    }

    public account getAccount1() {
        return account1;
    }

    public account getAccount2() {
        return account2;
    }

    public double getVolume() {
        return volume;
    }

    public double getPrice() {
        return price;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public String getDate() {
        return date;
    }
}
