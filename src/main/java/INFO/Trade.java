package INFO;

import java.io.Serializable;
import java.util.ArrayList;

public class Trade implements Serializable {

    public static ArrayList<Trade> trades = new ArrayList<>();

    protected account account1;
    protected account account2;
    protected Currency currency;
    protected double volume;

    public Trade(account account1, account account2, Currency currency, double volume) {
        this.account1 = account1;
        this.account2 = account2;
        this.currency = currency;
        this.volume = volume;
        account1.trades.add(this);
        account2.trades.add(this);
        trades.add(this);
    }

    public account getAccount1() {
        return account1;
    }

    public account getAccount2() {
        return account2;
    }

    public Currency getCurrency() {
        return currency;
    }

    public double getVolume() {
        return volume;
    }
}
