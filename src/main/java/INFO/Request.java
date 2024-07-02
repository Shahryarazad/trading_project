package INFO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

import static java.util.Comparator.comparing;
import static java.util.Comparator.naturalOrder;

public class Request implements Comparable{

    protected static ArrayList<Request> buyRequests = new ArrayList<>();
    protected static ArrayList<Request> sellRequests = new ArrayList<>();

    protected account account;
    protected Currency currency;
    protected double volume;
    protected Type type;

    public static Comparator<Request> COMPARE_BY_VOLUME = new Comparator<Request>() {
        public int compare(Request one, Request other) {
            return one.compareTo(other);
        }
    };

    @Override
    public int compareTo(Object o) {
        Request request = (Request) o;
        if (this.volume > request.volume)
            return 1;
        else if (this.volume < request.volume)
            return -1;
        else return 0;
    }

    protected enum Type{
        Buy, Sell;
    }

    public Request(account account, Currency currency, double volume, int type) {
        this.account = account;
        this.currency = currency;
        this.volume = volume;
        setType(type);
        saveRequest();
    }

    private void saveRequest() {
        if (type == Type.Buy) {
            buyRequests.add(this);
            Collections.sort(buyRequests, Request.COMPARE_BY_VOLUME);
            Collections.reverse(buyRequests);
            account.buyRequest.add(this);
        }
        else if (type == Type.Sell) {
            sellRequests.add(this);
            Collections.sort(sellRequests, Request.COMPARE_BY_VOLUME);
            account.sellRequest.add(this);
        }
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
