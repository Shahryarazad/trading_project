package INFO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Currency;

public class TradeHandler implements Runnable {

    private final String[] currencyNames = {"USD", "EUR", "TOMAN", "YEN", "GBP"};
    private ArrayList<Request> filteredBuys;
    private ArrayList<Request> filteredSells;

    @Override
    public void run() {
        try {
            while (true) {
                Collections.sort(Server.buyRequests, Request.COMPARE_BY_PRICE);
                Collections.sort(Server.sellRequests, Request.COMPARE_BY_PRICE);
                checkRequests();
                Thread.sleep(5000);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void checkRequests() {
        for (String name : currencyNames) {
            filteredBuys = filterBuys(name);
            filteredSells = filterSells(name);
            for (int i = 0, j = 0; i < filteredBuys.size() && j < filteredSells.size() ; ) {
                Request buy = filteredBuys.get(i);
                Request sell = filteredSells.get(j);
                if (buy.price >= sell.price && !buy.account.equals(sell.account)) {
                    double minVolume = Math.min(buy.volume, sell.volume);
                    double tradePrice = sell.price;
                    new Trade(buy.account, sell.account, name, minVolume, tradePrice);
                    setCurrencyVolume(name, minVolume);
                    if (buy.volume > sell.volume) {
                        buy.volume -= minVolume;
                        Server.sellRequests.remove(sell);
                        filteredSells.remove(sell);
                        j++;
                    }
                    else if (sell.volume > buy.volume) {
                        sell.volume -= minVolume;
                        Server.buyRequests.remove(buy);
                        filteredBuys.remove(buy);
                        i++;
                    }
                    else {
                        Server.buyRequests.remove(buy);
                        filteredBuys.remove(buy);
                        Server.sellRequests.remove(sell);
                        filteredSells.remove(sell);
                        i++;
                        j++;
                    }
                }
                else if (buy.account.equals(sell.account)) {
                    j++;
                }
                else
                    break;
            }
        }
    }

    private ArrayList<Request> filterBuys(String name) {
        ArrayList<Request> copy = new ArrayList<>();
        for (Request r : Server.buyRequests)
            if (r.currency.name.equals(name))
                copy.add(r);
        return copy;
    }

    private ArrayList<Request> filterSells(String name) {
        ArrayList<Request> copy = new ArrayList<>();
        for (Request r : Server.sellRequests)
            if (r.currency.name.equals(name))
                copy.add(r);
        return copy;
    }

    private void setCurrencyVolume(String name, double volume) {
        switch (name) {
            case "USD":
                Server.getCurrencyHandler().USD.totalVolume += volume;
                break;
            case "EUR":
                Server.getCurrencyHandler().EUR.totalVolume += volume;
                break;
            case "TOMAN":
                Server.getCurrencyHandler().TOMAN.totalVolume += volume;
                break;
            case "YEN":
                Server.getCurrencyHandler().YEN.totalVolume += volume;
                break;
            case "GBP":
                Server.getCurrencyHandler().GBP.totalVolume += volume;
                break;
        }
    }
}
