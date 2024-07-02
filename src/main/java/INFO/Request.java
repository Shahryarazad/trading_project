package INFO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

import static java.util.Comparator.comparing;
import static java.util.Comparator.naturalOrder;

public class Request implements Comparable, Serializable {


    protected account account;
    protected Currency currency;
    protected double price;
    protected double volume;
    protected double totalAmount;
    public Type type;

    public static Comparator<Request> COMPARE_BY_PRICE = new Comparator<Request>() {
        public int compare(Request one, Request other) {
            return one.compareTo(other);
        }
    };

    @Override
    public int compareTo(Object o) {
        Request request = (Request) o;
        if (this.price > request.price)
            return 1;
        else if (this.price < request.price)
            return -1;
        else return 0;
    }

    public enum Type{
        Buy, Sell;
    }

    public Request(account account, Currency currency, double volume, double price, int type) {
        this.account = account;
        this.currency = currency;
        this.volume = volume;
        this.price = price;
        totalAmount = price*volume;
        setType(type);
    }


    private void setType(int i) {
        if (i == 1)
            type = Type.Buy;
        else
            type = Type.Sell;
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

    public account getAccount1() {
        return account;
    }

    public Currency getCurrency() {
        return currency;
    }

    public Type getType() {
        return type;
    }

}
